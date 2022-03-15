package com.example.cencollocationsmaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RestaurantsActivity extends AppCompatActivity {
    String [] restaurants;
    ListView restaurantListView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        restaurantListView = findViewById(R.id.restaurantsListView);
        //TextView textView = new TextView(getApplicationContext());
        //textView.setText(getResources().getString(R.string.campus_txtPrompt));

        //lstView.addHeaderView(textView);
        //lstView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //lstView.setTextFilterEnabled(true);

        String cuisineType = getIntent().getStringExtra("cuisine");
        switch (cuisineType) {
            case "Korean":
                restaurants = getResources().getStringArray(R.array.korean);
                break;
            case "Chinese":
                restaurants = getResources().getStringArray(R.array.chinese);
                break;
            case "Filipino":
                restaurants = getResources().getStringArray(R.array.filipino);
                break;
            case "Italian":
                restaurants = getResources().getStringArray(R.array.italian);
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, restaurants);
        restaurantListView.setAdapter(adapter);

        // Event-handlers
        restaurantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                displayMap(position);
            }
        });
    }

    private void displayMap(int index) {
        intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}