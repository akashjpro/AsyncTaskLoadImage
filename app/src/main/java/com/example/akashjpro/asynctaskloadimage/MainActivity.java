package com.example.akashjpro.asynctaskloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnDownload;
    ImageView imgHinh;
    ProgressBar prBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        prBar.setVisibility(View.INVISIBLE);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new LoadImage().execute();
                    }
                });
            }
        });

    }
    private  class LoadImage extends AsyncTask<String, String, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmapHinh = null;
            try {
                URL url = new URL("http://hinhnenhd.com/wp-content/uploads/2014/03/hinh-nen-thien-nhien-2K.jpg");

                bitmapHinh = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapHinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgHinh.setImageBitmap(bitmap);
            prBar.setVisibility(View.INVISIBLE);
        }
    }
    private void addControls() {
        btnDownload = (Button) findViewById(R.id.button);
        imgHinh   = (ImageView) findViewById(R.id.imageView);
        prBar = (ProgressBar) findViewById(R.id.progressBar);
    }
}
