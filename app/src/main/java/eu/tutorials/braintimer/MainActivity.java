package eu.tutorials.braintimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button b1;
    Button b2;
    Button b3;Button b4;
    TextView score;
    TextView timer;TextView cbyt;
    TextView questionview;
    TextView answerview;
    int loca;
    int crct = 0;
    int total = 0;

    boolean nextque = false;
    boolean isrunning = false;

    ArrayList<Integer> numbers = new ArrayList<>();


    public void Answer(View view){
        String tag = view.getTag().toString();
        if(Integer.toString(loca).equals(tag)){
            crct++;
            answerview.setText("Correct");

        }
        else{
            answerview.setText("Wrong");
        }
        total++;
        String y = Integer.toString(crct)+"/"+Integer.toString(total);
        cbyt.setText(y);
        createQuestions();
    }

    public void Gobutton(View view){
        goButton.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        b4.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        cbyt.setVisibility(View.VISIBLE);
        questionview.setVisibility(View.VISIBLE);
        answerview.setVisibility(View.VISIBLE);
        isrunning = true;

        new CountDownTimer(30000,1000){
            public void onTick(long l){
                int sec =(int) l/1000;
                if(sec<10){
                    timer.setText("0"+Integer.toString(sec));
                }
                else{
                    timer.setText(Integer.toString(sec));
                }
            }

            public void onFinish(){
                b1.setVisibility(View.INVISIBLE);
                b2.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                b4.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.INVISIBLE);
                cbyt.setVisibility(View.INVISIBLE);
                questionview.setVisibility(View.INVISIBLE);
                answerview.setVisibility(View.INVISIBLE);
                goButton.setVisibility(View.VISIBLE);
                isrunning = false;
                score.setVisibility(view.VISIBLE);
                double percentage = ((double)crct/(double)total)*100.00;
                String nn= String.format("%.2f", percentage);
                score.setText("Your accuracy is "+nn);

                crct = 0;
                total = 0;
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.c);
                mp.start();



            }
        }.start();
        createQuestions();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button2);
        b2 = findViewById(R.id.button3);b3 = findViewById(R.id.button4);b4 = findViewById(R.id.button5);
        timer = findViewById(R.id.textView2);
        cbyt = findViewById(R.id.textView3);
        questionview = findViewById(R.id.textView4);
        answerview = findViewById(R.id.textView5);
        goButton  = findViewById(R.id.button);
        score = findViewById(R.id.textView6);

        goButton.setVisibility(View.VISIBLE);
        score.setVisibility(View.INVISIBLE);


        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        b3.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        cbyt.setVisibility(View.INVISIBLE);
        questionview.setVisibility(View.INVISIBLE);
        answerview.setVisibility(View.INVISIBLE);





    }
    public void createQuestions(){
        Random ran = new Random();

        int a = ran.nextInt(41);
        int b = ran.nextInt(41);


        loca = ran.nextInt(4);

        for(int i=0;i<4;i++){
            if(i == loca){
                numbers.add(a+b);
            }
            else{
                int getnum = ran.nextInt(41);
                while(getnum==(a+b)){
                    getnum = ran.nextInt(41);
                }
                numbers.add(getnum);
            }
        }
        questionview.setText(Integer.toString(a) + " + " + Integer.toString(b));
        b1.setText(Integer.toString(numbers.get(0)));
        b2.setText(Integer.toString(numbers.get(1)));
        b3.setText(Integer.toString(numbers.get(2)));
        b4.setText(Integer.toString(numbers.get(3)));

        numbers.clear();
    }
}