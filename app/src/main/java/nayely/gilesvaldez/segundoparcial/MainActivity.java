package nayely.gilesvaldez.segundoparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import nayely.gilesvaldez.segundoparcial.BD.CTransacciones;

public class MainActivity extends AppCompatActivity {
    Spinner Zona;
    CTransacciones OBJ2 = new CTransacciones();
    //CCajero Obj= new CCajero();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Zona = findViewById(R.id.Zon);

        //OBJ2.CargarZonasTrans(this,Zona);
    }

    public void MapaTransacciones (View vista){
        Intent OBJ = new Intent(MainActivity.this, MapsActivity_trans.class);
        OBJ.putExtra("Zona",Zona.getSelectedItem().toString());
        startActivity(OBJ);
    }

    public void RegistrarCaja (View vista){
        Intent OBJ = new Intent(MainActivity.this, activity_menu.class);
        startActivity(OBJ);
    }


    public void MapaGeneral (View vista){
        Intent OBJ = new Intent(MainActivity.this, MapaGeneral.class);
        startActivity(OBJ);
    }

    public void MapaGeneralPuntosGeograficos (View vista){
        Intent OBJ = new Intent(MainActivity.this, MapaActivityPuntosGeoGraficos.class);
        startActivity(OBJ);
    }

    public void Transacciones (View vista){
        Intent OBJ = new Intent(MainActivity.this, Transacciones.class);
        startActivity(OBJ);
    }
}