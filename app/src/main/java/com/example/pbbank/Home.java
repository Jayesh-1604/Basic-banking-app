package com.example.pbbank;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.SearchView;
        import androidx.appcompat.widget.Toolbar;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.pbbank.data.CustomerDbHelper;
        import com.example.pbbank.data.DatabaseAccess;
        import com.google.android.material.navigation.NavigationView;

        import java.util.ArrayList;

public class Home extends AppCompatActivity {
    Intent myIntent;
    CustomerAdapter adapter;
    ListView listView;
    public int cid;
    ArrayList<Customer> obj;
       int customerid[] = {1,2,3,4,5,6,7,8,9,10};

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("EXIT..")
                .setCancelable(false)
                .setMessage("Are you sure want to exit?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Home.super.onBackPressed();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        obj = new ArrayList<Customer>();

        obj.add(new Customer(1,"Jayesh Bhanudas Wani","Jalgaon"));
        obj.add(new Customer(2,"Rahul Rajendra Kadam","Pune"));
        obj.add(new Customer(3,"Sanket Pandharinath Rathod","Aurangabad"));
        obj.add(new Customer(4,"Vaibhav Rajesh Chirde","Yawatmal"));
        obj.add(new Customer(5,"Tanmay Bhangwat Chaudhari","Nashik"));
        obj.add(new Customer(6,"Sushrut Rajesh Babhulkar","Nagpur"));
        obj.add(new Customer(7,"Abhishek Rupchand Thakre","Nagpur"));
        obj.add(new Customer(8,"Khilesh Pramod Chaudhari","Kolhapur"));
        obj.add(new Customer(9,"Prasad Bhanudas Chaudhari","Thane"));
        obj.add(new Customer(10,"Deepak Pramod Chaudhari","Mumbai"));

        listView = (ListView) findViewById(R.id.list_restaurant);

        adapter = new CustomerAdapter(this, obj);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                cid = customerid[position];
                String customerdata[] = new String[5];
                customerdata = databaseAccess.getdatadb(cid);
                String tempname =  customerdata[0];
                String tempemail = customerdata[1];
                String tempadd = customerdata[2];
                String tempph = customerdata[3];
                String tempbalace = customerdata[4];

                databaseAccess.close();

                Toast.makeText(Home.this,"Welcome\t"+tempname,Toast.LENGTH_LONG).show();
                myIntent = new Intent(Home.this, UserDetail.class);

                myIntent.putExtra("sIntCustomerId", cid);
                myIntent.putExtra("name",tempname);
                myIntent.putExtra("email",tempemail);
                myIntent.putExtra("address",tempadd);
                myIntent.putExtra("ph",tempph);
                myIntent.putExtra("balance",tempbalace);
                startActivity(myIntent);
                finish();
            }
        });

    }


}


