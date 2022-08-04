package com.atguigu.ucenter.controller;
import com.atguigu.common_utils.R;
import com.atguigu.ucenter.entity.CrmBanner;
import com.atguigu.ucenter.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/educms/BannerFront")
@RestController
public class BannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

//    QueryAll
    @GetMapping("QueryAll")
    public R QueryAll(){

//        接口自己写，加Redis比较方便
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        return R.ok().data("list",list);
    }
}
