package com.example.backend.dashboard.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetTodaySignupRes {
    Long todaySignup;
    Long difSignup;
}
