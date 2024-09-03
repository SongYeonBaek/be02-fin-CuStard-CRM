package com.example.backend.dashboard.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCategoryOrdersRes {
    int[] orders;
    int[] productRead;
    int ordersCount;
    int ordersAmount;
}