package de.jugendhackt.depressionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    public void countdown(int yyyy, int mm, int dd){
        int secLive = yyyy*31557600+mm*2635200+dd*86400;

    }


}
