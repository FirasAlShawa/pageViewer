package com.firasshawa.pageviewer;

import android.app.Notification;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import static com.firasshawa.pageviewer.App.CHANNEL_1_ID;

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
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quote_layout,container,false);

        CardView QuoteCardView =view.findViewById(R.id.QuoteCardView);
        TextView quoteTextView = view.findViewById(R.id.quoteTextView);
        TextView dayCount = view.findViewById(R.id.dayCount);

        quoteTextView.setText(quotesList.get(position).getText());
        dayCount.setText(String.valueOf(position+1));

        QuoteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

                Notification notification = new NotificationCompat.Builder(context,CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.notifyiconsmile48)
                        .setContentTitle("Bahja time")
                        .setContentText(quotesList.get(position).getText())
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.notifyiconross96))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .setBigContentTitle("وقت البهجة")
                                .bigText(quotesList.get(position).getText())
                        )
                        .build();

                //make the notification un dismissible
//                notification.flags |= Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;

                notificationManager.notify(position,notification);
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
