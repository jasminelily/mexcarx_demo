package mercari.com.example.lily.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mercari.com.example.lily.common.constant.Constant;
import mercari.com.example.lily.common.constant.EnumConstant;
import mercari.com.example.lily.common.fragment.BaseFragment;
import mercari.com.example.lily.common.helper.CheckHelper;
import mercari.com.example.lily.common.helper.RequestHelper;
import mercari.com.example.lily.common.listener.CCDataListener;
import mercari.com.example.lily.demo.R;
import mercari.com.example.lily.fragment.adapter.ListItemAdapter;
import mercari.com.example.lily.fragment.model.ListItemSet;
import mercari.com.example.lily.fragment.model.ListItemSingle;
import mercari.com.example.lily.fragment.model.PagerItem;

/**
 * Created by lily on 4/17/18.
 */

public class ListFragment extends BaseFragment {

    private String url;
    private ListView mListView;

    private View mRootView;
    private ListItemAdapter mAdapter;
    private ArrayList<ListItemSet> mListData;

    public static ListFragment newInstance(PagerItem item){
        ListFragment fg = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constant.KEY_FG_ITEM,item);
        fg.setArguments(bundle);
        fg.setRetainInstance(true);
        return fg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(Constant.KEY_FG_ITEM)) {
            PagerItem item = getArguments().getParcelable(Constant.KEY_FG_ITEM);
            url = item.getUrl();
        }
    }

    @Override
    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle) {
        super.onCreateView(layoutinflater, viewgroup, bundle);

        if (mRootView == null) {
            mRootView = layoutinflater.inflate(R.layout.view_list, viewgroup, false);
        }
        mListView = (ListView)mRootView.findViewById(R.id.list_view);
        return mRootView;

    }

    @Override
    public void onStart(){
        super.onStart();

        getData();

    }

    private void getData(){
        if (CheckHelper.isStringEmpty(url)){return;}
        showLoading();
        RequestHelper.requestJson(url, new CCDataListener.CCDataJsonListListener() {
            @Override
            public void onRequstDataComplete(EnumConstant.Request_Status status, JSONArray datas) {
                dismissLoading();
                initList(datas);
            }
        });
    }

    private void initList(JSONArray datas){
        mListData = convertToModel(datas);
        mAdapter = new ListItemAdapter(mActivity,mListData);
        mListView.setAdapter(mAdapter);
    }

    private ArrayList<ListItemSet> convertToModel(JSONArray datas){
        ArrayList<ListItemSet> listData = new ArrayList<ListItemSet>();

        ListItemSet itemSet = new ListItemSet();
        ListItemSingle item = null;
        int colCount = itemSet.getItemCount();

        for (int i=0; i < datas.length(); i++) {
            item = new ListItemSingle();
            try {
                JSONObject single = datas.getJSONObject(i);
                item.convertJson(single);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            int index = i % colCount;
            switch (index){
                case 0:
                    itemSet = new ListItemSet();
                    itemSet.setListItemSet(item);
                    break;
                case 1:
                    itemSet.setListItemSet(item);
                    listData.add(itemSet);
                    break;

            }

        }

        return  listData;
    }

}
