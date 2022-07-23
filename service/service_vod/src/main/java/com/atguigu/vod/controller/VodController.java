package com.atguigu.vod.controller;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.common_utils.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.Utils.ConstantVodUtils;
import com.atguigu.vod.Utils.InitVodCilent;
import com.atguigu.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("eduvod/video")
public class VodController {

    @Autowired
    private VodService  vodService;

//   上传视频
    @PostMapping("uploadAliVideo")
    public R uploadAliVideo(MultipartFile file){
       String videoId= vodService.uploadAliYunVideo(file);
        return R.ok().data("videoId",videoId);
    }

//    根据视频id删除阿里云视频
    @DeleteMapping("RemoveAliyunVideo/{id}")
    public R RemoveAliyunVideo(@PathVariable String id){
      try {
//          初始化对象
          DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
//      创建一个删除视频request对象(如果没有出现DeleteVideoRequest，可能缺少依赖)
          DeleteVideoRequest request =new DeleteVideoRequest();
//        向request 设置视频id，因为是根据视频id来删除
          request.setVideoIds(id);
//        调用初始化对象的方法实现删除
          client.getAcsResponse(request);
//          如果成功就返回ok
          return R.ok();
      }catch (Exception e){
//          失败则打印
          e.printStackTrace();
          throw new GuliException(20001,"删除视频失败");
      }
    }
}
