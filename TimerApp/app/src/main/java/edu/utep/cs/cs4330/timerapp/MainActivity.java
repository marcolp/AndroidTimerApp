package edu.utep.cs.cs4330.timerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timerDisplay;
    private Button startButton;
    private Button stopButton;
    private TimerModel timerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerModel = new TimerModel();

        timerDisplay = (TextView) findViewById(R.id.textView);
        startButton = (Button) findViewById(R.id.startButton);
        stopButton = (Button) findViewById(R.id.stopButton);
        timerDisplay.setText("0:00:00");
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    /** Called when the start button is clicked. */
    public void startClicked(View view) {
//        Log.d("Timer", "Start button tapped!");
        //or Toast.makeText(this, "Start!", Toast.LENGTH_SHORT).show();
        timerModel.start();
        stopButton.setEnabled(true);

        runThread();
//
//        new Thread() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (true) {
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                System.out.print("Thread interrupted. " + e);
//                            }
//                            if (timerModel.isRunning()) {
//                                updateTime(timerModel.elapsedTime());
//                            }
//                        }
//                    }
//                }
//                );
//            }
//        }.start();
    }
    /** Called when the stop button is clicked. */
    public void stopClicked(View view) {
//        Log.d("Timer", "Stop button tapped!");
        //or Toast.makeText(this, "Start!", Toast.LENGTH_SHORT).show();
        stopButton.setEnabled(false);
        timerModel.stop();
    }

    public void updateTime(long elapsedMilli){
        //Amount of seconds elapsed since timer start
        double elapsedSeconds = elapsedMilli/1000;
        int seconds = (int) (elapsedSeconds % 60);

        //Amount of minutes elapsed since timer start
        double elapsedMinutes = elapsedSeconds / 60.0;
        int minutes = (int) (elapsedMinutes % 60);

        //Amount of hours elapsed since timer start
        double elapsedHours = elapsedMinutes / 60.0;
        int hours = (int)elapsedHours;

        String hoursString = ""+hours;
        String minutesString = ""+minutes;
        String secondsString = ""+seconds;

        if(hours < 10)
            hoursString = "0"+hours;

        if(minutes < 10)
            minutesString = "0"+minutes;

        if(seconds < 10)
            secondsString = "0"+seconds;

        timerDisplay.setText(hoursString+":"
                +minutesString+":"
                +secondsString);
    }

    private void runThread() {
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(timerModel.isRunning())
                                updateTime(timerModel.elapsedTime());
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
