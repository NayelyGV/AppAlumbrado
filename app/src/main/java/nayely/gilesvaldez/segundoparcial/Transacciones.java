package nayely.gilesvaldez.segundoparcial;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import nayely.gilesvaldez.segundoparcial.BD.CTransacciones;

public class Transacciones extends AppCompatActivity {

    EditText FECHA;
    TextView Den,Tipo;
    Spinner Vec,Lam;
    RadioGroup Prob;
    RadioButton Op1T,Op2T,Op3T;
    GridView GV;
    View vista;
    CTransacciones OBJ = new CTransacciones();

    Calendar c;
    DatePickerDialog D;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacciones);
        FECHA = findViewById(R.id.fecha);
        Den = findViewById(R.id.CajaMonto);
        Tipo = findViewById(R.id.personaMonto);
        Vec= findViewById(R.id.Vecinos);
        Lam = findViewById(R.id.Lamparas);
        Prob=findViewById(R.id.Problemas);
        Op1T = findViewById(R.id.Op1Tipo);
        Op2T = findViewById(R.id.Op2Tipo);
        Op3T = findViewById(R.id.Op3Tipo);
        GV = findViewById(R.id.gv2);

        OBJ.CargarClientesTrans(this,Vec);
        OBJ.CargarLamparasTran(this,Lam);
        //MostrarTransacciones(vista);

        FECHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                D = new DatePickerDialog(Transacciones.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        FECHA.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },day,month,year);
                D.show();
            }
        });

        Vec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String Denuncia =  OBJ.traerDenunciaVecinos(Transacciones.this, Vec.getSelectedItem().toString());
               // Den.setText("Denuncias: "+Denuncia);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Prob.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(Op1T.isChecked())
                {
                    //RadioButton rb=(RadioButton)findViewById(checkedId);
                    Tipo.setText("Apagado ");
                }else if(Op2T.isChecked())
                {
                    //RadioButton rb=(RadioButton)findViewById(checkedId);
                    Tipo.setText("Siempre Encendido ");
                }else if(Op3T.isChecked())
                {
                    //RadioButton rb=(RadioButton)findViewById(checkedId);
                    Tipo.setText("Alumbra Bajo ");
                }
                // checkedId is the RadioButton selected

                //Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        /*
        Lam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String Saldo =  OBJ.(Transacciones.this, CAJAS.getSelectedItem().toString());
                //Lam.setText(Saldo);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_transaccion,menu);
        return true;
    }

    public boolean onOptionsItemSelected( MenuItem item){
        int idItemSeleccionados = item.getItemId();
        if(idItemSeleccionados == R.id.home){
            Home(vista);
        }else if(idItemSeleccionados == R.id.save){
            //(vista);
        }
        return super.onOptionsItemSelected(item);
    }
/*
    public void GuardarTransaccion(View vista){
        if (DOLAR.isChecked()){
            if((Integer.parseInt(String.valueOf(MONTO.getText()))) <= OBJ.saldoCajaDolar(Transacciones.this,OBJ.ObtenerIdCaja(Transacciones.this,CAJAS.getSelectedItem().toString()))){
                if((Integer.parseInt(String.valueOf(MONTO.getText()))*7) <= Integer.parseInt((OBJ.traerSaldoClientes(Transacciones.this,CLIENTE.getSelectedItem().toString())))){
                    //Log.d("END", "WIN: ");
                    String idCaja = OBJ.ObtenerIdCaja(Transacciones.this,CAJAS.getSelectedItem().toString());
                    String idCli = OBJ.idCliente(Transacciones.this,CLIENTE.getSelectedItem().toString());
                    OBJ.guardarTransaccion(Transacciones.this,FECHA.getText().toString(),1,idCli,idCaja,MONTO.getText().toString());
                    OBJ.UpdateCajaDolar(Transacciones.this,idCaja,(Integer.parseInt(String.valueOf(MONTO.getText()))));
                    OBJ.UpdateClienteSaldo(Transacciones.this,idCli,(Integer.parseInt(String.valueOf(MONTO.getText())))*7);
                    MostrarTransacciones(vista);

                    String SaldoCli =  OBJ.traerSaldoClientes(Transacciones.this, CLIENTE.getSelectedItem().toString());
                    String SaldoCaja =  OBJ.TraerSaldoCajas(Transacciones.this, CAJAS.getSelectedItem().toString());

                    MONTOCJA.setText(SaldoCaja);
                    MONTOCLI.setText("Bs: "+SaldoCli);
                    Toast.makeText(this, "Transaccion Realizada", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Saldos Actualizados", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "RECHAZADO CLIENTE SIN SALDO SUFICIENTE", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "RECHAZADO CAJERO SIN SALDO/SUS SUFICIENTE", Toast.LENGTH_SHORT).show();
            }

        }else if (BS.isChecked()){
            if((Integer.parseInt(String.valueOf(MONTO.getText()))) <= OBJ.saldoCajaBolivianos(Transacciones.this,OBJ.ObtenerIdCaja(Transacciones.this,CAJAS.getSelectedItem().toString()))){
                if((Integer.parseInt(String.valueOf(MONTO.getText()))) <= Integer.parseInt((OBJ.traerSaldoClientes(Transacciones.this,CLIENTE.getSelectedItem().toString())))){
                    // Log.d("END", "WIN: ");
                    String idCaja = OBJ.ObtenerIdCaja(Transacciones.this,CAJAS.getSelectedItem().toString());
                    String idCli = OBJ.idCliente(Transacciones.this,CLIENTE.getSelectedItem().toString());
                    OBJ.guardarTransaccion(Transacciones.this,FECHA.getText().toString(),0,idCli,idCaja,MONTO.getText().toString());
                    OBJ.UpdateCajaBs(Transacciones.this,idCaja,(Integer.parseInt(String.valueOf(MONTO.getText()))));
                    OBJ.UpdateClienteSaldo(Transacciones.this,idCli,(Integer.parseInt(String.valueOf(MONTO.getText()))));
                    MostrarTransacciones(vista);

                    String SaldoCli =  OBJ.traerSaldoClientes(Transacciones.this, CLIENTE.getSelectedItem().toString());
                    String SaldoCaja =  OBJ.TraerSaldoCajas(Transacciones.this, CAJAS.getSelectedItem().toString());
                    MONTOCJA.setText(SaldoCaja);
                    MONTOCLI.setText("Bs: "+SaldoCli);

                    Toast.makeText(this, "Transaccion Realizada", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Saldos Actualizados", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "RECHAZADO CLIENTE SIN SALDO SUFICIENTE", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "RECHAZADO CAJERO SIN SALDO/BS SUFICIENTE", Toast.LENGTH_SHORT).show();
            }


        }else
        {
            Toast.makeText(this, "SELECCIONE UN TIPO DE MONEDA", Toast.LENGTH_SHORT).show();
        }

    }



    public void MostrarTransacciones(View vista){
        OBJ.MostrarTransacciones(Transacciones.this,GV);
    }*/
    public void Home(View vista){
        Intent OBJ = new Intent(Transacciones.this, MainActivity.class);
        startActivity(OBJ);
    }
}