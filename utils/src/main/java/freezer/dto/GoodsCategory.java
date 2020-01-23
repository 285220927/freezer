/**
 * @FileName: GoodsCategory
 * @Author: zzc
 * @Date: 2019年12月28日 18:49:36
 * @Version V1.0.0
 */

package freezer.dto;

import java.io.Serializable;

public class GoodsCategory implements Serializable {
    private int id;
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "GoodsCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
