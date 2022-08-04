package com.atguigu.ucenter.controller;


import com.atguigu.common_utils.R;
import com.atguigu.ucenter.entity.CrmBanner;
import com.atguigu.ucenter.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-25
 */

@RestController
@RequestMapping("/educms/BannerAdmin")
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

//    分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> pageBanner=new Page<>(page,limit);
        crmBannerService.page(pageBanner,null);
        Map map =new HashMap<>();
        map.put("records",pageBanner.getRecords());
        map.put("current",pageBanner.getTotal());
        return R.ok().data(map);
    }

//    添加
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.ok();
    }
//    删除
    @DeleteMapping("RemoveBanner/{crmBanner}")
    public R RemoveBanner(@PathVariable Boolean crmBanner){
        boolean removeById = crmBannerService.removeById(crmBanner);
       if (removeById){
           return R.ok();
       }else {
           return R.error().message("删除失败");
       }
    }

//    修改
    @PutMapping("UpdateBanner")
    public R UpdateBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return R.ok();
    }



}

