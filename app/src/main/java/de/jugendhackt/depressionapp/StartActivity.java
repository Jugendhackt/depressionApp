package de.jugendhackt.depressionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Spinner dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{"Gender", "Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"State", "Baden-Württemberg", "Bayern", "Berlin", "Brandenburg", "Bremen","Hamburg",
                "Hessen","Mecklenburg-Vorpommern", "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
                "Saarland","Sachsen", "Sachsen-Anhalt", "Schleswig-Holstein", "Thüringen"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);




    }

    public void onClickBtn(View v)
    {
        TextView bd = findViewById(R.id.Date);
        TextView Nm = findViewById(R.id.Name);
        TextView tb = findViewById(R.id.Hours);
        Spinner sx = findViewById(R.id.spinner);
        Spinner rt = findViewById(R.id.spinner1);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Preferences", 0);
        SharedPreferences.Editor editor = pref.edit();
        Intent intent = new Intent(this, MainActivity.class);
        editor.putString("birthday", bd.getText().toString());
        editor.putString("Name", Nm.getText().toString());
        editor.putString("timeOfBirth", tb.getText().toString());
        editor.putString("sex", sx.getSelectedItem().toString());
        editor.putString("Ort", rt.getSelectedItem().toString());
        editor.commit();
        startActivity(intent);
    }
}
