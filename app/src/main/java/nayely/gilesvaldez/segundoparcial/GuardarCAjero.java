package nayely.gilesvaldez.segundoparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nayely.gilesvaldez.segundoparcial.BD.CCajero;
import nayely.gilesvaldez.segundoparcial.databinding.ActivityGuardarCajeroBinding;


public class GuardarCAjero extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityGuardarCajeroBinding binding;

    CCajero obj2 = new CCajero();
    Button BtnRegre,BtnGuarda,BtnSate,BtnNormal;
    View vista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGuardarCajeroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        BtnRegre=findViewById(R.id.BtnRegresarCajero);
        BtnGuarda=findViewById(R.id.BtnGuardar);
        BtnSate=findViewById(R.id.BtnSatelitalCajero);
        BtnNormal=findViewById(R.id.BtnNormalCajero);


        BtnRegre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Obj=new Intent(GuardarCAjero.this,activity_menu.class);
                startActivity(Obj);
            }
        });
        BtnSate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(mMap.MAP_TYPE_SATELLITE);
            }
        });
        BtnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.setMapType(mMap.MAP_TYPE_NORMAL);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng coord = new LatLng(-17.7835, -63.1806);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMinZoomPreference(12);
        mMap.addMarker(new MarkerOptions().position(coord).title("SantaCruz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coord));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng cc) {
                String Zona = getIntent().getStringExtra("Zona");
                String Capacidad = getIntent().getStringExtra("Capacidad");
                String Estado = getIntent().getStringExtra("Estado");
                String Barrio = getIntent().getStringExtra("Barrio");

                MarkerOptions Marca = new MarkerOptions();
                Marca.position(cc);
                Marca.title("Zona: "+Zona+ "/"+" Capacidad Kw:"+ Capacidad+" / "+Estado);
                Marca.snippet(cc.latitude+" : "+cc.longitude);
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(cc));
                mMap.addMarker(Marca);
                BtnGuarda.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int CodZon =obj2.getCodZonForName(GuardarCAjero.this,Zona);
                        obj2.GuardarCaja(GuardarCAjero.this, CodZon,Integer.parseInt(Capacidad),cc.latitude,cc.longitude,Estado,Barrio);
                        Volver(vista);
                    }
                });
            }
        });
    }
    public void Volver (View vista){
        Intent OBJ = new Intent(this, activity_menu.class);
        startActivity(OBJ);
    }
}