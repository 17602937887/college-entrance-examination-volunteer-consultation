package cn.hangcc.collegeentranceexaminationvolunteerconsultation.service;

import cn.hangcc.collegeentranceexaminationvolunteerconsultation.domain.model.ObtainingScoreDataModel;

import java.util.List;

/**
 * (ObtainingScoreDataModel)表服务接口
 *
 * @author chenhang
 * @since 2021-02-27 13:58:43
 */
public interface ObtainingScoreDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ObtainingScoreDataModel queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ObtainingScoreDataModel> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param ObtainingScoreDataModel 实例对象
     * @return 实例对象
     */
    ObtainingScoreDataModel insert(ObtainingScoreDataModel ObtainingScoreDataModel);

    /**
     * 修改数据
     *
     * @param ObtainingScoreDataModel 实例对象
     * @return 实例对象
     */
    ObtainingScoreDataModel update(ObtainingScoreDataModel ObtainingScoreDataModel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 清空表
     */
    void truncateTable();
}