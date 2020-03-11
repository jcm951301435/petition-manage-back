package com.ssy.petition.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Component
public class JasperUtils {

    private static ResourceLoader resourceLoader;

    private static final String REPORT_FILE_BASE_PATH = "classpath:reports/";

    private static final String REPORT_SUFFIX = ".jrxml";

    public static ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        JasperUtils.resourceLoader = resourceLoader;
    }

    public static final String PRINT_TYPE = "print";
    public static final String PDF_TYPE = "pdf";
    public static final String EXCEL_TYPE = "excel";
    public static final String HTML_TYPE = "html";
    public static final String WORD_TYPE = "word";

    public static void prepareReport(JasperReport jasperReport, String type) {
        /*
         * 如果导出的是excel，则需要去掉周围的margin
         */
        if ("excel".equals(type)) {
            try {
                Field margin = JRBaseReport.class.getDeclaredField("leftMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                margin = JRBaseReport.class.getDeclaredField("topMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                margin = JRBaseReport.class.getDeclaredField("bottomMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                Field pageHeight = JRBaseReport.class.getDeclaredField("pageHeight");
                pageHeight.setAccessible(true);
                pageHeight.setInt(jasperReport, 2147483647);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * 导出excel
     */
    public static void exportExcel(JasperPrint jasperPrint, String defaultFilename, HttpServletRequest request, HttpServletResponse response)
            throws IOException, JRException {
        /*
         * 设置头信息
         */
        response.setContentType("application/vnd.ms-excel");
        String defaultname = null;
        if (defaultFilename.trim() != null && defaultFilename != null) {
            defaultname = defaultFilename + ".xls";
        } else {
            defaultname = "export.xls";
        }

        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(defaultname, "UTF-8") + "\"");


        ServletOutputStream ouputStream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

        // 删除记录最下面的空行
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);

        // 删除多余的ColumnHeader
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        // 显示边框
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.exportReport();
        ouputStream.flush();
        ouputStream.close();
    }

    public static enum DocType {
        /**
         * 类型
         */
        PDF, HTML, XLS, XML, RTF
    }

    @SuppressWarnings("rawtypes")
    public static JRAbstractExporter getExporter(DocType docType) {
        JRAbstractExporter exporter = null;
        switch (docType) {
            case PDF:
                exporter = new JRPdfExporter();
                break;
            //case HTML:
            //    exporter = new JRHtmlExporter();
            //    break;
            //case XLS:
            //    exporter = new JExcelApiExporter();
            //    break;
            case XML:
                exporter = new JRXmlExporter();
                break;
            case RTF:
                exporter = new JRRtfExporter();
                break;
            default:
                break;
        }
        return exporter;
    }

    /**
     * 导出pdf，注意此处中文问题，
     * 这里应该详细说：主要在ireport里变下就行了。看图
     * 1）在ireport的classpath中加入iTextAsian.jar 2）在ireport画jrxml时，看ireport最左边有个属性栏。
     * 下边的设置就在点字段的属性后出现。 pdf font name ：STSong-Light ，pdf encoding ：UniGB-UCS2-H
     */
    private static void exportPdf(JasperPrint jasperPrint, String defaultFilename, HttpServletRequest request, HttpServletResponse response)
            throws IOException, JRException {
        response.setContentType("application/pdf");
        String defaultname = null;
        if (defaultFilename.trim() != null && defaultFilename != null) {
            defaultname = defaultFilename + ".pdf";
        } else {
            defaultname = "export.pdf";
        }
        String fileName = new String(defaultname.getBytes("GBK"), "ISO8859_1");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        ServletOutputStream ouputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * 导出html
     */
    private static void exportHtml(JasperPrint jasperPrint, String defaultFilename, HttpServletRequest request, HttpServletResponse response)
            throws IOException, JRException {
        response.setContentType("text/html");
        ServletOutputStream ouputStream = response.getOutputStream();
        //JRHtmlExporter exporter = new JRHtmlExporter();
        //exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
        ////设置图片文件存放路径，此路径为服务器上的绝对路径
        //String imageDIR = request.getSession().getServletContext().getRealPath("/");
        //exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, imageDIR);
        //
        ////设置图片请求URI
        //String imageURI = request.getContextPath() + "/";
        //exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageURI);
        //
        ////设置导出图片到图片存放路径
        //exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.TRUE);
        //exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
        //
        //exporter.exportReport();

        ouputStream.flush();
        ouputStream.close();
    }
    //
    //public static void export(String type, String defaultFilename, File is, HttpServletRequest request, HttpServletResponse response, Map parameters, Connection conn) {
    //    try {
    //        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
    //        prepareReport(jasperReport, type);
    //        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
    //        if (EXCEL_TYPE.equals(type)) {
    //            exportExcel(jasperPrint, defaultFilename, request, response);
    //        } else if (PDF_TYPE.equals(type)) {
    //            exportPdf(jasperPrint, defaultFilename, request, response);
    //        } else if (HTML_TYPE.equals(type)) {
    //            exportHtml(jasperPrint, defaultFilename, request, response);
    //        } else if (WORD_TYPE.equals(type)) {
    //            exportWord(jasperPrint, defaultFilename, request, response);
    //        }
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}

    //public static void export(String type, String defaultFilename, File is, HttpServletRequest request, HttpServletResponse response,
    //                          Map parameters, JRDataSource conn) {
    //    try {
    //        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
    //        prepareReport(jasperReport, type);
    //        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
    //        if (EXCEL_TYPE.equals(type)) {
    //            exportExcel(jasperPrint, defaultFilename, request, response);
    //        } else if (PDF_TYPE.equals(type)) {
    //            exportPdf(jasperPrint, defaultFilename, request, response);
    //        } else if (HTML_TYPE.equals(type)) {
    //            exportHtml(jasperPrint, defaultFilename, request, response);
    //        } else if (WORD_TYPE.equals(type)) {
    //            exportWord(jasperPrint, defaultFilename, request, response);
    //        }
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}


    public static void showHtml(String defaultFilename, String reportfile, HttpServletRequest request, HttpServletResponse response,
                                Map parameters, JRDataSource conn) throws JRException, IOException {
        //request.setCharacterEncoding("utf-8");
        //response.setCharacterEncoding("utf-8");
        //response.setContentType("text/html");
        //
        //JRAbstractExporter exporter = getExporter(DocType.HTML);
        //
        //JasperPrint jasperPrint = JasperFillManager.fillReport(reportfile, parameters, conn);
        //request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
        //
        //PrintWriter out = response.getWriter();
        //
        //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
        //exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        //
        ////设置图片文件存放路径，此路径为服务器上的绝对路径
        //String imageDIR = request.getSession().getServletContext().getRealPath("/");
        //exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, imageDIR);
        //
        ////设置图片请求URI
        //String imageURI = request.getContextPath() + "/";
        //exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageURI);
        //
        ////设置导出图片到图片存放路径
        //exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.TRUE);
        //exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
        //exporter.exportReport();
        //out.flush();
    }

    public static void showHtml(String defaultFilename, String reportfile, HttpServletRequest request, HttpServletResponse response, Map parameters, Connection conn) throws JRException, IOException {
        //request.setCharacterEncoding("utf-8");
        //response.setCharacterEncoding("utf-8");
        //response.setContentType("text/html");
        //
        //JRAbstractExporter exporter = getExporter(DocType.HTML);
        //
        //JasperPrint jasperPrint = JasperFillManager.fillReport(reportfile, parameters, conn);
        //request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
        //
        //PrintWriter out = response.getWriter();
        //
        //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
        //exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        ////设置图片文件存放路径，此路径为服务器上的绝对路径
        //String imageDIR = request.getSession().getServletContext().getRealPath("/");
        //exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, imageDIR);
        //
        ////设置图片请求URI
        //String imageURI = request.getContextPath() + "/";
        //exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageURI);
        //
        ////设置导出图片到图片存放路径
        //exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.TRUE);
        //exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
        //exporter.exportReport();
        //out.flush();
    }

    //public static void showPdf(String reportFile, HttpServletRequest request, HttpServletResponse response,
    //                           Map<String, Object> parameters, JRDataSource conn) throws JRException, IOException {
    //    request.setCharacterEncoding("utf-8");
    //    response.setCharacterEncoding("utf-8");
    //    response.setContentType("text/html");
    //    response.setContentType("application/pdf");
    //    JRAbstractExporter exporter = getExporter(DocType.PDF);
    //    JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile, parameters, conn);
    //    request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
    //    OutputStream outputStream = response.getOutputStream();
    //
    //    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    //    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
    //    exporter.exportReport();
    //    outputStream.flush();
    //}

    public static <T> void exportWord(String name, List<T> list, Map<String, Object> paramsMap, HttpServletRequest request,
                                      HttpServletResponse response) throws UnsupportedEncodingException, JRException {
        String fileName = "一人一表";
        HttpUtils.downLoadResponse(fileName+ ".docx", response);
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            path = path.substring(1);
            paramsMap.put("imageUrl", path + "images/yes.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String filePath = REPORT_FILE_BASE_PATH + name + REPORT_SUFFIX;
        Resource resource = resourceLoader.getResource(filePath);
        JRDataSource dataSource = new JRBeanCollectionDataSource(list, true);
        try {
            InputStream inputStream = resource.getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramsMap, dataSource);
            OutputStream outputStream = response.getOutputStream();
            JRAbstractExporter exporter = new JRDocxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("找不到此模板文件，请联系管理员上传，文件名：" + name + REPORT_SUFFIX);
        } catch (JRException e) {
            e.printStackTrace();
        }

        //response.setContentType("application/msword;charset=utf-8");
        //String defaultname = null;
        //if (defaultFilename.trim() != null && defaultFilename != null) {
        //    defaultname = defaultFilename + ".docx";
        //} else {
        //    defaultname = "export.doc";
        //}
        //String fileName = new String(defaultname.getBytes("GBK"), "utf-8");
        //response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        //JRExporter exporter = new JRRtfExporter();
        //exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        //
        //exporter.exportReport();
    }

    public static <T> void showPdf(String name, List<T> list, Map<String, Object> paramsMap, HttpServletRequest request,
                                   HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setContentType("application/pdf");

        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            path = path.substring(1);
            paramsMap.put("imageUrl", path + "images/yes.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String filePath = REPORT_FILE_BASE_PATH + name + REPORT_SUFFIX;
        Resource resource = resourceLoader.getResource(filePath);
        JRDataSource dataSource = new JRBeanCollectionDataSource(list, true);
        try {
            InputStream inputStream = resource.getInputStream();
            //JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramsMap, dataSource);
            request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
            OutputStream outputStream = response.getOutputStream();
            JRAbstractExporter exporter = getExporter(DocType.PDF);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("找不到此模板文件，请联系管理员上传，文件名：" + name + REPORT_SUFFIX);
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

}
