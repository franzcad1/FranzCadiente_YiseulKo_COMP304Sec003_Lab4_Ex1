package com.example.cencollocationsmaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class CuisinesActivity extends AppCompatActivity {
    ListView cuisineListView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisines);

        cuisineListView = findViewById(R.id.cuisineListView);
        String[] cuisineArray = getResources().getStringArray(R.array.cuisines);
        List<String> cuisineList = Arrays.asList(cuisineArray);

        // Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1,cuisineList);

        cuisineListView.setAdapter(adapter);

        // Event-handlers
        cuisineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                displayNextActivity(position);
            }
        });

    }

    private void displayNextActivity(int index) {
        intent = new Intent(this, RestaurantsActivity.class);
        intent.putExtra("cuisine", cuisineListView.getItemAtPosition(index).toString());
        startActivity(intent);
    }
}