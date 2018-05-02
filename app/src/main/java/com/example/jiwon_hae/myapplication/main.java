package com.example.jiwon_hae.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.jiwon_hae.myapplication.call.calling;
import com.example.jiwon_hae.myapplication.tmap.map_navigation;
import com.example.jiwon_hae.myapplication.tmap.tmap;

public class main extends AppCompatActivity {
    private ImageButton alcohol_btn;
    private ImageButton navigation_btn;
    private ImageButton call_btn;
    private ImageButton alarm_btn;
    private ImageView lock_main_screen;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setImageButtons();
        // Example of a call to a native method

    }

    private void setImageButtons(){
        this.alcohol_btn = (ImageButton)findViewById(R.id.set_alocohol_imageButton);
        Glide.with(this)
                .load(R.drawable.beer_icon)
                .thumbnail(0.1f)
                .into(alcohol_btn);

        this.alcohol_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(main.this, "alcohol", Toast.LENGTH_SHORT).show();

            }
        });

        this.navigation_btn = (ImageButton)findViewById(R.id.set_navigation_imageButton);
        Glide.with(this)
                .load(R.drawable.navigation_icon)
                .thumbnail(0.1f)
                .into(navigation_btn);

        this.navigation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(main.this, "call", Toast.LENGTH_SHORT).show();
                //Intent to_call = new Intent(main.this, calling.class);
                Intent to_navigation = new Intent(main.this, map_navigation.class);
                startActivity(to_navigation);

            }
        });

    }



    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
