/**
 * @FileName: WxUser
 * @Author: zzc
 * @Date: 2019年12月30日 20:24:30
 * @Version V1.0.0
 */

package freezer.dto;

import java.io.Serializable;
import java.util.Date;

public class WxUser implements Serializable {
    private int id;
    private int userId;
    private float account;
    private Date createTime;
    private Date lastLogin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getAccount() {
        return account;
    }

    public void setAccount(float account) {
        this.account = account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "WxUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", account=" + account +
                ", createTime=" + createTime +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
