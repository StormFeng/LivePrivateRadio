package com.huashitu.liveradio.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.huashitu.liveradio.R;
import com.huashitu.liveradio.widget.BaseToast;
import com.midian.base.base.BaseActivity;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.FormatUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册
 * Created by Administrator on 2016/11/10 0010.
 */

public class Activity_Register extends BaseActivity {
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.et_Phone)
    EditText etPhone;
    @BindView(R.id.et_Code)
    EditText etCode;
    @BindView(R.id.btn_Code)
    Button btnCode;
    @BindView(R.id.et_Pass)
    EditText etPass;
    @BindView(R.id.btn_Register)
    Button btnRegister;

    private String phone;
    private String pass;
    private String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_Code, R.id.btn_Register})
    public void onClick(View view) {
        pass=etPass.getText().toString();
        phone=etPhone.getText().toString();
        code=etCode.getText().toString();
        switch (view.getId()) {
            case R.id.btn_Code:
                ifPhoneRight();
                break;
            case R.id.btn_Register:
                ifAvaliable();
                break;
        }
    }

    private void ifPhoneRight(){
        if("".equals(phone)){
            AnimatorUtils.onVibrationView(etPhone);
            BaseToast.show(_activity,"请输入手机号");
            return;
        }
        if(!FormatUtils.isMobile(phone)){
            AnimatorUtils.onVibrationView(etPhone);
            BaseToast.show(_activity,"手机号格式不正确");
            return;
        }
    }

    private void ifAvaliable() {
        if("".equals(phone)){
            AnimatorUtils.onVibrationView(etPhone);
            BaseToast.show(_activity,"请输入手机号");
            return;
        }
        if(!FormatUtils.isMobile(phone)){
            AnimatorUtils.onVibrationView(etPhone);
            BaseToast.show(_activity,"手机号格式不正确");
            return;
        }
        if("".equals(code)){
            AnimatorUtils.onVibrationView(etCode);
            BaseToast.show(_activity,"请输入验证码");
            return;
        }
        if("".equals(pass)){
            AnimatorUtils.onVibrationView(etPass);
            BaseToast.show(_activity,"请输入密码");
            return;
        }
    }
}
