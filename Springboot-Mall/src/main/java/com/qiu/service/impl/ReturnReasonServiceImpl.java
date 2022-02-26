package com.qiu.service.impl;

import com.qiu.dao.ReturnReasonDao;
import com.qiu.entity.ReturnReason;
import com.qiu.service.ReturnReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/24 21:06
 * @description 退货原因业务逻辑
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnReasonServiceImpl implements ReturnReasonService {
    @Autowired
    private ReturnReasonDao returnReasonDao;

    @Override
    public ReturnReason selectById(Integer reasonId) {
        return returnReasonDao.selectById(reasonId);
    }

    @Override
    public List<ReturnReason> selectAll() {
        return returnReasonDao.selectAll();
    }

    @Override
    public List<String> selectAllName() {
        return returnReasonDao.selectAllName();
    }

    @Override
    public Boolean existsWithReasonName(Integer reasonId, String reasonName) {
        return returnReasonDao.existsWithReasonName(reasonId, reasonName);
    }

    @Override
    public Boolean insertData(ReturnReason returnReason) {
        return returnReasonDao.insertData(returnReason);
    }

    @Override
    public Boolean updateById(ReturnReason returnReason) {
        return returnReasonDao.updateById(returnReason);
    }

    @Override
    public Boolean deleteById(Integer reasonId) {
        return returnReasonDao.deleteById(reasonId);
    }
}
