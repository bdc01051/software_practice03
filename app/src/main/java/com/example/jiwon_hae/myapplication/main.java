package com.example.jiwon_hae.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jiwon_hae.myapplication.call.calling;

public class main extends AppCompatActivity {
    private ImageButton unlock_btn;
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



        this.setBackgroundImage();
        this.setImageButtons();
        this.manage_locking_motion();

        // Example of a call to a native method

    }

    private void setBackgroundImage(){
        this.lock_main_screen = (ImageView)findViewById(R.id.lock_page_background);
        Glide.with(this)
                .load(R.drawable.background_sample1)
                .into(lock_main_screen);
    }

    private void setImageButtons(){
        this.call_btn = (ImageButton)findViewById(R.id.lock_pg_call_btn);
        Glide.with(this)
                .load(R.drawable.call_icon)
                .thumbnail(0.1f)
                .into(call_btn);

        this.call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(main.this, "call", Toast.LENGTH_SHORT).show();
                Intent to_call = new Intent(main.this, calling.class);
                startActivity(to_call);


            }
        });

        this.alarm_btn = (ImageButton)findViewById(R.id.lock_pg_alarm);
        Glide.with(this)
                .load(R.drawable.alarm_icon)
                .thumbnail(0.1f)
                .into(alarm_btn);

        this.alarm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(main.this, "alarm", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void manage_locking_motion(){
        this.unlock_btn = (ImageButton) findViewById(R.id.lock_pg_unlock);
        Glide.with(this)
                .load(R.drawable.lock_icon)
                .thumbnail(0.1f)
                .into(unlock_btn);


        this.unlock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(main.this, "unlock", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
