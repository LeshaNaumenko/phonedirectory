package com.course.phonedirectory;

import com.course.phonedirectory.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class GreetingController {

    public static final String TEMP = "Hello";

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/index")
    public String greeting(Model model) {
        return "index";
    }

    @PostMapping("/uploadFile")
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        modelMap.addAttribute("file", file);
        ObjectMapper objectMapper = new ObjectMapper();
        User[] user = objectMapper.readValue(file.getBytes(), User[].class);
        List<User> users = Arrays.asList(user);
        users.forEach(System.out::println);
        return "upload";
    }

    @PostMapping("/pdf")
    public void generateReport(HttpServletResponse response) throws Exception {

        byte[] data = TEMP.getBytes();

        streamReport(response, data, "my_report.pdf");
    }

    private void streamReport(HttpServletResponse response, byte[] data, String name)
            throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        response.setContentLength(data.length);

        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
}