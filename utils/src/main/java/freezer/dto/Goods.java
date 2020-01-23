/**
 * @FileName: Goods
 * @Author: zzc
 * @Date: 2019年12月28日 18:38:01
 * @Version V1.0.0
 */

package freezer.dto;

import java.io.Serializable;

public class Goods implements Serializable {
    private int id;
    private String goodsName;
    private float price;
    private GoodsCategory goodsCategory;
    private int stock;
    private int seller;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", goodsCategory=" + goodsCategory +
                ", stock=" + stock +
                ", seller=" + seller +
                '}';
    }
}
