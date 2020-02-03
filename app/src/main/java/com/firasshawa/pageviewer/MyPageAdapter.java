package com.firasshawa.pageviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyPageAdapter extends PagerAdapter {

    private Context context;
    private List<Quote> quotesList;

    public MyPageAdapter(Context context, List<Quote> quotesList) {
        this.context = context;
        this.quotesList = quotesList;
    }

    @Override
    public int getCount() {
        return quotesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quote_layout,container,false);

        TextView quoteTextView = view.findViewById(R.id.quoteTextView);

        quoteTextView.setText(quotesList.get(position).getText());

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
