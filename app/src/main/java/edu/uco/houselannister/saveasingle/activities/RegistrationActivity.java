package edu.uco.houselannister.saveasingle.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

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
