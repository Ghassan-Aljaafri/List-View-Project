package com.example.listviewproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.midi.MidiDeviceService;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    EditText editText;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = findViewById(R.id.listView);
        this.editText = findViewById(R.id.addItemEditeText);
        this.arrayList = new ArrayList();

        this.arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
                intent.putExtra("index", position);
                intent.putExtra("value", String.valueOf(arrayList.get(position)));
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Warning!")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            final int item = position;

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList.remove(item);
                                arrayAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Item removed", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setMessage("Are you sure to delete this item?")
                        .setNeutralButton("cancel", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                return false;
            }
        });
    }

    public void addItem(View view) {
        String item = editText.getText().toString();
        if (!item.isEmpty()) {
            arrayList.add(editText.getText().toString());
            arrayAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Please add an Item", Toast.LENGTH_SHORT).show();
        }
    }
}
