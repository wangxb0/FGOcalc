package com.phantancy.fgocalc.servant;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.phantancy.fgocalc.R;
import com.phantancy.fgocalc.base.BaseActy;
import com.phantancy.fgocalc.common.ActivityCollector;
import com.phantancy.fgocalc.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by PY on 2016/10/31.
 */
public class ServantListMVPActy extends BaseActy{

    @BindView(R.id.aslm_fl_main)
    FrameLayout aslmFlMain;
    private long exitTime = 0;//用于记录退出时间
    private ServantListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_servant_list_mvp);
        ButterKnife.bind(this);
        ServantListFragment slFrag = (ServantListFragment)getSupportFragmentManager().findFragmentById(R.id.aslm_fl_main);
        if (slFrag == null) {
            slFrag = ServantListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),slFrag,R.id.aslm_fl_main);
        }
        mPresenter = new ServantListPresenter(slFrag);
    }

    //重载返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(ctx, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityCollector.finishAll();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
