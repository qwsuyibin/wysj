package com.facebook.login;
import com.facebook.AccessToken;
import com.facebook.FacebookException;

/**
 * Created by Administrator on 2018/11/30.
 */

public interface FacebookLoginListener {
    void LoginSucces(AccessToken account);
    void LoginCancel();
    void LoginError(FacebookException error);
}
