package com.atguigu.eduservice.controller;
import com.atguigu.common_utils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class EduVideoController {

    @Autowired
    private VodClient vodClient;
    @Autowired
    private EduVideoService eduVideoService;

    @PostMapping("AddVideo")
    public R AddVideo(@RequestBody EduVideo eduVideo){
         eduVideoService.save(eduVideo);
         return R.ok();
    }


    @DeleteMapping("DeleteVideo/{eduVideoId}")
    public R DeleteVideo(@PathVariable String eduVideoId){
//        根据小节（video）id获取视频id，调用方法删除视频
        EduVideo eduvideo =eduVideoService.getById(eduVideoId);
        String courseId = eduvideo.getCourseId();
//        判断video小节里面是否有视频id
        if (!StringUtils.isEmpty(courseId)){
            vodClient.removeAliyVideo(courseId);
        }
//        删除小节
        eduVideoService.removeById(eduVideoId);
        return R.ok();
    }


    @PostMapping("UpdateVideo")
    public R UpdateVideo(@RequestBody EduVideo eduVideo){
        boolean updateById = eduVideoService.updateById(eduVideo);
        if (updateById){
            return R.ok().message("修改成功");
        }else {
            return R.error().message("修改失败");
        }
    }
}
