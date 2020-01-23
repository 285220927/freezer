/**
 * @FileName: Coupon
 * @Author: zzc
 * @Date: 2020年01月06日 20:09:55
 * @Version V1.0.0
 */

package freezer.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Coupon implements Serializable {
    // id
    private int id;
    // 标题
    private String title;
    // 描述信息
    private String describe;
    // 生效日期
    private Date effectiveTime;
    // 过期日期
    private Date expireTime;
    // 使用规则 1-满减 2-折扣
    private byte rule;
    // 满足金额条件
    private float moneyCondition;
    // 满减金额
    private float subtractMoney;
    // 折扣
    private float discount;
    // 获取途径 1-积分兑换 2-邀请好友 3-管理员发放 4-其他
    private byte obtainWay;
    // 商品分类
    private GoodsCategory goodsCategory;
    // 除去指定商品分类id外的其他商品id,用逗号分割
    private String otherGoodsId;
    // 减少的钱数(计算最优优惠券时使用)
    private float bestCouponDiscount;

    private List<Goods> otherGoods;
    // 数量
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public byte getRule() {
        return rule;
    }

    public void setRule(byte rule) {
        this.rule = rule;
    }

    public float getMoneyCondition() {
        return moneyCondition;
    }

    public void setMoneyCondition(float moneyCondition) {
        this.moneyCondition = moneyCondition;
    }

    public float getSubtractMoney() {
        return subtractMoney;
    }

    public void setSubtractMoney(float subtractMoney) {
        this.subtractMoney = subtractMoney;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public byte getObtainWay() {
        return obtainWay;
    }

    public void setObtainWay(byte obtainWay) {
        this.obtainWay = obtainWay;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public String getOtherGoodsId() {
        return otherGoodsId;
    }

    public void setOtherGoodsId(String otherGoodsId) {
        this.otherGoodsId = otherGoodsId;
    }

    public List<Goods> getOtherGoods() {
        return otherGoods;
    }

    public void setOtherGoods(List<Goods> otherGoods) {
        this.otherGoods = otherGoods;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getBestCouponDiscount() {
        return bestCouponDiscount;
    }

    public void setBestCouponDiscount(float bestCouponDiscount) {
        this.bestCouponDiscount = bestCouponDiscount;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", describe='" + describe + '\'' +
                ", effectiveTime=" + effectiveTime +
                ", expireTime=" + expireTime +
                ", rule=" + rule +
                ", moneyCondition=" + moneyCondition +
                ", subtractMoney=" + subtractMoney +
                ", discount=" + discount +
                ", obtainWay=" + obtainWay +
                ", goodsCategory=" + goodsCategory +
                ", otherGoodsId='" + otherGoodsId + '\'' +
                ", bestCouponDiscount=" + bestCouponDiscount +
                ", otherGoods=" + otherGoods +
                ", count=" + count +
                '}';
    }
}
