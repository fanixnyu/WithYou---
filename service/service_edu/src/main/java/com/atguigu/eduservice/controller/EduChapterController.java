package com.atguigu.eduservice.controller;
import com.atguigu.common_utils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("eduService/EduChapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

//    查询全部
    @GetMapping("/QueryChapterAll/{id}")
    public R QueryChapterAll(@PathVariable  String id){
        List<ChapterVo> list =eduChapterService.getChapterVideoByCourseId(id);
        return R.ok().data("allChapterVideo",list);
    }

       //    AddChapter
    @PostMapping("/AddChapter")
    public R AddChapter(@RequestBody EduChapter eduChapter){
      eduChapterService.save(eduChapter);
        return R.ok();
    }


    /**
        通过id查询
     */

    @GetMapping("/GetChapter/{id}")
    public R GetChapter(@PathVariable String id){
        EduChapter byId = eduChapterService.getById(id);
      return R.ok().data("byId",byId);
    }

//    修改章节
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

//    删除
    @DeleteMapping("/DeleteChapter")
    public R DeleteChapter(@PathVariable String id){
        Boolean b =eduChapterService.deleteChapter(id);
      if (b){
          return R.ok();
      }else {
          return R.error();
      }
    }


}
