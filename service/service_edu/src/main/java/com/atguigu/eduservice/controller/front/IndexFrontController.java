package com.atguigu.eduservice.controller.front;
import com.atguigu.common_utils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin
@RequestMapping("eduService/indexFront")
@RestController
public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("index")
    public R index(){
//        查询前8条热门课程
        QueryWrapper<EduCourse> queryWrapperCourse =new QueryWrapper<>();
        queryWrapperCourse.orderByDesc("id");
        queryWrapperCourse.last("limit 8");
        List<EduCourse> CourseList = eduCourseService.list(queryWrapperCourse);

        //       查询前4条名师
        QueryWrapper queryWrapperTeacher =new QueryWrapper<>();
        queryWrapperTeacher.orderByDesc("id");
        queryWrapperTeacher.last("limit 4");
        List TeacherList = eduTeacherService.list(queryWrapperTeacher);
        return R.ok().data("Course",CourseList).data("TeacherList",TeacherList);
    }
}
