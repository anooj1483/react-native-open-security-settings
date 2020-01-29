package com.securitysettings;

import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        Intent  intent=new Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.reactContext.startActivity(intent);
    }

@ReactMethod
public void isDeviceSecure(Promise promise)
{
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
    {
        KeyguardManager manager = (KeyguardManager) reactContext.getSystemService(ReactContext.KEYGUARD_SERVICE);
        promise.resolve(manager.isDeviceSecure());
    }else{
        String LOCKSCREEN_UTILS = "com.android.internal.widget.LockPatternUtils";
        try
{
            Class<?> lockUtilsClass = Class.forName(LOCKSCREEN_UTILS);
            Object lockUtils = lockUtilsClass.getConstructor(ReactContext.class).newInstance(reactContext);
            Method method = lockUtilsClass.getMethod("isLockScreenDisabled");

            boolean isDisabled;
            try {
                isDisabled = Boolean.valueOf(String.valueOf(method.invoke(lockUtils)));
            }
            catch (InvocationTargetException ex) {
                isDisabled = true;
            }
            promise.resolve(!isDisabled);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        promise.resolve(false);
    }
    //promise.resolve(false);
}




}
