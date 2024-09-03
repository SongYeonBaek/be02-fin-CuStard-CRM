package com.example.backend.answer.service;


import com.example.backend.admin.model.entity.Admin;
import com.example.backend.admin.repository.AdminRepository;
import com.example.backend.answer.model.Answer;
import com.example.backend.answer.model.request.PostAnswerRegisterReq;
import com.example.backend.answer.model.response.PostAnswerRegisterRes;
import com.example.backend.answer.repository.AnswerRepository;
import com.example.backend.common.BaseException;
import com.example.backend.qna.model.entity.Qna;
import com.example.backend.qna.repository.QnaRepository;
import com.example.backend.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.backend.common.BaseResponseStatus.*;


@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QnaRepository qnaRepository;
    private final AdminRepository adminRepository;

    public PostAnswerRegisterRes registerAnswer(String token, Long qnaIdx, PostAnswerRegisterReq postAnswerRegisterReq) throws BaseException {
        Optional<Answer> searchdup = answerRepository.findByQnaIdx(qnaIdx); //이미 작성된 답변이 있을 경우
        if (searchdup.isEmpty()) {

                PostAnswerRegisterRes postAnswerRegisterRes = null;
                Optional<Qna> result = qnaRepository.findById(qnaIdx);
                if (result.isPresent()) {
                    Qna qna = result.get();

                    token = TokenProvider.replaceToken(token);
                    Long customerIdx = TokenProvider.getIdx(token);
                    Optional<Admin> admin = adminRepository.findById(customerIdx);
                    if (admin.isPresent()) {
                        Admin writer = admin.get();

                        Answer answer = Answer.builder()
                                .answerContent(postAnswerRegisterReq.getAnswerContent())
                                .qna(qna)
                                .admin(writer)
                                .build();
                        answerRepository.save(answer);

                        postAnswerRegisterRes = PostAnswerRegisterRes.builder()
                                .title(qna.getTitle())
                                .qnaContent(qna.getQnaContent())
                                .answerContent(answer.getAnswerContent())
                                .build();
                    } else {
                        throw new BaseException(ADMIN_ANSWER_EMPTY_TOKEN);
                    }
                } else {
                    throw new BaseException(ADMIN_ANSWER_NOT_FOUND);
                }
                return postAnswerRegisterRes;
        } else {
            throw new BaseException(ADMIN_ANSWER_ISPRESENT);
        }
    }
}
