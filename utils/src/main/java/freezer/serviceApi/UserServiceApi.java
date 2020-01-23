/**
 * @FileName: UserServiceApi
 * @Author: zzc
 * @Date: 2019年12月29日 13:00:48
 * @Version V1.0.0
 */

package freezer.serviceApi;

import freezer.dto.User;
import freezer.dto.WxUser;

public interface UserServiceApi {
    User findUserByCookie(String userCookieString);

    WxUser findWxUserByUserId(int userId);
}
