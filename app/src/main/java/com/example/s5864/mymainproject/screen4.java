package com.example.s5864.mymainproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ShivM on 08-Oct-17.
 */

public class screen4 extends AppCompatActivity {

    SQLiteDatabase budgetDB = null;
    ListView listView, listView2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen4);

        budgetDB = this.openOrCreateDatabase("MyBudget.db",MODE_PRIVATE,null);
        listView = (ListView)findViewById(R.id.list);
        listView2 = (ListView)findViewById(R.id.list2);

        Cursor cursor = budgetDB.rawQuery("SELECT * FROM expense", null);

        ArrayList<String> expenseList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, expenseList);

        if(cursor != null && cursor.getCount() > 0)
        {
            int expenseTypeCol = cursor.getColumnIndex("expenseType");
            int expenseItemCol = cursor.getColumnIndex("item");
            int expensePriceCol = cursor.getColumnIndex("price");

            String title = "Essential Expense";
            expenseList.add(title);

            cursor.moveToFirst();

                do{
                    String row = cursor.getString(expenseItemCol)+":  $"+cursor.getString(expensePriceCol);

                    if(cursor.getString(expenseTypeCol).length() == 9) {
                        expenseList.add(row);
                    }
                }while(cursor.moveToNext());
            }
        listView.setAdapter(adapter);

        Cursor cursor2 = budgetDB.rawQuery("SELECT * FROM expense", null);

        ArrayList<String> expenseList2 = new ArrayList<>();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, expenseList2);

        if(cursor2 != null && cursor.getCount() > 0)
        {
            int expenseTypeCol = cursor.getColumnIndex("expenseType");
            int expenseItemCol = cursor.getColumnIndex("item");
            int expensePriceCol = cursor.getColumnIndex("price");

            String title = "Luxury Expense";
            expenseList2.add(title);

            cursor.moveToFirst();

            do{
                String row = cursor.getString(expenseItemCol)+":  $"+cursor.getString(expensePriceCol);

                if(cursor.getString(expenseTypeCol).length() != 9) {
                    expenseList2.add(row);
                }
            }while(cursor.moveToNext());
        }
        listView2.setAdapter(adapter2);
    }


}
