/*
 * Copyright (c) 2021 hangcc.cn
 * All rights reserved.
 *
 */
package cn.hangcc.collegeentranceexaminationvolunteerconsultation.biz;

import cn.hangcc.collegeentranceexaminationvolunteerconsultation.domain.model.ObtainingScoreDataModel;
import cn.hangcc.collegeentranceexaminationvolunteerconsultation.service.ObtainingScoreDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 录取分数相关biz层
 *
 * @author chenhang
 * @created 2021/5/5
 */
@Service
public class ObtainingScoreBiz {

    @Resource
    private ObtainingScoreDataService obtainingScoreDataService;

    public List<ObtainingScoreDataModel> pageData(Integer offset, Integer limit) {
        List<ObtainingScoreDataModel> dataList = obtainingScoreDataService.pageData(offset, limit);
        Map<String, ObtainingScoreDataModel> lowestRecommendList = dataList.stream().collect(Collectors.toMap(
                ObtainingScoreDataModel::difference,
                Function.identity(),
                (o1, o2) -> {
                    return o1.getLowestScore() < o2.getLowestScore() ? o1 : o2;
                }
        ));
        return new ArrayList<>(lowestRecommendList.values());
    }

    public Integer getTotalData() {
        return obtainingScoreDataService.totalPageData();
    }
}