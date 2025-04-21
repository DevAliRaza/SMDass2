package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    EditText etrating,etname,etphone,etlocation,etdescription;
    Button btnsubmit,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        init();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = etrating.getText().toString().trim();
                String name = etname.getText().toString().trim();
                String location = etlocation.getText().toString().trim();
                String phone = etphone.getText().toString().trim();
                String description = etdescription.getText().toString().trim();
                if(rating.isEmpty() || name.isEmpty() || location.isEmpty() || phone.isEmpty() || description.isEmpty()){
                    Toast.makeText(RegisterPage.this, "Please fill all the Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent();
                    intent.putExtra("rating",rating);
                    intent.putExtra("name",name);
                    intent.putExtra("phone",phone);
                    intent.putExtra("description",description);
                    intent.putExtra("location",location);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }


    void init(){
        etrating = findViewById(R.id.etrating);
        etname = findViewById(R.id.etname);
        etlocation = findViewById(R.id.etlocation);
        etphone = findViewById(R.id.etphone);
        etdescription = findViewById(R.id.etdescription);
        btnsubmit = findViewById(R.id.btnsubmit);
        btnCancel = findViewById(R.id.btnCancel);

    }


}


