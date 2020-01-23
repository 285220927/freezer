/**
 * @FileName: GoodsService
 * @Author: zzc
 * @Date: 2019年12月28日 18:37:33
 * @Version V1.0.0
 */

package com.freezer.service;

import com.freezer.dao.GoodsDao;
import freezer.dto.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public List<Goods> findGoods() {
        return goodsDao.findGoods();
    }
}
