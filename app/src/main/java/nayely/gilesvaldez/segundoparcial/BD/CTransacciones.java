package nayely.gilesvaldez.segundoparcial.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CTransacciones {

    ArrayAdapter<String> Adaptador ;
    public void CargarLamparasTran(Context c, Spinner Cli){
        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Arg = new String[]{"OK"};
        Cursor cursor1 = Obj2.rawQuery("Select  b.Nombre,c.CodLam from Lamparas as c,Zonas as b where b.CodZon = c.CodZon and c.Estado =?",Arg);
        int Nro = cursor1.getCount();
        String[] datos = new String[Nro];
        int VisDat = -1;
        if(cursor1.moveToFirst()){
            do{
                VisDat++;
                datos[VisDat] = cursor1.getString(0) +"-"+cursor1.getString(1) ;

            }while(cursor1.moveToNext());
        }
        Adaptador =new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1,datos);
        Cli.setAdapter(Adaptador);
        Obj2.close();
    }

    public void CargarClientesTrans(Context c, Spinner Cli){
        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        Cursor cursor1 = Obj2.rawQuery("Select Nombre from Vecinos  ",null);
        int Nro = cursor1.getCount();
        String[] datos = new String[Nro];
        int VisDat = -1;
        if(cursor1.moveToFirst()){
            do{
                VisDat++;
                datos[VisDat] = cursor1.getString(0);
            }while(cursor1.moveToNext());
        }
        Adaptador =new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1,datos);
        Cli.setAdapter(Adaptador);
        Obj2.close();
    }

    public void CargarZonasTrans(Context c, Spinner Zon){
        BD obj1 = new BD(c,"BDAlumbrado.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        Cursor cursor1 = Obj2.rawQuery("Select Nombre from Zonas ",null);
        int Nro = cursor1.getCount();
        String[] datos = new String[Nro];
        int VisDat = -1;
        if(cursor1.moveToFirst()){
            do{
                VisDat++;
                datos[VisDat] = cursor1.getString(0);
            }while(cursor1.moveToNext());
        }
        Adaptador =new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1,datos);
        Zon.setAdapter(Adaptador);
        Obj2.close();
    }


    /*

    public void CargarCajas(Context c, Spinner Cli){
        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Arg = new String[]{"OK"};
        Cursor cursor1 = Obj2.rawQuery("Select  b.Nombre,c.CodLam from Lamparas as c,Zonas as b where b.CodZon = c.CodZon and c.Estado =?",Arg);
        int Nro = cursor1.getCount();
        String[] datos = new String[Nro];
        int VisDat = -1;
        if(cursor1.moveToFirst()){
            do{
                VisDat++;
                datos[VisDat] = cursor1.getString(0) +"-"+cursor1.getString(2)+"-"+cursor1.getString(1) ;

            }while(cursor1.moveToNext());
        }
        Adaptador =new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1,datos);
        Cli.setAdapter(Adaptador);
        Obj2.close();
    }

    public void CargarVecinos(Context c, Spinner Vec){
        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        Cursor cursor1 = Obj2.rawQuery("Select Nombre from Vecinos  ",null);
        int Nro = cursor1.getCount();
        String[] datos = new String[Nro];
        int VisDat = -1;
        if(cursor1.moveToFirst()){
            do{
                VisDat++;
                datos[VisDat] = cursor1.getString(0);
            }while(cursor1.moveToNext());
        }
        Adaptador =new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1,datos);
        Vec.setAdapter(Adaptador);
        Obj2.close();
    }

    public String  traerDenunciaVecinos(Context c , String Selec){

        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{Selec};
        Cursor cursor1 = Obj2.rawQuery("Select Denuncia from Vecinos ",null);
        cursor1.moveToFirst();
        String Denuncia ;
        Denuncia =Integer.toString(cursor1.getInt(0));
        Obj2.close();
        return Denuncia;
    }

    public String ObtenerIdLam(Context c, String Selec){
        int max =Selec.length();
        int cont = 0;
        int pos = 0;
        for(int i = 0; i<max; i++){
            // Log.d(Integer.toString(i), "TraerSaldoCajas: "+Selec.charAt(i));
            if ((Selec.charAt(i))=='-'){
                cont++;
                //Log.d("Cont", "TraerSaldoCajas: "+cont);
            }else
            if(cont == 2 && Selec.charAt(i-1) =='-'){
                pos = i;
            }
        }
        Selec = Selec.substring(pos,max);
        return Selec;
    }

    public String  TraerProblemas(Context c , String Selec){

       Selec = ObtenerIdLam(c,Selec);

        //Log.d("Selec", "TraerSaldoCajas: "+Selec);

        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{Selec};
        Cursor cursor1 = Obj2.rawQuery("Select Tipo from Denncias where CodLam = ?", Argumento,null);
        cursor1.moveToFirst();
        String Saldo ;
        Saldo ="Bs: "+Integer.toString(cursor1.getInt(0))+" SUS: "+ Integer.toString(cursor1.getInt(1));
        Obj2.close();
        return Saldo;
    }


    public double saldoCajaDolar(Context c, String id){

        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{id};
        Cursor cursor1 = Obj2.rawQuery("Select Tipo from Denuncias where CodLam = ?", Argumento,null);
        cursor1.moveToFirst();
        double Saldo ;
        Saldo = cursor1.getInt(0);
        Obj2.close();
        return Saldo;
    }

    public double saldoCajaBolivianos(Context c, String id){

        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{id};
        Cursor cursor1 = Obj2.rawQuery("Select Tipo from Denuncia where CodLam = ?", Argumento,null);
        cursor1.moveToFirst();
        double Saldo ;
        Saldo = cursor1.getInt(0);
        Obj2.close();
        return Saldo;
    }

    public String idCliente(Context c, String name) {

        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{name};
        Cursor cursor1 = Obj2.rawQuery("Select CodCli from Clientes where Nombre = ?", Argumento,null);
        cursor1.moveToFirst();
        String id ;
        id = Integer.toString(cursor1.getInt(0));
        Obj2.close();
        return id;
    }

    public void guardarTransaccion(Context c, String Fecha, int tipomoneda, String idCli, String IdCaja, String Monto) {
        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getWritableDatabase();
        ContentValues Reg = new ContentValues();
        Reg.put("Fecha",Fecha);
        Reg.put("CodVec",idCli);
        Reg.put("CodLam",IdCaja);
        Reg.put("TipoProblema",tipomoneda);
        Reg.put("MontoEstad",Monto);
        Obj2.insert("Transacciones",null,Reg);
        Obj2.close();
    }

    public void UpdateCajaDolar(Context c, String idCaja, int Monto) {
        double saldoactual = saldoCajaDolar(c, idCaja);
        BD Obj1 = new BD(c, "BDCAJEROS_BANCOS.db", null, 1);
        SQLiteDatabase Obj2 = Obj1.getReadableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("MontoSus", saldoactual-Monto);
        Obj2.update("Cajeros", reg, "CodCaj =" + idCaja, null);
        Obj2.close();
    }

    public void UpdateClienteSaldo(Context c, String idCli, int i) {
        double saldoactual = traerSaldoClientesid(c, idCli);
        BD Obj1 = new BD(c, "BDCAJEROS_BANCOS.db", null, 1);
        SQLiteDatabase Obj2 = Obj1.getReadableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("Saldo", saldoactual-i);
        Obj2.update("Clientes", reg, "CodCli =" + idCli, null);
        Obj2.close();
    }

    public int  traerSaldoClientesid(Context c , String id){

        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{id};
        Cursor cursor1 = Obj2.rawQuery("Select Saldo from Clientes where CodCli = ?", Argumento,null);
        cursor1.moveToFirst();
        int Saldo ;
        Saldo =cursor1.getInt(0);
        Obj2.close();
        return Saldo;
    }

    public void UpdateCajaBs(Context c, String idCaja, int Monto) {
        double saldoactual = saldoCajaBolivianos(c, idCaja);
        BD Obj1 = new BD(c, "BDCAJEROS_BANCOS.db", null, 1);
        SQLiteDatabase Obj2 = Obj1.getReadableDatabase();
        ContentValues reg = new ContentValues();
        reg.put("MontoBs", saldoactual-Monto);
        Obj2.update("Cajeros", reg, "CodCaj =" + idCaja, null);
        Obj2.close();
    }


    public void MostrarTransacciones(Context c, GridView Gv){
        Gv.setNumColumns(6);

        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        Cursor cursor1 = Obj2.rawQuery("Select * from Transacciones",null);
        int Nro = cursor1.getCount();
        String[] datos = new String[(Nro+1)*6];
        int VisDat = -1;
        datos[0] = "CodTra";
        datos[1] = "Fecha";
        datos[2] = "Moneda";
        datos[3] = "CodCli";
        datos[4] = "CodCaj";
        datos[5] = "MontoTra";
        VisDat = 5;

        if(cursor1.moveToFirst()){
            do{
                VisDat++;
                datos[VisDat] = cursor1.getString(0);
                VisDat++;
                datos[VisDat] = cursor1.getString(1);
                VisDat++;
                if(cursor1.getString(2).equals("0")){
                    datos[VisDat] = "Bolivianos";
                }else
                {
                    datos[VisDat] = "Dolar";
                }

                VisDat++;
                datos[VisDat] = cursor1.getString(3);
                VisDat++;
                datos[VisDat] = cursor1.getString(4);
                VisDat++;
                datos[VisDat] = cursor1.getString(5);

            }while(cursor1.moveToNext());
        }
        Adaptador =new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1,datos);
        Gv.setAdapter(Adaptador);
        Obj2.close();
    }

    public void CargarClientesTransacciones(Context c, Spinner clientes) {

        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();

        Cursor cursor1 = Obj2.rawQuery("Select DISTINCT c.Nombre"+
                " from Clientes as c,Transacciones as t where "+
                "c.CodCli = t.CodCli ",null);
        int Nro = cursor1.getCount();


        String[] datos = new String[Nro];
        int VisDat = -1;
        if(cursor1.moveToFirst()){
            do{
                VisDat++;
                datos[VisDat] = cursor1.getString(0) ;

            }while(cursor1.moveToNext());
        }
        Adaptador =new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1,datos);
        clientes.setAdapter(Adaptador);
        Obj2.close();

    }


    public int  CargarTodosTransaccionesss(Context c, LatLng[] Vcoors, String[] Vdatos,String[] Ban, String name) {
        String idcliente = idCliente(c,name);
        BD obj1 = new BD(c, "BDCAJEROS_BANCOS.db", null, 1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{idcliente};
        Cursor cursor1 = Obj2.rawQuery("Select t.CodTra,t.Fecha,t.TipoMoneda,t.MontoTra,b.Nombre,c.CodCaj,c.Zona,c.lat,c.lon" +
                "" +
                " from Bancos as b,Cajeros as c,Transacciones as t Where c.CodBan = b.CodBan and c.CodCaj = t.CodCaj and t.CodCli =?",Argumento);
        int Nro = cursor1.getCount();


        int VisDat = -1;

        if (cursor1.moveToFirst()) {
            do {
                VisDat++;
                Ban[VisDat] = cursor1.getString(4);
                Vcoors[VisDat] = new LatLng(cursor1.getDouble(7), cursor1.getDouble(8));
                Vdatos[VisDat] = cursor1.getString(4) + " / " + cursor1.getString(5) + " / " + cursor1.getString(6) ;
            } while (cursor1.moveToNext());
        }
        Obj2.close();
        return Nro;
    }

    public void  CargarTodosTransaccionesGV(Context c,  String name,GridView gv) {
        gv.setNumColumns(9);
        String idcliente = idCliente(c,  name);
        BD obj1 = new BD(c, "BDCAJEROS_BANCOS.db", null, 1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{idcliente};
        Cursor cursor1 = Obj2.rawQuery("Select t.CodTra,t.Fecha,t.TipoMoneda,t.MontoTra,b.Nombre,c.CodCaj,c.Zona,c.lat,c.lon" +
                "" +
                " from Bancos as b,Cajeros as c,Transacciones as t Where c.CodBan = b.CodBan and c.CodCaj = t.CodCaj and t.CodCli =?",Argumento);
        int Nro = cursor1.getCount();
        String[] datos = new String[(Nro+1)*9];
        int VisDat2 = -1;
        datos[0] = "CodTra";
        datos[1] = "Fecha";
        datos[2] = "Moneda";
        datos[3] = "MontoTra";
        datos[4] = "Banco";
        datos[5] = "CodCaj";
        datos[6] = "Zona";
        datos[7] = "lon";
        datos[8] = "Lat";

        VisDat2 = 8;
        if(cursor1.moveToFirst()){
            do{
                VisDat2++;
                datos[VisDat2] = cursor1.getString(0);
                VisDat2++;
                datos[VisDat2] = cursor1.getString(1);
                VisDat2++;
                datos[VisDat2] = cursor1.getString(2);
                VisDat2++;
                datos[VisDat2] = cursor1.getString(3);
                VisDat2++;
                datos[VisDat2] = cursor1.getString(4);
                VisDat2++;
                datos[VisDat2] = cursor1.getString(5);
                VisDat2++;
                datos[VisDat2] = cursor1.getString(6);
                VisDat2++;
                datos[VisDat2] = cursor1.getString(7);
                VisDat2++;
                datos[VisDat2] = cursor1.getString(8);

            }while(cursor1.moveToNext());
        }
        Adaptador =new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1,datos);
        gv.setAdapter(Adaptador);

        Obj2.close();

    }*/
}