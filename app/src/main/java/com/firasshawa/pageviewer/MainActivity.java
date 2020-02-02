package com.firasshawa.pageviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView QuotesListView;
    ArrayList<String> StringQuotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuotesListView = findViewById(R.id.QuotesListView);

        FillListview();

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

}
