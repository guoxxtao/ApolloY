package com.sunlands.apolloy.baseResultJson;

/**
 * Created with IntelliJ IDEA.
 * User: guotao
 * Date: 2019/11/26
 * Time: 14:26
 */
public class ResultJsonManager {

    private static ThreadLocal<BaseResultJson> resultJsonThreadLocal = new ThreadLocal<>();

    public static BaseResultJson getResultJson() {
        BaseResultJson resultJson = resultJsonThreadLocal.get();
        if (null == resultJson) {
            resultJson = new BaseResultJson();
            resultJsonThreadLocal.set(resultJson);
        }
        return resultJson;
    }

}
