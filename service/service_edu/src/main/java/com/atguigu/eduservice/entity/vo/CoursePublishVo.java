package com.atguigu.eduservice.entity.vo;
import lombok.Data;

@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectOne;
    private String subjectTwo;
    private String teacherName;
    private String price;
}
