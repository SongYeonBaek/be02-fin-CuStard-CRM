package com.example.backend.dashboard.model.response;

import com.example.backend.dashboard.model.dto.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MainDashBoardRes {
    GetTodayLoginRes getTodayLoginRes;
    GetTodaySignupRes getTodaySignupRes;
    GetTodayOrdersRes getTodayOrdersRes;
    GetCategoryOrdersRes getCategoryOrdersRes;
    GetCategoryOrdersRes getMonthOrdersRes;
    GetSleepAccountRes getSleepAccountRes;
    GetLoginTimeRes getLoginTimeRes;
}
