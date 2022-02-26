package com.qiu.service;

import com.qiu.entity.ReturnReason;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/24 21:04
 * @description 退货原因业务逻辑
 */
public interface ReturnReasonService {
    /**
     * 根据ID查询
     *
     * @param reasonId 退货原因ID
     * @return 退货原因
     */
    ReturnReason selectById(Integer reasonId);

    /**
     * 查询所有退货原因
     *
     * @return 退货原因
     */
    List<ReturnReason> selectAll();

    /**
     * 查询所有退货原因名称
     *
     * @return 退货原因
     */
    List<String> selectAllName();

    /**
     * 查询退货原因名称是否存在
     *
     * @param reasonId   退货原因ID
     * @param reasonName 退货原因
     * @return 是否存在
     */
    Boolean existsWithReasonName(Integer reasonId, String reasonName);

    /**
     * 新增退货原因
     *
     * @param returnReason 退货原因
     * @return 是否新增成功
     */
    Boolean insertData(ReturnReason returnReason);

    /**
     * 更新退货原因
     *
     * @param returnReason 退货原因
     * @return 是否更新成功
     */
    Boolean updateById(ReturnReason returnReason);

    /**
     * 删除退货原因
     *
     * @param reasonId 退货原因
     * @return 是否删除成功
     */
    Boolean deleteById(Integer reasonId);
}
