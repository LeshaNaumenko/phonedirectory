package com.course.phonedirectory.utils;

import com.course.phonedirectory.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        response.setHeader("Content-disposition", "attachment; filename=" + pdfName);
        response.setContentLength(data.length);
        response.getOutputStream().write(data);
    }
}
