## Bluetooth
This webpage will be discussed on Bluetooth and will explain on how develop your own Bluetooth android app in android studio. Bluetooth is an android platform which supports the Bluetooth network stack. Network stack allows a device to wirelessly exchange data with other Bluetooth devices. What this app will do is that it will display all the Bluetooth devices your Android phone has connected to in the past or is connected to at the moment as the app is being operated. If your phones Bluetooth is off this app will make sure that is on by having its own Bluetooth on/off switch, so that the no matter if the phone’s Bluetooth is on/off the app will turn on the Bluetooth to be assure that after the other functionalities will run properly. 

## Classes
The class I will be using is android.bluetoothAdapter. This lets you perform fundamental bluetooth tasks, such as initiate device discovery. It will give you a list of paired devices. 

## Motivation
This project lets the user look at the devices the phone has been paired to in the past. This project can help the user if the device isn't connecting to a Bluetooth device or is glitching, the user can go to this app and see if the phone still remembers that particular Bluetooth device or not. 

## Installation
How to get this project is you will have to install android studio, then after installing start new project, Empty Activity, create a name for the application, choose a package name, choose the language as JAVA then make sure you also choose the minimum API level at 21 so most phones will be compatible with your app and click finish.  
The first thing after setting up the Bluetooth project you will have to set up the layout which with checkboxes and text views.
``` <CheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/enable_bt"
        android:text="Enable"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="my bt name"
        android:layout_centerHorizontal="true"
        android:textStyle="bold"
        android:id="@+id/name_bt"
        android:textSize="20sp"/>

    <ImageView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="#368ACE"
        android:padding="5dp"
        android:id="@+id/search_bt"
        android:layout_below="@+id/name_bt"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="20dp"
        android:src="@drawable/ic_action_search" />
    
    <ListView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/list_view"
        android:layout_below="@id/search_bt"/>
```
This is just an example of setting up the checkboxes and text views. 
After setting all this the next thing you need to move on to is the MainActivity where you will first declare the checkboxes,Imageview,Text view, and List View before the onCreate. 
```public class MainActivity extends AppCompatActivity {
    CheckBox enable_bt,visible_bt;
    ImageView search_bt;
    TextView name_bt;
    ListView listView;
```
Next you declare the find view by id for the layout actions:
``` enable_bt = findViewById(R.id.enable_bt);
search_bt= findViewById(R.id.search_bt);
name_bt = findViewById(R.id.name_bt);
listView = findViewById(R.id.list_view);
```

Now use the library android.bluetoothAdapter so the device can find the paired Bluetooth devices
```
BA = BluetoothAdapter.getDefaultAdapter();
```

After this set the checkboxes and the buttons and lists. In this when the user clicks on the enable checkbox, what it does is that it enables or disables the app from checking the list of Bluetooth in the phone. List is the search button with the Bluetooth picture, also a button. When the List is clicked it creats an array list for the names of the bluetooth devices to be put in after the getLocalBluetoothName method is excuted. After there is data put into the list it will displays the names of the connected Bluetooth devices to the phone.
```
enable_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
private void list(){   // this creates the list for the names of bluetooth devces 
    pairedDevices = BA.getBondedDevices();
    ArrayList list = new ArrayList();

    for (BluetoothDevice bt : pairedDevices){
        list.add(bt.getName());
    }
    Toast.makeText(this,"Showing Devices", Toast.LENGTH_SHORT).show();
    ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
    listView.setAdapter((adapter));
}

```


Lastly, we will have to create a method so that it can get the local Bluetooth name. This will be where after the search button being pressed and when the Bluetooth devices are displayed. For the Bluetooth devices to be displayed we will need to make a method to get the local Bluetooth name. 
```
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

```

So now after making this, declare the function in the onCreate.

## API Reference


Depending on the size of the project, if it is small and simple enough the reference docs can be added to the README. For medium size to larger projects it is important to at least provide a link to where the API reference docs live.

## How to use the application
Testing this App can be only done by running the code on an actual phone due to it being compatible by a Bluetooth device and having Bluetooth compatibility. It can not be running on the launcher phone provided by the android studio because that phone is not compatible with Bluetooth and itself is not possible to connect to any Bluetooth devices. 
How to work this app: 
1.	Click enable which will turn on the Bluetooth
2.	Then click the Bluetooth button which will display the Bluetooth devices that are connected to your phone. 
3.	Also the app will display the phones name(aka the name you gave your phone) on the app indicating from to which device are the           all the Bluetooth devices displaying in a list are connected too. 
 
## Reference
During this project I had referred to [Android Developers](https://developer.android.com/) and a turtorial on [youtube](https://www.youtube.com/watch?v=iFtjox9_zAI) on how to implement bluetooth on an actual device. 
