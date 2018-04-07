package com.prohua.timerrecycler;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.prohua.universal.UniversalAdapter;

import java.util.List;

/**
 * 时间适配器
 * Created by Deep on 2018/4/7 0007.
 */

public class TimerAdapter extends UniversalAdapter {

    private TimerAdapter.OnBindItemRecycledView onBindItemRecycledView;

    public TimerAdapter(Context context, List list, @LayoutRes int layoutId, @LayoutRes int headerLayoutId, @LayoutRes int footerLayoutId) {
        super(context, list, layoutId, headerLayoutId, footerLayoutId);
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        //onBindItemRecycledView.onBindItemRecycledView(holder);
    }

    public void setOnBindItemRecycledView(OnBindItemRecycledView onBindItemRecycledView) {
        this.onBindItemRecycledView = onBindItemRecycledView;
    }

    public interface OnBindItemRecycledView {
        void onBindItemRecycledView(RecyclerView.ViewHolder holder);
    }
}
