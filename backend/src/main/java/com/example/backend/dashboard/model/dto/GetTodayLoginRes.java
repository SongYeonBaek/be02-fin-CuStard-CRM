package com.example.backend.dashboard.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetTodayLoginRes {
    Long todayLogin;
    Long difLogin;
}
