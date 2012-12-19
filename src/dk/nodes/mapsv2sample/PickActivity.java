package dk.nodes.mapsv2sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class PickActivity extends Activity {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_pick );
	}
	
	public void onClusterClick( View v ) {
		Intent i = new Intent( this, ClusterActivity.class );
		startActivity( i );
	}
	
	public void onStandardClick( View v ) {
		Intent i = new Intent( this, StandardActivity.class );
		startActivity( i );
	}
	
}
