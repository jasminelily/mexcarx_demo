package mercari.com.example.lily.common.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import mercari.com.example.lily.MainActivity;
import mercari.com.example.lily.MainApplication;

/**
 * Created by lily on 4/17/18.
 */

public class BaseFragment extends Fragment {
    protected MainActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();

        detectException();
    }

    // *********** exception ************
    protected void detectException() {

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable e) {
                MainActivity activity = (MainActivity) getActivity();
                MainApplication application = (MainApplication) activity.getApplication();
                application.handleUncaughtException(thread, e);
            }
        });
    }

    protected void showLoading() {
        mActivity.showLoading();
    }

    protected void dismissLoading() {
        mActivity.dismissLoading();
    }
}
