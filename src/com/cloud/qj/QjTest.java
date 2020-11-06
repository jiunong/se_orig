package com.cloud.qj;

import com.bocom.pay.BocomClient;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2020/11/6 12:29
 */
public class QjTest {


    public static void main(String[] args) {
        BocomClient client = new BocomClient();
        client.initialize("");
        client.AttachedSign("","");
    }

}
