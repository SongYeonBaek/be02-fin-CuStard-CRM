package com.example.backend.elastic.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetTodayOrdersRes {
    Integer todayOrdersCount;
    Integer difOrdersCount;
    Integer todayOrdersAmount;
    Integer difOrdersAmount;

}
