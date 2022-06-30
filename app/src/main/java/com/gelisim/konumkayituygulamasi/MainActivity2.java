package com.gelisim.konumkayituygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    public Button istanbul;
    public Button ankara;
    public Button izmir;
    public Button bursa;
    public Button antalya;
    public Button eskisehir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        istanbul=findViewById(R.id.istanbul);
        ankara=findViewById(R.id.ankara);
        izmir=findViewById(R.id.izmir);
        bursa=findViewById(R.id.bursa);
        antalya=findViewById(R.id.antalya);
        eskisehir=findViewById(R.id.eskisehir);
        istanbul.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity2.this,istanbulharita.class);
            startActivity(intent);



        });
        ankara.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity2.this,ankaraharita.class);
            startActivity(intent);



        });
        izmir.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity2.this,izmirharita.class);
            startActivity(intent);



        });
        bursa.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity2.this,bursaharita.class);
            startActivity(intent);



        });
        antalya.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity2.this,antalyaharita.class);
            startActivity(intent);



        });
        eskisehir.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity2.this,eskisehirharita.class);
            startActivity(intent);



        });






    }
}