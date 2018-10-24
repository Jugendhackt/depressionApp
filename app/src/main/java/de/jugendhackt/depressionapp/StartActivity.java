package de.jugendhackt.depressionapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;

public class StartActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText dateEditText;
    EditText timeEditText;
    EditText nameEditText;


    int day, month, year, hour, minute;
    int yearFinal, monthFinal, dayFinal, hourFinal, minuteFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {

        setTheme(R.style.AppTheme);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Preferences",0);
        SharedPreferences.Editor editor = pref.edit();

        if(!(pref.getBoolean("changedSettings", false))) {

            if (pref.getInt("birthDay", 0) != 0) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }


        //sets sex
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        dateEditText = findViewById(R.id.dateOfBirthET);
        timeEditText = findViewById(R.id.timeOfBirthET);
        nameEditText = findViewById(R.id.userNameET);



        Spinner stateSpinner = findViewById(R.id.stateSpinner);

        //array with regions of residence (German)
        String[] items1 = new String[]{
                "State", "Baden-Württemberg", "Bayern", "Berlin", "Brandenburg", "Bremen","Hamburg",
                "Hessen","Mecklenburg-Vorpommern", "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
                "Saarland","Sachsen", "Sachsen-Anhalt", "Schleswig-Holstein", "Thüringen"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        stateSpinner.setAdapter(adapter1);


        //array with genders
        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        String[] items = new String[]{"Gender", "Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        genderSpinner.setAdapter(adapter);



        EditText dateOfBirthEditText = findViewById(R.id.dateOfBirthET);

        dateOfBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (yearFinal != 0 && monthFinal != 0 && dayFinal != 0) {
                    year = yearFinal;
                    month = monthFinal - 1;
                    day = dayFinal;

                } else {
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);
                }

                DatePickerDialog datePickerDialog = new DatePickerDialog(StartActivity.this, StartActivity.this, year, month, day);

                DatePicker datePicker = new DatePicker(getApplicationContext());
                datePicker.setCalendarViewShown(false);

                datePickerDialog.show();


            }
        });


        EditText timeOfBirthEditText = findViewById(R.id.timeOfBirthET);

        timeOfBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hourFinal != 0 && minuteFinal != 0) {
                    hour = hourFinal;
                    minute = minuteFinal;

                } else {
                    Calendar c = Calendar.getInstance();
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);
                }


                TimePickerDialog timePickerDialog = new TimePickerDialog(StartActivity.this, StartActivity.this, hour, minute, true);
                timePickerDialog.show();
            }
        });




        if(pref.getBoolean("changedSettings", false)) {

            // birthday = pref.getString("birthday", "29.03.2000");
          //  sex = pref.getString("sex", "Male");
          //  Ort = pref.getString("Ort", null);
            //timeOfBirth = pref.getString("timeOfBirth", "12:00");

            nameEditText.setText(pref.getString("Name", "Marina"));

            int indexGender = Arrays.asList(items).indexOf(pref.getString("sex", "Gender"));
            genderSpinner.setSelection(indexGender, false);

            int indexState = Arrays.asList(items1).indexOf(pref.getString("Ort", "State"));
            stateSpinner.setSelection(indexState, false);


            dayFinal = pref.getInt("birthDay", 0);
            monthFinal = pref.getInt("birthMonth", 0);
            yearFinal = pref.getInt("birthYear", 0);
            hourFinal = pref.getInt("birthHour", 0);
            minuteFinal = pref.getInt("birthMinute", 0);


            String monthFinalString = String.valueOf(monthFinal);
            String dayFinalString = String.valueOf(dayFinal);

            if (monthFinal < 10) {
                monthFinalString = addZeroToTheBeginning(monthFinal);
            }

            if (dayFinal < 10) {
                dayFinalString = addZeroToTheBeginning(dayFinal);
            }

            dateEditText.setText(dayFinalString + "." + monthFinalString + "." + String.valueOf(yearFinal));




            String hourFinalString = String.valueOf(hourFinal);
            String minuteFinalString = String.valueOf(minuteFinal);


            if (hourFinal < 10) {
                hourFinalString = addZeroToTheBeginning(hourFinal);
            }

            if (minuteFinal < 10) {
                minuteFinalString = addZeroToTheBeginning(minuteFinal);
            }

            timeEditText.setText(hourFinalString + ":" + minuteFinalString);

            editor.putBoolean("changedSettings", false);

            editor.commit();

        }


    }



        //what happens when you click a button
    public void onClickBtn(View v)
    {


        Spinner stateSpinner = findViewById(R.id.stateSpinner);
        Spinner genderSpinner = findViewById(R.id.genderSpinner);

        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));


        Calendar birthday = Calendar.getInstance();

        birthday.set(yearFinal, monthFinal, dayFinal, hourFinal, minuteFinal);



        if(!(dateEditText.getText().toString().trim().length() < 1) && !(timeEditText.getText().toString().trim().length() < 1)) {

            if(c.after(birthday)) {

                //accesses information shared amongst all classes
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Preferences", 0);
                SharedPreferences.Editor editor = pref.edit();
                Intent intent = new Intent(this, ChoosingActivity.class);

                if (nameEditText.getText().toString().trim().length() < 1) {
                    editor.putString("Name", "Nobody");
                } else {
                    editor.putString("Name", nameEditText.getText().toString());
                }


                // editor.putString("birthday", dateTextView.getText().toString());
                editor.putInt("birthDay", dayFinal);
                editor.putInt("birthMonth", monthFinal);
                editor.putInt("birthYear", yearFinal);
                editor.putInt("birthHour", hourFinal);
                editor.putInt("birthMinute", minuteFinal);

                editor.putString("sex", genderSpinner.getSelectedItem().toString());
                editor.putString("Ort", stateSpinner.getSelectedItem().toString());
                editor.commit();
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Please enter your right date of birth", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Please enter your date of birth", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int i1, int i2, int i3) {

        yearFinal = i1;
        monthFinal = i2 + 1;
        dayFinal = i3;

        String monthFinalString = String.valueOf(monthFinal);
        String dayFinalString = String.valueOf(dayFinal);

        if (monthFinal < 10) {
            monthFinalString = addZeroToTheBeginning(monthFinal);
        }

        if (dayFinal < 10) {
            dayFinalString = addZeroToTheBeginning(dayFinal);
        }

        dateEditText.setText(dayFinalString + "." + monthFinalString + "." + String.valueOf(yearFinal));




    }

    @Override
    public void onTimeSet(TimePicker view, int i1, int i2) {
        hourFinal = i1;
        minuteFinal = i2;

        String hourFinalString = String.valueOf(hourFinal);
        String minuteFinalString = String.valueOf(minuteFinal);


        if (hourFinal < 10) {
            hourFinalString = addZeroToTheBeginning(hourFinal);
        }

        if (minuteFinal < 10) {
            minuteFinalString = addZeroToTheBeginning(minuteFinal);
        }

        timeEditText.setText(hourFinalString + ":" + minuteFinalString);

    }

    private String addZeroToTheBeginning(int value) {
        return "0" + value;
    }
}
