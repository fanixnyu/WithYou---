package com.atguigu.eduservice.controller;
import com.atguigu.common_utils.R;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @PostMapping("AddVideo")
    public R AddVideo(@RequestBody EduVideo eduVideo){
         eduVideoService.save(eduVideo);
         return R.ok();
    }

// 后面需要完善
    @DeleteMapping("DeleteVideo/{eduVideo}")
    public R DeleteVideo(@PathVariable EduVideo eduVideo){
        eduVideoService.removeById(eduVideo);
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
