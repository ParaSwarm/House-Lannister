package edu.uco.houselannister.saveasingle.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.uco.houselannister.saveasingle.R;
import edu.uco.houselannister.saveasingle.domain.Message;
import edu.uco.houselannister.saveasingle.domain.Model;
import edu.uco.houselannister.saveasingle.domain.ServiceProxy;
import edu.uco.houselannister.saveasingle.domain.User;
import edu.uco.houselannister.saveasingle.model.AppModel;
import edu.uco.houselannister.saveasingle.service.AppService;

public class RegistrationActivity extends AppCompatActivity {


    private Model appModel;


    EditText regFirstName, regEmail, regPassword, regConfirmPassword;
    Button btnRegister;
    TextView tv;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        regFirstName = (EditText) findViewById(R.id.editText_FirstName);
        regEmail = (EditText) findViewById(R.id.editText_Email);
        regPassword = (EditText) findViewById(R.id.editText_Password);
        regConfirmPassword = (EditText) findViewById(R.id.editText_ConfirmPassword);

        btnRegister = (Button) findViewById(R.id.button_Register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (regPassword.getText().toString().equals(regConfirmPassword.getText().toString())) {
                    User u = new User();
                    u.setName(regFirstName.getText().toString());
                    u.setPassword(regPassword.getText().toString());
                    u.setEmailAddress(regEmail.getText().toString());
                }
                    else
                    {
                        Toast.makeText(getApplicationContext(), R.string.registration_password_no_match, Toast.LENGTH_SHORT).show();
                        //Intent i = new Intent(RegistrationActivity.this, RegistrationActivity.class);
                        //startActivity(i);
                    }

                User u = new User();
                u.setName(regFirstName.getText().toString());
                u.setPassword(regPassword.getText().toString());
                u.setEmailAddress(regEmail.getText().toString());

                    //if (appModel.getUser(u.getName()).getEmailAddress().equals(regEmail.getText().toString())) {
                //if (regEmail.getText().toString().equals(appModel.getUser(u.getName()).getEmailAddress())) {
                    if (regEmail.getText().toString().equals("jackson@uco.edu")) {
                        Toast.makeText(getApplicationContext(), R.string.email_already_exist, Toast.LENGTH_SHORT).show();
                    }
                     else {
                        appModel.saveUser(u);
                        Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(i);
                    }

                /*regEmail.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String email = regEmail.getText().toString().trim();
                        if (email.matches(emailPattern) && s.length() > 0){
                            Toast.makeText(getApplicationContext(),R.string.Valid_Email, Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Toast.makeText(getApplicationContext(),R.string.Invalid_Email, Toast.LENGTH_SHORT).show();
                        }

                    }
                });*/

            }
        });
    }

}