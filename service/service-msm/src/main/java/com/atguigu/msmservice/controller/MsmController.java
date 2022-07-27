package com.atguigu.msmservice.controller;
import com.atguigu.common_utils.R;
import com.atguigu.msmservice.Utils.RandomUtil;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("Msm")
@RestController
public class MsmController {

    @Autowired
    private MsmService msmService;

//    整合redis
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

//    发送短信方法
    @GetMapping("send/{phone}")
    public R send(@PathVariable String phone){
//1.从redis获取验证码，如果获取不到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
//        2.如果redis获取不到，就进行阿里云发送
//      生成随机
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param =new HashMap<>();
        param.put("code",code);
       boolean isSend= msmService.send(param,phone);
       if (isSend){
//           发送成功，把发送成功验证码放到redis里面
//           设置有效时间(5分钟)
           redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
           return R.ok();
       }else {
           return R.error().message("发送短信失败");
       }
    }
}
