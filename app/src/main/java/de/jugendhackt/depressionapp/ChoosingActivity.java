package de.jugendhackt.depressionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ChoosingActivity extends AppCompatActivity {



    Boolean wLCBoolean = false;
    Boolean dCCBoolean = false;
    Boolean dRCBoolean = false;
    Boolean fACBoolean = false;
    //Checkboxes are not checked by default by assigning boolean values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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