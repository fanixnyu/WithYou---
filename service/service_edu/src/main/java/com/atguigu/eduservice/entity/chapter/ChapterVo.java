package com.atguigu.eduservice.entity.chapter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo {
    private String id;
    private String title;
    //    一对多关系（一个章节中包含很多小节）
    private List<VideoVo> children =new ArrayList<>();
}
