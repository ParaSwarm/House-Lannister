package edu.uco.houselannister.saveasingle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.uco.houselannister.saveasingle.model.ZipCode;

public class MainActivity extends Activity {

    @BindView(R.id.sample_TextView) TextView mTextView;

    public String something(){
        ZipCode x = new ZipCode("83838",23.34343,-38.333,"Edmond, OK");
        return x.Name();
    }
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //this is master!

        //mTextView = (TextView) findViewById(R.id.sample_TextView);
        mTextView.setText(something());
    }
}
