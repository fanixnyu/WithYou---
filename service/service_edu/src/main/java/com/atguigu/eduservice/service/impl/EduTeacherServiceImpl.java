package com.atguigu.eduservice.service.impl;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
//    分页查询讲师
    @Override
    public Map<String, Object> getTeacherFromList(Page<EduTeacher> page) {
        QueryWrapper<EduTeacher> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        baseMapper.selectPage(page,queryWrapper);

        List<EduTeacher> records = page.getRecords();
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();
        long pagePages = page.getPages();
        long current = page.getCurrent();
        boolean hasNext = page.hasNext();//下一页
        boolean hasPrevious = page.hasPrevious();//上一页
//        用map做个接受返回
        Map<String,Object> map =new HashMap<>();
        map.put("records",records);
        map.put("pages",pages);
        map.put("size",size);
        map.put("total",total);
        map.put("pagePages",pagePages);
        map.put("current",current);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }
}
