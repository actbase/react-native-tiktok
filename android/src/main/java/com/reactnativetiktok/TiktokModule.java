package com.reactnativetiktok;

import androidx.annotation.NonNull;

import com.bytedance.sdk.open.tiktok.TikTokOpenApiFactory;
import com.bytedance.sdk.open.tiktok.TikTokOpenConfig;
import com.bytedance.sdk.open.tiktok.api.TikTokOpenApi;
import com.bytedance.sdk.open.tiktok.authorize.model.Authorization;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;

import java.util.ArrayList;

@ReactModule(name = TiktokModule.NAME)
public class TiktokModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Tiktok";

  public TiktokModule(ReactApplicationContext reactContext){
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName(){
    return NAME;
  }


  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  public void init(String key){
    TikTokOpenApiFactory.init(new TikTokOpenConfig(key));
  }

  @ReactMethod
  public void auth(ReadableArray scopes, Callback callBack){
    TikTokOpenApi tiktokOpenApi = TikTokOpenApiFactory.create(getCurrentActivity());
    Authorization.Request request = new Authorization.Request();
    ArrayList<String> scopeList = new ArrayList<>();
    for(Object scope : scopes.toArrayList()){
      scopeList.add(scope.toString());
    }
    request.scope = String.join(",", scopeList);
    request.callerLocalEntry = "com.reactnativetiktok.TikTokEntryActivity";
    tiktokOpenApi.authorize(request);
  }
}
