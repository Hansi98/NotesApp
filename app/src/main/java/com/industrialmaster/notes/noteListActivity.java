package com.industrialmaster.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class noteListActivity extends AppCompatActivity {

    Map<Integer, String> data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(noteListActivity.this, notesAddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        File folder = new File(getFilesDir() + File.separator + "notes");
        if (folder.exists()) {

            File[] files = folder.listFiles();
            List<String> list = new ArrayList<>();
            int index = 0;

            for (File file : files) {

                data.put(index, file.getPath());
                index++;

                try {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] chars = new byte[fis.available()];

                    fis.read(chars);
                    fis.close();

                    String note = new String(chars);
                    list.add(note);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Error Reading..", Toast.LENGTH_SHORT).show();
                }

            }
            final ListView lv = findViewById(R.id.note_list);

            int layout = android.R.layout.simple_list_item_1;

            ArrayAdapter adapter = new ArrayAdapter(this, layout, list);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() { //ara list ekta onclick method ekka
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String filename = data.get(position);
                    Object o = lv.getItemAtPosition(position);
                    String note = (String) o;

                    Intent intent = new Intent(noteListActivity.this, notesAddActivity.class);
                    intent.putExtra("File", filename);
                    intent.putExtra("Note", note);
                    startActivity(intent);
                }
            });

        } else {
            Toast.makeText(this, "App has some serious issue.", Toast.LENGTH_SHORT).show();
        }




    }}
