/*
 * Copyright (c) 2021 hangcc.cn
 * All rights reserved.
 *
 */
package cn.hangcc.collegeentranceexaminationvolunteerconsultation.biz;

import cn.hangcc.collegeentranceexaminationvolunteerconsultation.domain.model.ObtainingScoreDataModel;
import cn.hangcc.collegeentranceexaminationvolunteerconsultation.service.ObtainingScoreDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 志愿服务biz层
 *
 * @author chenhang
 * @created 2021/5/5
 */
@Slf4j
@Service
public class VolunteerBiz {

    @Resource
    private ObtainingScoreDataService obtainingScoreDataService;

    /**
     * 根据用户分数进行推荐
     * @param score 用户成绩
     * @param limit 分页参数
     * @param offset 分页参数
     * @return 符合用户条件的List集合
     */
    public List<ObtainingScoreDataModel> allRecommend(Integer score, Integer offset, Integer limit) {
        try {
            // 获取符合条件的所有数据集合
            List<ObtainingScoreDataModel> recommendList = obtainingScoreDataService.allRecommend(score, offset, limit);
            Map<String, ObtainingScoreDataModel> lowestRecommendList = recommendList.stream().collect(Collectors.toMap(
                    ObtainingScoreDataModel::difference,
                    Function.identity(),
                    (o1, o2) -> {
                        return o1.getLowestScore() < o2.getLowestScore() ? o1 : o2;
                    }
            ));
            return new ArrayList<>(lowestRecommendList.values());
        } catch (Exception e) {
            log.error("VolunteerBiz.pageData | 推荐志愿Biz获取数据时出现异常, score:{}, e=", score, e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public Integer getRecommendTotalRecord(Integer score) {
        try {
            // 获取符合条件的所有数据集合
            List<ObtainingScoreDataModel> recommendList = obtainingScoreDataService.getRecommendTotalRecord(score);
            Map<String, ObtainingScoreDataModel> lowestRecommendList = recommendList.stream().collect(Collectors.toMap(
                    ObtainingScoreDataModel::difference,
                    Function.identity(),
                    (o1, o2) -> {
                        return o1.getLowestScore() < o2.getLowestScore() ? o1 : o2;
                    }
            ));
            return lowestRecommendList.values().size();
        } catch (Exception e) {
            log.error("VolunteerBiz.pageData | 推荐志愿Biz获取数据时出现异常, score:{}, e=", score, e);
            throw new RuntimeException(e.getMessage());
        }
    }
}