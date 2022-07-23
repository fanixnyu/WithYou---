package com.atguigu.eduservice.service;
import com.atguigu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface EduVideoService extends IService<EduVideo> {

//    根据课程id删除小节
    void removeVideoCourseId(String courseId);
}
