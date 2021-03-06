package com.example.jiwon_hae.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.signature.StringSignature;
import com.example.jiwon_hae.myapplication.call.calling;
import com.example.jiwon_hae.myapplication.schedule.at_main.main_listview_adapter;
import com.example.jiwon_hae.myapplication.schedule.schedule;
import com.example.jiwon_hae.myapplication.tmap.map_navigation;
import com.example.jiwon_hae.myapplication.tmap.tmap;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.jiwon_hae.myapplication.utility.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;

public class main extends AppCompatActivity {
    private ImageButton alcohol_btn;
    private ImageButton navigation_btn;

    //Listview
    main_listview_adapter main_display_adapter;


    //Permission
    private static int REQUEST_LOCATION_PERMISSION = 1;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setImageButtons();

        utility Utility = new utility();
        Utility.RequestPermission(this);

        setToggleButtons();

        ListView main_schedule_display = (ListView)findViewById(R.id.schedule_listView);
        main_display_adapter = new main_listview_adapter(this);
        main_schedule_display.setAdapter(main_display_adapter);

        // Example of a call to a native method
    }

    @Override
    protected void onResume() {
        super.onResume();

        getToday_day();
    }

    private void getToday_day(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch(day){
            case Calendar.SUNDAY:
                check_off_toggleButtons("sun", toggleButtonArrayList);
                main_display_adapter.update_schedule_display("sunday");
                break;
            case Calendar.MONDAY:
                check_off_toggleButtons("mon", toggleButtonArrayList);
                main_display_adapter.update_schedule_display("monday");
                break;
            case Calendar.TUESDAY:
                check_off_toggleButtons("tues", toggleButtonArrayList);
                main_display_adapter.update_schedule_display("tuesday");
                break;
            case Calendar.WEDNESDAY:
                check_off_toggleButtons("wed", toggleButtonArrayList);
                main_display_adapter.update_schedule_display("wednesday");
                break;
            case Calendar.THURSDAY:
                check_off_toggleButtons("thurs", toggleButtonArrayList);
                main_display_adapter.update_schedule_display("thursday");
                break;
            case Calendar.FRIDAY:
                check_off_toggleButtons("fri", toggleButtonArrayList);
                main_display_adapter.update_schedule_display("friday");
                break;
            case Calendar.SATURDAY:
                check_off_toggleButtons("sat", toggleButtonArrayList);
                main_display_adapter.update_schedule_display("saturday");
                break;
        }
    }

    private void setImageButtons(){
        this.alcohol_btn = (ImageButton)findViewById(R.id.set_alocohol_imageButton);
        Glide.with(this)
                .load(R.drawable.beer_icon)
                .thumbnail(0.5f)
                .signature(new StringSignature("a"))
                .into(alcohol_btn);

        this.alcohol_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(main.this, "alcohol", Toast.LENGTH_SHORT).show();
                Intent to_set_schedule = new Intent(main.this, schedule.class);
                startActivity(to_set_schedule);
            }
        });

        this.navigation_btn = (ImageButton)findViewById(R.id.set_navigation_imageButton);
        Glide.with(this)
                .load(R.drawable.navigation_icon)
                .thumbnail(0.3f)
                .signature(new StringSignature("b"))
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

    private ArrayList<ToggleButton> toggleButtonArrayList = new ArrayList<>();

    private void setToggleButtons(){
        ToggleButton sunday = (ToggleButton)findViewById(R.id.toggle_btn_sunday);
        ToggleButton monday = (ToggleButton)findViewById(R.id.toggle_btn_monday);
        ToggleButton tuesday = (ToggleButton)findViewById(R.id.toggle_btn_tuesday);
        ToggleButton wednesday = (ToggleButton)findViewById(R.id.toggle_btn_wednesday);
        ToggleButton thursday = (ToggleButton)findViewById(R.id.toggle_btn_thursday);
        ToggleButton friday = (ToggleButton)findViewById(R.id.toggle_btn_friday);
        ToggleButton saturday = (ToggleButton)findViewById(R.id.toggle_btn_saturday);

        toggleButtonArrayList.add(sunday);
        toggleButtonArrayList.add(monday);
        toggleButtonArrayList.add(tuesday);
        toggleButtonArrayList.add(wednesday);
        toggleButtonArrayList.add(thursday);
        toggleButtonArrayList.add(friday);
        toggleButtonArrayList.add(saturday);

    }

    private void check_off_toggleButtons(String days, ArrayList<ToggleButton> toggleButtons){
        switch(days) {
            case "sun":
                for(int i = 0; i < toggleButtons.size() ; i++){
                    if(i != 0){
                        toggleButtons.get(i).setChecked(false);
                    }else{
                        toggleButtons.get(i).setChecked(true);
                    }
                }
                break;
            case "mon":
                for(int i = 0; i < toggleButtons.size() ; i++){
                    if(i != 1){
                        toggleButtons.get(i).setChecked(false);
                    }else{
                        toggleButtons.get(i).setChecked(true);
                    }
                }
                break;
            case "tues":
                for(int i = 0; i < toggleButtons.size() ; i++){
                    if(i != 2){
                        toggleButtons.get(i).setChecked(false);
                    }else{
                        toggleButtons.get(i).setChecked(true);
                    }
                }
                break;
            case "wed":
                for(int i = 0; i < toggleButtons.size() ; i++){
                    if(i != 3){
                        toggleButtons.get(i).setChecked(false);
                    }else{
                        toggleButtons.get(i).setChecked(true);
                    }
                }
                break;
            case "thurs":
                for(int i = 0; i < toggleButtons.size() ; i++){
                    if(i != 4){
                        toggleButtons.get(i).setChecked(false);
                    }else{
                        toggleButtons.get(i).setChecked(true);
                    }
                }
                break;
            case "fri":
                for(int i = 0; i < toggleButtons.size() ; i++){
                    if(i != 5){
                        toggleButtons.get(i).setChecked(false);
                    }else{
                        toggleButtons.get(i).setChecked(true);
                    }
                }
                break;
            case "sat":
                for(int i = 0; i < toggleButtons.size() ; i++){
                    if(i != 6){
                        toggleButtons.get(i).setChecked(false);
                    }else{
                        toggleButtons.get(i).setChecked(true);
                    }
                }
                break;

        }
    }

    public void setToggleActions(View view){
        ToggleButton s = (ToggleButton) view;

        switch (view.getId()){
            case R.id.toggle_btn_sunday:
                check_off_toggleButtons("sun", toggleButtonArrayList);
                main_display_adapter.update_schedule_display(view.getTag().toString());
                break;
            case R.id.toggle_btn_monday:
                check_off_toggleButtons("mon", toggleButtonArrayList);
                main_display_adapter.update_schedule_display(view.getTag().toString());
                break;
            case R.id.toggle_btn_tuesday:
                check_off_toggleButtons("tues", toggleButtonArrayList);
                main_display_adapter.update_schedule_display(view.getTag().toString());
                break;
            case R.id.toggle_btn_wednesday:
                check_off_toggleButtons("wed", toggleButtonArrayList);
                main_display_adapter.update_schedule_display(view.getTag().toString());
                break;
            case R.id.toggle_btn_thursday:
                check_off_toggleButtons("thurs", toggleButtonArrayList);
                main_display_adapter.update_schedule_display(view.getTag().toString());
                break;
            case R.id.toggle_btn_friday:
                check_off_toggleButtons("fri", toggleButtonArrayList);
                main_display_adapter.update_schedule_display(view.getTag().toString());
                break;
            case R.id.toggle_btn_saturday:
                check_off_toggleButtons("sat", toggleButtonArrayList);
                main_display_adapter.update_schedule_display(view.getTag().toString());
                break;

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(main.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(main.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
