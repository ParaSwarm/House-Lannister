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

    //EditText regDateOfBirth = (EditText) findViewById(R.id.editText_DateOfBirth);
    /*EditText regDateOfBirth = (EditText) findViewById(R.id.editText_DateOfBirth);

    DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

    String datePickerRegistration = datePicker.getDayOfMonth();*/

   // ArrayList<User> user;
>>>>>>> master

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

        /*String firstNameRegistration = regFirstName.getText().toString();
        String lastNameRegistration = regLastName.getText().toString();
        String emailRegistration = regEmail.getText().toString();
        String passwordRegistration = regPassword.getText().toString();
        String confirmPasswordRegistration = regConfirmPassword.getText().toString();*/

        btnRegister = (Button) findViewById(R.id.button_Register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!regPassword.equals(regConfirmPassword)) {
                    Context context = getApplicationContext();
                    CharSequence text = "Password doesn't match";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                /*Context context = getApplicationContext();
                CharSequence text = "Password match";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/

                User u = new User();
                u.setName(regFirstName.getText().toString());
                u.setPassword(regPassword.getText().toString());
                u.setEmailAddress(regEmail.getText().toString());
                appModel.saveUser(u);

                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);


                    /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                    final EditText et = new EditText(getApplicationContext());
                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(et);
                    // set dialog message
                    alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // show it
                    alertDialog.show();*/


                //Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                //startActivity(intent);
            }
        });
    }



   /* public void onRadioButtonChecked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.maleRadioButton:
                if (checked)
                    break;
            case R.id.femaleRadiobutton:
                if (checked)
                    break;
        }
    }

    public void setDate (View view) {
        //final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        regDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegistrationActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

        private void updateLabel(){

            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            regDateOfBirth.setText(sdf.format(myCalendar.getTime()));
        }*/
    }
>>>>>>> master
