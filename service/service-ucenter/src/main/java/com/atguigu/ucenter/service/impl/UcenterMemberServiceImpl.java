package com.atguigu.ucenter.service.impl;

import com.atguigu.common_utils.JwtUtils;
import com.atguigu.common_utils.MD5;
import com.atguigu.ucenter.entity.UcenterMember;
import com.atguigu.ucenter.entity.vo.RegisterVo;
import com.atguigu.ucenter.mapper.UcenterMemberMapper;
import com.atguigu.ucenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author withYou
 * @since 2022-07-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

   @Autowired
   private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember ucenterMember) {
//        先拿到值
        String mobile =ucenterMember.getMobile();
        String password =ucenterMember.getPassword();
//        再进行判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuliException(20001,"登录失败");
        }
//   判断手机号是否正确
        QueryWrapper<UcenterMember> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(queryWrapper);
//        判断查出来的对象是否为空
        if (mobileMember==null){
            throw new GuliException(20001,"登录失败");
        }

//        判断密码(数据库密码是加密的，把输入的密码进行加密，再和数据库密码进行比较)
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new GuliException(20001,"登录失败");
        }

//        判断用户是否被禁用
        if (mobileMember.getIsDisabled()){
            throw new GuliException(20001,"登录失败");
        }

//        登录成功(使用jwt工具类生成token字符串)
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }


//    注册
    @Override
    public void register(RegisterVo registerVo) {
//       拿到值，再进行非空判断
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname =registerVo.getNickname();
        String password = registerVo.getPassword();

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile)
                || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(mobile)) {
            throw new GuliException(20001,"注册失败");
        }

//        判断验证码，先获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)){
            throw new GuliException(20001,"注册失败");
        }

//        判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count=baseMapper.selectCount(queryWrapper);
        if (count>0){
            throw new GuliException(20001,"注册失败");
        }

//        数据库添加
        UcenterMember ucenterMember =new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("http:liuzhichao520.top/log/90.jpg");
        baseMapper.insert(ucenterMember);
    }





//    微信登录接口实现
    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        UcenterMember member =baseMapper.selectOne(queryWrapper);
        return member;
    }


//    统计模块

    @Override
    public Integer countRegisterDay(String day) {
       Integer integer= baseMapper.countRegisterDay(day);
        return integer;
    }
}
