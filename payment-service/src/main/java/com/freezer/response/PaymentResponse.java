/**
 * @FileName: PaymentResponse
 * @Author: zzc
 * @Date: 2019年12月30日 20:49:41
 * @Version V1.0.0
 */

package com.freezer.response;

import freezer.response.Response;

public class PaymentResponse extends Response {
    public PaymentResponse() {
    }

    public PaymentResponse(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public static final Response BALANCE_NOT_ENOUGH = new Response("1003", "your balance is not enough");
    public static final Response DEDUCTION_ABNORMAL = new Response("1004", "this trade has some problems, please contact wei970806@163.com");
}
