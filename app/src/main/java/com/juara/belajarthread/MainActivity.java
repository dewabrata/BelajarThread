package com.juara.belajarthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    TextView txtHasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtHasil = findViewById(R.id.txtHasil);
        new Thread(new Runnable() {
            @Override
            public void run() {
                openHttpConnection("https://newsapi.org/v2/top-headlines?country=id");
            }
        }).start();

    }
    String text;
    private void openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode = -1;

        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }

            HttpURLConnection httpConn = (HttpURLConnection)urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestProperty("X-Api-Key", "4fae9d3eb3594ae998d68c9b83363899");

            httpConn.setRequestMethod("GET");
            httpConn.connect();

            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
         text = "";
        char[] inputBuffer = new char[2000];

        try {
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString =
                        String.copyValueOf(inputBuffer, 0, charRead);
                text += readString;
                inputBuffer = new char[2000];
            }
        }catch (IOException e){

        }


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtHasil.setText(text);
            }
        });

    }

}
