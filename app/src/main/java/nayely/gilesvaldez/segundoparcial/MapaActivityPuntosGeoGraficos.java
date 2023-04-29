package nayely.gilesvaldez.segundoparcial;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import nayely.gilesvaldez.segundoparcial.BD.CCajero;
import nayely.gilesvaldez.segundoparcial.databinding.ActivityMapaPuntosGeoGraficosBinding;

public class MapaActivityPuntosGeoGraficos extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ActivityMapaPuntosGeoGraficosBinding binding;

    RadioButton Oes,Est,Nor,Sur;
    Button filtra;
    Paint paint = new Paint();


    CCajero OBJJ = new CCajero();
    LatLng Vcoord[] = new LatLng[100];
    String VDatos[] = new String[100];
    String Estado[] = new String[100];
    int Nro =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapaPuntosGeoGraficosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Oes = findViewById(R.id.Oeste);
        Est = findViewById(R.id.Este);
        Nor = findViewById(R.id.Norte);
        Sur = findViewById(R.id.Sur);
        filtra = findViewById(R.id.fil);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng coords = new LatLng(-17, -63);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coords));
        filtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMap.clear();

                if(Oes.isChecked()){
                    Nro = OBJJ.CargarTodosFiltroc2(MapaActivityPuntosGeoGraficos.this, Vcoord,VDatos,Estado,"OESTE");
                }else if(Est.isChecked()){
                    Nro = OBJJ.CargarTodosFiltroc2(MapaActivityPuntosGeoGraficos.this, Vcoord,VDatos,Estado,"ESTE");
                }else if (Nor.isChecked()){
                    Nro = OBJJ.CargarTodosFiltroc2(MapaActivityPuntosGeoGraficos.this, Vcoord,VDatos,Estado,"NORTE");
                }else if (Sur.isChecked()) {
                    Nro = OBJJ.CargarTodosFiltroc2(MapaActivityPuntosGeoGraficos.this, Vcoord, VDatos, Estado, "SUR");
                }
                for(int i = 0; i<Nro;i++){
                    MarkerOptions Marca = new MarkerOptions();
                    Marca.position(Vcoord[i]);
                    Marca.title(VDatos[i]+" / "+ Estado[i]+"/");

                    if(Nro>(i+1)){
                        Location locationA = new Location("punto A");

                        locationA.setLatitude(Vcoord[i].latitude);
                        locationA.setLongitude(Vcoord[i].longitude);

                        Location locationB = new Location("punto B");

                        locationB.setLatitude(Vcoord[i+1].latitude);
                        locationB.setLongitude(Vcoord[i+1].longitude);

                        double distance = locationA.distanceTo(locationB);

                        PolylineOptions x = new PolylineOptions();

                        x.color(Color.RED);
                        x.width(5);


                        MarkerOptions dist = new MarkerOptions();

                        LatLng Posmedio = new LatLng(((Vcoord[i].latitude)+(Vcoord[i+1].latitude))/2,((Vcoord[i].longitude)+(Vcoord[i+1].longitude))/2);
                        dist.position(Posmedio);
                        dist.title(""+distance +" ( "+VDatos[i]+" - "+VDatos[i+1]+" )");
                        dist.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                        x.add(Vcoord[i],Vcoord[i+1]);

                        mMap.addPolyline(x);
                        mMap.addMarker(dist);
                    }
                    if(Estado[i].equals("OK"))
                        Marca.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    /*if(Estado[i].equals("HABILITADO")){
                        Marca.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    }else if(Estado[i].equals("INHABILITADO")) {
                        Marca.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }*/
                    mMap.addMarker(Marca);
                }
            }
        });
    }
    public void Volver (View vista){
        Intent OBJ = new Intent(this, MainActivity.class);
        startActivity(OBJ);
    }
}
