package com.example.cuongtran.itapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cuongtran.itapp.R;
import com.example.cuongtran.itapp.app.AppController;
import com.example.cuongtran.itapp.model.Account;
import com.example.cuongtran.itapp.model.AccountBuilder;
import com.example.cuongtran.itapp.utils.Common;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyStore;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by linh.tran1 on 28/04/2017.
 */

public class ChangeProfileActivity extends AppCompatActivity {

    private static final String url_change_profile = Common.API_SERVER_IP + "api/changeProfile";

    private Button changeProfile;
    private EditText name;
    private EditText gender;
    private EditText phone;
    private EditText dob;
    private EditText job;
    private EditText city;
    Account account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile_activity);


        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");

        changeProfile = (Button) findViewById(R.id.btnChange);
        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeChangeProfile();
            }
        });

    }
    private void executeChangeProfile() {
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_change_profile,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();

                        try {
                            JSONObject jsonsObject = new JSONObject(response);

                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);


                        } catch (JSONException e) {
                            Toast.makeText(ChangeProfileActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(ChangeProfileActivity.this, "Cannot connect to server!", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                name = (EditText) findViewById(R.id.name);
                gender = (EditText) findViewById(R.id.gender);
                dob = (EditText) findViewById(R.id.dob);
                job = (EditText) findViewById(R.id.job);
                city = (EditText) findViewById(R.id.city);
                phone = (EditText) findViewById(R.id.phone);

                String nameString = name.getText().toString().trim();
                String genderString = gender.getText().toString().trim();
                String dobString = dob.getText().toString().trim();
                String jobString = job.getText().toString().trim();
                String cityString = city.getText().toString().trim();
                String phoneString = phone.getText().toString().trim();

                String id_user  = Integer.toString(account.getIdUser());
                Map<String, String> params = new Hashtable<>();
                params.put("id_user", id_user);
                params.put("name", nameString);
                params.put("gender", genderString);
                params.put("birthday", dobString);
                params.put("job", jobString);
                params.put("city", cityString);
                params.put("phone", phoneString);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
