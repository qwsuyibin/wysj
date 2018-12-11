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
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
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
    private ShareDialog shareDialogfx;
    private ShareDialog shareDialogfxPic;
    private ShareDialog shareDialogfriend;
    private FacebookShareListener shareListener;
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
                        shareType = 0;
                        shareListener.ShareSucces(result);
                    }
                    @Override
                    public void onCancel() {
                        shareType = 0;
                        shareListener.ShareCancel();
                    }
                    @Override
                    public void onError(FacebookException error) {
                        shareType = 0;
                        shareListener.ShareError(error);
                    }
                });

        callbackManagerfxPic = CallbackManager.Factory.create();
        shareDialogfxPic = new ShareDialog(mainActivity);
        shareDialogfxPic.registerCallback(callbackManagerfxPic,
                new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        shareType = 0;
                        shareListener.ShareSucces(result);
                    }
                    @Override
                    public void onCancel() {
                        shareType = 0;
                        shareListener.ShareCancel();
                    }
                    @Override
                    public void onError(FacebookException error) {
                        shareType = 0;
                        shareListener.ShareError(error);
                    }
                });

        callbackManagerFriend = CallbackManager.Factory.create();
        shareDialogfriend = new ShareDialog(mainActivity);
        shareDialogfriend.registerCallback(callbackManagerFriend,new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        shareType = 0;
                        shareListener.ShareSucces(result);
                    }
                    @Override
                    public void onCancel() {
                        shareType = 0;
                        shareListener.ShareCancel();
                    }
                    @Override
                    public void onError(FacebookException error) {
                        shareType = 0;
                        shareListener.ShareError(error);
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
        else {
            shareType = 0;
            shareListener.ShareShowError();
        }
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
        else {
            shareType = 0;
            shareListener.ShareShowError();
        }
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
        }
    }
}
