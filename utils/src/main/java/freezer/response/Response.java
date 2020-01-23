/**
 * @FileName: Response
 * @Author: zzc
 * @Date: 2019年12月28日 14:22:10
 * @Version V1.0.0
 */

package freezer.response;

import java.io.Serializable;

public class Response implements Serializable {
    private String responseCode;
    private String responseMessage;
    public static final Response USERNAME_OR_PASSWORD_INVALID = new Response("1002", "username or password invalid");
    public static final Response GOODS_STOCK_NOT_ENOUGH = new Response("1005", "some goods stock is not enough");
    // should return amount of this purchase(float)
    public static final Response SUCCESS = new Response("2000", "");
    public static final Response UNEXPECTED_ERROR = new Response("1006", "some unexpected error occured, please connect with wei970806@163.com");
    public static final Response BALANCE_NOT_ENOUGH = new Response("1007", "balance not enough");

    public Response() {
        this.responseCode = "1001";
        this.responseMessage = "success";
    }

    public Response(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
