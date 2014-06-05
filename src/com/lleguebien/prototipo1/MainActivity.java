package com.lleguebien.prototipo1;

import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import com.lleguebien.prototipo1.LocationClass;

public class MainActivity extends FragmentActivity implements
				GooglePlayServicesClient.ConnectionCallbacks,
				GooglePlayServicesClient.OnConnectionFailedListener,
				LocationListener{
    private GoogleMap mMap;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    static final int REQUEST_CODE_RECOVER_PLAY_SERVICES = 1001;
    
    Location mCurrentLocation;
    LocationClass l;
    
    LocationClient mLocationClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpMapIfNeeded();
        
        //LocationClient mLoc = l.getLocationClient();
        
        
        //final TextView lblLat = (TextView)findViewById(R.string.latitud); 
        //lblLat.setText("bla");
        mLocationClient = new LocationClient(this, this, this);
        mLocationClient.connect();
        //mCurrentLocation = mLocationClient.getLastLocation();
        //mCurrentLocation.getLatitude();
        //mCurrentLocation.getLongitude();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    
    @Override  
    protected void onStart(){
    	super.onStart();
    	//l = new LocationClass(this);
    	//l.onResume();
    	//String bla = 
    	//		String.
    	//		valueOf(l.getLocationClient().getLastLocation().getLatitude());
    	//Log.i("APPPPPP", String.
    	//    			valueOf(l.getPreviousLocation().getLatitude()));
        // Connect the client.
        //mLocationClient.connect();
    }
    
    @Override
    protected void onStop(){
    	mLocationClient.disconnect();
        super.onStop();
    }

    private void setUpMapIfNeeded() {
        if (mMap != null) {
            return;
        }
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mMap == null) {
            return;
        }
        // Initialize map options. For example:
        // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            showErrorDialog(connectionResult.getErrorCode());
        }
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
        while (true){
        	if(mLocationClient.getLastLocation() != null) break;
        	
        }
        String bla = mLocationClient.getLastLocation().toString();
        Toast.makeText(this, "Connected"+ bla, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	
	void showErrorDialog(int code) {
		  GooglePlayServicesUtil.getErrorDialog(code, this, 
		      REQUEST_CODE_RECOVER_PLAY_SERVICES).show();
		}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}
}
