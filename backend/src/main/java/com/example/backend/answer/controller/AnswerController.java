package com.example.backend.answer.controller;

import com.example.backend.answer.model.request.PostAnswerRegisterReq;
import com.example.backend.answer.service.AnswerService;
import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/qna")
@CrossOrigin("*")
public class AnswerController {
    private final AnswerService answerService;


    @RequestMapping(method = RequestMethod.POST,value = "/answer/{idx}")
    public ResponseEntity registerAnswer(@RequestHeader(value = "Authorization") String token, @PathVariable Long idx,@Valid @RequestBody PostAnswerRegisterReq postAnswerRegisterReq) {
        try {
            return ResponseEntity.ok().body(BaseResponse.successResponse(answerService.registerAnswer(token, idx, postAnswerRegisterReq)));
        } catch (BaseException exception) {
            return ResponseEntity.ok().body(BaseResponse.failResponse(exception.getBaseResponseStatus()));
        }
    }
}
