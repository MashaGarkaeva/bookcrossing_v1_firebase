package com.bookcross;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class AddBookScreen extends AppCompatActivity {

    EditText name_book;
    EditText auhtor_book;
    Button save;
    Button prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        name_book = findViewById(R.id.name_book);
        auhtor_book = findViewById(R.id.auhtor_book);
        save = findViewById(R.id.save);
        prof = findViewById(R.id.see_place_books);
        save.setOnClickListener(view -> {

        });

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        resetUI();
    }
    private void resetUI() {
    }

    public void onClick (View v){
        startActivity(new Intent(AddBookScreen.this, RegistrScreen.class));
    }

}

