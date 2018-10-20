package de.jugendhackt.depressionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.time.LocalDate;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    public void countdown(int dd, int mm, int yyyy){
        int day = Integer.parseInt(birthday.substring(0,2));
        int month = Integer.parseInt(birthday.substring(3,5));
        int year = Integer.parseint(birthday.substring(7,11));
        Calendar cal = Calendar.getInstance();
        int dayslived = (int)(cal.get(Calendar.YEAR)*365+cal.get(Calendar.MONTH)*30.5+cal.get(Calendar.DAY_OF_MONTH));
        int secondslived = dayslived*86400;
        int timeremaining = 1000000000-secondslived;
    }


}
