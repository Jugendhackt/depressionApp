package de.jugendhackt.depressionapp;
//import android libraries
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
//import java libraries
import java.text.DecimalFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

//needed variables
    private String birthday;
    private String timeOfBirth;
    private String sex;
    private String Ort;
    private String Name;



    private CountDownTimer countDownTimer;
    private long timeLeftInSeconds = 10000;

    TextView secondsLifeTime;
    TextView PeopleDead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences pref = getApplicationContext().getSharedPreferences("Preferences",0);
        SharedPreferences.Editor editor = pref.edit();

        birthday = pref.getString("birthday", "29.03.2000");
        sex = pref.getString("sex", "Male");
        Ort = pref.getString("Ort", null);
        Name = pref.getString("Name", "Marina");
        timeOfBirth = pref.getString("timeOfBirth", "12:00");

        //"access" to Textfields in UI
        TextView helloTextView = findViewById(R.id.helloTv);
        TextView wastedLifetimeTextView = findViewById(R.id.wastedLifetimePercent);
        TextView wastedLifetimeTextViewText = findViewById(R.id.wastedLifetimeTv);
        ProgressBar progressBar = findViewById(R.id.wastedLifetimeProgressBar);
        View trennlinie1 = findViewById(R.id.line1);



//hiding  information fields user does not want to see
        if (!pref.getBoolean("wastedLifetimeCheckbox", true)) {
            helloTextView.setVisibility(View.GONE);
            wastedLifetimeTextView.setVisibility(View.GONE);
            wastedLifetimeTextViewText.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            trennlinie1.setVisibility(View.GONE);
        } else {
            helloTextView.setVisibility(View.VISIBLE);
            wastedLifetimeTextView.setVisibility(View.VISIBLE);
            wastedLifetimeTextViewText.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            trennlinie1.setVisibility(View.VISIBLE);
        }


        TextView coundownTextView = findViewById(R.id.countdownTv);
        TextView countdownValueTextView = findViewById(R.id.countdownValueTv);
        View trennlinie2 = findViewById(R.id.line2);

        if (!pref.getBoolean("deathCountdownCheckbox", true)) {
            coundownTextView.setVisibility(View.GONE);
            countdownValueTextView.setVisibility(View.GONE);
            trennlinie2.setVisibility(View.GONE);

        } else {
            coundownTextView.setVisibility(View.VISIBLE);
            countdownValueTextView.setVisibility(View.VISIBLE);
            trennlinie2.setVisibility(View.VISIBLE);
        }


        TextView whatArchievedTv = findViewById(R.id.whatArchievedTv);
        TextView whatArchievedTvText = findViewById(R.id.whatArchievedTvText);
        View trennlinie3 = findViewById(R.id.line3);

        if (!pref.getBoolean("famousAchievementsCheckbox", true)) {
            whatArchievedTv.setVisibility(View.GONE);
            whatArchievedTvText.setVisibility(View.GONE);
            trennlinie3.setVisibility(View.GONE);

        } else {
            whatArchievedTv.setVisibility(View.VISIBLE);
            whatArchievedTvText.setVisibility(View.VISIBLE);
            trennlinie3.setVisibility(View.VISIBLE);
        }


        TextView peopleDiedTextView = findViewById(R.id.peopleDiedTv);
        TextView peopleDiedCounterTextView = findViewById(R.id.peopleDiedCounterTv);

        if (!pref.getBoolean("deathRateCheckbox", true)) {
            peopleDiedTextView.setVisibility(View.GONE);
            peopleDiedCounterTextView.setVisibility(View.GONE);

        } else {
            peopleDiedTextView.setVisibility(View.VISIBLE);
            peopleDiedCounterTextView.setVisibility(View.VISIBLE);
        }



        PeopleDead = findViewById(R.id.peopleDiedCounterTv);
        PeopleDead.setText(String.valueOf(peopleDied()));

        DecimalFormat f = new DecimalFormat("#0.00");

        TextView wastedLifeTimeTextView = findViewById(R.id.wastedLifetimePercent);
        wastedLifeTimeTextView.setText(String.valueOf(f.format(percentagelived())) + "%");

        ProgressBar lifetimeProgressBar = findViewById(R.id.wastedLifetimeProgressBar);
        lifetimeProgressBar.setProgress((int)(percentagelived()));

        helloTextView = findViewById(R.id.helloTv);
        helloTextView.setText("Hello "+ Ngiame+ ", you wasted");

        if((1000000000 - (int)secsAlive()) > 0) {
            timeLeftInSeconds = 1000000000 - (int) secsAlive();
        } else {
            timeLeftInSeconds = 1;
            System.out.println("Works");
        }



        secondsLifeTime = (TextView)findViewById(R.id.countdownValueTv);




        countDownTimer = new CountDownTimer(timeLeftInSeconds*1000, 1000) {
            TextView secondsLifeTime = findViewById(R.id.countdownValueTv);
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInSeconds = millisUntilFinished;


                secondsLifeTime.setText(String.valueOf(millisUntilFinished/1000));

                PeopleDead.setText(String.valueOf(peopleDied()));




            }

            @Override
            public void onFinish() {

                secondsLifeTime.setText("You missed. You're too late!");

            }
        }.start();
    }

    public void clickDoNothing(View v)//does nothing and insults you
    {
        Toast.makeText(getApplicationContext(), "Can't you read?", Toast.LENGTH_SHORT).show();
    }

    public void countdown(){ //countdown to your billionth second subtracting your age in seconds
        int secondslived = dayslived()*86400;
        int timeremaining = 1000000000-secondslived;
    }

    public int lifeexpectancy() {//assigning life expectancies dependant of sex and residence using switch case
        int expectancy;
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
                default:
                    switch(sex){
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
        }
        return expectancy;
    }

    public double percentagelived(){//returns the percentage of passed live in relation to average life expectancy
        return (dayslived()/365.25 /lifeexpectancy()) * 100;
    }
    public int peopleDied() {//counts dead people by multiplying the percentage of the current day that as already passed with the average number of dead people per day
        Calendar cal = Calendar.getInstance();
        double h = cal.get(Calendar.HOUR_OF_DAY);
        double m = cal.get(Calendar.MINUTE);
        double s = cal.get(Calendar.SECOND);
        return (int)(((h + m/60 + s/3600) / 24) * 151600);
    }
    public int dayslived(){//returns age in days
        int day = Integer.parseInt(birthday.substring(0,2));
        int month = Integer.parseInt(birthday.substring(3,5));
        int year = Integer.parseInt(birthday.substring(6,10));


        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        return (int)((cal.get(Calendar.YEAR)-year)*365+(cal.get(Calendar.MONTH)-month)*30.5+(cal.get(Calendar.DAY_OF_MONTH)-day));
    }


    public double secsAlive() {//return age in seconds
        Calendar cal = Calendar.getInstance();
        double hour = Integer.parseInt(timeOfBirth.substring(0,2));
        double minute = Integer.parseInt(timeOfBirth.substring(3,5));
        double s = cal.get(Calendar.SECOND);
        double h = cal.get(Calendar.HOUR_OF_DAY);
        double m = cal.get(Calendar.MINUTE);
        return  ((dayslived() * 24 + (h - hour)) * 3600 + (m - minute) * 60) + s;
    }
}
