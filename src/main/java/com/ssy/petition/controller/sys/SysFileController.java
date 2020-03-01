package com.ssy.petition.controller.sys;

import com.ssy.petition.common.CommonResult;
import com.ssy.petition.entity.sys.SysFile;
import com.ssy.petition.service.sys.SysFileService;
import com.ssy.petition.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/sysFile")
public class SysFileController {

    @Value("${file.upload.path}")
    private String fileBasePath ;

    private static final Logger LOGGER = LoggerFactory.getLogger(SysFileController.class);

    private final SysFileService sysFileService;

    @Autowired
    public SysFileController(SysFileService sysFileService) {
        this.sysFileService = sysFileService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public CommonResult list(@RequestParam("id") Long contradictionId) {
        List<SysFile> list = sysFileService.list(contradictionId);
        return CommonResult.success(list);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResult upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long contradictionId) {
        if (file.isEmpty()) {
            return CommonResult.failed("文件为空，请检查");
        }
        String fileName = Long.toString(EntityUtils.generateId());
        SysFile sysFile = new SysFile();
        sysFile.setFileName(fileName);
        sysFile.setContradictionId(contradictionId);
        sysFile.setFileOldName(file.getOriginalFilename());
        try {
            boolean flag = sysFileService.upload(fileBasePath, fileName, file);
            if (flag) {
                if (sysFileService.create(sysFile) == 1) {
                    return CommonResult.success("上传成功");
                }
            }
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.failed("上传失败");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        SysFile sysFile = sysFileService.getById(id);
        if (sysFile == null) {
            return CommonResult.failed("找不到此记录，请联系管理员");
        }
        String fileName = sysFile.getFileName();
        String filePath = fileBasePath + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            if (!file.delete()) {
                return CommonResult.failed("删除失败，请联系管理员");
            }
        }
        int result = sysFileService.delete(id);
        if (result == 1) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.failed("删除失败，请联系管理员");
    }


    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public CommonResult download(@RequestParam("id") Long id, HttpServletResponse response) {
        SysFile sysFile = sysFileService.getById(id);
        if (sysFile == null) {
            return CommonResult.failed("找不到此记录，请联系管理员");
        }
        String fileName = sysFile.getFileName();
        String fileOldName = sysFile.getFileOldName();
        String filePath = fileBasePath + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileOldName);
            FileInputStream fileInputStream = null;
            BufferedInputStream bufferedInputStream = null;
            byte[] buffer = new byte[1024];
            try {
                fileInputStream = new FileInputStream(file);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                int i = bufferedInputStream.read(buffer);
                OutputStream outputStream = response.getOutputStream();
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bufferedInputStream.read(buffer);
                }
            } catch (FileNotFoundException e) {
                LOGGER.error("文件不存在" + filePath);
                e.printStackTrace();
            } catch (IOException e) {
                LOGGER.error("读写错误");
                e.printStackTrace();
            } finally {
                try {
                    bufferedInputStream.close();
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return CommonResult.failed("找不到此文件，请联系管理员");
        }
        return CommonResult.failed("");
    }

}
