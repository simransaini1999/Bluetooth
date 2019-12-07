## Bluetooth
The project is Bluetooth. It is an android platform which supports the bluetooth network stack. Network stack allows a device to wirelessly exchange data with other bluetooth devices. What this project will do is that it will display all the bluetooth devices your phone remebered it was connected too. 

At the top of the file there should be a short introduction and/ or overview that explains **what** the project is. This description should match descriptions added for package managers (Gemspec, package.json, etc.)

## Code Example
The library I will be using is android.bluetoothAdapter. this lets you perform fundamental bluetooth tasks, such as initiate device discovery. It will give you a list of paired devices. 

Show what the library does as concisely as possible, developers should be able to figure out **how** your project solves their problem by looking at the code example. Make sure the API you are showing off is obvious, and that your code is short and concise.

## Motivation
This project lets the user look at the devices the phone has been paired to in the past. This project can help the user if the device isn't connecting to a Bluetooth device or is glitching, the user can go to this app and see if the phone still remembers that particular Bluetooth device or not. 

A short description of the motivation behind the creation and maintenance of the project. This should explain **why** the project exists.

## Installation
How to get this project is you will have to install android studio, then after installing start new project, Empty Activity, create a name for the application, choose a package name, choose the language as JAVA then make sure you also choose the minimum API level at 21 so most phones will be compatible with your app and click finish.  
The first thing after setting up the Bluetooth project you will have to set up the layout which with checkboxes and text views.
``` <CheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/enable_bt"
        android:text="Enable"/>

    <CheckBox
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/visible_bt"
        android:text="Visible"
        android:layout_alignParentRight="true"/>
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
visible_bt = findViewById(R.id.visible_bt);
search_bt= findViewById(R.id.search_bt);
name_bt = findViewById(R.id.name_bt);
listView = findViewById(R.id.list_view);
```

Now use the library android.bluetoothAdapter so the device can find the paired Bluetooth devices
```
BA = BluetoothAdapter.getDefaultAdapter();
```

After this set the checkboxes and the buttons and lists. In this when the user clicks on the enable checkbox, what it does is that it enables or disables the app from checking the list of Bluetooth in the phone. When the user Visible button is clicked it means that it is searching for the devices for 2 minutes and will display when the list is clicked, in this case the list is the search button which is the Bluetooth picture also a button. When the List is clicked it searches and displays the connected Bluetooth devices to the phone.
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
    visible_bt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(getVisible, 0);
                Toast.makeText(MainActivity.this, "Visible for 2 min", Toast.LENGTH_SHORT).show();
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
private void list(){
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
public String getLocalBluetoothName(){
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

## Testing the APP
Testing this App can be only done by running the code on an actual phone due to it being compatible by a Bluetooth device and having Bluetooth compatibility. It can not be running on the launcher phone provided by the android studio because that phone is not compatible with Bluetooth and itself is not possible to connect to any Bluetooth devices. 
How to work this app: 
1.	Click enable which will turn on the Bluetooth
2.	Click visible which will allow your phone to be visible to other devices for 120 seconds 
3.	Then click the Bluetooth button which will display the Bluetooth devices that are connected to your phone. 
4.	Also the app will display the phones name(aka the name you gave your phone) on the app indicating from to which device are the           all the Bluetooth devices displaying in a list are connected too. 
## Contributors

Let people know how they can dive into the project, include important links to things like issue trackers, irc, twitter accounts if applicable.

## License

A short snippet describing the

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
