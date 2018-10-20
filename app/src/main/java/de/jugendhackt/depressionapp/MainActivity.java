package de.jugendhackt.depressionapp;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView PeopleDead = findViewById(R.id.peopleDiedCounterTv);
        PeopleDead.setText(String.valueOf(peopleDied()));

        TextView wastedLifeTimeTextView = findViewById(R.id.wastedLifetimePercent);





        wastedLifeTimeTextView.setText(String.valueOf(percentagelived()));






    }

    private String birthday = "12.10.2000";
    private String sex = "Female";
    private String Ort = "Bayern";
    private String Name = "Max";






    public int dayslived(){
        int day = Integer.parseInt(birthday.substring(0,2));
        int month = Integer.parseInt(birthday.substring(3,5));
        int year = Integer.parseInt(birthday.substring(6,10));


        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
       return (int)((cal.get(Calendar.YEAR)-year)*365+(cal.get(Calendar.MONTH)-month)*30.5+(cal.get(Calendar.DAY_OF_MONTH)-day));
    }

    public void countdown(){
        int secondslived = dayslived()*86400;
        int timeremaining = 1000000000-secondslived;
    }

    public int lifeexpectancy() {
        int expectancy = 80;
        switch (Ort) {
            case "Baden-Württemberg":
                switch (sex) {
                    case "Male":
                        expectancy = 80;
                        break;
                    case "Female":
                        expectancy = 84;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Bayern":
                switch (sex) {
                    case "Male":
                        expectancy = 79;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Berlin":
                switch (sex) {
                    case "Male":
                        expectancy = 78;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Brandenburg":
                switch (sex) {
                    case "Male":
                        expectancy = 77;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Bremen":
                switch (sex) {
                    case "Male":
                        expectancy = 77;
                        break;
                    case "Female":
                        expectancy = 82;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Hamburg":
                switch (sex) {
                    case "Male":
                        expectancy = 79;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Hessen":
                switch (sex) {
                    case "Male":
                        expectancy = 79;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Mecklenburg-Vorpommern":
                switch (sex) {
                    case "Male":
                        expectancy = 77;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Niedersachsen":
                switch (sex) {
                    case "Male":
                        expectancy = 78;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Nordrhein-Westfahlen":
                switch (sex) {
                    case "Male":
                        expectancy = 78;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Rheinland-Pfalz":
                switch (sex) {
                    case "Male":
                        expectancy = 79;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Saarland":
                switch (sex) {
                    case "Male":
                        expectancy = 78;
                        break;
                    case "Female":
                        expectancy = 82;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Sachsen":
                switch (sex) {
                    case "Male":
                        expectancy = 78;
                        break;
                    case "Female":
                        expectancy = 84;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Sachsen_Anhalt":
                switch (sex) {
                    case "Male":
                        expectancy = 76;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Schleswig-Holstein":
                switch (sex) {
                    case "Male":
                        expectancy = 78;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
            case "Thüringen":
                switch (sex) {
                    case "Male":
                        expectancy = 77;
                        break;
                    case "Female":
                        expectancy = 83;
                        break;
                    default:
                        expectancy = 81;
                        break;
                }
                break;
        }
        return expectancy;
    }

    public double percentagelived(){
        return (dayslived()/365.25 /lifeexpectancy()) * 100;
    }
    public int peopleDied() {
        Calendar cal = Calendar.getInstance();
        double h = cal.get(Calendar.HOUR_OF_DAY);
        double m = cal.get(Calendar.MINUTE);
        double s = cal.get(Calendar.SECOND);
        return (int)(((h + m/60 + s/3600) / 24) * 151600);
    }
}
