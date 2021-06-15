package com.example.pbbank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetail extends AppCompatActivity {
    Button button;
    int intValue;
    String user_name, user_email, user_address, user_ph, user_bal;
    TextView bcusname, bcusemail, bcusadd, bcusph, bcusbal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);


        bcusname = findViewById(R.id.username);
        bcusemail = findViewById(R.id.useremail);
        bcusph = findViewById(R.id.usercontact);
        bcusadd = findViewById(R.id.useradd);
        bcusbal = findViewById(R.id.userbal);

        Intent intent = getIntent();
        intValue = intent.getIntExtra("sIntCustomerId", 0);
        user_name = intent.getStringExtra("name");
        user_email = intent.getStringExtra("email");
        user_bal = intent.getStringExtra("balance");
        user_address = intent.getStringExtra("address");
        user_ph = intent.getStringExtra("ph");


        bcusname.setText(user_name);

        bcusemail.setText(user_email);

        bcusadd.setText(user_address);

        bcusph.setText(user_ph);

        bcusbal.setText(user_bal);


        button = findViewById(R.id.btnback);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(UserDetail.this, Home.class);
                startActivity(myIntent);
                finish();

            }
        });


    }

    public void payment(View view) {
        Intent myIntent = new Intent(UserDetail.this, Transaction.class);
        myIntent.putExtra("sIntCustomerId", intValue);
        myIntent.putExtra("balance", user_bal);
        startActivity(myIntent);
        finish();

    }


}




