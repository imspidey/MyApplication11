package spidey.com.reyaj.chat.myapplication;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Remove extends AppCompatActivity {
    EditText id, name, author,Rack,Shelf;
    Button btRemove;
    ImageButton search;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        id = (EditText) findViewById(R.id.id);
        name = (EditText) findViewById(R.id.name);
        author = (EditText) findViewById(R.id.author);
        Rack = (EditText) findViewById(R.id.Rack);
        Shelf = (EditText) findViewById(R.id.Shelf);
        btRemove = (Button) findViewById(R.id.btRemove);
        search=(ImageButton)findViewById(R.id.search);



        db = openOrCreateDatabase("Book_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS book(bid INTEGER,bname VARCHAR,aname VARCHAR,INTEGER Rack,INTEGER Shelf);");

        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.getText().toString().trim().length() == 0) {
                    showMessage("Error", "Please enter Book ID");
                    return;
                }
                Cursor c = db.rawQuery("SELECT * FROM book WHERE bid='" + id.getText() + "'", null);
                if (c.moveToFirst()) {
                    db.execSQL("DELETE FROM book WHERE bid='" + id.getText() + "'");
                    showMessage("Success", "Record Deleted");
                } else {
                    showMessage("Error", "Invalid Book ID");
                }
                clearText();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Book ID");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM book WHERE bid='"+id.getText()+"'", null);
                if(c.moveToFirst())
                {
                    name.setText(c.getString(1));
                    author.setText(c.getString(2));
                    Rack.setText(c.getString(3));
                    Shelf.setText(c.getString(4));


                    }
                else
                {
                    showMessage("Error", "Invalid Book ID");
                    clearText();
                }
            }

        });
    }

        public void showMessage (String title, String message)
        {
            Builder builder = new Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.show();
        }
        public void clearText ()
        {
            id.setText("");
            author.setText("");
            name.setText("");
            Rack.setText("");
            Shelf.setText("");

            id.requestFocus();
        }

    }



