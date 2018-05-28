package com.example.diu.bangladictionary;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static TextView numberTextView,familyTextView,colorTextView,phrasesTextView;
    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberTextView=(TextView)findViewById(R.id.numbers);
        numberTextView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent=new Intent(MainActivity.this,NumbersActivity.class);
                        startActivity(intent);
                    }
                }
        );
        colorTextView=(TextView)findViewById(R.id.colors);
        colorTextView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent=new Intent(MainActivity.this,ColorsActivity.class);
                        startActivity(intent);
                    }
                }
        );
        familyTextView=(TextView)findViewById(R.id.family);
        familyTextView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent=new Intent(MainActivity.this,FamilyActivity.class);
                        startActivity(intent);
                    }
                }
        );
        phrasesTextView=(TextView)findViewById(R.id.phrases);

        phrasesTextView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent=new Intent(MainActivity.this,PhrasesActivity.class);
                        startActivity(intent);
                    }
                }
        );
        ReportCard reportCard=new ReportCard("Rabbi","A+","A+","A+","A+");

        AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Report Card");
        alertDialog.setMessage(reportCard.toString());
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

}
