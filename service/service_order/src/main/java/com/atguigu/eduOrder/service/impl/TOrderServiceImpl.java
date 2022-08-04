package com.atguigu.eduOrder.service.impl;

import com.atguigu.eduOrder.entity.TOrder;
import com.atguigu.eduOrder.mapper.TOrderMapper;
import com.atguigu.eduOrder.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author withYou
 * @since 2022-08-01
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {


        return null;
    }
}
