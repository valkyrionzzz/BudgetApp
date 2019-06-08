package com.example.s5864.mymainproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ShivM on 01-Oct-17.
 */

public class screen2 extends AppCompatActivity {

    SQLiteDatabase budgetDB = null;
    ListView salaryView;
    TextView textView2;
    Button button3, button4;
    double TotalExp = 0;
    double AmountLeft = 0;
    double salaryValue = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen2);

        budgetDB = this.openOrCreateDatabase("MyBudget.db",MODE_PRIVATE,null);
        salaryView = (ListView)findViewById(R.id.salaryView);
        textView2 = (TextView)findViewById(R.id.textView2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        try {
            File database = getApplicationContext().getDatabasePath("MyBudget.db");
            if (database.exists()) {
                Cursor cursor = budgetDB.rawQuery("SELECT * FROM salary", null);

                ArrayList<String> salaryList = new ArrayList<>();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_activated_1, salaryList);

                if(cursor != null && cursor.getCount() > 0)
                {
                    int salaryCol = cursor.getColumnIndex("salaryAmount");

                    cursor.moveToFirst();

                    do{
                        String row = "YOUR TOTAL BUDGET: "+cursor.getString(salaryCol);

                        //add to list
                        salaryList.add(row);

                    }while(cursor.moveToNext());
                }
                else{
                    Toast.makeText(this, "No results to display", Toast.LENGTH_SHORT).show();

                }
                salaryView.setAdapter(adapter);




                Cursor cursor2 = budgetDB.rawQuery("SELECT * FROM expense", null);

                if(cursor2 != null && cursor2.getCount() > 0)
                {
                    int expenseCol = cursor2.getColumnIndex("price");

                    cursor2.moveToFirst();

                    do{
                        String row2 = cursor2.getString(expenseCol);
                        TotalExp = TotalExp + Integer.valueOf(row2);

                    }while(cursor2.moveToNext());
                }
                else{
                    Toast.makeText(this, "No results to display", Toast.LENGTH_SHORT).show();

                }



                Cursor cursor3 = budgetDB.rawQuery("SELECT * FROM salary", null);

                if(cursor3 != null && cursor3.getCount() > 0)
                {
                    int salaryCol = cursor3.getColumnIndex("salaryAmount");

                    cursor3.moveToFirst();

                    do{
                        String row3 = cursor3.getString(salaryCol);
                        salaryValue = Integer.valueOf(row3);

                    }while(cursor3.moveToNext());
                }
                else{
                    Toast.makeText(this, "No results to display", Toast.LENGTH_SHORT).show();

                }



                AmountLeft = salaryValue - TotalExp;


                textView2.setText("Your Total expense is: "+String.valueOf(TotalExp)+"\n"+"Amount left to spend: "+AmountLeft);

                Toast.makeText(this,"Your Total expense is: "+TotalExp, Toast.LENGTH_LONG).show();


                salaryView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            else{
                Toast.makeText(this,"DATABASE NOT FOUND", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent refresh = new Intent(this, screen2.class);
        finish();
        startActivity(refresh);
    }


    public void Essential(View view){
        Intent nextActivityIntent = new Intent(this, screen3.class);
        nextActivityIntent.putExtra("expType","Essential");
        startActivity(nextActivityIntent);
    }

    public void Luxury(View view){
        Intent nextActivityIntent = new Intent(this, screen3.class);
        nextActivityIntent.putExtra("expType","Luxury");;
        startActivity(nextActivityIntent);
    }

    public void ViewLists(View view){
        Intent nextActivityIntent = new Intent(this, screen4.class);
        startActivity(nextActivityIntent);
    }

    public void Test(View view) {

        Toast.makeText(this,"Your Total expense is: "+TotalExp, Toast.LENGTH_LONG).show();
    }

}
