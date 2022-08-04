package com.atguigu.eduOrder.controller;
import com.atguigu.common_utils.JwtUtils;
import com.atguigu.common_utils.R;
import com.atguigu.eduOrder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author withYou
 * @since 2022-08-01
 */
@CrossOrigin
@RestController
@RequestMapping("/eduOrder/t-order")
public class TOrderController {

    @Autowired
    private TOrderService tOrderService;

//    生成订单编号

    @PostMapping("createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){
        //通过HttpServletRequest里面Header取到
      String orderNo =  tOrderService.createOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return  R.ok().data("orderId",orderNo);
    }
}

