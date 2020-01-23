/**
 * @FileName: Utils
 * @Author: zzc
 * @Date: 2019年12月28日 14:49:48
 * @Version V1.0.0
 */

package freezer.utils;

import java.util.UUID;

public class Utils {

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
