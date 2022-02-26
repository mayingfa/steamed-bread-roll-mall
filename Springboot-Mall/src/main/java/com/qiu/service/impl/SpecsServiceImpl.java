package com.qiu.service.impl;

import com.qiu.dao.SpecsDao;
import com.qiu.entity.Specs;
import com.qiu.service.SpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Captain
 * @email qiudb.top@aliyun.com
 * @date 2020/11/17 16:21
 * @description 商品规格业务逻辑
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecsServiceImpl implements SpecsService {
    @Autowired
    private SpecsDao specsDao;

    @Override
    public Specs selectById(Integer specsId) {
        return specsDao.selectById(specsId);
    }

    @Override
    public List<Specs> selectAll() {
        return specsDao.selectAll();
    }

    @Override
    public List<Specs> selectAllByType(String productType) {
        return specsDao.selectAllByType(productType);
    }

    @Override
    public Boolean insertData(Specs specs) {
        return specsDao.insertData(specs);
    }

    @Override
    public Boolean updateById(Specs specs) {
        return specsDao.updateById(specs);
    }

    @Override
    public Boolean deleteById(Integer specsId) {
        return specsDao.deleteById(specsId);
    }

    @Override
    public Boolean existsWithSpecsName(Integer specsId, String specsName, String productType) {
        return specsDao.existsWithSpecsName(specsId, specsName, productType);
    }
}
