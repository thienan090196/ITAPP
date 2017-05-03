package com.example.cuongtran.itapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuongtran.itapp.R;
import com.example.cuongtran.itapp.model.Account;
import com.example.cuongtran.itapp.model.AccountBuilder;

/**
 * Created by linh.tran1 on 26/04/2017.
 */

public class ProfileActivity extends AppCompatActivity{

    private TextView name;
    private TextView gender;
    private TextView dob;
    private TextView phone;
    private TextView job;
    private TextView city;
    private ImageView imgView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent() ;
        final Account account = (Account) intent.getSerializableExtra("account");

        imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangeProfileActivity.class );
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });

        name = (TextView) findViewById(R.id.name);
        name.setText(account.getProfile().getName());

        gender = (TextView) findViewById(R.id.gender);
        if(account.getProfile().getGender() == 1){
            gender.setText("Male");
        }else{
            gender.setText("FeMale");
        }


        dob = (TextView) findViewById(R.id.dob);
        dob.setText(account.getProfile().getDob());

        phone = (TextView) findViewById(R.id.phone);
        phone.setText(account.getProfile().getPhone());

        job = (TextView) findViewById(R.id.job);
        job.setText(account.getProfile().getJob());

        city = (TextView) findViewById(R.id.city);
        city.setText(account.getProfile().getCity());

    }
}
