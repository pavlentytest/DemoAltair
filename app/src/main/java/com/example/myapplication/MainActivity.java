package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText obj_a, obj_b, obj_c;
    TextView result;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playMusic(View v) {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.furelise);
            mediaPlayer.start();
        }
    }

    public void solve(View v) {
        obj_a = findViewById(R.id.editTextA);
        obj_b = findViewById(R.id.editTextB);
        obj_c = findViewById(R.id.editTextC);

        DecimalFormat df = new DecimalFormat("###.###");

        if(v.getId() == R.id.solve) {

            result = findViewById(R.id.result);

            String str_a = obj_a.getText().toString();
            String str_b = obj_b.getText().toString();
            String str_c = obj_c.getText().toString();
            String str = "";
            boolean flag = false;

            if(str_a.trim().isEmpty()) {
               str = "коэффициент а ";
               flag = true;
            }
            if(str_b.trim().isEmpty()) {
                str += "коэффициент b ";
                flag = true;
            }
            if(str_c.trim().isEmpty()) {
                str += "коэффициент c ";
                flag = true;
            }

            if(flag) {
                Toast.makeText(this,"Ошибка "+str,Toast.LENGTH_LONG).show();
            } else {
                double a = Double.parseDouble(str_a);
                double b = Double.parseDouble(str_b);
                double c = Double.parseDouble(str_c);

                double d = b * b - 4 * a * c;

                if (d == 0) {
                    double x = (-b + Math.sqrt(d)) / 2 * a;
                    result.setText("Один корень: " + df.format(x));
                } else if (d < 0) {
                    result.setText("Нет решений");
                } else if (d > 0) {
                    double x1 = (-b + Math.sqrt(d)) / 2 * a;
                    double x2 = (-b - Math.sqrt(d)) / 2 * a;
                    result.setText("Два корня x1 = " + df.format(x1) + "; x2 = " + df.format(x2));
                }
            }
        } else {
            obj_a.setText("");
            obj_b.setText("");
            obj_c.setText("");
            result.setText("");
        }

    }
}
