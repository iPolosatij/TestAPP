package space.digitallab.testapp;

import android.os.Bundle;
import android.widget.TextView;


import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;


public class About extends AppCompatActivity {

    TextView infoAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

    }
}
