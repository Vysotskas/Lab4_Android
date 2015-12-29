package com.example.tatiana.lab4;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity implements GestureOverlayView.OnGesturePerformedListener {
    TextView tvInfo;
    EditText etInput;
    Button bControl;
    int guess;
    boolean gameFinished;
    TableRow tableRow;
    GestureLibrary gLib;
    GestureOverlayView gestures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        tvInfo = (TextView)findViewById(R.id.tableView1);
        etInput = (EditText)findViewById(R.id.editText1);
        bControl = (Button)findViewById(R.id.button1);
        guess = (int)(Math.random()*100);
        gameFinished = false;
        tableRow = (TableRow) findViewById(R.id.TableRow2);
        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            finish();
        }
        gestures = (GestureOverlayView) findViewById(R.id.gestureView);
        gestures.addOnGesturePerformedListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void onClick(View v){
        if (!gameFinished){
            int inp=Integer.parseInt(etInput.getText().toString());
            if (inp > guess)
                tvInfo.setText(getResources().getString(R.string.ahead) + guess);
            if (inp < guess)
                tvInfo.setText(getResources().getString(R.string.behind));
            if (inp == guess)
            {
                tvInfo.setText(getResources().getString(R.string.hit));
                bControl.setText(getResources().getString(R.string.play_more));
                gameFinished = true;
                tableRow.setBackgroundResource(R.drawable.finish);
            }
        }
        else
        {
            guess = (int)(Math.random()*100);
            bControl.setText(getResources().getString(R.string.input_values));
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            gameFinished = false;
        }
        etInput.setText("");
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        //Создаёт ArrayList c загруженными из gestures жестами
        ArrayList<Prediction> predictions = gLib.recognize(gesture);
        if (predictions.size() > 0) {
        //если загружен хотябы один жест из gestures
            Prediction prediction = predictions.get(0);
            if (prediction.score > 1.0) {
                if (prediction.name.equals("one"))
                    addTextToEdit("1");
                else if (prediction.name.equals("two"))
                    addTextToEdit("2");
                else if (prediction.name.equals("three"))
                    addTextToEdit("3");
                else if (prediction.name.equals("four"))
                    addTextToEdit("4");
                else if (prediction.name.equals("five"))
                    addTextToEdit("5");
                else if (prediction.name.equals("six"))
                    addTextToEdit("6");
                else if (prediction.name.equals("seven"))
                    addTextToEdit("7");
                else if (prediction.name.equals("eight"))
                    addTextToEdit("8");
                else if (prediction.name.equals("nine"))
                    addTextToEdit("9");
                else if (prediction.name.equals("stop"))
                    bControl.performClick();
            }
// else{
//               // tvOut.setText("Жест неизвестен");
//            }
        }
    }

    private void addTextToEdit(String str){
        etInput.setText(etInput.getText() + str);
    }
}
