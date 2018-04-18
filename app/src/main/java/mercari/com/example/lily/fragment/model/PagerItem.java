package mercari.com.example.lily.fragment.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lily on 3/6/18.
 */

public class PagerItem implements Parcelable {

    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static final Creator<PagerItem> CREATOR = new Creator<PagerItem>() {
        @Override
        public PagerItem createFromParcel(Parcel in) {
            return new PagerItem(in);
        }

        @Override
        public PagerItem[] newArray(int size) {
            return new PagerItem[size];
        }
    };

    public PagerItem() {

    }

    protected PagerItem(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.url);
    }
}
