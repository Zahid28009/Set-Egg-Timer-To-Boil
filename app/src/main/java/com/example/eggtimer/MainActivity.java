package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controllerButton;
    Boolean counterIsActive = false;        // true set first
    CountDownTimer countDownTimer;

    public void resetTimer() {

        timerTextView.setText("0:30");          // Set First Initial with 0.30
        timerSeekBar.setProgress(30);           // Set First Initial with 30
        countDownTimer.cancel();                //count cancel hobe
        controllerButton.setText("Go!");        // Then set text - Go
        timerSeekBar.setEnabled(true);          // seekbar enable again
        counterIsActive = false;                // Initial counterisactive

    }

    public void updateTimer(int secondsLeft) {          // progress/second start with 30 -600

        int minutes = (int) secondsLeft / 60;          // Let, 70 seekbar , then 70/60 =1
        int seconds = secondsLeft - minutes * 60;       // 70-1*60 =10

        String secondString = Integer.toString(seconds);    //secondstring convert into string

        if (seconds <= 9) {                             // If second in under 9

            secondString = "0" + secondString;      // then add 0 So, ... 11,10,09,08.....

        }

        timerTextView.setText(Integer.toString(minutes) + ":" + secondString); // Text view show

    }


    public void controlTimer(View view) {

        if (counterIsActive == false) {      // First we set False (Stop Thake first e, tai counter false). if, counterIsActive==false then - enter inside this condition

            counterIsActive = true;         // Start Counter
            timerSeekBar.setEnabled(false);     // Seekbar don't work , when time start
            controllerButton.setText("Stop");   // Button Will Changed to Stop

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {        // Suru theke kombe, 1s(1000ms) kore
                                                            // Seekbar er  Progress Nia *1000 to Convert second and end +100 to not to dealay (1-0 s)
                                                            // In milisUntilFinished with 1 where we need to 0 countdown so , we add +100 to not delay (1-0 s)
                @Override
                public void onTick(long millisUntilFinished) {              // Mili Second Until Finished,Just Like stopwatch (hidden) count down

                    updateTimer((int) millisUntilFinished / 1000);  // Called UpdateTimer function to send 1000/1000=1 , to secondsLeft parameter to pass
                          Log.i("Every second Count","zahid");                        // Count Every Second
                }

                @Override
                public void onFinish() {

                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();

                }
            }.start();           // Start the countdown

        } else {                // Countdown cholse

            resetTimer();        //ai oboshtai reset dile abar initial obosthai chole jabe

        }
    }       // Full is Like oobject


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controllerButton = (Button) findViewById(R.id.controllerButton);

        timerSeekBar.setMax(600);           // Seekbar max- 600
        timerSeekBar.setProgress(30);       // Progress initial 30

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}


