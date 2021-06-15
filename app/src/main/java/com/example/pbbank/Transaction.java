package com.example.pbbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pbbank.data.DatabaseAccess;

import org.w3c.dom.Text;

public class Transaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public int customerid = 0;
    int intValue;
    float balance;
    int sCustomerid;
    TextView moneytext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        Intent intent = getIntent();
        intValue = intent.getIntExtra("sIntCustomerId", 0);
        String bal = intent.getStringExtra("balance");
        balance = Float.parseFloat(bal);


        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        if (!TextUtils.isEmpty(text)) {
            if (text.equals("Jayesh Bhanudas Wani")) {
                 customerid = 1;
            } else if (text.equals("Rahul Rajendra Kadam")) {
                customerid = 2;
            } else if (text.equals("Sanket Pandharinath Rathod")) {
                customerid = 3;
            } else if (text.equals("Vaibhav Rajesh Chirde")) {
                customerid = 4;
            } else if (text.equals("Tanmay Bhagwat Chaudhari")) {
                customerid = 5;
            } else if (text.equals("Sushrut Rajesh Babhulkar")) {
                customerid = 6;
            } else if (text.equals("Abhishek Rupchand Thakare")) {
                customerid = 7;
            } else if (text.equals("Khilesh Pramod Chaudhari")) {
                customerid = 8;
            } else if (text.equals("Prasad Bhanudas Chaudhari")) {
                customerid = 9;
            } else if (text.equals("Deepak Pramod Chaudhari")) {
                customerid = 10;
            } else {
                customerid = 0;
            }
        }
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CANCEL..")
                .setCancelable(false)
                .setMessage("Are you sure want to cancel Transaction?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent myintent;
                        myintent = new Intent(Transaction.this,Home.class);
                        startActivity(myintent);
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    public void paymoney(View view) {
        moneytext = (TextView) findViewById(R.id.money);
        String amountString = moneytext.getText().toString().trim();
        float amount = Float.parseFloat(amountString);
        if (amountString.isEmpty() || customerid == 0)
            Toast.makeText(Transaction.this, "Please Enter amount", Toast.LENGTH_LONG).show();
        else {
            if (amount <= balance) {
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                int r = databaseAccess.addamount(intValue, customerid, amount);
                if (r == -1)
                    Toast.makeText(Transaction.this, "Something Went Wrong...", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Transaction.this, "Transaction Successfull...", Toast.LENGTH_LONG).show();
                databaseAccess.close();
                Intent myIntent;
                myIntent = new Intent(Transaction.this, Home.class);
                startActivity(myIntent);
                finish();
            } else
                Toast.makeText(Transaction.this, "You don't have enough balance", Toast.LENGTH_LONG).show();
        }
    }

    public void backmoney(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CANCEL..")
                .setCancelable(false)
                .setMessage("Are you sure want to cancel Transaction?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent myIntent;
                        myIntent = new Intent(Transaction.this, Home.class);
                        startActivity(myIntent);
                        finish();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}
