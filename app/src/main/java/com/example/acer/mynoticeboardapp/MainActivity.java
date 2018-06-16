package com.example.acer.mynoticeboardapp;

import android.app.PendingIntent;
import android.app.VoiceInteractor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final String URL_STORE_TOKEN = "http://192.168.43.107/noticeappdb/Devices.php";
    private BroadcastReceiver br;
    /*  private Button admin,student;
      public String USER;*/
    private EditText email_id;
    private Button register;
    private Spinner type;
    private ImageView next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regactivity);
       /* admin= (Button)findViewById(R.id.admin);
        student= (Button) findViewById(R.id.student);
        admin.setOnClickListener(this);
        student.setOnClickListener(this);*/
        email_id = (EditText) findViewById(R.id.edit_email);
        register = findViewById(R.id.register);
        type = findViewById(R.id.type);
        next = findViewById(R.id.next);
        email_id.setOnClickListener(this);
        register.setOnClickListener(this);
        type.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("HOD");
        categories.add("Staff");
        categories.add("Student");
        categories.add("Principal");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        type.setAdapter(dataAdapter);

        next.setOnClickListener(this);


        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("myToken", SharedprefManager.getInstance(MainActivity.this).getDeviceToken());

            }
        };
        registerReceiver(br, new IntentFilter(FirebaseToken.TOKEN_BROADCAST));

        if (SharedprefManager.getInstance(this).getDeviceToken() != null)
            Log.d("myToken", SharedprefManager.getInstance(this).getDeviceToken());

    }


    private void sendTokenToServer() {

        final String email = email_id.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter your email please", Toast.LENGTH_LONG).show();
        } else {
            if (SharedprefManager.getInstance(this).getDeviceToken() != null) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        URL_STORE_TOKEN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email);
                        params.put("token", SharedprefManager.getInstance(getApplicationContext()).getDeviceToken());
                        return params;
                    }


                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

               /* if(msg.equalsIgnoreCase("Device registered successfully")){
                    Intent i=new Intent(getApplicationContext(),ShowNotification.class);

                    i.putExtra("heading",type.getSelectedItem().toString());
                    i.putExtra("title"," ");
                    i.putExtra("message"," ");
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, i,
                            PendingIntent.FLAG_ONE_SHOT);
                }*/

            } else {
                Toast.makeText(this, "token is not generated", Toast.LENGTH_LONG).show();
            }
        }

    }


    @Override
    public void onClick(View view) {
        if (view == register)
            sendTokenToServer();
        if (view == next) {

            Intent i = new Intent(getApplicationContext(), ShowNotification.class);
            // String h=type.getSelectedItem().toString();

            i.putExtra("heading ", type.getSelectedItem().toString());
            i.putExtra("title", " ");
            i.putExtra("message", " ");
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, i,
                    PendingIntent.FLAG_ONE_SHOT);
            startActivity(i);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
