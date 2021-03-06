package com.phantancy.fgocalc.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.phantancy.fgocalc.R;
import com.phantancy.fgocalc.base.BaseActy;
import com.phantancy.fgocalc.servant.ServantListMVPActy;

/**
 * Created by HATTER on 2017/11/8.
 * https://developer.android.com/training/system-ui/navigation.html#behind
 */

public class SplashActy extends Activity {
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_splash);
        ctx = this;
        hideBottomUIMenu();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ctx, ServantListMVPActy.class));
                finish();
            }
        },1000);
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    public void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
