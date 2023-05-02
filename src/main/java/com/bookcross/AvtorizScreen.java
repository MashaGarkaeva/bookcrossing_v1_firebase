package com.bookcross;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AvtorizScreen extends AppCompatActivity {

    Button enter;
    EditText loginUserName;
    EditText loginPassword;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avtoriz);

        loginUserName = findViewById(R.id.login);
        loginPassword = findViewById(R.id.password);
        enter = findViewById(R.id.enter1);
        loginText = findViewById(R.id.loginText);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUserName() | ! validatePassword()){

                } else {
                    checkUser();
                }
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AvtorizScreen.this, RegistrScreen.class));
            }
        });
    }

    public Boolean validateUserName(){
        String val = loginUserName.getText().toString();
        if (val.isEmpty()){
            loginUserName.setError("Введите логин");
            return false;
        } else {
            loginUserName.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()){
            loginPassword.setError("Введите пароль");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUserName = loginUserName.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUserName);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (snapshot.exists()){
                    loginUserName.setError(null);
                    String passwordFromDb = snapshot.child(userUserName).child("Password").getValue(String.class);

                    if (passwordFromDb.equals(userPassword)){
                        loginUserName.setError(null);
                        Intent intent = new Intent(AvtorizScreen.this, ProfilScreen.class);


                        /*String nameDb = snapshot.child(userUserName).child("Name").getValue(String.class);
                        String emailDb = snapshot.child(userUserName).child("Email").getValue(String.class);
                        String usernameDb = snapshot.child(userUserName).child("userName").getValue(String.class);

                        intent.putExtra("Name", nameDb);
                        intent.putExtra("Email", emailDb);
                        intent.putExtra("userName", usernameDb);
                        intent.putExtra("Password", passwordFromDb);*/
                        startActivity(intent);
                    } else {
                        loginPassword.setError("Invalid");
                        loginPassword.requestFocus();
                    }
                } else {
                    loginUserName.setError("Пользователь не найден");
                    loginUserName.requestFocus();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

}
