package com.example.cuongtran.itapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.cuongtran.itapp.R;
import com.example.cuongtran.itapp.app.AppController;
import com.example.cuongtran.itapp.utils.Common;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

public class RegisterActivity extends BaseActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    private static final String URL_REGISTER = Common.API_SERVER_IP + "api/register" ;
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnRegister;
    private Button btnLinkToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmail = (EditText) findViewById(R.id.edtemail);
        edtPassword = (EditText) findViewById(R.id.edtpassword);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeRegister();
            }
        });

        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginActivity);
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    //excute register
    private void executeRegister() {
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Log.d(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONObject dataObject = jsonObject.getJSONObject("dataObject");
                            int status = jsonObject.getInt("status");
                            if (status == 1) {
                                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                                loginActivity.putExtra(EMAIL, dataObject.getString(EMAIL));
                                loginActivity.putExtra(PASSWORD, dataObject.getString(PASSWORD));
                                startActivity(loginActivity);
                                Toast.makeText(RegisterActivity.this, "Register Succeeded!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Register Failed!", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(RegisterActivity.this, "Register Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(RegisterActivity.this, edtEmail.getText().toString().trim() + edtPassword.getText().toString().trim(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                Map<String, String> params = new Hashtable<>();
                //addding params
                params.put(KEY_USER_EMAIL, email);
                params.put(KEY_USER_PASSWORD, password);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}