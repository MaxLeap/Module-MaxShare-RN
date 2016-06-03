package com.maxleap.reactnative;

import android.support.v4.app.FragmentActivity;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.maxleap.social.EventListener;
import com.maxleap.social.HermsException;
import com.maxleap.social.MLHermes;
import com.maxleap.social.thirdparty.param.ShareItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SidneyXu on 2016/05/11.
 */
public class MLSocialShareNativeModule extends ReactContextBaseJavaModule {

    private static final String LIBRARY_NAME = "MaxSocialShare";

    private FragmentActivity fragmentActivity;

    public MLSocialShareNativeModule(ReactApplicationContext reactContext, FragmentActivity fragmentActivity) {
        super(reactContext);
        this.fragmentActivity = fragmentActivity;
        MLHermes.initialize(reactContext, null, null);
    }

    @Override
    public String getName() {
        return LIBRARY_NAME;
    }

    @ReactMethod
    public void share(ReadableMap map, final Promise promise) {
        ShareItem shareItem = ShareItem.newBuilder()
                .text(optString(map, "title"))
                .description(optString(map, "detail"))
                .actionUrl(optString(map, "actionUrl"))
                .createShareItem();

        MLHermes.showShareDialog(fragmentActivity,
                LIBRARY_NAME, shareItem, new EventListener() {
                    @Override
                    public void onSuccess() {
                        promise.resolve(null);
                    }

                    @Override
                    public void onError(HermsException e) {
                        promise.reject("" + e.getErrorCode(), e.getMessage());
                    }

                    @Override
                    public void onCancel() {
//                            promise.reject(new ());
                    }
                });
    }

    private Map<String, String> convertMap(ReadableMap readableMap) {
        if (readableMap == null) return null;
        Map<String, String> map = new HashMap<String, String>();
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            map.put(key, optString(readableMap, key));
        }
        return map;
    }

    private List<String> converArray(ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) return null;
        List<String> list = new ArrayList<String>();
        int len = readableArray.size();
        for (int i = 0; i < len; i++) {
            list.add(readableArray.getString(i));
        }
        return list;
    }

    private String optString(ReadableMap map, String key) {
        if (map.hasKey(key)) {
            return map.getString(key);
        }
        return null;
    }

    private boolean optBoolean(ReadableMap map, String key, boolean defaultValue) {
        if (map.hasKey(key)) {
            return map.getBoolean(key);
        }
        return defaultValue;
    }

    private int optInt(ReadableMap map, String key) {
        if (map.hasKey(key)) {
            return map.getInt(key);
        }
        return 0;
    }

    private double optDouble(ReadableMap map, String key) {
        if (map.hasKey(key)) {
            return map.getDouble(key);
        }
        return 0;
    }

    private ReadableMap optMap(ReadableMap map, String key) {
        if (map.hasKey(key)) {
            return map.getMap(key);
        }
        return null;
    }

    private ReadableArray optArray(ReadableMap map, String key) {
        if (map.hasKey(key)) {
            return map.getArray(key);
        }
        return null;
    }
}
