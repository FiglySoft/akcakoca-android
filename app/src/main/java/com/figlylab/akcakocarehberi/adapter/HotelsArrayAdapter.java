package com.figlylab.akcakocarehberi.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.figlylab.akcakocarehberi.R;


public class HotelsArrayAdapter extends RecyclerView.Adapter<HotelsArrayAdapter.MenuViewHolder> {

    private Context context = null;
    private String[] hotels_array = null;
    private String[] hotel_phone_array = null;
    private int hotel_drawables;

    public HotelsArrayAdapter(Context context, String[] names, String[] phones, int drawables){
        this.context = context;
        hotels_array = names;
        hotel_phone_array = phones;
        hotel_drawables = drawables;
    }

    @Override
    public int getItemCount() {
        return hotels_array.length;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder menuViewHolder, int i) {
        menuViewHolder.hotelName.setText(hotels_array[i]);

        String parsedPhone = hotel_phone_array[i];
        menuViewHolder.hotelPhone.setText(parsedPhone);
        TypedArray imgs = context.getResources().obtainTypedArray(hotel_drawables);
        imgs.getResourceId(i, -1);
        //menuViewHolder.cardView.setBackgroundResource(imgs.getResourceId(i, -1));
        menuViewHolder.hotelIcon.setImageResource(imgs.getResourceId(i, -1));
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_hotels, viewGroup, false);

        return new MenuViewHolder(itemView);
    }
    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView hotelName;
        TextView hotelPhone;
        CardView cardView;
        ImageView hotelIcon;

        public MenuViewHolder(View v) {
            super(v);
            hotelName = (TextView) v.findViewById(R.id.hotel_item_name);
            hotelPhone = (TextView) v.findViewById(R.id.hotel_item_phone);
            cardView = (CardView) v.findViewById(R.id.cv);
            hotelIcon = (ImageView) v.findViewById(R.id.hotel_item_icon);
        }
    }
}
