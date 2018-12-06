package com.kochava;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.kochava.base.Tracker;

/**
 * Created by Administrator on 2018/12/5.
 */

public class KochavaLogger {
    private String TAG = "KochavaLogger";
    private String kochavakey;
    private Activity mainActivity;
    public KochavaLogger(Activity activity) {
        mainActivity = activity;
        init();
    }
    private void init()
    {
        ApplicationInfo ai = null;
        try {
            ai = mainActivity.getPackageManager().getApplicationInfo(mainActivity.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            kochavakey = bundle.getString("kochavakey");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if(kochavakey == null)
        {
            Log.i(TAG,"kochavakey未配置");
            return;
        }
        Tracker.configure(new Tracker.Configuration(mainActivity.getApplicationContext())
                .setAppGuid(kochavakey)
                .setLogLevel(Tracker.LOG_LEVEL_INFO));
    }
    //游戏中常用的
    public void SendTracker(String name)
    {
        Tracker.sendEvent(new Tracker.Event(name));
    }
    public void SendTracker(String name,String uid)
    {
        Tracker.sendEvent(new Tracker.Event(name).setUserId(uid));
    }
    public void SendTracker(String name,String uid,String pmoney,String currency)
    {
        Tracker.sendEvent(new Tracker.Event(name).setUserId(uid).setPrice(Double.valueOf(pmoney)).setCurrency(currency));
    }
    public void SendTrackerAddMoney(String name,String uid,String pmoney,String currency)
    {
        Tracker.sendEvent(new Tracker.Event(name).setUserId(uid).setPrice(Double.valueOf(pmoney)).addCustom("revenue",pmoney).setCurrency(currency));
    }
}
