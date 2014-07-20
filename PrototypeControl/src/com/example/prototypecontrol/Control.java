package com.example.prototypecontrol;

import java.io.OutputStream;
import java.util.Set;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.os.Build;


public class Control extends ActionBarActivity {

	private View viewwaterinstance;
	private View viewsuctioninstance;
	float density;
	float pixHeight;
	float pixWidth;
	float btnWidth;
	BluetoothAdapter BTAdapter;
	BluetoothDevice btdevice;
	Set<BluetoothDevice> devices;
	private OutputStream out;
	private BluetoothSocket btsocket;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
		viewSetup();
		findPairedDevice();

	}
	
	private void viewSetup(){
		Display display = getWindowManager().getDefaultDisplay();
	    DisplayMetrics outMetrics = new DisplayMetrics ();
	    display.getMetrics(outMetrics);

	    density  = getResources().getDisplayMetrics().density;
	    pixHeight = outMetrics.heightPixels;
	    pixWidth  = outMetrics.widthPixels;
	    btnWidth = (pixWidth-90)/2;
		
		viewwaterinstance = (View)findViewById(R.id.waterbtn);
		viewsuctioninstance = (View)findViewById(R.id.suctionbtn);
		RelativeLayout.LayoutParams param1 = (android.widget.RelativeLayout.LayoutParams) viewwaterinstance.getLayoutParams();
		RelativeLayout.LayoutParams param2 = (android.widget.RelativeLayout.LayoutParams) viewsuctioninstance.getLayoutParams();
		param1.width=(int)btnWidth;
		param2.width=(int)btnWidth;
		viewwaterinstance.setLayoutParams(param1);
		viewsuctioninstance.setLayoutParams(param2);
	}
	
	private void findPairedDevice(){
		devices = BTAdapter.getBondedDevices();
		if(devices.size()>0){
			for(BluetoothDevice d:devices){
				if(d.getName().equals("RN42-454D")) //Note, you will need to change this to match the name of your device
                {
                    btdevice = d;
                    break;
                }
			}
		}
		else{
			Toast.makeText(getApplicationContext(), "could not find", Toast.LENGTH_LONG).show();
		}
		
	}

}
