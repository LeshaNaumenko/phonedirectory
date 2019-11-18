package com.course.phonedirectory;

import com.course.phonedirectory.model.User;
import com.course.phonedirectory.rep.UserRepository;
import com.course.phonedirectory.utils.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/rest")
public class MyRestController {

    private static final String PDF_NAME_REST = "users-rest.pdf";


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping("/getallusers")
    public List<User> getAllUser(Model model) {
        return userRepository.findAll();
    }

    @GetMapping(value = "/getuserbyid/{id}")
    public User getUser(@PathVariable(name = "id") int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No such user"));
    }

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        User userFromDB = userRepository.save(user);
        if (Objects.isNull(userFromDB)) {
            throw new RuntimeException("Not found");
        }
        return userFromDB;
    }

    @PostMapping(value = "/pdf", consumes = "application/pdf")
    @ResponseBody
    public byte[] generateReport() {
        byte[] bytes = null;
        List<User> users = userRepository.findAll();
        if (users == null || users.isEmpty()) {
            //handling is needed
            throw new RuntimeException("DB does not contain any user");
        }

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(users);
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
