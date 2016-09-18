package edu.uco.houselannister.saveasingle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.uco.houselannister.saveasingle.model.ZipCode;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    public String something(){
        ZipCode x = new ZipCode("83838",23.34343,-38.333,"Edmond, OK");
        return x.Name();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this is master!

        mTextView = (TextView) findViewById(R.id.sample_TextView);
        mTextView.setText(something());
    }
}
