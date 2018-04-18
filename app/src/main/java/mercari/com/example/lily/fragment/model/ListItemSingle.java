package mercari.com.example.lily.fragment.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lily on 4/18/18.
 */

public class ListItemSingle implements Parcelable {
    private String name;
    private String status;
    private int numLike;
    private int numComment;
    private double price;
    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumLike() {
        return numLike;
    }

    public void setNumLike(int numLike) {
        this.numLike = numLike;
    }

    public int getNumComment() {
        return numComment;
    }

    public void setNumComment(int numComment) {
        this.numComment = numComment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public ListItemSingle(){

    }

    protected ListItemSingle(Parcel in) {
        this.name = in.readString();
        this.status = in.readString();
        this.numLike = in.readInt();
        this.numComment = in.readInt();
        this.price = in.readFloat();
        this.imgUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.status);
        parcel.writeInt(this.numLike);
        parcel.writeInt(this.numComment);
        parcel.writeDouble(this.price);
        parcel.writeString(this.imgUrl);
    }

    public void convertJson(JSONObject single){
        try {

            this.setName(single.getString("name"));
            this.setStatus(single.getString("status"));
            this.setNumLike(single.getInt("num_likes"));
            this.setNumComment(single.getInt("num_comments"));
            this.setPrice(single.getDouble("price"));
            this.setImgUrl(single.getString("photo"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
