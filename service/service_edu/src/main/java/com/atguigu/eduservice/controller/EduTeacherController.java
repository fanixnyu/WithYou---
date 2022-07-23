package com.atguigu.eduservice.controller;
import com.atguigu.common_utils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/eduService")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

//    查询全部
    @GetMapping("list")
    public R TeacherAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("list",list);
    }

//    逻辑删除
    @DeleteMapping("Delete/{id}")
    public R Delete(@PathVariable String id){
        boolean byId = eduTeacherService.removeById(id);
        if (byId){
            return R.ok();
        }else {
            return R.error();
        }
    }


//    分页
    @GetMapping("OnePage/{current}/{limit}")
    public R OnePage(@PathVariable long current,@PathVariable long limit){
        Page EduTeacherPage =new Page(current,limit);
        eduTeacherService.page(EduTeacherPage,null);
        long total = EduTeacherPage.getTotal();
        Map map =new HashMap();
        map.put("EduTeacherPage",EduTeacherPage);
        map.put("total",total);
        return R.ok().data(map);
    }

//    分页带条件
    @PostMapping("Page/{current}/{limit}")
    public R Page(@PathVariable long current, @PathVariable  long limit,@RequestBody TeacherQuery teacherQuery)
    {
//获取分页
        Page pageTeacher =new Page<>(current,limit);
//        拿到查询的值
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        QueryWrapper queryWrapper =new QueryWrapper<>();
//        new 构建条件，通过条件判断
        if (!StringUtils.isEmpty(name)){
//      用mybatisPlus封装好的QueryWrapper里面提供的方法使用
            queryWrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            queryWrapper.gt("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            queryWrapper.lt("gmt_modified",end);
        }

//        获取总记录数
        long total = pageTeacher.getTotal();
        eduTeacherService.page(pageTeacher,queryWrapper);
        List<EduTeacher> records = pageTeacher.getRecords();

//        创建map来装total 和 records
//        Map map =new HashMap();
//        map.put("total",total);
//        map.put("records",records);
//        return  R.ok().data("total",total).data("records",records);
        return  R.ok().data("total",total).data("records",records);
    }

//    修改
    @PostMapping("Update")
    public R update(@RequestBody EduTeacher id){
        boolean updateById = eduTeacherService.updateById(id);
        if (updateById){
            return R.ok();
        }else {
            return R.error();
        }
    }


//    查询
    @GetMapping("Query/{id}")
    public R Query(@PathVariable String id){
        EduTeacher byId = eduTeacherService.getById(id);
        return R.ok().data("byid",byId);
    }

//    添加
    @PostMapping("Add")
    public R Add(@RequestBody EduTeacher eduTeacher){
        eduTeacher.setGmtCreate(new Date());
        eduTeacher.setGmtModified(new Date());
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
