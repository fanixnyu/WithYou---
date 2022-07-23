package com.atguigu.eduservice.listener;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Date;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    public EduSubjectService eduSubjectService;
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExcelListener() {
    }



//    判断一级分类不能重复添加(最终要查询数据库，所以把eduSubjectService 引入过来；第二个参数：一级分类名称)
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String FirstLevelName){
//       先查询数据库，再调用这个方法进行判断
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("title",FirstLevelName);
        queryWrapper.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        return one;
    }


//    判断二级分类不能重复添加(最终要查询数据库，所以把eduSubjectService 引入过来；第1个参数：2级分类名称,)
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String TwoName,String parentId){
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("title",TwoName);
        queryWrapper.eq("parent_id",parentId);
        EduSubject Two = eduSubjectService.getOne(queryWrapper);
        return Two;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData==null){
            throw new GuliException(20001,"文件数据为空");
        }
//        调用方法判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (existOneSubject==null){//没有相同一级分类，进行添加
            existOneSubject =new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            /**
             * 实体注解没生效，自己new date
             * @TableField(fill = FieldFill.INSERT)
             * @TableField(fill = FieldFill.INSERT_UPDATE)
             */
            existOneSubject.setGmtCreate(new Date());
            existOneSubject.setGmtModified(new Date());
            eduSubjectService.save(existOneSubject);
        }


//判断二级分类及是否重复
       String parentId = existOneSubject.getId();//获取一级分类id
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(),parentId);
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(parentId);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            existTwoSubject.setGmtCreate(new Date());
            existTwoSubject.setGmtModified(new Date());
            eduSubjectService.save(existTwoSubject);
        }
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
