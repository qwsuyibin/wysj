package com.facebook.Share;

import com.facebook.FacebookException;
import com.facebook.share.widget.AppInviteDialog;

/**
 * Created by Administrator on 2018/12/3.
 */

public interface FacebookShareAppListener {
    void ShareSucces(AppInviteDialog.Result result);
    void ShareCancel();
    void ShareError(FacebookException error);
    void ShareShowError();
}
