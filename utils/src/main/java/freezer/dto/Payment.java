/**
 * @FileName: Payment
 * @Author: zzc
 * @Date: 2020年01月12日 12:19:13
 * @Version V1.0.0
 */

package freezer.dto;

import java.io.Serializable;

public class Payment implements Serializable {
    // 商品id
    private int goodsId;
    // 购买数量
    private int purchaseCount;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "goodsId=" + goodsId +
                ", purchaseCount=" + purchaseCount +
                '}';
    }
}
