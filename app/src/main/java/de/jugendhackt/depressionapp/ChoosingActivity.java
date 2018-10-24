package de.jugendhackt.depressionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class ChoosingActivity extends AppCompatActivity {

    //Checkboxes are checked by default by assigning boolean values
    Boolean wLCBoolean = true;
    Boolean dCCBoolean = true;
    Boolean dRCBoolean = true;
    Boolean fACBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
//checking buttons selected value
        setContentView(R.layout.choosing_activity);

        CheckBox wLC = findViewById(R.id.wastedLifetimeCheckbox);
        CheckBox dCC = findViewById(R.id.deathCountdownCheckbox);
        CheckBox dRC = findViewById(R.id.deathRateCheckbox);
        CheckBox fAC = findViewById(R.id.famousAchievementsCheckbox);

        wLC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                wLCBoolean = isChecked;
            }
        });

        dCC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                dCCBoolean = isChecked;
            }
        });

        dRC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                dRCBoolean = isChecked;
            }
        });

        fAC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                fACBoolean = isChecked;
            }
        });




    }




    public void onClickNext(View v)
    {
        //accesses shared information amongst all classes
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Preferences", 0);
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, MainActivity.class);



        editor.putBoolean("wastedLifetimeCheckbox", wLCBoolean);
        editor.putBoolean("deathCountdownCheckbox", dCCBoolean);
        System.out.println("Checkbox" + dCCBoolean);
        editor.putBoolean("deathRateCheckbox", dRCBoolean);
        editor.putBoolean("famousAchievementsCheckbox", fACBoolean);

        editor.commit();
        startActivity(intent);

    }
}