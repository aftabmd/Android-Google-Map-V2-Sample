package dk.nodes.mapsv2sample;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import dk.nodes.map.v2.NClusterV2;
import dk.nodes.map.v2.NClusterV2.OnNMarkerClickListener;
import dk.nodes.map.v2.model.NMarkerV2;
import dk.nodes.mapsv2sample.controllers.CustomInfoAdapter;

public class StandardActivity extends FragmentActivity {

	private GoogleMap mMap = null;
	private SupportMapFragment mMapFragment = null;
	
	private static final LatLng NODES = new LatLng(55.6658, 12.5806);
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		initMap();
		
		moveCamera();
		
		addMarker();
		
		addLine();
		
		mMap.setMyLocationEnabled( true );
		
		setupMarkerListener();
		
		addCustomMarker();
	}
	
	private void initMap() {
		mMapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
		mMap = mMapFragment.getMap();
		
		CustomInfoAdapter mAdapter = new CustomInfoAdapter( getLayoutInflater() );
		mMap.setInfoWindowAdapter( mAdapter );
	}
	
	private void moveCamera() {
		CameraPosition cameraPosition = new CameraPosition.Builder()
	    .target(NODES)      // Sets the center of the map to Mountain View
	    .zoom(17)           // Sets the zoom
	    .bearing(90)        // Sets the orientation of the camera to east
	    .tilt(30)           // Sets the tilt of the camera to 30 degrees
	    .build();           // Creates a CameraPosition from the builder
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	}
	
	private void addMarker() {
		Marker m = mMap.addMarker(
			new MarkerOptions()
			.position(NODES)
			.title("Nodes")
			.snippet( "Casper elsker blackberry" )
			.draggable( false )
			.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
		);
	}
	
	private void addLine() {
		mMap.addPolyline(
			new PolylineOptions()
		    .add(new LatLng(55.66613, 12.5816), NODES)
		    .add(NODES, new LatLng(55.66397, 12.5852))
		    .add(new LatLng(55.66397, 12.5852), new LatLng(55.66351, 12.5851))
		    .width(5)
		    .color(Color.BLUE)
		);
	}
	
	private void setupMarkerListener() {
		mMap.setOnMarkerClickListener( new OnMarkerClickListener() {			
			@Override
			public boolean onMarkerClick( Marker marker ) {
				moveCamera();
				if( marker.isInfoWindowShown() ) {
					marker.hideInfoWindow();
				} else {
					marker.showInfoWindow();
				}
				return true;
			}
		});
	}
	
	private void addCustomMarker() {
		mMap.addMarker(
			new MarkerOptions()
			.position(new LatLng(55.66351, 12.5851))
			.title("Metro")
			.draggable( false )
			.icon(BitmapDescriptorFactory.fromResource( R.drawable.mapmarker ))
		);
	}
	

}
