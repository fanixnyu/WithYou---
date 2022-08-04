package com.atguigu.eduOrder.service;

import com.atguigu.eduOrder.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author withYou
 * @since 2022-08-01
 */
public interface TOrderService extends IService<TOrder> {
    String createOrder(String courseId, String memberIdByJwtToken);
}
