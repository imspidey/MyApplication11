package spidey.com.reyaj.chat.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class search extends AppCompatActivity {
    ListView lvw;
    ArrayList<String> list ;
    ArrayAdapter adapter;
    SQLiteDatabase db;
    Button load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        list = new ArrayList<>();
        lvw = findViewById(R.id.listView);
        load= findViewById(R.id.btnLoad);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);

        db = openOrCreateDatabase("Book_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS book(bid INTEGER,bname VARCHAR,aname VARCHAR,rack INTEGER,shel INTEGER);");


        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c =db.rawQuery("SELECT * FROM book",null);
                if(c.moveToFirst())
                {
                    do {
                        list.add(c.getString(0));
                    }while (c.moveToNext());
                }
                //close the cursor
                c.close();
                //close the database
                db.close();
                lvw.setAdapter((ListAdapter) list);
            }
        });

    }

}
