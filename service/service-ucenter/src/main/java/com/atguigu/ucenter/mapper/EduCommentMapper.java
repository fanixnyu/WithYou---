package com.atguigu.ucenter.mapper;

import com.atguigu.ucenter.entity.EduComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author withYou
 * @since 2022-07-31
 */

@Mapper
public interface EduCommentMapper extends BaseMapper<EduComment> {

}
