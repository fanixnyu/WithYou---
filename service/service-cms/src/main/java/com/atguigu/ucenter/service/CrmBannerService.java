package com.atguigu.ucenter.service;

import com.atguigu.ucenter.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-25
 */
public interface CrmBannerService extends IService<CrmBanner> {
//    查询所有
    List<CrmBanner> selectAllBanner();
}
