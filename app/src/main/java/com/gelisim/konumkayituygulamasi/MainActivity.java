package com.gelisim.konumkayituygulamasi;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    ArrayList<Place> placeList = new ArrayList<>();
    ListView listView;
    CustomAdapter customAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.yer_ekle,menu);
        menuInflater.inflate(R.menu.tarihi_yerler,menu);
        menuInflater.inflate(R.menu.nerede,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.yer_ekle){
            Intent intent = new Intent(this,MapsActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }
        if(item.getItemId() == R.id.tarihi_yerler){
            Intent intent = new Intent(this,MainActivity2.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.nerede){
            Intent intent = new Intent(this,MapsActivityNerede.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        getData();
    }

    public void getData() {
        customAdapter = new CustomAdapter(this,placeList);
              try {

                  database = this.openOrCreateDatabase("Places",MODE_PRIVATE,null);
                  Cursor cursor = database.rawQuery("SELECT * FROM places",null);

                  int nameIx = cursor.getColumnIndex("name");
                  int latitudeIx = cursor.getColumnIndex("latitude");
                  int longitudeIx = cursor.getColumnIndex("longitude");

                  while(cursor.moveToNext()) {

                      String nameFromDatabase = cursor.getString(nameIx);
                      String latitudeFromDatabase = cursor.getString(latitudeIx);
                      String longitudeFromDatabase = cursor.getString(longitudeIx);

                      Double latitude = Double.parseDouble(latitudeFromDatabase);
                      Double longitude = Double.parseDouble(longitudeFromDatabase);

                      Place place = new Place(nameFromDatabase,latitude,longitude);

                      System.out.println(place.name);

                      placeList.add(place);

                  }
                  customAdapter.notifyDataSetChanged();
                  cursor.close();

              } catch (Exception e) {
                       e.printStackTrace();
              }


             listView.setAdapter(customAdapter);

              listView.setOnItemClickListener((parent, view, position, id) -> {
                  Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                  intent.putExtra("info","old");
                  intent.putExtra("place",placeList.get(position));
                  startActivity(intent);
              });
              listView.setOnItemLongClickListener((parent, view, position, id) -> {

                  new AlertDialog.Builder(MainActivity.this)
                          .setIcon(android.R.drawable.ic_delete)
                          .setTitle("Emin Misiniz ?")
                          .setMessage("Seçilen Konumu Silmek İstediğinize Emin Misiniz")
                          .setPositiveButton("Evet", (dialog, which) -> {
                              placeList.remove(position);
                              customAdapter.notifyDataSetChanged();
                          }).setNegativeButton("Hayır",null)
                          .show();
                  return true;
              });





    }
}