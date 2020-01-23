/**
 * @FileName: GoodsController
 * @Author: zzc
 * @Date: 2019年12月28日 18:33:49
 * @Version V1.0.0
 */

package com.freezer.controller;

import com.freezer.service.GoodsService;
import freezer.dto.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/goods")
@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("")
    public List<Goods> findGoods() {
        return goodsService.findGoods();
    }
}
