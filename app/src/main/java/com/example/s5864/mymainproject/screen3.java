package com.example.s5864.mymainproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by ShivM on 08-Oct-17.
 */

public class screen3 extends AppCompatActivity {

    SQLiteDatabase budgetDB = null;
    TextView textView, textView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen3);

        budgetDB = this.openOrCreateDatabase("MyBudget.db",MODE_PRIVATE,null);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);

    }

    public void enterItem(View view) {
        File database = getApplicationContext().getDatabasePath("MyBudget.db");
        String expType = getIntent().getStringExtra("expType");

        if (database.exists()){

            String itemName = textView.getText().toString();
            int expense = Integer.parseInt(textView2.getText().toString());

            budgetDB.execSQL("INSERT INTO expense (expenseType, item, price)"+
                    " VALUES('"+expType+"','"+itemName+"','"+expense+"');");

            Intent nextActivityIntent = new Intent(this, screen2.class);
            finish();
            startActivity(nextActivityIntent);
        }




    }

}
