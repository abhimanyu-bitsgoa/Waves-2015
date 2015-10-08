package bits.mobileappclub.waves;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final LatLng CENTRALLAWN = new LatLng(15.39163,73.879207);
    private static final LatLng AUDITORIUM  = new LatLng(15.392983,73.880476);
    private static final LatLng LT1  = new LatLng(15.392406,73.88109);
    private static final LatLng LT2 = new LatLng(15.392711,73.881055);
    private static final LatLng LT3  = new LatLng(15.393529,73.880208);
    private static final LatLng LT4  = new LatLng(15.393611,73.879862);
    private static final LatLng CC  = new LatLng(15.39163,73.880994);
    private static final LatLng AWing  = new LatLng(15.393066,73.879765);
    private static final LatLng CWing  = new LatLng(15.392303,73.88057);
    private static final LatLng Library  = new LatLng(15.391457,73.880436);
    private static final LatLng LibraryLawn  = new LatLng(15.391749,73.880146);

    //private static final LatLng Sac = new LatLng(15.392073,73.875466);
    private MapFragment map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapView));
                map.getMapAsync(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        CameraPosition cameraPosition = new CameraPosition.Builder().
                target(CENTRALLAWN).
                tilt(60).
                zoom(17).
                bearing(0).
                build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
       // googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CENTRALLAWN, 17));

        GoogleMap gmap=map.getMap();

       Marker centralLawn= gmap.addMarker(new MarkerOptions()
                .position(CENTRALLAWN)
                .title("Central Lawns"));

        Marker auditorium =gmap.addMarker(new MarkerOptions()
                .position(AUDITORIUM)
                .title("Auditorium"));
        Marker lt1= gmap.addMarker(new MarkerOptions()
                .position(LT1)
                .title("LT1"));

        Marker lt2=gmap.addMarker(new MarkerOptions()
                .position(LT2)
                .title("LT2"));

        Marker lt3=gmap.addMarker(new MarkerOptions()
                .position(LT3)
                .title("LT3"));

        Marker lt4=gmap.addMarker(new MarkerOptions()
                .position(LT4)
                .title("LT4"));

        Marker cc=gmap.addMarker(new MarkerOptions()
                .position(CC)
                .title("CC"));

        Marker aWing=gmap.addMarker(new MarkerOptions()
                .position(AWing)
                .title("AWing"));

        ;
        Marker cwing=gmap.addMarker(new MarkerOptions()
                .position(CWing)
                .title("CWing"));

        Marker library=gmap.addMarker(new MarkerOptions()
                .position(Library)
                .title("Library"));

        Marker librarylawn =gmap.addMarker(new MarkerOptions()
                .position(LibraryLawn)
                .title("Library Lawns"));


    }
}
