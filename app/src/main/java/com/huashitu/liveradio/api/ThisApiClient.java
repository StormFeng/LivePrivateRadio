package com.huashitu.liveradio.api;

import com.huashitu.liveradio.bean.LoginBean;
import com.huashitu.liveradio.bean.RegisterBean;
import com.huashitu.liveradio.bean.SendCodeBean;
import com.midian.base.afinal.http.AjaxParams;
import com.midian.base.api.ApiCallback;
import com.midian.base.api.BaseApiClient;
import com.midian.base.app.AppContext;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class ThisApiClient extends BaseApiClient {

    public ThisApiClient(AppContext ac) {
        super(ac);
    }
    static public void init(AppContext appcontext) {
        if (appcontext == null)
            return;
        appcontext.api.addApiClient(new ThisApiClient(appcontext));
    }

    /**
     * 注册
     * @param phone
     * @param password
     * @param callback
     */
    public void login(String phone,String password,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("clientKey", ac.getClientKey());
        params.put("phone", phone);
        params.put("deviceToken", ac.device_token);
        params.put("password", password);

        post(callback,Constant.LOGIN, params, LoginBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 注册
     * @param phone
     * @param password
     * @param code
     * @param callback
     */
    public void register(String phone,String password,String code,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("clientKey", ac.getClientKey());
        params.put("phone", phone);
        params.put("deviceToken", ac.device_token);
        params.put("password", password);
        params.put("code", code);

        post(callback,Constant.REGISTER, params, RegisterBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    public void sendCode(String phone,String password,String type,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("clientKey", ac.getClientKey());
        params.put("phone", phone);
        params.put("password", password);
        params.put("type", type);

        post(callback,Constant.SENDCODE, params, SendCodeBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

}