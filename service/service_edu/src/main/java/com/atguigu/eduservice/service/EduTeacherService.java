package com.atguigu.eduservice.service;
import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface EduTeacherService extends IService<EduTeacher> {
//   返回所有数据
    Map<String, Object> getTeacherFromList(Page<EduTeacher> page);
}
