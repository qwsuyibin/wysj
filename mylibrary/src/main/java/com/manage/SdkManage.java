package com.manage;

import android.app.Activity;
import android.content.Intent;

import com.facebook.Share.FacebookShare;
import com.facebook.logger.FacebookLogger;
import com.facebook.login.FacebookLogin;
import com.google.pay.GooglePay;
import com.google.pay.GooglePayListener;
import com.kochava.KochavaLogger;

/**
 * Created by Administrator on 2018/11/30.
 */

public class SdkManage {
    private Activity mainActivity;
    private GooglePay googlePay;
    private FacebookLogin facebookLogin;
    private FacebookLogger facebookLogger;
    private FacebookShare facebookShare;
    private KochavaLogger kochavaLogger;
    public SdkManage(Activity activity)
    {
        mainActivity = activity;
    }
    public void init()
    {
        googlePay = new GooglePay(mainActivity);
        facebookLogin = new FacebookLogin(mainActivity);
        facebookLogger = new FacebookLogger(mainActivity);
        facebookShare = new FacebookShare(mainActivity);
        kochavaLogger = new KochavaLogger(mainActivity);
    }
    /**
     * GooglePay
     * 返回谷歌充值带充值(Pay)
     * @return FacebookShare
     */
    public GooglePay getGooglePay()
    {
        return googlePay;
    }
    /**
     * FaceBookLogin
     * 返回登录类 带登录（Login）和退出登录方法（LoginOut）
     * @return FacebookLogin
     */
    public FacebookLogin getFaceBookLogin()
    {
        return facebookLogin;
    }
    /**
     * FacebookLogger
     * 返回脸书记录
     * @return FacebookLogger
     */
    public FacebookLogger getFaceBookLogger()
    {
        return facebookLogger;
    }
    /**
     * FacebookShare
     * 返回脸书分享
     * @return FacebookShare
     */
    public FacebookShare getFaceBookShare()
    {
        return facebookShare;
    }
    /**
     * KochavaLogger
     * 返回Kochava记录
     * @return KochavaLogger
     */
    public KochavaLogger getKochavaLogger()
    {
        return kochavaLogger;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(googlePay != null)
            googlePay.onActivityResult(requestCode,resultCode,data);
        if(facebookLogin != null)
            facebookLogin.onActivityResult(requestCode,resultCode,data);
    }
}
