package com.reactnativetiktok;

import android.content.Intent;
import android.os.Bundle;

import com.bytedance.sdk.open.tiktok.TikTokOpenApiFactory;
import com.bytedance.sdk.open.tiktok.api.TikTokOpenApi;
import com.bytedance.sdk.open.tiktok.authorize.model.Authorization;
import com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler;
import com.bytedance.sdk.open.tiktok.common.model.BaseReq;
import com.bytedance.sdk.open.tiktok.common.model.BaseResp;
import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class TikTokEntryActivity extends ReactActivity implements IApiEventHandler {
  TikTokOpenApi ttOpenApi;

  public void onReq(BaseReq baseReq){
  }

  public void onCreate(Bundle bundle){
    super.onCreate(bundle);
    TikTokOpenApi create = TikTokOpenApiFactory.create(this);
    this.ttOpenApi = create;
    create.handleIntent(getIntent(), this);
  }

  public void onResp(BaseResp baseResp){
    if(baseResp instanceof Authorization.Response){
      Authorization.Response response = (Authorization.Response) baseResp;
      WritableMap params = Arguments.createMap();
      params.putInt("status", response.errorCode);
      params.putString("code", response.authCode);
      ReactContext ctx = getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
      if(ctx != null){
        ctx.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onAuthCompleted", params);
      }
      finish();
    }
  }

  public void onErrorIntent(Intent intent){
    finish();
  }
}
