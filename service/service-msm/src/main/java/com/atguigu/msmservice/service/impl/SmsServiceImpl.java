package com.atguigu.msmservice.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class SmsServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone)) return false;
        DefaultProfile profile = DefaultProfile.
                getProfile("regionid",
                        "accesskeyid",
                        "secret");

        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
//        设置发送相关的参数
        request.putQueryParameter("phoneNumbers",phone);
        request.putQueryParameter("SignName","阿里云签名名称");
        request.putQueryParameter("TemplateCode","模板code");
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));//验证码数据，用JSONObject转换map集合

//      最终发送
       try{
         CommonResponse response =client.getCommonResponse(request);
         boolean success =response.getHttpResponse().isSuccess();
         return success;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }

    }
}
