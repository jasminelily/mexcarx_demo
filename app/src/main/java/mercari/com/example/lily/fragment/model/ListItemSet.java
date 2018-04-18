package mercari.com.example.lily.fragment.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by lily on 4/18/18.
 */

public class ListItemSet implements Parcelable {

    public final int NUM_COL = 2;

    private ArrayList<ListItemSingle> colsData = null;

    public ListItemSet() {
        colsData = new ArrayList<ListItemSingle>();
    }

    public int getItemCount() {
        return NUM_COL;
    }

    public ArrayList<ListItemSingle> getListItemSet() {
        return colsData;
    }

    public void setListItemSet(ListItemSingle item) {

        if (colsData.size() < NUM_COL) {
            colsData.add(item);
        }
    }
    public void setListItemSet(int index, ListItemSingle item) {

        if (index < NUM_COL) {
            colsData.add(index,item);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
