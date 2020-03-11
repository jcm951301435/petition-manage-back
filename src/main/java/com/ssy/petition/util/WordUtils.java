package com.ssy.petition.util;

import com.ssy.petition.dto.petition.result.PetitionContradictionResult;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class WordUtils {

    private static ResourceLoader resourceLoader;

    private static final String TEMPLATE_FILE_PATH = "classpath:static/templates/contradictionTemplate.docx";

    public static ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        WordUtils.resourceLoader = resourceLoader;
    }

    public static void downLoadContradiction(List<PetitionContradictionResult> list, HttpServletResponse response) {
        String title = "一人一表";
        XWPFDocument document;
        HttpUtils.downLoadResponse(title+ ".docx", response);
        document = getDocumentResult();
        //for (PetitionContradictionResult result : list) {
        //    document = getDocumentResult();
        //    try {
        //        document.write(response.getOutputStream());
        //        document.close();
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
        //}
        downLoadSingle(document, response);
    }

    private static void downLoadSingle(XWPFDocument document, HttpServletResponse response) {
        try {
            document.write(response.getOutputStream());
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void downLoadMultiple() {

    }

    public static XWPFDocument getDocumentResult() {
        XWPFDocument document = getDocument();
        return document;
    }

    private static XWPFDocument getDocument() {
        XWPFDocument document = null;
        Resource resource = resourceLoader.getResource(TEMPLATE_FILE_PATH);
        try {
            InputStream inputStream = resource.getInputStream();
            document = new XWPFDocument(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }



}
