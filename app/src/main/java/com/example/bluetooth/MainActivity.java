package com.example.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    CheckBox enable_bt,visible_bt;
    ImageView search_bt;
    TextView name_bt;
    ListView listView;

    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enable_bt = findViewById(R.id.enable_bt);
        search_bt= findViewById(R.id.search_bt);
        name_bt = findViewById(R.id.name_bt);
        listView = findViewById(R.id.list_view);

        name_bt.setText(getLocalBluetoothName());

        BA = BluetoothAdapter.getDefaultAdapter();

        if (BA == null){
            Toast.makeText(this,"Bluetooth not supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(BA.isEnabled()){
            enable_bt.setChecked(true);
        }
        enable_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { //this enables the bluetooth on the phone
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    BA.disable();
                    Toast.makeText(MainActivity.this, "Turned off", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intentOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intentOn,0);
                    Toast.makeText(MainActivity.this, "Turned on", Toast.LENGTH_SHORT).show();
                }
            }
        });

        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });
    }
    private void list(){ // this creates the list for the names of bluetooth devices
        pairedDevices = BA.getBondedDevices();
        ArrayList list = new ArrayList();

        for (BluetoothDevice bt : pairedDevices){
            list.add(bt.getName());
        }
        Toast.makeText(this,"Showing Devices", Toast.LENGTH_SHORT).show();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter((adapter));
    }
    public String getLocalBluetoothName(){  // This function gets the name of the bluetooth devices the phone is connected too.
        if(BA == null){
            BA = BluetoothAdapter.getDefaultAdapter();
        }
        String name = BA.getName();
        if(name == null){
            name = BA.getAddress();
        }
        return name;
    }
}
