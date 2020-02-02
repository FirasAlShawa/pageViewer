package com.firasshawa.pageviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.Buffer;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InputStream qoutesStream = getAssets().open("qoutes.json");
            int size = qoutesStream.available();
            byte[] buffer = new byte[size];
            qoutesStream.read(buffer);
            qoutesStream.close();
            String q = new String(buffer,"UTF-8");
            JsonReader jsonReader = new JsonReader(new StringReader(q));
            Gson gsonQoutes = new Gson();
            Quote[] Quotes = gsonQoutes.fromJson(q,Quote[].class);
            System.out.println();
            for (Quote singleqoute:Quotes) {
                System.out.println("=> "+ singleqoute.getText());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
