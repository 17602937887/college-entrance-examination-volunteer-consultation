/*
 * Copyright (c) 2021 hangcc.cn
 * All rights reserved.
 *
 */
package cn.hangcc.collegeentranceexaminationvolunteerconsultation.provider.controller;

import cn.hangcc.collegeentranceexaminationvolunteerconsultation.biz.VolunteerBiz;
import cn.hangcc.collegeentranceexaminationvolunteerconsultation.common.response.Paging;
import cn.hangcc.collegeentranceexaminationvolunteerconsultation.common.response.PagingResponse;
import cn.hangcc.collegeentranceexaminationvolunteerconsultation.domain.model.ObtainingScoreDataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cn.hangcc.collegeentranceexaminationvolunteerconsultation.service.converter.ObtainingScoreDataModelConverter.*;

/**
 * 志愿相关对外提供Http接口
 *
 * @author chenhang
 * @created 2021/5/5
 */
@Slf4j
@RestController
@RequestMapping("/api/volunteer/")
public class VolunteerController {

    @Resource
    private VolunteerBiz volunteerBiz;

    @GetMapping("recommend.json")
    public PagingResponse recommend(@RequestParam("score") Integer score,
                                    @RequestParam("offset") Integer offset,
                                    @RequestParam("limit") Integer limit) {
        // 参数校验
        if (score == null || score < 0) {
            return PagingResponse.buildFailure("请求参数错误");
        }
        try {
            List<ObtainingScoreDataModel> recommendList = volunteerBiz.allRecommend(score, offset, limit);
            int totalRecord = volunteerBiz.getRecommendTotalRecord(score);
            return PagingResponse.buildSuccess(
                    convertToVolunteerRecommendVOs(recommendList),
                    new Paging(offset, limit, totalRecord, totalRecord > limit + offset));
        } catch (Exception e) {
            log.error("VolunteerController.pageData | 推荐志愿时出现异常, score:{}, e=", score, e);
            return PagingResponse.buildFailure(e.getMessage());
        }
    }
}