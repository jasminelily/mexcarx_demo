package mercari.com.example.lily.common.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import mercari.com.example.lily.demo.R;
import mercari.com.example.lily.common.listener.CCViewOnClickListener;

public class ViewFloatButton extends LinearLayout {

    private CCViewOnClickListener completeListener;
    private Button btnMenu;

    public ViewFloatButton(Context context) {
        super(context);
        initialize();
    }

    public ViewFloatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(attrs);
    }

    public ViewFloatButton(Context context, AttributeSet attrs, int defStyleAttr) {
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

        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.float_menu);

        attributes.recycle();

        inflate(getContext(), R.layout.view_float_menu, this);

        btnMenu = (Button) findViewById(R.id.btn_menu_main);
    }


    public void setMenuBtns( CCViewOnClickListener completeListener) {
        this.completeListener = completeListener;

        setAction();
    }

    private void setAction() {
        btnMenu.setOnClickListener(new CCViewOnClickListener() {

            @Override
            public void onSingleClick(View v) {
                if (completeListener != null){
                    completeListener.onSingleClick(v);
                }
            }
        });

    }





}
