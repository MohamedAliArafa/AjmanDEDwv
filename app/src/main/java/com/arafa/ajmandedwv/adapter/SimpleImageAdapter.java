package com.arafa.ajmandedwv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.arafa.ajmandedwv.CursorWheelLayout;
import com.arafa.ajmandedwv.R;
import com.arafa.ajmandedwv.data.ImageData;

import java.util.List;


public class SimpleImageAdapter extends CursorWheelLayout.CycleWheelAdapter {
    private List<ImageData> mMenuItemDatas;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mLastPosition;

    public SimpleImageAdapter(Context context, List<ImageData> menuItemDatas) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mMenuItemDatas = menuItemDatas;
    }

    @Override
    public int getCount() {
        return mMenuItemDatas == null ? 0 : mMenuItemDatas.size();
    }

    @Override
    public View getView(View parent, int position) {
        ImageData item = getItem(position);
        View root = mLayoutInflater.inflate(R.layout.wheel_image_item, null, false);
        ImageView imgView = (ImageView) root.findViewById(R.id.wheel_menu_item_iv);
        imgView.setImageResource(item.mImageRes);
//        float initialTranslation = (mLastPosition <= position ? 500f : -500f);
//
//        root.setTranslationY(initialTranslation);
//        root.animate()
//                .setInterpolator(new DecelerateInterpolator(1.0f))
//                .translationY(0f)
//                .setDuration(300l)
//                .setListener(null);
//
//        // Keep track of the last position we loaded
//        mLastPosition = position;


        return root;
    }

    @Override
    public ImageData getItem(int position) {
        return mMenuItemDatas.get(position);
    }

}
