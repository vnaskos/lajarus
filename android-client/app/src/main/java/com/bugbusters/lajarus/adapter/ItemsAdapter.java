package com.bugbusters.lajarus.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bugbusters.lajarus.R;
import com.bugbusters.lajarus.entity.Item;
import com.bugbusters.lajarus.entity.ItemType;

import java.util.List;

/**
 * Created by vasilis on 12/13/16.
 */

public class ItemsAdapter extends ArrayAdapter<Item> {

    public ItemsAdapter(Context context, int resource, List<Item> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_list_row, null);
        }

        Item item = getItem(position);

        if(item != null) {
            TextView ttName = (TextView) v.findViewById(R.id.itemName);
            TextView ttType = (TextView) v.findViewById(R.id.itemType);
            TextView ttValue = (TextView) v.findViewById(R.id.itemValue);
            TextView ttPrice = (TextView) v.findViewById(R.id.itemPrice);
            TextView ttDescr = (TextView) v.findViewById(R.id.itemDescription);

            ttName.setText(item.getName());
            ttType.setText(getItemType(item.getType()));
            ttValue.setText("value: " + item.getValue());
            ttPrice.setText(item.getPrice() + "$");
            ttDescr.setText(item.getDescription());
        }

        return v;
    }

    private String getItemType(ItemType type) {
        switch (type) {
            case DEFENCE:
                return "DEF";
            case ATTACK:
                return "ATK";
            default:
                return "UKN";
        }
    }
}
