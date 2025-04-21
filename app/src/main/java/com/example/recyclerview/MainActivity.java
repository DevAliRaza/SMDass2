package com.example.recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RestrauntAdapter.ItemSelected {

    RecyclerView recyclerview;
    RecyclerView.LayoutManager manager;
    RecyclerView.Adapter myAdapter;
    final int RegisterPage = 3;
    Button btnadd;
    Button btnsearch;
    ArrayList<Restraunt> restraunts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadd = findViewById(R.id.btnadd);
        btnsearch = findViewById(R.id.btnsearch);
        recyclerview = findViewById(R.id.list);

        recyclerview.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);


        restraunts = new ArrayList<>();
        restraunts.add(new Restraunt("2", "Fuoco", "MM Alam road", "03242233022", "Very expensive"));
        restraunts.add(new Restraunt("5", "Carnivor", "DHA phase 6", "03242233022", "Exceptional"));
        restraunts.add(new Restraunt("1", "Grill Badshah", "Sadar Cantt", "03242233022", "Very good"));
        restraunts.add(new Restraunt("3", "Pizza Hut", "Fortress", "03242233022", "Very expensive"));
        myAdapter = new RestrauntAdapter(this, restraunts);
        recyclerview.setAdapter(myAdapter);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterPage.class);
                startActivityForResult(intent, RegisterPage);
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortArrayListByRating();
                myAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RegisterPage) {
            if (requestCode == RESULT_CANCELED) {
                Toast.makeText(this, "No data entered", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_OK) {
                String rating = data.getStringExtra("rating");
                String name = data.getStringExtra("name");
                String phone = data.getStringExtra("phone");
                String location = data.getStringExtra("location");
                String description = data.getStringExtra("description");
                restraunts.add(new Restraunt(rating, name, location, phone, description));
                myAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClicked(int index) {
        Toast.makeText(this, restraunts.get(index).getName(), Toast.LENGTH_SHORT).show();
    }

    void sortArrayListByRating() {
        for (int i = 0; i < restraunts.size() - 1; i++) {
            for (int j = 0; j < restraunts.size() - i - 1; j++) {
                int rating1 = Integer.parseInt(restraunts.get(j).getRating());
                int rating2 = Integer.parseInt(restraunts.get(j + 1).getRating());
                if (rating1 < rating2) {
                    // Swap elements if they are in the wrong order
                    Restraunt temp = restraunts.get(j);
                    restraunts.set(j, restraunts.get(j + 1));
                    restraunts.set(j + 1, temp);
                }
            }
        }
    }
}
