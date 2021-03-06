package com.communication.majorproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PatientActivity extends AppCompatActivity {

    Button button;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        button = findViewById(R.id.report_btn);
        welcome = findViewById(R.id.wel_text);
        welcome.append("\n" + Login.email);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getString(R.string.base_url) + "db_preport.php?email=" + Login.email, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Download Started", Toast.LENGTH_LONG).show();
                        File output = new File("/storage/emulated/0/project/report.txt");
                        String res = response.toString();
                        try {
                            FileWriter fileWriter = new FileWriter(output);
                            fileWriter.write(res, 0, res.length() - 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        });
    }

    @Override
    public void onBackPressed() {
        CustomDialog dialog = new CustomDialog();
        dialog.show(getSupportFragmentManager(),"Logout");
    }
}
