package com.course.phonedirectory.service;

import com.course.phonedirectory.rep.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean changeMobileOperator(){
        
    }

}
