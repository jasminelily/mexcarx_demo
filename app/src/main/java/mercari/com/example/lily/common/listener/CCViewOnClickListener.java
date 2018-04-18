package mercari.com.example.lily.common.listener;

import android.view.View;

import mercari.com.example.lily.common.constant.Constant;

/**
 * Created by lily on 4/18/18.
 */


public abstract class CCViewOnClickListener implements View.OnClickListener {

    long lastClickTime = 0;

    @Override
    public void onClick(View v) {

        long clickTime = System.currentTimeMillis();

        //double click
        if (clickTime - lastClickTime > Constant.TIME_DOUBLE_CLICK_DELTA_MSC) {
            //single click
            onSingleClick(v);
        } else {

        }
        lastClickTime = clickTime;
    }

    public abstract void onSingleClick(View v);


}
