package com.course.phonedirectory.utils;

import com.course.phonedirectory.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class PdfGenerator {

    public void generatePdfResponse(HttpServletResponse response, List<User> userList, String pdfName) throws IOException {
        byte[] data;
        data = new ObjectMapper().writeValueAsBytes(userList);
        response.setContentType("application/pdf");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Content-Disposition", "filename=" + pdfName);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setContentLength(data.length);
        response.getOutputStream().write(data);
    }
}
