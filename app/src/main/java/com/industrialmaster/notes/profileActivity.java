package com.industrialmaster.notes;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class profileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
    }

    public void saveProf(View v){
        EditText etName = findViewById(R.id.profile_name);
        EditText etEmail = findViewById(R.id.profile_email);
        EditText etMobile = findViewById(R.id.profile_mobile);

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String mobile = etMobile.getText().toString();

        //Get SP Object
        SharedPreferences profile = getSharedPreferences("profile", MODE_PRIVATE);
        //Get Editor Object from SP
        SharedPreferences.Editor  profileEditor = profile.edit();
        //Use putString() to add Key_Value Pairs
        profileEditor.putString("name", name);
        profileEditor.putString("email", email);
        profileEditor.putString("mobile", mobile);
        //call commit() to Save Changes
        profileEditor.commit();

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
    }
}

