package com.example.foursquareassig;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class ShowMap extends MapActivity implements LocationListener {
   
	MapView map;
	long start;
	long stop;
	public static JSONRequest jr = new JSONRequest();
	MyLocationOverlay compass;
	MapController controller;
	int x,y;
	GeoPoint touchedPoint;
	GeoPoint myPoint;
	Drawable d;
	List<Overlay> overlayList;
	LocationManager lm;
	String towers;
	int lat =0;
    int lng= 0;
    int a = 60898388;
    int b = 10569580;
    int alat=60298388;
    int along=10069580;
    double plat;
    double plng;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
       
        map=(MapView)findViewById(R.id.mvMain);
        map.setBuiltInZoomControls(true);
        Intent i = getIntent();
        int p = i.getIntExtra(Main.MESSAGE, -1);
        //Toast.makeText(this, p, Toast.LENGTH_SHORT).show();
        //plat = jr.latitude[p];
       // plng = jr.longitude[p];
        
       // alat = (int) (plat*1E6);
       // along = (int) (plng*1E6);
        
        //Current Location
        lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria c =new Criteria();
        towers =lm.getBestProvider(c, false);
        Location location=lm.getLastKnownLocation(towers);
    	lat = (int) (location.getLatitude()*1E6);
    	lng = (int) (location.getLongitude()*1E6);
        overlayList =map.getOverlays();
        compass= new MyLocationOverlay(ShowMap.this,map);
        overlayList.add(compass);
        controller = map.getController();
        GeoPoint point = new GeoPoint(lat, lng);
        controller.animateTo(point);
        controller.setZoom(15);
        d = getResources().getDrawable(R.drawable.marker);

        GeoPoint ourLocation=new GeoPoint(lat,lng);
        OverlayItem overlayItem =new OverlayItem(ourLocation, "Hello","Hello2");
   		PinPoint custom =new PinPoint(d, ShowMap.this);
   		custom.insertPinpoint(overlayItem);
    	overlayList.add(custom);
        
        //takes the coordinates of the selected place and displays a pinpoint on the map
        GeoPoint randomLocation=new GeoPoint(alat, along);
        OverlayItem overlayItem1 =new OverlayItem(randomLocation,"Yes","yesss");
        PinPoint mypoint=new PinPoint(d, ShowMap.this);
	    mypoint.insertPinpoint(overlayItem1);
	    overlayList.add(mypoint);
      }
     
    
  
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
    	compass.disableCompass();
		super.onPause();
		lm.removeUpdates(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		compass.enableCompass();
		super.onResume();
		lm.requestLocationUpdates(towers,500,1,this);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void onLocationChanged(Location l) {
		// TODO Auto-generated method stub
		lat=(int) (l.getLatitude()*1E6);
		lng=(int) (l.getLongitude()*1E6);
		GeoPoint ourLocation=new GeoPoint(lat,lng);
        OverlayItem overlayItem =new OverlayItem(ourLocation, "Hello","Hello2");
   		PinPoint custom1 =new PinPoint(d, ShowMap.this);
   		custom1.insertPinpoint(overlayItem);
   		overlayList.add(custom1);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub	
	}
}




