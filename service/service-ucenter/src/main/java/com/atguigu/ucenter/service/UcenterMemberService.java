package com.atguigu.ucenter.service;

import com.atguigu.ucenter.entity.UcenterMember;
import com.atguigu.ucenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author withYou
 * @since 2022-07-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    String login(UcenterMember ucenterMember);

    void register(RegisterVo registerVo);


//    微信扫码登录接口
    UcenterMember getOpenIdMember(String openid);

    Integer countRegisterDay(String day);
}
