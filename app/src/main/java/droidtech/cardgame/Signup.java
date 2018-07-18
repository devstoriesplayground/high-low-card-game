package droidtech.cardgame;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText fullname,username,userpass,confirmpass;
    Button btn_signup;

    CardgameDBHandler db;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        db = new CardgameDBHandler(this);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        userpass = findViewById(R.id.userpass);
        confirmpass = findViewById(R.id.confirm);

        btn_signup = findViewById(R.id.signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fullname.getText().toString().equals("") && username.getText().toString().equals("") && userpass.getText().toString().equals("") && confirmpass.getText().toString().equals("")) {

                }else if ((!fullname.getText().toString().equals("")) && (!username.getText().toString().equals("")) && (!userpass.getText().toString().equals("")) && (!confirmpass.getText().toString().equals(""))) {
                    if (userpass.getText().toString().equals(confirmpass.getText().toString())) {

                        sqLiteDatabase = db.getWritableDatabase();
                        db.addUser(fullname.getText().toString(),username.getText().toString(),userpass.getText().toString(),sqLiteDatabase);
                        Toast.makeText(Signup.this,"You have successfully added the user", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(Signup.this, MainActivity.class);
                        startActivity(login);

                    } else {
                        userpass.setText("");
                        confirmpass.setText("");
                        Toast.makeText(Signup.this,"Password mismatch in the confirm field. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });

    }
}
