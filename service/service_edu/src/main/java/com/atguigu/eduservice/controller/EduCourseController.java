package com.atguigu.eduservice.controller;
import com.atguigu.common_utils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("EduCourse")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息方法
    @PostMapping("AddCourseInfo")
    public R AddCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
//        返回id为了后面添加大纲使用
       String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }

//    根据课程查询课程基本信息
    @GetMapping("getCourseInfo/{CourseId}")
    public R getCourseInfo(@PathVariable String CourseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(CourseId);
        return R.ok().data("courseInfoVo",courseInfoVo);

    }

//    修改课程信息
    @PostMapping("update")
    public R update(@RequestBody CourseInfoVo courseInfoVo ){
        eduCourseService.CourseUpdate(courseInfoVo);
        return R.ok();
    }

//    根据课程id查询课程基本信息
    @GetMapping("GetCourse/{coursePublishVo}")
    public R GetCourse(@PathVariable String coursePublishVo){
        CoursePublishVo courseInfo = eduCourseService.publishCourseInfo(coursePublishVo);
        return R.ok().data("courseInfo",courseInfo);
    }


//    课程发布
    @PostMapping("Coursefabu/{id}")
    public R Coursefabu(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
            eduCourse.setId(id);
            eduCourse.setStatus("Normal");//已发布
            eduCourse.setGmtCreate(new Date());
            eduCourse.setGmtModified(new Date());
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

//    删除课程
    @DeleteMapping("DeleteCourse/{id}")
    public R DeleteCourse(@PathVariable String id){
        eduCourseService.removeCourse(id);
        return R.ok();
    }
}
