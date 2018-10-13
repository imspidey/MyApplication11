package spidey.com.reyaj.chat.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class second extends AppCompatActivity {
TextView Entry_for_Student,Login_as_Admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Entry_for_Student=findViewById(R.id.es);
                Login_as_Admin=findViewById(R.id.admin);
        Entry_for_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l=new Intent(second.this,student.class);
                startActivity(l);
            }
        });
        Login_as_Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q=new Intent(second.this,log.class);
                startActivity(q);
            }
        });

    }
}
