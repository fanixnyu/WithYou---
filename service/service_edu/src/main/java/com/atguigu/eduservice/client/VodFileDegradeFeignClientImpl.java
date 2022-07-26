package com.atguigu.eduservice.client;
import com.atguigu.common_utils.R;
import org.springframework.stereotype.Component;

@Component
public class VodFileDegradeFeignClientImpl implements VodClient{
    @Override
    public R removeAliyVideo(String id) {
        return R.error().message("删除视频出错了，臭宝");
    }
}
