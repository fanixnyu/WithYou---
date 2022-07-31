package com.atguigu.eduservice.controller.front;
import com.atguigu.common_utils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.frontvo.CourseFrontVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/courseFront")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

//    1.条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{current}/{size}")
    public R getFrontCourseList(@PathVariable long current,long size,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageEduCourse =new Page<>(current,size);
       Map<String,Object> map= eduCourseService.getCourseFrontList(pageEduCourse,courseFrontVo);
        return R.ok().data(map);
    }

//    课程详情
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
//        根据课程id,编写sql语句查询课程信息
       CourseWebVo courseWebVo= eduCourseService.getBaseCourseInfo(courseId);
//        根据课程id查询章节和小节
        List<ChapterVo> chapterVideoByCourseId = eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoByCourseId",chapterVideoByCourseId);
    }
}
