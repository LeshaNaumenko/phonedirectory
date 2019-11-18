package com.course.phonedirectory.converter;

import com.course.phonedirectory.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class PdfHttpMessageConverter extends AbstractHttpMessageConverter<List<User>> {

    public PdfHttpMessageConverter() {
        super(MediaType.valueOf(String.valueOf(MediaType.APPLICATION_PDF)));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    protected List<User> readInternal(Class<? extends List<User>> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(List<User> userList, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = outputMessage.getBody();
        byte[] data = new ObjectMapper().writeValueAsBytes(userList);
        outputStream.write(data);
//        outputStream.flush();
        outputStream.close();
    }
}
