package com.example.FranzCadiente_YiseulKo_COMP304Sec003_Lab4_Ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cencollocationsmaps.R;

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

        // Event-handler for listview items
        cuisineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                displayRestaurantsActivity(position);
            }
        });

    }

    private void displayRestaurantsActivity(int index) {
        intent = new Intent(this, RestaurantsActivity.class);
        intent.putExtra("cuisine", cuisineListView.getItemAtPosition(index).toString());
        startActivity(intent);
    }
}