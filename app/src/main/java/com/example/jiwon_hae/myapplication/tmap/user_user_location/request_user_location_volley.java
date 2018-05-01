package com.example.jiwon_hae.myapplication.tmap.user_user_location;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiwon_hae on 2018. 5. 1..
 */

public class request_user_location_volley extends StringRequest {
    private static final String URL = "Location URL";
    private Map<String, String> paramters = new HashMap();

    public request_user_location_volley(String user_id, Response.Listener<String> listener) {
        super(1, URL, listener, null);
        this.paramters.put("user_email", user_id);
    }

    public Map<String, String> getParams() {
        return this.paramters;
    }
}
