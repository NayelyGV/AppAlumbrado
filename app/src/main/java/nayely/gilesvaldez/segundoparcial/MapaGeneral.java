package nayely.gilesvaldez.segundoparcial;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nayely.gilesvaldez.segundoparcial.BD.CCajero;
import nayely.gilesvaldez.segundoparcial.databinding.ActivityMapaGeneralBinding;

public class MapaGeneral extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapaGeneralBinding binding;
    CCajero OBJJ = new CCajero();
    LatLng Vcoord[] = new LatLng[100];
    String VDatos[] = new String[100];
    String VZon[] = new String[100];
    int Nro =0;

    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapaGeneralBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        regresar = findViewById(R.id.regresar);

        Nro = OBJJ.CargarTodos(this, Vcoord,VDatos,VZon);


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng coords = new LatLng(-17, -63);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coords));

        for(int i = 0; i<Nro;i++){
            MarkerOptions Marca = new MarkerOptions();
            Marca.position(Vcoord[i]);
            Marca.title(VDatos[i]+" / "+VZon[i]);
            if(VZon[i].contains("OESTE")){
                Marca.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            }else if(VZon[i].contains("ESTE")){
                Marca.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            }else if (VZon[i].contains("NORTE")){
                Marca.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }else if (VZon[i].contains("SUR")){
                Marca.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }
        }
    }
    public void Volver (View vista){
        Intent OBJ = new Intent(this, MainActivity.class);
        startActivity(OBJ);
    }
}