package com.course.phonedirectory;

import com.course.phonedirectory.model.User;
import com.course.phonedirectory.rep.UserRepository;
import com.course.phonedirectory.utils.PdfGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Controller
public class MainController {

    private static final String PDF_NAME = "users.pdf";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping("/")
    public String forUser(Model model){
        model.addAttribute("message", "Hi there. Choose something");
        return "userPage";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/success")
    public String defaultTarget1(Model model) {
        return "greeting";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    // task 1
    @PostMapping("/uploadFile")
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) throws IOException {
        if (file.getSize() == 0 || file.isEmpty()) {
            setMessage(modelMap, "Upload file with data please");
        } else {
            modelMap.addAttribute("file", file);
            ObjectMapper objectMapper = new ObjectMapper();
            User[] user = objectMapper.readValue(file.getBytes(), User[].class);
            List<User> users = Arrays.asList(user);
            userRepository.saveAll(users);
            setMessage(modelMap, "Done");
        }
        return "upload";
    }

    // task 2
    @PostMapping("/pdf")
    public String generateReport(HttpServletResponse response, ModelMap modelMap) throws Exception {
        if (userRepository.findAll().isEmpty()) {
            setMessage(modelMap, "DB does not contain any user");
            return "upload";
        }
        List<User> users = userRepository.findAll();
        pdfGenerator.generatePdfResponse(response, users, PDF_NAME);
        response.getOutputStream().flush();
        return "upload";
    }

    // task 3
    @GetMapping("/getallusers")
    public String getAllUser(Model model) {
        List<User> all = userRepository.findAll();
        model.addAttribute("users", all);
        return "users";
    }

    @GetMapping("/getuserbyid")
    public String getUser(@RequestParam int id, Model model) {
        userRepository.findById(id)
                .ifPresent(user -> model.addAttribute("user", user));
        return "user";
    }

    @GetMapping("/personalinfo")
    public String getPersonalInformation(Model model){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = null;
        if(null != securityContext.getAuthentication()){
            username = securityContext.getAuthentication().getName();
        }
        userRepository.findByName(username)
                .ifPresent(user -> model.addAttribute("user", user));
        return "user";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam int id, Model model) {
        userRepository.deleteById(id);
        return "redirect:getallusers";
    }
    @GetMapping("/add")
    public String addUser() {
        return "add";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam String username, @RequestParam String password,
                            Model model) {
        System.out.println(username);
        System.out.println(password);
//        System.out.println(number);
//        System.out.println(company);
        return "user";
    }



    private ModelMap setMessage(ModelMap modelMap, String s) {
        return modelMap.addAttribute("message", s);
    }
}