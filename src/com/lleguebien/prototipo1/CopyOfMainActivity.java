package com.lleguebien.prototipo1;

import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class CopyOfMainActivity extends FragmentActivity implements
				GooglePlayServicesClient.ConnectionCallbacks,
				GooglePlayServicesClient.OnConnectionFailedListener,
				LocationListener, android.location.LocationListener{
    private GoogleMap mMap;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    static final int REQUEST_CODE_RECOVER_PLAY_SERVICES = 1001;
    
    Location mCurrentLocation;
    LocationManager locationManager;
    LocationClient mLocationClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         
        /********** get Gps location service LocationManager object ***********/
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         
        /* CAL METHOD requestLocationUpdates */
           
          // Parameters :
          //   First(provider)    :  the name of the provider with which to register 
          //   Second(minTime)    :  the minimum time interval for notifications, 
          //                         in milliseconds. This field is only used as a hint 
          //                         to conserve power, and actual time between location 
          //                         updates may be greater or lesser than this value. 
          //   Third(minDistance) :  the minimum distance interval for notifications, in meters 
          //   Fourth(listener)   :  a {#link LocationListener} whose onLocationChanged(Location) 
          //                         method will be called for each location update 
        
         
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                3000,   // 3 sec
                10, this);
         
        /********* After registration onLocationChanged method  ********/
        /********* called periodically after each 3 sec ***********/
    }
     
    /************* Called after each 3 sec **********/
    @Override
    public void onLocationChanged(Location location) {
            
        String str = "Latitude: "+location.getLatitude()+"Longitude: "+location.getLongitude();

        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }
 
    @Override
    public void onProviderDisabled(String provider) {
         
        /******** Called when User off Gps *********/
         
        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();
    }
 
    @Override
    public void onProviderEnabled(String provider) {
         
        /******** Called when User on Gps  *********/
         
        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
         
    }

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
}


