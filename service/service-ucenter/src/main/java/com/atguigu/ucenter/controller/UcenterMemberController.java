package com.atguigu.ucenter.controller;
import com.atguigu.common_utils.JwtUtils;
import com.atguigu.common_utils.R;
import com.atguigu.common_utils.ordervo.UcenterMemberOrder;
import com.atguigu.ucenter.entity.UcenterMember;
import com.atguigu.ucenter.entity.vo.RegisterVo;
import com.atguigu.ucenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author withYou
 * @since 2022-07-27
 */
@RestController
@RequestMapping("/educms/ucenter-member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    //登录
    @PostMapping("login")
    public R login(@RequestBody UcenterMember ucenterMember){
//        返回token值，使用jwt生成出来
        String token= ucenterMemberService.login(ucenterMember);
        return R.ok().data("token",token);
    }

//注册
    @PostMapping("Register")
    public R Register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return R.ok();
    }


//    根据token显示用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        //因为工具类是HttpServletRequest request所以传个request
//调用jwt工具的方法，根据request对象获取头信息,返回用户的id
        String jwtToken = JwtUtils.getMemberIdByJwtToken(request);
//   查询数据库根据用户id获取用户信息
        UcenterMember byId = ucenterMemberService.getById(jwtToken);
        return R.ok().data("userInfo",byId);
    }

//根据用户id获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id){
//        把member对象复制给UcenterMemberOrder对象
        UcenterMember member =ucenterMemberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder =new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }


//    查询一天注册的人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day){
       Integer count= ucenterMemberService.countRegisterDay(day);
       return R.ok().data("count",count);
    }



}

