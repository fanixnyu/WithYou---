package com.atguigu.eduservice.client;
import com.atguigu.common_utils.R;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClientImpl.class)//调用的服务名称
@Component//交给spring管理

public interface VodClient {
//    定义调用的方法路径
    @DeleteMapping("eduvod/video/removeAliyVideo/{id}")
//    根据@PathVariable注解一定要指定参数名称，否则出错
    public R removeAliyVideo(@PathVariable("id") String id);

}
