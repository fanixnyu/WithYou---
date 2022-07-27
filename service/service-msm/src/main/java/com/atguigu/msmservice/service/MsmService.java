package com.atguigu.msmservice.service;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface MsmService  {
    boolean send(Map<String, Object> param, String phone);
}
