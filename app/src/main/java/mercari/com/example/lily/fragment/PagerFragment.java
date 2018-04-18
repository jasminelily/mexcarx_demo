package mercari.com.example.lily.fragment;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mercari.com.example.lily.common.constant.Constant;
import mercari.com.example.lily.common.constant.EnumConstant;
import mercari.com.example.lily.common.helper.RequestHelper;
import mercari.com.example.lily.common.listener.CCDataListener;
import mercari.com.example.lily.demo.R;
import mercari.com.example.lily.fragment.adapter.PagerAdapter;
import mercari.com.example.lily.common.fragment.BaseFragment;
import mercari.com.example.lily.fragment.model.PagerItem;

/**
 * Created by lily on 4/17/18.
 */

public class PagerFragment extends BaseFragment {

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private Button mBtnBack;

    private View mRootView;


    private JSONArray pagerDatas ;
    private ArrayList<PagerItem> pagerItemsData;

    @Override
    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle) {
        super.onCreateView(layoutinflater, viewgroup, bundle);

        if (mRootView == null) {
            mRootView = layoutinflater.inflate(R.layout.fragment_home, viewgroup, false);
        }
        return mRootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            return;
        }

        String url = getResources().getString(R.string.url_home_all);
        showLoading();
        RequestHelper.requestJson(url,new CCDataListener.CCDataJsonListListener(){

            @Override
            public void onRequstDataComplete(EnumConstant.Request_Status status, JSONArray datas) {
                dismissLoading();
                pagerDatas = datas;
                setPager();
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constant.KEY_FG_ITEM,mAdapter.getDetaSource());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            pagerItemsData = savedInstanceState.getParcelableArrayList(Constant.KEY_FG_ITEM);
            setPager();
        }
    }

    private void setPager() {
        mViewPager = (ViewPager)mRootView.findViewById(R.id.pager_tab);
        setViewPagerAdapter(mViewPager);

        TabLayout tabLayout = (TabLayout) mRootView.findViewById(R.id.pager_tab_header);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setViewPagerAdapter(ViewPager viewPager) {
        mAdapter = new PagerAdapter(getChildFragmentManager());
        //adapter data
        mAdapter = setPagerContent(mAdapter);
        viewPager.setAdapter(mAdapter);
    }

    protected PagerAdapter setPagerContent(PagerAdapter adapter)  {

        if (pagerItemsData != null){
            adapter.setDetaSource(pagerItemsData);
            return adapter;
        }

        PagerItem item = null;

        for (int i=0; i < pagerDatas.length(); i++) {
            item = new PagerItem();
            try {
                JSONObject pagerData = pagerDatas.getJSONObject(i);

                item.setTitle(pagerData.getString("name"));
                item.setUrl(pagerData.getString("data"));
                adapter.addContentData(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return adapter;
    }


}
