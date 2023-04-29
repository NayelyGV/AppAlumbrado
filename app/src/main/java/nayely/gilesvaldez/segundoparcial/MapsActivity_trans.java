package nayely.gilesvaldez.segundoparcial;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import nayely.gilesvaldez.segundoparcial.BD.CTransacciones;
import nayely.gilesvaldez.segundoparcial.databinding.ActivityMapsTransBinding;

public class MapsActivity_trans extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsTransBinding binding;

    View vista;
    LatLng Vcoord[] = new LatLng[100];
    String VDatos[] = new String[100];
    String VLug[] = new String[100];
    GridView GV;
    int Nro =0;
    CTransacciones OBJ = new CTransacciones();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsTransBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String nombreZon = getIntent().getStringExtra("Zona");
        GV = findViewById(R.id.gv2);
        //OBJ.CargarTodosTransaccionesGV(this,  nombreZon, GV);
        //Nro = OBJ.CargarTodosTransaccionesss(this, Vcoord, VDatos,VLug,nombreZon);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_trans,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int idItemSeleccionados = item.getItemId();
        if(idItemSeleccionados == R.id.home2){
            Home(vista);
        }
        return super.onOptionsItemSelected(item);
    }

    public void Home(View vista){
        Intent OBJ = new Intent(MapsActivity_trans.this, MainActivity.class);
        startActivity(OBJ);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng coord = new LatLng(-17.7835, -63.1806);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(12);
        //mMap.addMarker(new MarkerOptions().position(coord).title("SantaCruz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coord));

        for(int i = 0; i<Nro;i++){
            MarkerOptions Marca = new MarkerOptions();
            Marca.position(Vcoord[i]);
            Marca.title(VDatos[i]);

            if(VLug[i].contains("OESTE")){
                Marca.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round));
            }else if(VLug[i].contains("ESTE")){

                Marca.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round));
            }else if (VLug[i].contains("NORTE")){
                Marca.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round));
            }else if (VLug[i].contains("SUR")){
                Marca.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round));
            }

            mMap.addMarker(Marca);
        }
    }
}