package com.roombastorm;

import roombacomm.RoombaCommSerial;
import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	RoombaCommSerial serial = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

        serial = new RoombaCommSerial();

        final Button connectBtn = (Button) findViewById(R.id.connectbtn);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	System.out.println("log test");
                serial.connect((UsbManager)getSystemService(Context.USB_SERVICE), MainActivity.this);
                serial.pause( 200 );
                serial.startup();
                serial.control();
                serial.playNote( 72, 10 );  // C , test note
                serial.pause( 200 );
            }
        });

        final Button forwardBtn = (Button) findViewById(R.id.forwardbtn);
        forwardBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                serial.goForward();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
