package mercari.com.example.lily;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import mercari.com.example.lily.demo.R;


public class BaseActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    protected Toolbar mToolBar;
    private ActionBar mActionBar;
    private ProgressBar mProgressBar;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for progressbar
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(true);

        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progressBar);

        //ActionBar
        setActionBar();

        //Drawer
        setDrawerLayout();

    }


    private void setDrawerLayout() {
        //Drawer
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_top);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void setActionBar() {
        //add action bar
        mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.ic_left_open_24dp);
        //disable display app label in action bar
        mActionBar.setDisplayShowTitleEnabled(false);

    }

    @Override
    public void onBackPressed() {
        confirmExit();
    }

    private boolean isHasSub() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void confirmExit() {

        //subあり
        if (isHasSub()) {
            super.onBackPressed();
            return;
        }

        //left open,then close
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Fragment処理
    protected void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_center, fragment,fragment.getClass().getName()).commit();
    }

    //Fragment処理

    //ローディング
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void dismissLoading() {
        if (mProgressBar.isShown())
        mProgressBar.setVisibility(View.GONE);
    }
    //ローディング
}
