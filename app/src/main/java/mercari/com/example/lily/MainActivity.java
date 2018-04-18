package mercari.com.example.lily;

import android.content.res.Configuration;
import android.os.Bundle;

import mercari.com.example.lily.fragment.PagerFragment;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PagerFragment fg = null;
        if (savedInstanceState!= null){
            fg = (PagerFragment) getSupportFragmentManager().findFragmentByTag(PagerFragment.class.getName());
        }

        if (fg == null){
            fg = new PagerFragment();
        }

        replaceFragment(fg);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


    }

}
