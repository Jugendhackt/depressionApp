package de.jugendhackt.depressionapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class StartActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    TextView dateTextView;
    int day, month, year, hour, minute;
    int yearFinal, monthFinal, dayFinal, hourFinal, minuteFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        //sets sex
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Gender", "Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dateTextView = findViewById(R.id.dateOfBirthET);


        Spinner dropdown1 = findViewById(R.id.spinner1);

        //array with regions of residence (German)
        String[] items1 = new String[]{
                "State", "Baden-Württemberg", "Bayern", "Berlin", "Brandenburg", "Bremen","Hamburg",
                "Hessen","Mecklenburg-Vorpommern", "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
                "Saarland","Sachsen", "Sachsen-Anhalt", "Schleswig-Holstein", "Thüringen"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);


        EditText dateOfBirthEditText = findViewById(R.id.dateOfBirthET);



        dateOfBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(StartActivity.this, StartActivity.this, year, month, day);

                DatePicker datePicker = new DatePicker(getApplicationContext());
                datePicker.setCalendarViewShown(false);

                datePickerDialog.show();
            }
        });


    }
        //what happens when you click a button
    public void onClickBtn(View v)
    {

        TextView nameTextView = findViewById(R.id.Name);
        Spinner stateSpinner = findViewById(R.id.spinner);
        Spinner genderSpinner = findViewById(R.id.spinner1);

        if(!(dateTextView.getText().toString().trim().length() < 1)) {

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
            Toast.makeText(getApplicationContext(), "Please enter your date of birth", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int i1, int i2, int i3) {

        yearFinal = i1;
        monthFinal = i2 + 1;
        dayFinal = i3;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);



        TimePickerDialog timePickerDialog = new TimePickerDialog(StartActivity.this, StartActivity.this, hour, minute, true);
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int i1, int i2) {
        hourFinal = i1;
        minuteFinal = i2;

        String hourFinalString = String.valueOf(hourFinal);
        String minuteFinalString = String.valueOf(minuteFinal);

        String monthFinalString = String.valueOf(monthFinal);
        String dayFinalString = String.valueOf(dayFinal);


        if (monthFinal < 10) {
            monthFinalString = addZeroToTheBeginning(monthFinal);
        }

        if (dayFinal < 10) {
            dayFinalString = addZeroToTheBeginning(dayFinal);
        }

        if (hourFinal < 10) {
            hourFinalString = addZeroToTheBeginning(hourFinal);
        }

        if (minuteFinal < 10) {
            minuteFinalString = addZeroToTheBeginning(minuteFinal);
        }

        dateTextView.setText(dayFinalString + "." + monthFinalString + "." + String.valueOf(yearFinal) + " " + hourFinalString + ":" + minuteFinalString);

    }

    private String addZeroToTheBeginning(int value) {
        return "0" + value;
    }
}
