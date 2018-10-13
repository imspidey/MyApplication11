package spidey.com.reyaj.chat.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

public class student extends AppCompatActivity {
Button LIBRARY,RECOMANDATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        LIBRARY=(Button)findViewById(R.id.li);
        RECOMANDATION=(Button)findViewById(R.id.re);
        LIBRARY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ll=new Intent(student.this,search.class);
                startActivity(ll);
            }
        });
        RECOMANDATION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rr=new Intent(student.this,book.class);
                startActivity(rr);
            }
        });
    }
}
