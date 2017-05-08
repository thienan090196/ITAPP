package com.example.cuongtran.itapp.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import com.example.cuongtran.itapp.R;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtName;
    private String gender;
    private static EditText edtBirthday;
    private static String birthday;
    private Spinner spnJob;
    private EditText edtEmail;
    private EditText edtPassword;
    private Spinner spnCity;

    //private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        edtEmail = (EditText) findViewById(R.id.email);

        edtPassword = (EditText) findViewById(R.id.password);

        spnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spnCity.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Nothing
            }
        });
    }


    //Birthday
    public void showDatePickerDialog(View view) {
        DialogFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            //Return what you chose
            String dateToSaveDatabase;
            String dateToDisplay;
            month += 1;

            if (day < 10 && month < 10) {
                dateToSaveDatabase = year + "-" + "0" + month + "-" + "0" + day;
                dateToDisplay = "0" + day + "/" + "0" + month + "/" + year;
            } else if (day < 10 && month >= 10) {
                dateToSaveDatabase = year + "-" + month + "-" + "0" + day;
                dateToDisplay = "0" + day + "/" + month + "/" + year;
            } else if (day >= 10 && month < 10) {
                dateToSaveDatabase = year + "-" + "0" + month + "-" + day;
                dateToDisplay = day + "/" + "0" + month + "/" + year;
            } else {
                dateToSaveDatabase = year + "-" + month + "-" + day;
                dateToDisplay = day + "/" + month + "/" + year;
            }

            edtBirthday.setText(dateToDisplay);
            birthday = dateToSaveDatabase;
        }
    }

}
