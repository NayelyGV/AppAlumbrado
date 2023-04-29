package nayely.gilesvaldez.segundoparcial;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import nayely.gilesvaldez.segundoparcial.BD.CCajero;

public class activity_menu extends AppCompatActivity {
    Spinner ZONAS;
    TextView Cap,Barr;
    RadioButton Ok;
    CCajero obj = new CCajero();
    GridView GV;
    View Vista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ZONAS=findViewById(R.id.Zon);
        Cap = findViewById(R.id.Op1Tipo);
        Barr=findViewById(R.id.Barrio);
        Ok = findViewById(R.id.Estado);
        GV = findViewById(R.id.gv2);

        obj.CargarZona(this, ZONAS);
        obj.MostrarCajas(this,GV);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_trans,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int idItemSeleccionados = item.getItemId();
        if(idItemSeleccionados == R.id.home2)
            Volver(Vista);

        return super.onOptionsItemSelected(item);
    }
    public void guardar (View vista){
        Intent OBJ = new Intent(this, GuardarCAjero.class);
        OBJ.putExtra("Zona",ZONAS.getSelectedItem().toString());
        OBJ.putExtra("Capacidad",Cap.getText().toString());
        if(Ok.isChecked())
            OBJ.putExtra("Estado","OK");
        OBJ.putExtra("Barrio",Barr.getText().toString());

        startActivity(OBJ);

    }


    public void Volver (View vista){
        Intent OBJ = new Intent(this, MainActivity.class);
        startActivity(OBJ);
    }
}
