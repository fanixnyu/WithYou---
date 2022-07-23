package com.atguigu.eduservice.service;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.service.IService;


public interface EduCourseService extends IService<EduCourse> {
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void CourseUpdate(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String coursePublishVo);

    void removeCourse(String id);
}
