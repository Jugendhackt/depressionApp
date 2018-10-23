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

import java.util.Calendar;

public class StartActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText dateEditText;
    EditText timeEditText;


    int day, month, year, hour, minute;
    int yearFinal, monthFinal, dayFinal, hourFinal, minuteFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        //sets sex
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        dateEditText = findViewById(R.id.dateOfBirthET);
        timeEditText = findViewById(R.id.timeOfBirthET);


        Spinner dropdown1 = findViewById(R.id.stateSpinner);

        //array with regions of residence (German)
        String[] items1 = new String[]{
                "State", "Baden-Württemberg", "Bayern", "Berlin", "Brandenburg", "Bremen","Hamburg",
                "Hessen","Mecklenburg-Vorpommern", "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
                "Saarland","Sachsen", "Sachsen-Anhalt", "Schleswig-Holstein", "Thüringen"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);


        //array with genders
        Spinner dropdown = findViewById(R.id.genderSpinner);
        String[] items = new String[]{"Gender", "Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);



        EditText dateOfBirthEditText = findViewById(R.id.dateOfBirthET);

        dateOfBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (year == 0) {
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    day = c.get(Calendar.DAY_OF_MONTH);

                } else {
                    year = yearFinal;
                    month = monthFinal - 1;
                    day = dayFinal;
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

                if (hour == 0) {
                    Calendar c = Calendar.getInstance();
                    hour = c.get(Calendar.HOUR_OF_DAY);
                    minute = c.get(Calendar.MINUTE);
                } else {
                    hour = hourFinal;
                    minute = minuteFinal;
                }


                TimePickerDialog timePickerDialog = new TimePickerDialog(StartActivity.this, StartActivity.this, hour, minute, true);
                timePickerDialog.show();
            }
        });


    }



        //what happens when you click a button
    public void onClickBtn(View v)
    {

        TextView nameTextView = findViewById(R.id.userNameET);
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

                if (nameTextView.getText().toString().trim().length() < 1) {
                    editor.putString("Name", "Nobody");
                } else {
                    editor.putString("Name", nameTextView.getText().toString());
                }


                // editor.putString("birthday", dateTextView.getText().toString());
                editor.putInt("birthDay", dayFinal);
                editor.putInt("birthMonth", monthFinal);
                editor.putInt("birthYear", yearFinal);
                editor.putInt("birthHour", hourFinal);
                editor.putInt("birthMinute", minuteFinal);

                editor.putString("sex", stateSpinner.getSelectedItem().toString());
                editor.putString("Ort", genderSpinner.getSelectedItem().toString());
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
