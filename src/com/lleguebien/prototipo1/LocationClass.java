package com.lleguebien.prototipo1;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
//import com.neopixel.listenapp.SplashActivity;

public class LocationClass
  implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener, LocationListener
{
  private final LocationRequest REQUEST = LocationRequest.create().setInterval(5000L).setFastestInterval(160L).setPriority(100);
  private final String TAG = "Main";
  private Context context;
  private LocationClient mLocationClient;
  private Location previousLocation;

  public LocationClass(Context paramContext)
  {
    this.context = paramContext;
  }

  private void setUpLocationClientIfNeeded()
  {
    if (this.mLocationClient == null)
      this.mLocationClient = new LocationClient(this.context, this, this);
  }

  public LocationClient getLocationClient()
  {
    return this.mLocationClient;
  }

  public Location getPreviousLocation()
  {
    return this.previousLocation;
  }

  public void onConnected(Bundle paramBundle)
  {
    this.previousLocation = new Location("0");
    this.mLocationClient.requestLocationUpdates(this.REQUEST, this);
  }

  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
  }

  public void onDisconnected()
  {
  }

  public void onLocationChanged(Location paramLocation)
  {
    if ((this.previousLocation.getLatitude() == 0.0D) && (this.previousLocation.getLongitude() == 0.0D))
    {
      Log.w("Map", "Init Location = " + paramLocation);
      //SplashActivity.mainController.updateLocation(paramLocation);
      this.previousLocation.set(paramLocation);
      return;
    }
    if (paramLocation.distanceTo(this.previousLocation) > 1.0F)
    {
      Log.w("Distance", String.valueOf(paramLocation.distanceTo(this.previousLocation)));
      Log.w("Map", "Location = " + paramLocation);
      this.previousLocation.set(paramLocation);
      //SplashActivity.mainController.updateLocation(paramLocation);
      return;
    }
    Log.w("Distance", String.valueOf(paramLocation.distanceTo(this.previousLocation)));
  }

  public void onPause()
  {
    if (this.mLocationClient != null)
      this.mLocationClient.disconnect();
  }

  public void onResume()
  {
    setUpLocationClientIfNeeded();
    this.mLocationClient.connect();
  }

  public void showMyLocation(View paramView)
  {
    if ((this.mLocationClient != null) && (this.mLocationClient.isConnected()))
    {
      String str = "Location = " + this.mLocationClient.getLastLocation();
      Toast.makeText(this.context, str, 0).show();
    }
  }
}