package mt.locationmaps;

import android.app.Activity;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import com.google.android.gms.location.LocationServices;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Dao.DoDao;
import Model.Toalett;

public class MapsActivity extends Activity implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener, LocationListener{

    private GoogleMap mMap;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    List<Marker> markers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        MapFragment map = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);
        mMap = map.getMap();
        mMap.setMyLocationEnabled(true);
        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart(){
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        DoDao liste = new DoDao(getApplicationContext());
        Marker marker;
        markers = new ArrayList<Marker>();
        for(final Toalett toalett : liste.getToalett()){
            marker = map.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(toalett.getLatitude()), Double.valueOf(toalett.getLongitude()))));
            marker.setTitle(toalett.getPlassering());
            //marker.setSnippet("Pris: " + toalett.getPris());
            markers.add(marker);
        }

    }

    private Marker getNearestMarker(List<Marker> markers, Location loc) {

        Marker nearestMarker = null;
        double lowestDistance = Double.MAX_VALUE;
        LatLng origin = new LatLng(loc.getLatitude(), loc.getLongitude());

        if (markers != null) {

            for (Marker marker : markers) {

                double dist = distBetween(origin, marker.getPosition());

                if (dist < lowestDistance) {
                    nearestMarker = marker;
                    lowestDistance = dist;
                }
            }
        }

        return nearestMarker;
    }

    private float distBetween(LatLng pos1, LatLng pos2) {
        return distBetween(pos1.latitude, pos1.longitude, pos2.latitude,
                pos2.longitude);
    }

    /** distance in meters **/
    private float distBetween(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
                * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        int meterConversion = 1609;

        return (float) (dist * meterConversion);
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
           // mMap.addMarker(new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())));
            //Toast.makeText(getBaseContext(), mLastLocation.getLatitude() + " " + mLastLocation.getAltitude(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getBaseContext(),"NÆRMESTE: " + getNearestMarker(markers, mLastLocation).getTitle(), Toast.LENGTH_SHORT).show();
            getNearestMarker(markers, mLastLocation).setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        }
        else{
            Toast.makeText(getBaseContext(),"FEIL", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location){
        Toast.makeText(getBaseContext(), location.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result){
        Log.i("TAG", "Connection failed: ConnectionResult.getErrorCode()= " + result.getErrorCode());
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i("TAG", "Connection suspended");
        mGoogleApiClient.connect();
    }
}
