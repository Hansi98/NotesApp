package com.industrialmaster.notes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class notesAddActivity extends AppCompatActivity {

    String fileName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_add);

        EditText etNote = findViewById(R.id.note_text);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            String note = bundle.getString("Note");
            fileName = bundle.getString("File");
            etNote.setText(note);
        }
    }

    public void save(View v) {
        EditText etNote = findViewById(R.id.note_text);
        String note = etNote.getText().toString();
        Date date = new Date();
        String FILE_NAME = date.getTime()+".txt";

        if(fileName != null){
            FILE_NAME = fileName;
        }


        File file = new File(getFilesDir()+File.separator+"notes"+File.separator+FILE_NAME);
        //location/notes/76262454.txt

        try{
            FileOutputStream fos = new FileOutputStream(file);

            fos.write(note.getBytes());
            fos.close();
            Toast.makeText(this, "SAVED!", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

            e.printStackTrace();
            Toast.makeText(this, "ERROR!", Toast.LENGTH_SHORT).show();
        }
    }
}
