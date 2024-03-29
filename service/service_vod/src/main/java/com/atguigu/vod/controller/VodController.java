package com.atguigu.vod.controller;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.common_utils.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.Utils.ConstantVodUtils;
import com.atguigu.vod.Utils.InitVodCilent;
import com.atguigu.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 批量删除
     * 用list集合 进行传递
     * @param videoIdList
     * @RequestParam("") 里面参数不加也可以
     * @return
     */
    @DeleteMapping("deleteBatch")
    public R deleteBatch(@RequestParam("videoIdList")List videoIdList){
        vodService.removeMoreAliyVideo(videoIdList);
        return R.ok();
    }

//    根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuto(@PathVariable String id){
        try{
//            创建初始化对象
            DefaultAcsClient client=InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET);
            //            创建获取凭证request 和 response对象
            GetVideoPlayAuthRequest request =new GetVideoPlayAuthRequest();
//            向request设置视频id
            request.setVideoId(id);
//            调用方法得到凭证
            GetVideoPlayAuthResponse response =new GetVideoPlayAuthResponse();
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        }catch (Exception e){
        throw new GuliException(20001,"获取凭证失败");
        }
    }
}
