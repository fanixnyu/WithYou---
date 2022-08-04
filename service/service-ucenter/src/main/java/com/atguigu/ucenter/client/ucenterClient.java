package com.atguigu.ucenter.client;
import com.atguigu.common_utils.R;
import com.atguigu.ucenter.entity.EduComment;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
//@FeignClient(name="service-ucenter",fallback = ucenterClientImpl.class)
public interface ucenterClient {

    //根据用户id获取用户信息
    @GetMapping("/ucenterservice/member/getUcenterPay/{memberId}")
    public EduComment getUcenterPay(@PathVariable("memberId") String memberId);

}
