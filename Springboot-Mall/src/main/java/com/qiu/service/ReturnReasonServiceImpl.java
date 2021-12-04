package com.qiu.service;
import com.qiu.dao.ReturnReasonDao;
import com.qiu.entity.ReturnReason;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author Qiu
 * @email qiudb.top@aliyun.com
 * @date 2020/11/24 21:06
 * @description 退货原因 service 实现类
 */
@Service
@Transactional
public class ReturnReasonServiceImpl implements ReturnReasonService {
    private final ReturnReasonDao returnReasonDao;
    public ReturnReasonServiceImpl(ReturnReasonDao returnReasonDao) {
        this.returnReasonDao = returnReasonDao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ReturnReason selectById(Integer reasonId) {
        return returnReasonDao.selectById(reasonId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ReturnReason> selectAll() {
        return returnReasonDao.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> selectAllName() {
        return returnReasonDao.selectAllName();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Boolean existsWithReasonName(Integer reasonId,String reasonName) {
        return returnReasonDao.existsWithReasonName(reasonId,reasonName);
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
