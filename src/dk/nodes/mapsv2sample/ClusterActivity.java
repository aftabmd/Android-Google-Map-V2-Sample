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

public class ClusterActivity extends FragmentActivity {

	private GoogleMap mMap = null;
	private SupportMapFragment mMapFragment = null;
	
	private static final LatLng NODES = new LatLng(55.6658, 12.5806);
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		
		initMap();
		
		moveCamera();
		
		mMap.setMyLocationEnabled( true );
		
		testCluster();
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
	
	private void testCluster() {
		final NClusterV2 cluster = new NClusterV2( mMap );
		
		NMarkerV2 m = new NMarkerV2();
		m.setId( "First" );
		m.position( new LatLng( 55.6101, 12.555 ) );
		cluster.addMarker( m );
		
		m = new NMarkerV2();
		m.setId( "Second" );
		m.position( new LatLng( 55.6000, 12.555 ) );
		cluster.addMarker( m );
		
		m = new NMarkerV2();
		m.setId( "Third" );
		m.position( new LatLng( 55.5900, 12.544 ) );
		cluster.addMarker( m );
		
		m = new NMarkerV2();
		m.setId( "Fourth" );
		m.position( new LatLng( 55.6130, 12.535 ) );
		cluster.addMarker( m );
		
		cluster.setOnMarkerClickListener( new OnNMarkerClickListener() {
			@Override
			public void onMarkerClick( NMarkerV2 marker ) {
				Log.i("MainActivity", "onMarkerClicked --> " + marker.getId());
			}

			@Override
			public void onClusterClick( ArrayList<NMarkerV2> markers ) {
				Log.i("MainActivity", "onClusterClick --> " + markers.size() + " | " + markers.get( 0 ).getId());
			}
		});
		
		mMap.setOnCameraChangeListener( new OnCameraChangeListener() {
			
			@Override
			public void onCameraChange( CameraPosition arg0 ) {
				Log.i("","onCameraChange()");
				cluster.updateMarkers( mMapFragment.getView().getHeight(), mMapFragment.getView().getWidth() );
			}
		});
	}

}
