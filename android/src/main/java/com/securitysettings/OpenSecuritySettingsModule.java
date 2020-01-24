package com.securitysettings;

import android.content.Intent;
import android.provider.Settings;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class OpenSecuritySettingsModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public OpenSecuritySettingsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "OpenSecuritySettings";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }


    @ReactMethod
    public void openSecuritySettings(){
        Intent intent=new Intent(Settings.ACTION_SECURITY_SETTINGS);
        this.reactContext.startActivity(intent);
    }
}
