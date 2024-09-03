package com.example.backend.elastic.controller;

import com.example.backend.customer.model.response.GetCustomerReadRes;
import com.example.backend.customer.service.CustomerService;
import com.example.backend.elastic.model.response.*;
import com.example.backend.elastic.model.dto.*;
import com.example.backend.dashboard.model.dto.GetTodaySignupRes;
import  com.example.backend.dashboard.model.dto.GetSleepAccountRes;
import com.example.backend.common.BaseException;
import com.example.backend.common.BaseResponse;
import com.example.backend.dashboard.service.CalculateLogService;
import com.example.backend.elastic.model.dto.GetTodayLoginRes;
import com.example.backend.elastic.model.response.MainDashBoardRes;
import com.example.backend.elastic.service.LoginService;
import com.example.backend.elastic.service.ESOrdersService;
//import com.example.backend.elastic.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/es/dashboard")
public class ElasticController {

    private final LoginService loginService;
    private final ESOrdersService ESOrdersService;
//    private final ProductDetailService productDetailService;

    private final CalculateLogService calculateLogService;
    private final CustomerService customerService;

    //메인 대시보드 요청
    @RequestMapping ("/main")
    public BaseResponse getMainDashboard(){
        try{
            GetTodayLoginRes getTodayLoginRes = loginService.countTodayLogins();
            GetTodaySignupRes getTodaySignupRes = calculateLogService.todaySignUp();
            GetTodayOrdersRes getTodayOrdersRes = ESOrdersService.countTodayOrders();
            GetCategoryOrdersRes getCategoryOrdersRes = ESOrdersService.catergorySales();
            GetCategoryOrdersRes getMonthOrdersRes = ESOrdersService.monthlySales();
            GetSleepAccountRes getSleepAccountRes = calculateLogService.sleepAccountGrowthRate();
            GetLoginTimeRes getLoginTimeRes = loginService.countLoginTime();

            return BaseResponse.successResponse(MainDashBoardRes.builder()
                    .getTodayLoginRes(getTodayLoginRes)
                    .getTodaySignupRes(getTodaySignupRes)
                    .getTodayOrdersRes(getTodayOrdersRes)
                    .getCategoryOrdersRes(getCategoryOrdersRes)
                    .getMonthOrdersRes(getMonthOrdersRes)
                    .getSleepAccountRes(getSleepAccountRes)
                    .getLoginTimeRes(getLoginTimeRes)
                    .build());

        } catch (BaseException exception){
            return BaseResponse.failResponse(exception.getBaseResponseStatus());
        }
    }

    //고객 대시보드 요청
    @RequestMapping("customer/detail/{idx}")
    public BaseResponse getCustomerDetail(@PathVariable Long idx) {
        try {
            GetLoginTimeRes getLoginTimeRes = loginService.custLoginTime(idx);
            GetCategoryOrdersRes getCategoryOrdersRes = ESOrdersService.custCatergoryOrders(idx);
//            GetCustomerReadRes getCustomerReadRes = productDetailService.catergoryRead(idx);
            GetCustomerReadRes getCustomerReadRes = customerService.read(idx);

            return  BaseResponse.successResponse(CustomerDetailRes.builder()
                    .getLoginTimeRes(getLoginTimeRes)
                    .getCategoryOrdersRes(getCategoryOrdersRes)
                    .getCustomerReadRes(getCustomerReadRes)
                    .build());

        } catch (BaseException exception){
            return BaseResponse.failResponse(exception.getBaseResponseStatus());
        }
    }
//======================테스트용 요청===============================
    @GetMapping("/login/count")
    public Object getLoginCount()  {
        return loginService.countTodayLogins();
    }

    @GetMapping("/orders/count")
    public Object getOrdersCount()  {
        return ESOrdersService.countTodayOrders();
    }


//    =====하단 집계함수====
    @GetMapping("/orders/month")
    public Object monthlySales() {
        return ESOrdersService.monthlySales();
    }

    @GetMapping("/login/time")
    public Object countLoginTime()  {
        return loginService.countLoginTime();
    }

    @GetMapping("/orders/category")
    public Object catergory() {
        return ESOrdersService.catergorySales();
    }

    //====고객 집계함수=====
    @GetMapping("/cust/login/time/{idx}")
    public Object custLoginTime(@PathVariable Long idx)  {
        return loginService.custLoginTime(idx);
    }

    @GetMapping("/cust/orders/category/{idx}")
    public Object custCatergoryOrders(@PathVariable Long idx) {
        return ESOrdersService.custCatergoryOrders(idx);
    }

//    @GetMapping("/cust/read/category/{idx}")
//    public Object catergoryRead(@PathVariable Long idx) {
//        return productDetailService.catergoryRead(idx);
//    }
//    @GetMapping("/relogin")
//    public int reLogin(){
//        return loginService.reLogin();
//    }
//    @GetMapping("/be")
//    public List<Long> be(){
//        return loginService.between90();
//    }
//
//    @GetMapping("/id")
//    public List<CustomerDocument> id(){
//        return loginService.id();
//    }

}