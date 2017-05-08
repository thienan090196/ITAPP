package com.example.cuongtran.itapp.activity;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cuongtran.itapp.R;
import com.example.cuongtran.itapp.app.AppController;
import com.example.cuongtran.itapp.fragment.ListPostFragment;
import com.example.cuongtran.itapp.model.Account;
import com.example.cuongtran.itapp.model.AccountBuilder;
import com.example.cuongtran.itapp.model.Profile;
import com.example.cuongtran.itapp.utils.Common;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class HomeActivity extends AppCompatActivity{

    private static final String url_post = Common.API_SERVER_IP + "api/post";
    private static final String url_logout = Common.API_SERVER_IP + "api/logout";
    SharedPreferences sharedPreferences;

    private ImageButton profile;
    private Account account;
    private EditText content;
    private ImageButton logout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profile = (ImageButton) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                excuteDisplayProfile();
            }
        });
        logout = (ImageButton) findViewById(R.id.ibt_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("ac");

        Bundle bundle = new Bundle();
        bundle.putSerializable("account", account);
//
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ListPostFragment listPostFragment = new ListPostFragment();
        listPostFragment.setArguments(bundle);
        ft.add(R.id.idListPost, listPostFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        Button btPost = (Button) findViewById(R.id.btPost);
        btPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = (EditText) findViewById(R.id.contentPost);
                executePost();
                //content.setText("");

            }
        });
    }

    private void excuteDisplayProfile() {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("account",account);
        startActivity(intent);
    }

    private void executePost(){
        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_post,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        JSONObject jsonObject = null;
                        //JSONObject dataObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            //dataObject = jsonObject.getJSONObject("dataObject");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        content.setText("");
                        Account account = AccountBuilder.getAccount(jsonObject);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("account", account);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ListPostFragment listPostFragment = new ListPostFragment();
                        listPostFragment.setArguments(bundle);
                        ft.replace(R.id.idListPost, listPostFragment);
                        //ft.addToBackStack(null);
                        ft.commit();



                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(HomeActivity.this, "Cannot connect to server!", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                String id_user = Integer.toString(account.getIdUser());
                Map<String,String> params = new Hashtable<>();


                String contentString = content.getText().toString().trim();


                params.put("id_user", id_user);
                params.put("input", contentString);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    private void logout(){

        final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_logout,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        try {
                            JSONObject  jsonObject = new JSONObject(response);
                           if(jsonObject.isNull("dataObject")){
                               Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                               startActivity(intent);
                               Toast.makeText(HomeActivity.this, "Logout successful", Toast.LENGTH_LONG).show();
                           }
                        } catch (JSONException e) {
                            Toast.makeText(HomeActivity.this, "Error !", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(HomeActivity.this, "Cannot connect to server!", Toast.LENGTH_LONG).show();
            }
        });

        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
