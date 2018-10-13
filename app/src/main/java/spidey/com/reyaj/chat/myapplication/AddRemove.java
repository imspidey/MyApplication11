
package spidey.com.reyaj.chat.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddRemove extends AppCompatActivity {
    Button Add_Book, Remove_Book, brec;
    EditText bn, edition, aname;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_remove);
        Add_Book = findViewById(R.id.add);
        Remove_Book = findViewById(R.id.re);
        brec = findViewById(R.id.brec);
        db = openOrCreateDatabase("Book_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS book(bid INTEGER,bname VARCHAR,aname VARCHAR,rack INTEGER,shel INTEGER)");

        brec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.rawQuery("SELECT * FROM book", null);
                if (c.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("Book ID: "+c.getString(0)+"\n");
                    buffer.append("Author: "+c.getString(1)+"\n\n");
                    buffer.append("Book Name: "+c.getString(2)+"\n\n");
                    buffer.append("rack: "+c.getString(3)+"\n\n");
                    buffer.append("shelf: "+c.getString(4)+"\n\n");
                }
                showMessage("Book Details", buffer.toString());
            }
        });

        Add_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(AddRemove.this, add.class);
                startActivity(aa);
            }
        });
        Remove_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bb = new Intent(AddRemove.this, Remove.class);
                startActivity(bb);
            }
        });
    }

        public void showMessage (String title, String message)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.show();
        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_scrolling, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
}








