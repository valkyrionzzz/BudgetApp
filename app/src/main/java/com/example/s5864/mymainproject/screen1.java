package com.example.s5864.mymainproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by ShivM on 28-Sep-17.
 */

public class screen1 extends AppCompatActivity {

    SQLiteDatabase budgetDB = null;
    Button submitSalButton, newUser, deleteUser, next;
    EditText EnterSal;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);

        submitSalButton = (Button)findViewById(R.id.submitSalButton);
        newUser = (Button)findViewById(R.id.newUser);
        EnterSal = (EditText)findViewById(R.id.EnterSal);
        deleteUser = (Button)findViewById(R.id.deleteUser);
        textView = (TextView)findViewById(R.id.textView);
        next = (Button)findViewById(R.id.next);

        try {
            File database = getApplicationContext().getDatabasePath("MyBudget.db");
            if (database.exists()) {
                submitSalButton.setEnabled(true);
                newUser.setEnabled(false);
                deleteUser.setEnabled(true);
                next.setEnabled(true);
        }
            else{
                next.setEnabled(false);
                deleteUser.setEnabled(false);
                submitSalButton.setEnabled(false);
            }
        }
        catch (Exception e)
        {
        }

    }

    public void CreateDatabase(View view) {
        try{
            budgetDB = this.openOrCreateDatabase("MyBudget.db",MODE_PRIVATE,null);

            budgetDB.execSQL("CREATE TABLE IF NOT EXISTS expense"+"(id integer primary key, expenseType VARCHAR, item VARCHAR, price DOUBLE);");
            budgetDB.execSQL("CREATE TABLE IF NOT EXISTS salary"+"(id integer primary key, salaryAmount DOUBLE);");

            File database = getApplicationContext().getDatabasePath("MyBudget.db");

            if(database.exists())
            {
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "DATABASE NOT CREATED OR FOUND!", Toast.LENGTH_SHORT).show();
            }
            submitSalButton.setEnabled(true);
            newUser.setEnabled(false);
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Error creating DB: "+e, Toast.LENGTH_LONG).show();
        }
        deleteUser.setEnabled(true);
    }
    public void AddSalary(View view) {
        try {
            int salary = 0;
            salary = Integer.parseInt(textView.getText().toString());
            budgetDB.execSQL("INSERT INTO salary (salaryAmount)" +
                    " VALUES('" + salary + "');");


            submitSalButton.setEnabled(false);
            next.setEnabled(true);
        }
        catch (Exception e){
            Toast.makeText(this,"Invalid salary", Toast.LENGTH_LONG).show();
        }
    }

    public void DeleteDatabase(View view) {
        this.deleteDatabase("MyBudget.db");
        newUser.setEnabled(true);
        submitSalButton.setEnabled(false);
        deleteUser.setEnabled(false);
        next.setEnabled(false);
    }

    public void NextScreen(View view){
        Intent nextActivityIntent = new Intent(this, screen2.class);
        startActivity(nextActivityIntent);
    }

    @Override
    protected void onDestroy(){
        try{
            budgetDB.close();
        }
        catch(Exception e)
        {
            //nothing
        }
        super.onDestroy();
    }
}
