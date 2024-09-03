package com.example.backend.elastic.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetTodayLoginRes {
    Long todayLogin;
    Long difLogin;
}
