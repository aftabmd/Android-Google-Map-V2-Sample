package dk.nodes.mapsv2sample.controllers;

import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

import dk.nodes.mapsv2sample.R;


public class CustomInfoAdapter implements InfoWindowAdapter {

	LayoutInflater inflater = null;
	
	public CustomInfoAdapter( LayoutInflater inflater ) {
		this.inflater = inflater;
	}
	
	/*
	 * Provides custom contents for the default info-window frame of a marker. 
	 * This method is only called if getInfoWindow(Marker) first returns null. 
	 * If this method returns a view, it will be placed inside the default info-window frame.
	 */
	@Override
	public View getInfoContents( Marker arg0 ) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Provides a custom info-window for a marker. If this method returns a view, 
	 * it is used for the entire info-window. If you change this view after this method is called, 
	 * those changes will not necessarily be reflected in the rendered info-window. 
	 * If this method returns null , the default info-window frame will be used, 
	 * with contents provided by getInfoContents(Marker).
	 */
	@Override
	public View getInfoWindow( Marker marker ) {
		
		return null;
	}

}
