package com.atguigu.eduservice.service.impl;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    /**
     * 一对一的关系，把课程添加后再添加课程简介
     */
    @Autowired
    private  EduCourseMapper eduCourseMapper;

//    小节service
    @Autowired
    private EduVideoService eduVideoService;
//注入章节service
    @Autowired
    private EduChapterService eduChapterService;
//    注入简介描述接口
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

//    添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo){
//    向课程表添加课程基本信息
//        CourseInfoVo转换成eduCourse (把vo转换成实体)
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        eduCourse.setGmtCreate(new Date());
        eduCourse.setGmtModified(new Date());
        int insert = baseMapper.insert(eduCourse);
        if (insert==0){
            throw  new GuliException(20001,"添加课程失败");
        }
//获取EduCourse的id值，再设置描述课程id，得到EduCourse的id值
        String id=eduCourse.getId();
//        向课程表添加课程简介
        EduCourseDescription eduCourseDescription =new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setGmtCreate(new Date());
        eduCourseDescription.setGmtModified(new Date());
//        设置描述课程id
        eduCourseDescription.setId(id);
        eduCourseDescriptionService.save(eduCourseDescription);
        return "Fuck!";
    }



//    根据课程id查询课程基本信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
//        查询课程表
        EduCourse eduCourse = this.baseMapper.selectById(courseId);
//        做个转换（实体转vo）
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

//        查询描述表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        return courseInfoVo;
    }

//    修改课程方法
    @Override
    public void CourseUpdate(CourseInfoVo courseInfoVo) {
//        1.修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        int updateById = baseMapper.updateById(eduCourse);
        if (updateById==0){
            new GuliException(20001,"修改失败了，臭宝贝");
        }

//      2.修改描述表
        EduCourseDescription eduCourseDescription =new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

//    根据课程id调用信息
    @Override
    public CoursePublishVo publishCourseInfo(String coursePublishVo) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(coursePublishVo);
        return publishCourseInfo;
    }

//    删除课程
    @Override
    public void removeCourse(String courseId) {
//        根据课程id删除小节
        eduVideoService.removeVideoCourseId(courseId);
//        根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);
//        根据课程id删除描述
        eduCourseDescriptionService.removeById(courseId);
//        根据课程id删除课程本身
        int deleteById = baseMapper.deleteById(courseId);
        if (deleteById==0){
            throw new GuliException(20001,"删除失败");
        }
    }
}
