package com.facebook.Share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by Administrator on 2018/12/3.
 */

public class FacebookShare {
    private int shareType = 0;
    private Activity mainActivity;
    private CallbackManager callbackManagerfx;
    private CallbackManager callbackManagerfxPic;
    private CallbackManager callbackManagerFriend;
    private CallbackManager callbackManageryq;
    private ShareDialog shareDialogfx;
    private ShareDialog shareDialogfxPic;
    private ShareDialog shareDialogfriend;
    private AppInviteDialog appInviteDialog;
    private FacebookShareListener shareListener;
    private FacebookShareAppListener shareAppListener;
    public FacebookShare(Activity activity)
    {
        mainActivity = activity;
        init();
    }
    private void init()
    {

        callbackManagerfx = CallbackManager.Factory.create();
        shareDialogfx = new ShareDialog(mainActivity);
        shareDialogfx.registerCallback(callbackManagerfx,
                new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        shareListener.ShareSucces(result);
                    }
                    @Override
                    public void onCancel() {
                        shareListener.ShareCancel();
                    }
                    @Override
                    public void onError(FacebookException error) {
                        shareListener.ShareError(error);
                    }
                });

        callbackManagerfxPic = CallbackManager.Factory.create();
        shareDialogfxPic = new ShareDialog(mainActivity);
        shareDialogfxPic.registerCallback(callbackManagerfxPic,
                new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        shareListener.ShareSucces(result);
                    }
                    @Override
                    public void onCancel() {
                        shareListener.ShareCancel();
                    }
                    @Override
                    public void onError(FacebookException error) {
                        shareListener.ShareError(error);
                    }
                });

        callbackManagerFriend = CallbackManager.Factory.create();
        shareDialogfriend = new ShareDialog(mainActivity);
        shareDialogfriend.registerCallback(callbackManagerFriend,new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        shareListener.ShareSucces(result);
                    }
                    @Override
                    public void onCancel() {
                        shareListener.ShareCancel();
                    }
                    @Override
                    public void onError(FacebookException error) {
                        shareListener.ShareError(error);
                    }
                });

        appInviteDialog = new AppInviteDialog(mainActivity);
        callbackManageryq = CallbackManager.Factory.create();
        appInviteDialog.registerCallback(callbackManageryq, new FacebookCallback<AppInviteDialog.Result>(){
                    @Override
                    public void onSuccess(AppInviteDialog.Result result)
                    {
                        shareAppListener.ShareSucces(result);
                    }
                    @Override
                    public void onCancel()
                    {
                        shareAppListener.ShareCancel();
                    }
                    @Override
                    public void onError(FacebookException e)
                    {
                        shareAppListener.ShareError(e);
                    }
                });
    }
    public void Share(String contentUrl,FacebookShareListener sListener)
    {
        shareType = 1;
        this.shareListener = sListener;
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(contentUrl))
                .build();
        shareDialogfx.show(linkContent);
    }
    public void Share(byte[] arg,FacebookShareListener sListener)
    {
        shareType = 2;
        this.shareListener = sListener;
        Bitmap image = BitmapFactory.decodeByteArray(arg, 0, arg.length);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        if (shareDialogfxPic.canShow(content))
            shareDialogfxPic.show(content, ShareDialog.Mode.AUTOMATIC);
        else
            shareListener.ShareShowError();
    }
    public void ShareFriend(byte[] arg,FacebookShareListener sfListener) {
        shareType = 3;
        this.shareListener = sfListener;
        Bitmap image = BitmapFactory.decodeByteArray(arg, 0, arg.length);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        if (shareDialogfriend.canShow(content))
            shareDialogfriend.show(content);
        else
            shareListener.ShareShowError();
    }
    public void ShareInvitation(String appUrl,String imgUrl,FacebookShareAppListener sfListener) {
        shareType = 4;
        this.shareAppListener = sfListener;
        AppInviteContent content = new AppInviteContent.Builder()
                .setApplinkUrl(imgUrl)
                .setPreviewImageUrl(appUrl)
                .build();
        appInviteDialog.show(content);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (shareType)
        {
            case 1:
                callbackManagerfx.onActivityResult(requestCode, resultCode, data);
                break;
            case 2:
                callbackManagerfxPic.onActivityResult(requestCode, resultCode, data);
                break;
            case 3:
                callbackManagerFriend.onActivityResult(requestCode, resultCode, data);
                break;
            case 4:
                callbackManageryq.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
