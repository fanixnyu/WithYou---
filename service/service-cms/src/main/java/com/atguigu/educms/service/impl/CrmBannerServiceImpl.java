package com.atguigu.educms.service.impl;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.mapper.CrmBannerMapper;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-25
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    /**
     * 为了减少数据库的压力，使用缓存
     * selectIndexList作用于：页面刷新访问后，redis会出现这个key：value（banner::selectIndexList），使用get banner::selectIndexList 就可以查看
     *
     */
    @Cacheable(value = "banner" ,key ="selectIndexList")
    @Override
    public List<CrmBanner> selectAllBanner() {
        QueryWrapper<CrmBanner> queryWrapper =new QueryWrapper<>();
//        根据id进行降序排列
        queryWrapper.orderByDesc("id");
//        显示排列之后前两条记录(last方法,拼接sql语句)
        queryWrapper.last("limit 2");
        List<CrmBanner> bannerList = baseMapper.selectList(null);
        return bannerList;
    }

}
