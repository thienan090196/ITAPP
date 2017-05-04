package com.example.cuongtran.itapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.cuongtran.itapp.model.Post;
import com.example.cuongtran.itapp.model.Profile;
import com.example.cuongtran.itapp.utils.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String url_login = Common.API_SERVER_IP + "api/login" ;
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PASSWORD = "password";

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnLinkToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = (EditText) findViewById(R.id.email);
        edtPassword = (EditText) findViewById(R.id.password);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excuteLogin();
            }
        });

        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Move to another activity
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
            }
        });
    }

    @Override
    public void onPermissionsGranted(int requestCode) {

    }

    //excute register
    private void excuteLogin(){
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Log.d(TAG, response.toString());
                        try {
                            //Get api status
                            //int status = response.getInt("status");
                            //Get api message content
                            //String message = response.getString("message");
                            JSONObject jsonObject = new JSONObject(response);

                            JSONObject dataObject = jsonObject.getJSONObject("dataObject");
                            String email = dataObject.getString("email");
                            String password = dataObject.getString("password");
                          //  String id_user = dataObject.getString("id_user");
                            if (email.equals(edtEmail.getText().toString().trim()) && password.equals(edtPassword.getText().toString().trim())) {
                                Toast.makeText(LoginActivity.this, "Login Succeeded!", Toast.LENGTH_LONG).show();
                                Account account = AccountBuilder.getAccount(jsonObject);
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.putExtra("ac", account);
                                startActivity(intent);
                            }
                            else Toast.makeText(LoginActivity.this, "Your email or password is incorrect!", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            Toast.makeText(LoginActivity.this, "Your email or password is incorrect!", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(LoginActivity.this, "Cannot connect to server!", Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        String email = edtEmail.getText().toString().trim();
                        String password = edtPassword.getText().toString().trim();

                        Map<String, String> params = new Hashtable<>();
                        //adding params
                        params.put(KEY_USER_EMAIL, email);
                        params.put(KEY_USER_PASSWORD, password);

                        return params;
                    }
                };
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }


}
