package com.atguigu.eduservice.controller.front;
import com.atguigu.common_utils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/teacherFront")
public class TeacherFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

//    分页查询讲师
    @GetMapping("/FrontPageTeacher/{current}/{size}")
    public R FrontPageTeacher(@PathVariable long current,@PathVariable long size){
        Page<EduTeacher> page =new Page<>(current,size);
       Map<String,Object> map = eduTeacherService.getTeacherFromList(page);
//        返回所有数据
        return R.ok().data(map);
    }


//    讲师详情功能
    @GetMapping("getTeacherFrontInfo/{}")
    public R getTeacherFrontInfo(@PathVariable String teacherId){
//        根据id查询讲师基本信息
        EduTeacher eduTeacherId = eduTeacherService.getById(teacherId);
    //根据讲师id查询所有课程
        QueryWrapper<EduCourse> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> list = eduCourseService.list(queryWrapper);
        return R.ok().data("eduTeacherId",eduTeacherId).data("list",list);
    }
}
