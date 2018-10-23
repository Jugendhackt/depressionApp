package de.jugendhackt.depressionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    //private String birthday = "12.10.2000";
  //  private String timeOfBirth = "04:34";
    private String sex = "Female";
    private String Ort = "Bayern";
    private String Name = "Max";

    private int birthDay;
    private int birthMonth;
    private int birthYear;

    private int birthHour;
    private int birthMinute;





    private long timeLeftInSeconds = 10000;

    TextView secondsLifeTime;
    TextView PeopleDead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CountDownTimer countDownTimer;
        CountDownTimer countDownTimer2;

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Preferences",0);
        SharedPreferences.Editor editor = pref.edit();

       // birthday = pref.getString("birthday", "29.03.2000");
        sex = pref.getString("sex", "Male");
        Ort = pref.getString("Ort", null);
        Name = pref.getString("Name", "Marina");
        //timeOfBirth = pref.getString("timeOfBirth", "12:00");

        birthDay = pref.getInt("birthDay", 0);
        birthMonth = pref.getInt("birthMonth", 0);
        birthYear = pref.getInt("birthYear", 0);
        birthHour = pref.getInt("birthHour", 0);
        birthMinute = pref.getInt("birthMinute", 0);





        choosePerson();


        TextView helloTextView = findViewById(R.id.helloTv);
        TextView wastedLifetimeTextView = findViewById(R.id.wastedLifetimePercent);
        TextView wastedLifetimeTextViewText = findViewById(R.id.wastedLifetimeTv);
        ProgressBar progressBar = findViewById(R.id.wastedLifetimeProgressBar);
        View trennlinie1 = findViewById(R.id.line1);




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
        helloTextView.setText("Hello "+ Name+ ", you wasted");


        if((1000000000 - (int)secsAlive()) > 0) {
            timeLeftInSeconds = 1000000000 - (int) secsAlive();
        } else {
            timeLeftInSeconds = 1;
            System.out.println("Works");
        }



        secondsLifeTime = (TextView)findViewById(R.id.countdownValueTv);




        new CountDownTimer(timeLeftInSeconds*1000, 1000) {
            TextView secondsLifeTime = findViewById(R.id.countdownValueTv);
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInSeconds = millisUntilFinished;


                secondsLifeTime.setText(String.valueOf(millisUntilFinished/1000));

               // PeopleDead.setText(String.valueOf(peopleDied()));




            }

            @Override
            public void onFinish() {

                secondsLifeTime.setText("You missed it. You're too late!");

            }
        }.start();



       // loop();



        countDownTimer2 = new CountDownTimer(1000000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                PeopleDead.setText(String.valueOf(peopleDied()));




            }

            @Override
            public void onFinish() {



            }
        }.start();



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        //menu.add("Save").setIcon(R.drawable.ic_launcher_round_start).
        //setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

   return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                   /* Intent intent = new Intent(this, StartActivity.class);
                    startActivity(intent);
                    finish();*/

                //accesses shared information amongst all classes
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Preferences", 0);
                SharedPreferences.Editor editor = pref.edit();
                Intent intent = new Intent(this, StartActivity.class);

                editor.putBoolean("changedSettings", true);

                editor.commit();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();



                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


    public void clickDoNothing(View v)
    {
        Toast.makeText(getApplicationContext(), "Can't you read?", Toast.LENGTH_SHORT).show();
    }




    /*public int secondslived(){
        int hour = Integer.parseInt(birthday.substring(0,2));
        int second = Integer.parseInt(birthday.substring(3,5));


        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        return (int)((cal.get(Calendar.YEAR)-year)*365+(cal.get(Calendar.MONTH)-month)*30.5+(cal.get(Calendar.DAY_OF_MONTH)-day));
    }
    */


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



    private void choosePerson() {
        TextView personTextView = findViewById(R.id.whatArchievedTvText);
        TextView ageTextView = findViewById(R.id.whatArchievedTv);
        int yearsOld = (int)(dayslived()/365.25);
        System.out.println("Tage alt: " + dayslived());

        ageTextView.setText("What others achieved at your age (" + yearsOld + ")");




        switch (yearsOld) {
            case 10:
                personTextView.setText("Recording artist Stevie Wonder was signed by Motown records.");
                break;

            case 11:
                personTextView.setText("Pilot Victoria Van Meter became the youngest girl ever to fly across the United States.");
                break;

            case 12:
                personTextView.setText("Albert Einstein taught himself Euclidean geometry. He also dedicated himself to solving the riddle of the \"huge world.\"");
                break;

            case 13:
                personTextView.setText("Pablo Picasso was so skilled at drawing that his father handed over his own brushes and paints and gave up painting.");
                break;

            case 14:
                personTextView.setText("Given the choice of a bicycle or guitar as a birthday gift, Kurt Cobain chose guitar.");
                break;

            case 15:
                personTextView.setText("Anne Frank wrote the final entry in her diary.");
                break;

            case 16:
                personTextView.setText("Albert Einstein, with poor grades in geography, history and languages, briefly dropped out of school. He also wrote an essay about a thought experiment that contained the beginnings of the special theory of relativity.");
                break;

            case 17:
                personTextView.setText("Nicolo Paganini dazzled audiences with his virtuosity and pawned his violin in order to pay gambling debts.");
                break;

            case 18:
                personTextView.setText("19th century composer Franz Schubert wrote nearly 200 songs (including two of his best songs), 3 masses, 3 symphonies, and a great deal of piano and chamber music before turning 19.");
                break;

            case 19:
                personTextView.setText("Randy Gardner stayed awake for 264 hours for a high school science project.");
                break;

            case 20:
                personTextView.setText("Sir Isaac Newton began developing a new branch of mathematics that would help him precisely predict the position of the planets at any given time. Today we call this branch differential and integral calculus.");
                break;

            case 21:
                personTextView.setText("College dropout Steven Jobs co-founded Apple Computer.");
                break;

            case 22:
                personTextView.setText("Charles Darwin set off as ship's naturalist on a voyage to South America and the Galapagos Islands.");
                break;

            case 23:
                personTextView.setText("Jamaican-born Barrington Irving became the youngest person to fly around the world solo. He had constructed the plane from over $300,000 in donated parts.");
                break;

            case 24:
                personTextView.setText("Johannes Kepler defended the Copernican theory and described the structure of the solar system.");
                break;

            case 25:
                personTextView.setText("By this age, Charles Chaplin had appeared in 35 films.");
                break;

            default:
                switch (yearsOld/5) {
                    case 6:
                        personTextView.setText("Bill Gates was the first person ever to become a billionaire by age 30.");
                        break;

                    case 7:
                        personTextView.setText("Amedeo Avogadro developed Avogadro's hypothesis.");
                        break;

                    case 8:
                        personTextView.setText("John Glenn became the first American to orbit the Earth.");
                        break;

                    case 9:
                        personTextView.setText("Andre Marie Ampere, a French physicist, discovered the rules relating magnetic fields and electric currents.");
                        break;

                    case 10:
                        personTextView.setText("Hermann Hesse wrote Steppenwolf, which dealt with man's double nature.");
                        break;

                    case 11:
                        personTextView.setText("Einstein achieved a major new result in the general theory of relativity.");
                        break;

                    case 12:
                        personTextView.setText("Benjamin Franklin helped draft the Declaration of Independence.");
                        break;

                    case 13:
                        personTextView.setText("Warren Buffett set up a $30 billion contribution to the Bill and Melinda Gates Foundation for use in various world-wide cheritable causes.");
                        break;

                    case 14:
                        personTextView.setText("Michelangelo created the architectural plans for the Church of Santa Maria degli Angeli.");
                        break;

                    default:
                        personTextView.setText("Jeanne Calment reached an age age of 122 years.");
                }



        }

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


    public int dayslived(){


        Calendar cal = Calendar.getInstance();

        System.out.println((cal.get(Calendar.YEAR) + " " + cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH)));

        return (int)((cal.get(Calendar.YEAR)-birthYear)*365.25+(cal.get(Calendar.MONTH) + 1 -birthMonth)*30.43687+(cal.get(Calendar.DAY_OF_MONTH)-birthDay));
    }


    public double secsAlive() {
        Calendar cal = Calendar.getInstance();
      
        double s = cal.get(Calendar.SECOND);
        double h = cal.get(Calendar.HOUR_OF_DAY);
        double m = cal.get(Calendar.MINUTE);
        return  ((dayslived() * 24 + (h - birthHour)) * 3600 + (m - birthMinute) * 60) + s;
    }
}
