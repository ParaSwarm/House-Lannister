package edu.uco.houselannister.saveasingle.activities;

<<<<<<< HEAD
=======
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
>>>>>>> master
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
import android.widget.EditText;
import android.widget.RadioButton;

import edu.uco.houselannister.saveasingle.R;

public class RegistrationActivity extends AppCompatActivity {


    /*EditText firstNameReg = (EditText) findViewById(R.id.regFirstName);
    EditText lastNameReg = (EditText) findViewById(R.id.regLastName);
    EditText emailReg = (EditText) findViewById(R.id.regEmail);
    EditText passwordReg = (EditText) findViewById(R.id.regPassword);
    EditText confirmPasswordReg = (EditText) findViewById(R.id.regConfirmPassword);

    //Convert Data into string

    String strFirstName = firstNameReg.getText().toString();
    String strLastName = lastNameReg.getText().toString();
    String strEmail = emailReg.getText().toString();
    String strPassword = passwordReg.getText().toString();
    String strConfirmPassword = confirmPasswordReg.getText().toString();

    Button registerButton = (Button) findViewById(R.id.btnRegister);*/
=======
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

    final Calendar myCalendar = Calendar.getInstance();

    private Model appModel;


    EditText regFirstName, regLastName, regEmail, regPassword, regConfirmPassword, regDateOfBirth;
    Button btnRegister;
    TextView tv;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

<<<<<<< HEAD
    //EditText regDateOfBirth = (EditText) findViewById(R.id.editText_DateOfBirth);
    /*EditText regDateOfBirth = (EditText) findViewById(R.id.editText_DateOfBirth);

    DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

    String datePickerRegistration = datePicker.getDayOfMonth();*/

   // ArrayList<User> user;
>>>>>>> master
=======
>>>>>>> dev

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

<<<<<<< HEAD
        /*registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back to login activity
            }
        });*/
    }

    /*public void onRadioButtonChecked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.maleRdoButton:
                if (checked)
                    // Save Gender
                    break;
            case R.id.femaleRdoButton:
                if (checked)
                    // Save Gender
                    break;
        }
    }*/
}
=======
        appModel = AppModel.getAppModelInstance(AppService.getAppServiceInstance());
        regFirstName = (EditText) findViewById(R.id.editText_FirstName);
        //regLastName = (EditText) findViewById(R.id.editText_LastName);
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
                    if (regEmail.getText().toString().equals("sierra@uco.edu")) {
                        Toast.makeText(getApplicationContext(), "Email Already exist", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        appModel.saveUser(u);
                        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }

                else {
                    Toast.makeText(getApplicationContext(), R.string.registration_password_no_match, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                }

                regEmail.addTextChangedListener(new TextWatcher() {
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
                            Toast.makeText(getApplicationContext(),"Valid Email", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            Toast.makeText(getApplicationContext(),"inValid Email", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }

    }
>>>>>>> master
