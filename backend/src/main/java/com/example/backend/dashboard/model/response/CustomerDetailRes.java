package com.example.backend.dashboard.model.response;

import com.example.backend.customer.model.response.GetCustomerReadRes;
import com.example.backend.dashboard.model.dto.GetCategoryOrdersRes;
import com.example.backend.dashboard.model.dto.GetLoginTimeRes;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDetailRes {
    GetLoginTimeRes getLoginTimeRes;
    GetCategoryOrdersRes getCategoryOrdersRes;
    GetCustomerReadRes getCustomerReadRes;
}
