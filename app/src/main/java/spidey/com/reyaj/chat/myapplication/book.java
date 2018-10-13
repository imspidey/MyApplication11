package spidey.com.reyaj.chat.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class book extends AppCompatActivity {
    EditText bn,edition,aname ;
    Button SUBMIT;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        bn = (EditText)findViewById(R.id.bn);
        edition = (EditText)findViewById(R.id.edition);
        aname = (EditText)findViewById(R.id.aname);
        SUBMIT=(Button) findViewById(R.id.SUBMIT);
        db=openOrCreateDatabase("Book_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS book(edition INTEGER,bn VARCHAR,aname VARCHAR)");

        SUBMIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bn.getText().toString().trim().length()==0||
                        aname.getText().toString().trim().length()==0||
                        edition.getText().toString().trim().length()==0)


                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO book VALUES('"+bn.getText()+"','"+aname.getText()+
                        "','"+edition.getText()+"');");
               showMessage("Success", "added successfully");
                clearText();

            }
        });

    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        bn.setText("");
        aname.setText("");
        edition.setText("");

        bn.requestFocus();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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





