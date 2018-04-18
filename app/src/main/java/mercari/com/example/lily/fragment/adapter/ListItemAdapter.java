package mercari.com.example.lily.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mercari.com.example.lily.common.helper.CheckHelper;
import mercari.com.example.lily.common.helper.ConvertHelper;
import mercari.com.example.lily.common.view.ViewListItem;
import mercari.com.example.lily.demo.R;
import mercari.com.example.lily.fragment.model.ListItemSet;
import mercari.com.example.lily.fragment.model.ListItemSingle;

/**
 * Created by lily on 4/18/18.
 */

public class ListItemAdapter extends ArrayAdapter<ListItemSet> {
    private Context mContext;

    public ListItemAdapter(Context context, ArrayList<ListItemSet> items) {
        super(context, 0, items);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_list_cell, parent, false);
        }

        ViewListItem itemView = null;
        ListItemSet itemSet = getItem(position);
        ListItemSingle item = null;
        try {
            for (int i = 0; i < itemSet.getItemCount(); i++) {

                switch (i) {
                    case 0:
                        itemView = (ViewListItem) convertView.findViewById(R.id.item1);
                        item = itemSet.getListItemSet().get(i);
                        break;
                    case 1:
                        itemView = (ViewListItem) convertView.findViewById(R.id.item2);
                        item = itemSet.getListItemSet().get(i);
                        break;
                }

                if (itemView == null) {
                    return convertView;
                }

                TextView txtName = (TextView) itemView.findViewById(R.id.txt_name);
                TextView txtLike = (TextView) itemView.findViewById(R.id.txt_like);
                TextView txtComment = (TextView) itemView.findViewById(R.id.txt_comment);
                TextView txtPrice = (TextView) itemView.findViewById(R.id.txt_price);
                ImageView txtSaleMark = (ImageView) itemView.findViewById(R.id.img_mark);

                txtName.setText(item.getName());
                txtLike.setText(ConvertHelper.toString(item.getNumLike()));
                txtComment.setText(ConvertHelper.toString(item.getNumComment()));
                txtPrice.setText(ConvertHelper.toDollar(item.getPrice()));

                if (CheckHelper.isStringEquals("on_sale", item.getStatus())) {
                    txtSaleMark.setVisibility(View.GONE);
                } else {
                    txtSaleMark.setVisibility(View.VISIBLE);
                }

                ImageView imageView = (ImageView) itemView.findViewById(R.id.img_goods);
                Picasso.get().load(item.getImgUrl()).placeholder(R.mipmap.image_placeholder).into(imageView);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }
}
