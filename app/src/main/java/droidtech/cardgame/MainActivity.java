package droidtech.cardgame;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, userpass;
    Button btn_signIn, btn_signUp;
    CardgameDBHandler db;
    Cardgame cg;
    public boolean isLogging;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new CardgameDBHandler(this);
        username = findViewById(R.id.username);
        userpass = findViewById(R.id.userpass);

        btn_signIn = findViewById(R.id.signin);
        btn_signUp = findViewById(R.id.signup);

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().equals("") && (!userpass.getText().toString().equals("")))
                {
                    if (db.isValid(username.getText().toString(),userpass.getText().toString())) {
                        if (!username.getText().toString().equals("") && (!userpass.getText().toString().equals(""))) {
                            cg = db.getUserByUsernamePassword(username.getText().toString(), userpass.getText().toString());
                            Intent play = new Intent(MainActivity.this, PlayGround.class);
                            startActivity(play);
                        }
                    }else {
                        isLogging = false;
                        Toast.makeText(MainActivity.this, "No credentials found.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "No credentials found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

       btn_signUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent signup = new Intent(MainActivity.this, Signup.class);
               startActivity(signup);
           }
       });
    }
}

