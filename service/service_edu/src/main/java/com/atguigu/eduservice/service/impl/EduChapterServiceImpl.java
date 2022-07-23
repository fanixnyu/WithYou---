package com.atguigu.eduservice.service.impl;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String id) {
//        先获取章节，再获取小节
//    1.根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> queryChapter =new QueryWrapper<>();
        queryChapter.eq("course_id",id);
        List<EduChapter> eduChapterList = baseMapper.selectList(queryChapter);
//    2.根据课程id查询课程里面得所有小节
        QueryWrapper<EduVideo> wrapperVideo =new QueryWrapper<>();
        wrapperVideo.eq("course_id",id);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapperVideo);
//        创建list集合，用于最终封装数据
        List<ChapterVo> finalList =new ArrayList<>();
//    3.遍历查询章节list集合进行封装
        for (int i = 0; i < eduChapterList.size(); i++) {
//        每个章节
        EduChapter  eduChapter =eduChapterList.get(i);
//        eduChapter 对象复制到ChapterVo里面
            ChapterVo chapterVo =new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
//            chapterVo放到最终list集合里面
            finalList.add(chapterVo);
            //    4.遍历查询小节list集合进行封装
//            创建集合用于封装章节的小节
            List<VideoVo> videoList =new ArrayList<>();
            for (int m =0; m<eduVideoList.size();m++){
//                得到每个小节
                EduVideo eduVideo =eduVideoList.get(m);
//                判断小节里面chapterId和章节里面id是否一样
                if (eduVideo.getChapterId().equals(eduChapter.getId())){
//                    如果相同,则进行封装
                    VideoVo videoVo =new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
//                    放到小节封装集合
                    videoList.add(videoVo);
                }
            }
//            把封装之后小节list集合,放到章节对象里面
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }

//    删除章节方法
    @Override
    public Boolean deleteChapter(String id) {
//        根据chapterId章节id查询小节表，如果查询到数据，不进行删除
        QueryWrapper<EduVideo> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("chapter_id",id);
//       只看章节下面小节有没有数据
        int count = eduVideoService.count(queryWrapper);
        if (count>0){
           throw new GuliException(20001,"不能删除");
        }else {
            int result = baseMapper.deleteById(id);
//          删除结果大于0就代表成功
            return result>0;
        }

    }

//    根据课程id删除章节
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }


}
