package com.maxleap.reactnative;

import android.support.v4.app.FragmentActivity;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SidneyXu on 2016/04/12.
 */
public class MLSocailShareReactPackage extends MainReactPackage {

    private FragmentActivity fragmentActivity;

    public MLSocailShareReactPackage(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Arrays.<NativeModule>asList(
                new MLSocialShareNativeModule(reactContext, fragmentActivity)
        );
    }

}
