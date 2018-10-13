package spidey.com.reyaj.chat.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add extends AppCompatActivity {

    EditText etname,etauthor,etbookid,rack,shel;
    Button btnadd;
    Button btview;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);




                etname = (EditText)findViewById(R.id.etname);
                etauthor = (EditText)findViewById(R.id.etauthor);
                etbookid = (EditText)findViewById(R.id.etbookid);
                rack=(EditText)findViewById(R.id.Rack);
                shel=(EditText)findViewById(R.id.shel);

                btnadd = (Button)findViewById(R.id.btnadd);

                btview=(Button)findViewById(R.id.btview);
        db=openOrCreateDatabase("Book_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS book(bid INTEGER,bname VARCHAR,aname VARCHAR,rack INTEGER,shel INTEGER)");




        btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(etbookid.getText().toString().trim().length()==0||
                                etauthor.getText().toString().trim().length()==0||
                                etname.getText().toString().trim().length()==0||
                            rack.getText().toString().trim().length()==0||
                        shel.getText().toString().trim().length()==0)

                        {
                            showMessage("Error", "Please enter all values");
                            return;
                        }
                        db.execSQL("INSERT INTO book VALUES('"+etbookid.getText()+"','"+etauthor.getText()+
                                "','"+etname.getText()+"','"+rack.getText()+"','"+shel.getText()+"');");
                        showMessage("Success", "Record added successfully");
                        clearText();

                    }
                });
        btview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM book", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Book ID: "+c.getString(0)+"\n");
                    buffer.append("Author: "+c.getString(1)+"\n");
                    buffer.append("Book Name: "+c.getString(2)+"\n\n");
                    buffer.append("rack: "+c.getString(3)+"\n\n");
                    buffer.append("shelf: "+c.getString(4)+"\n\n");
                }
                showMessage("Book Details", buffer.toString());
            }
        });
            }







    public void showMessage(String title,String message)
    {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        etbookid.setText("");
        etauthor.setText("");
        etname.setText("");
        rack.setText("");
        shel.setText("");
        etbookid.requestFocus();
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








