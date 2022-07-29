package com.atguigu.educms.service;

import com.atguigu.educms.entity.UcenterMember;
import com.atguigu.educms.entity.vo.RegisterVo;
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
}
