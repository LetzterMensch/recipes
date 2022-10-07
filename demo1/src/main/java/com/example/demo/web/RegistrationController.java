package com.example.demo.web;


import com.example.demo.model.User;
import com.example.demo.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/api/")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public ResponseEntity httpRegister(@Valid @RequestBody User user,@NotNull BindingResult bindingResult){
        return registrationService.userRegister(user);
    }
}
