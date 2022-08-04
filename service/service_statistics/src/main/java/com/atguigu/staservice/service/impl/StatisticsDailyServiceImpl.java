package com.atguigu.staservice.service.impl;

import com.atguigu.common_utils.R;
import com.atguigu.staservice.client.UcenterClient;
import com.atguigu.staservice.entity.StatisticsDaily;
import com.atguigu.staservice.mapper.StatisticsDailyMapper;
import com.atguigu.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-08-03
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
//    注入Client 实现远程调用
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void registerCount(String day) {
/**
 * 每次查询时候数据库都会添加更新一条最新的数据，
 * 细节上完善
 * 添加记录之前，先删除表相同日期的数据
 */
        QueryWrapper<StatisticsDaily> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
        baseMapper.delete(queryWrapper);

        R register=ucenterClient.countRegister(day);
        Integer countRegister = (Integer) register.getData().get("countRegister");

//        把获取数据添加数据库，统计分析表里面
        StatisticsDaily statisticsDaily =new StatisticsDaily();
        statisticsDaily.setRegisterNum(countRegister);//注册人数
        statisticsDaily.setDateCalculated(day);//统计日期
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        baseMapper.insert(statisticsDaily);
    }


    //    图表显示，返回两部分数据，日期json数组，数量json数组
    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
//       根据条件查询对应的数据
        QueryWrapper<StatisticsDaily> wrapper =new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);
        /**
         * 返回两部分数据，日期和日期对应的数量
         * 前端要求数组json结构，对应后端Java代码是list集合
         */
        List<String> date_calculatedList =new ArrayList<>();
        ArrayList<Integer> numDataList = new ArrayList<>();
//        遍历查询所有数据list集合，进行封装
        for (int i = 0; i < statisticsDailies.size(); i++) {
//            得到每一个
            StatisticsDaily Daily = statisticsDailies.get(i);
//            封装日期list集合
           date_calculatedList.add(Daily.getDateCalculated());
//           封装对应的数量
            switch(type){
                case "login_num":
                    numDataList.add(Daily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(Daily.getRegisterNum());
                break;
                case "video_view_num":
                    numDataList.add(Daily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(Daily.getCourseNum());
                    break;
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("date_calculatedList",date_calculatedList);
        map.put("numDataList",numDataList);
        return map;
    }
}
