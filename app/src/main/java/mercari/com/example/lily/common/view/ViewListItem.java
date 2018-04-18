package mercari.com.example.lily.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import mercari.com.example.lily.demo.R;

/**
 * Created by lily on 4/18/18.
 */

public class ViewListItem extends LinearLayout {
    private ImageView imageView;
    public String imageUrl;

    public ViewListItem(Context context) {
        super(context);
        initialize();
    }

    public ViewListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs);
    }

    public ViewListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(attrs);
    }

    /**
     * Initialize with no custom attributes.
     */
    private void initialize() {
        initialize(null);
    }

    private void initialize(AttributeSet attrs) {

        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.list_item);

        attributes.recycle();

        inflate(getContext(), R.layout.view_list_cell_item, this);

        imageView = (ImageView)findViewById(R.id.img_goods);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

    }

    static class SavedState extends BaseSavedState{

        String imgUrl;
        public SavedState(Parcel source) {
            super(source);
            imgUrl = source.readString();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(imgUrl);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>(){

            @Override
            public SavedState createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
