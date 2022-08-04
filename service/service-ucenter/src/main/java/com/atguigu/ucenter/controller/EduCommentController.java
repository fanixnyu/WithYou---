package com.atguigu.ucenter.controller;
import com.atguigu.common_utils.JwtUtils;
import com.atguigu.common_utils.R;
import com.atguigu.ucenter.client.ucenterClient;
import com.atguigu.ucenter.entity.EduComment;
import com.atguigu.ucenter.service.EduCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author withYou
 * @since 2022-07-31
 */
@RestController
@RequestMapping("/educms/eduComment")
public class EduCommentController {
    @Autowired
    private ucenterClient ucenterClient;

    @Autowired
    private EduCommentService eduCommentService;

    @GetMapping("index/{current}/{size}")
    public R index(@PathVariable long current, @PathVariable long size, String courseId){
        Page<EduComment> page =new Page<>(current,size);
        QueryWrapper<EduComment> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        eduCommentService.page(page,queryWrapper);
        List<EduComment> records = page.getRecords();
        Map<String,Object> map =new HashMap<>();
        map.put("records",records);
        map.put("current",page.getCurrent());
        map.put("pages", page.getPages());
        map.put("size", page.getSize());
        map.put("total", page.getTotal());
        map.put("hasNext", page.hasNext());
        map.put("hasPrevious", page.hasPrevious());
        return R.ok().data(map);
    }

//    添加评论
    @PostMapping("auth/save")
    public R save(@RequestBody EduComment eduComment, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)){
            return R.ok().code(20001).message("请登录");
        }
        eduComment.setMemberId(memberId);
        EduComment ucenterInfo = ucenterClient.getUcenterPay(memberId);
        eduComment.setNickname(ucenterInfo.getNickname());
        eduComment.setAvatar(ucenterInfo.getAvatar());
        eduCommentService.save(eduComment);
        return R.ok();
    }
}

