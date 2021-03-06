package com.huashitu.liveradio.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.apkfuns.logutils.LogUtils;
import com.huashitu.liveradio.R;
import com.huashitu.liveradio.api.AppUtil;
import com.huashitu.liveradio.bean.LoginBean;
import com.huashitu.liveradio.widget.BaseToast;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.FormatUtils;
import com.midian.base.util.UIHelper;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 */
public class Activity_Login extends BaseActivity {

    @BindView(R.id.et_Phone)
    EditText etPhone;
    @BindView(R.id.et_Pass)
    EditText etPass;
    @BindView(R.id.btn_Login)
    Button btnLogin;
    @BindView(R.id.tv_Register)
    TextView tvRegister;
    @BindView(R.id.tv_ForgetPass)
    TextView tvForgetPass;
    @BindView(R.id.iv_WeChat)
    ImageView ivWeChat;
    @BindView(R.id.iv_QQ)
    ImageView ivQQ;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    private String phone;
    private String pass;
    private UMShareAPI mShareAPI = null;
    private SHARE_MEDIA platform = null;
    private String uid, thirdname, thirdhead, authType,sex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ac.isUserIdExsit()){
            UIHelper.jump(_activity,Activity_Main.class);
            return;
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mShareAPI = UMShareAPI.get( this );
    }

    @OnClick({R.id.btn_Login, R.id.tv_ForgetPass,R.id.tv_Register, R.id.iv_WeChat, R.id.iv_QQ})
    public void onClick(View view) {
        pass=etPass.getText().toString();
        phone=etPhone.getText().toString();
        switch (view.getId()) {
            case R.id.btn_Login:
                if(ifAvaliable()){
                    AppUtil.getApiClient(ac).login(phone, pass, this);
                }
                break;
            case R.id.tv_ForgetPass:
                break;
            case R.id.tv_Register:
                UIHelper.jump(_activity,Activity_Register.class);
                break;
            case R.id.iv_WeChat:
                platform=SHARE_MEDIA.WEIXIN;
                mShareAPI.getPlatformInfo(_activity, platform, umAuthListener);
                break;
            case R.id.iv_QQ:
                platform=SHARE_MEDIA.QQ;
                mShareAPI.getPlatformInfo(_activity, platform, umAuthListener);
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            LoginBean bean = (LoginBean) res;
//            ac.saveUserInfo();
            ac.setProperty("user_id", "0");
            UIHelper.jump(_activity,Activity_Main.class);
        }else{
            AnimatorUtils.onVibrationView(btnLogin);
            ac.handleErrorCode(_activity,res.getRet_info());
        }
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            LogUtils.e(data);
            uid=data.get("unionid");
            thirdname=data.get("screen_name");
            thirdhead=data.get("profile_image_url");
            sex=data.get("gender");
            System.out.println("nickName" + thirdname + "headurl"
                    + thirdhead);
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
        }
    };

    private boolean ifAvaliable() {
        if("".equals(phone)){
            AnimatorUtils.onVibrationView(etPhone);
            BaseToast.show(_activity,"请输入手机号");
            return false;
        }
        if(!FormatUtils.isMobile(phone)){
            AnimatorUtils.onVibrationView(etPhone);
            BaseToast.show(_activity,"手机号格式不正确");
            return false;
        }
        if("".equals(pass)){
            AnimatorUtils.onVibrationView(etPass);
            BaseToast.show(_activity,"请输入密码");
            return false;
        }
        return true;
    }
}
