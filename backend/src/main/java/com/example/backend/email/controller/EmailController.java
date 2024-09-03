package com.example.backend.email.controller;

import com.example.backend.common.BaseResponse;
import com.example.backend.email.service.EmailService;
import com.example.backend.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/admin/ad")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmailController {
    private final EmailService emailService;
    private final ProductService productService;

    @RequestMapping(method = RequestMethod.POST, value = "/email")
    public ResponseEntity testEmail() throws MessagingException {

        emailService.sendEmails();
        return ResponseEntity.ok().body(BaseResponse.successResponse("전송"));        //TODO: 변경 필요
    }
}
