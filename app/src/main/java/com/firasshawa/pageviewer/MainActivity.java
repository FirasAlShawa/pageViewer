package com.firasshawa.pageviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class MainActivity extends AppCompatActivity {

    ListView QuotesListView;
    ViewPager viewPager ;
    ImageButton like ;

    ArrayList<String> StringQuotes;
    List<Quote> quoteList;

    MyPageAdapter adapter;

    boolean flag = false;
    int[] colors;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        like= findViewById(R.id.likeBtn);
        viewPager = findViewById(R.id.viewPager);

        colors =new int[] {
                getResources().getColor(R.color.color0),
                getResources().getColor(R.color.color1)
        };

        FillPageViewer();

        like.setOnClickListener(likeListener);

        viewPager.setOnPageChangeListener(pageChangeListener);


//        FillCarouselPicker();
//        listView code
//        QuotesListView = findViewById(R.id.QuotesListView);
//        FillListview();

    }

    //my functions
    private void FillPageViewer(){
        String QuotesString = ReadJson();
        Quote[] Quotes = new Gson().fromJson(QuotesString,Quote[].class);
        quoteList = new ArrayList<>();

        for (int i= 0 ; i < 10 ;i++) {
            quoteList.add(Quotes[i]);
        }

        adapter = new MyPageAdapter(this,quoteList);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(5);

        viewPager.setPadding(130,0,130,0);

    }

    public void FillListview(){
        StringQuotes = new ArrayList<>();

        String QuotesString = ReadJson();
        Quote[] Quotes = new Gson().fromJson(QuotesString,Quote[].class);

        for (Quote singleQuote : Quotes) {
            StringQuotes.add(singleQuote.getText());
        }

        ArrayAdapter<String> quoteArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,StringQuotes);
        QuotesListView.setAdapter(quoteArrayAdapter);
    }

    public String ReadJson(){
        InputStream quotesStream;
        int size;
        byte[] buffer;
        String JsonString;
        try {
            //open the file from the assets local folder
            quotesStream = getAssets().open("quotes.json");

            //getting the size  of the file
            size = quotesStream.available();

            //buffering the file into bytes
            buffer = new byte[size];

            //read the file and close it
            quotesStream.read(buffer);
            quotesStream.close();

            //parsing the
            JsonString = new String(buffer, "UTF-8");


            return JsonString;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            JsonString = "[\n" +
                    "    {\"text\" : \"Error!\"}\n" +
                    "]";

            return JsonString;
        }
    }

    //listeners
    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(position < (adapter.getCount() -1) ){
                if(position % 2 == 0){
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset,colors[0],colors[1]));
                }else{
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset,colors[1],colors[0]));
                }
            }

        }

        @Override
        public void onPageSelected(int position) {
            like.setImageDrawable(getDrawable(R.drawable.ic_like_white_24dp));
            flag = false;

        }

        @Override

        public void onPageScrollStateChanged(int state) {

        }
    };

    Button.OnClickListener likeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!flag){
                like.setImageDrawable(getDrawable(R.drawable.ic_like_red_24dp));
            }
            else{
                like.setImageDrawable(getDrawable(R.drawable.ic_like_white_24dp));
            }
            flag = !flag;
        }
    };
}
