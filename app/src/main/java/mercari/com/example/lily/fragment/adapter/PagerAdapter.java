package mercari.com.example.lily.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import mercari.com.example.lily.fragment.ListFragment;
import mercari.com.example.lily.fragment.model.PagerItem;

public class PagerAdapter extends FragmentPagerAdapter {

    private ArrayList<PagerItem> mDataList = new ArrayList<PagerItem>();
    private Fragment mCurrentFragment;

    public PagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        ListFragment swipeTabFragment = ListFragment.newInstance(mDataList.get(position));
        return swipeTabFragment;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataList.get(position).getTitle();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            mCurrentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    public PagerItem getDataByIndex(int index) {
        if (index < mDataList.size()) {
            return mDataList.get(index);
        } else {
            return new PagerItem();
        }
    }

    public void addContentData(PagerItem item) {
        mDataList.add(item);
    }


    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }
    public ArrayList<PagerItem> getDetaSource(){
        return mDataList;
    }
    public void setDetaSource(ArrayList<PagerItem> items){
        mDataList = items;
    }
}
