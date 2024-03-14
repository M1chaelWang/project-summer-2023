package com.neusoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.po.CommonResult;
//import com.neusoft.InvalidTokenException;
//import com.neusoft.SecurityConfig;
import com.neusoft.po.Food;
import com.neusoft.service.FoodService;
@CrossOrigin("*")
@RestController
@RequestMapping("/FoodController")
public class FoodController {
    @Autowired
    private FoodService foodService;
    //@Autowired
    //private SecurityConfig securityConfig = SecurityConfig.getInstance();

    @GetMapping("/business/{businessId}")
    /*public List<Food> listFoodByBusinessId(@PathVariable("businessId") Integer businessId,
            @RequestHeader("token") String token) throws Exception {
        if (securityConfig.isValidToken(token))
            return foodService.listFoodByBusinessId(businessId);
        else
            throw new InvalidTokenException("Invalid token");
    }*/
    public CommonResult<List> listFoodByBusinessId(@PathVariable("businessId") Integer
    		businessId) throws Exception{
    		 List<Food> list = foodService.listFoodByBusinessId(businessId);
    		 return new CommonResult(200,"success",list);
    		 }
}
