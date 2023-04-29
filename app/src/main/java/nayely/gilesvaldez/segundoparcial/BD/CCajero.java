package nayely.gilesvaldez.segundoparcial.BD;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;

public class CCajero {
    ArrayAdapter<String> Adaptador ;

    public void CargarZona(Context c, Spinner Zon){
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
    public void GuardarCaja(Context c, int Zon, int Cap, Double la, Double lo, String Est,String Barr){
        BD obj1 = new BD(c,"BdAlumbrado.db",null,1);
        SQLiteDatabase Obj2 = obj1.getWritableDatabase();
        ContentValues Reg = new ContentValues();
        Reg.put("CodZon",Zon);
        Reg.put("Capacidad",Cap);
        Reg.put("Lat",la);
        Reg.put("Lon",lo);
        Reg.put("Estado",Est);
        Reg.put("Barrio",Barr);
        Obj2.insert("Lamparas",null,Reg);
        Obj2.close();
    }

    public int getCodZonForName(Context c, String Zon) {
        BD obj1 = new BD(c,"BDCAJEROS_BANCOS.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{Zon};
        Cursor cursor1 = Obj2.rawQuery("Select CodZon from Zonas where Nombre = ?", Argumento,null);
        cursor1.moveToFirst();
        int Cod = cursor1.getInt(0);
        Obj2.close();
        return Cod;
    }


    public void MostrarCajas(Context c, GridView Gv){
        Gv.setNumColumns(6);
        BD obj1 = new BD(c,"BDAlumbrado.db",null,1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        Cursor cursor1 = Obj2.rawQuery("Select c.CodLam, b.Nombre,c.Capacidad,c.lat,c.lon,c.Estado from Zonas as b,Lamparas as c Where c.CodZon = b.CodZon",null);
        int Nro = cursor1.getCount();
        String[] datos = new String[(Nro+1)*6];
        int VisDat = -1;
        datos[0] = "CODIGO";
        datos[1] = "ZONA";
        datos[2] = "CAPACIDAD KW";
        datos[3] = "LAT";
        datos[4] = "LON";
       datos[5] = "ESTADO";
        VisDat = 5;
        //VisDat = 5;
        if(cursor1.moveToFirst()){
            do{
                VisDat++;
                datos[VisDat] = cursor1.getString(0);
                VisDat++;
                datos[VisDat] = cursor1.getString(1);
                VisDat++;
                datos[VisDat] = cursor1.getString(2);
                VisDat++;
                datos[VisDat] = cursor1.getString(3);
                VisDat++;
                datos[VisDat] = cursor1.getString(4);
                VisDat++;
                datos[VisDat] = cursor1.getString(5);
            }while(cursor1.moveToNext());
        }
        Obj2.close();
        Adaptador =new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1,datos);
        Gv.setAdapter(Adaptador);

    }

    public int  CargarTodos(Context c, LatLng[] Vcoors, String[] Vdatos,String[] Zon) {
        BD obj1 = new BD(c, "BDCAlumbrado.db", null, 1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        Cursor cursor1 = Obj2.rawQuery("Select c.CodLam, b.Nombre,c.Capacidad,c.lat,c.lon, c.Estado from Zonas as b,Lamparas as c Where c.CodZon = b.CodZon", null);
        int Nro = cursor1.getCount();
        //String[] datos = new String[(Nro + 1) * 7];
        int VisDat = -1;
        if (cursor1.moveToFirst()) {
            do {
                VisDat++;
                Zon[VisDat] = cursor1.getString(1);
                Vcoors[VisDat] = new LatLng(cursor1.getDouble(4), cursor1.getDouble(5));
                Vdatos[VisDat] = cursor1.getString(0) + " / " + cursor1.getString(2) + " / "+ cursor1.getString(3) + " / " ;
            } while (cursor1.moveToNext());
        }
        Obj2.close();
        return Nro;
    }


    public int  CargarTodosFiltroc2(Context c, LatLng[] Vcoors, String[] Vdatos,String[] Estado,String Zon) {
        BD obj1 = new BD(c, "BDCAJEROS_BANCOS.db", null, 1);
        SQLiteDatabase Obj2 = obj1.getReadableDatabase();
        String[] Argumento = new String[]{Zon};
        Cursor cursor1 = Obj2.rawQuery("Select c.CodLam, b.Nombre,c.Capacidad,c.lat,c.lon, c.Estado from Zonas as b,Lamparas as c Where c.CodZon = b.CodZon and b.Nombre = ? ",Argumento );
        int Nro = cursor1.getCount();

        int VisDat = -1;

        if (cursor1.moveToFirst()) {
            do {
                VisDat++;
                Estado[VisDat] = cursor1.getString(5);
                Vcoors[VisDat] = new LatLng(cursor1.getDouble(3), cursor1.getDouble(4));
                Vdatos[VisDat] = cursor1.getString(0) + " / " + cursor1.getString(1) + " / " + cursor1.getString(2) + " / " ;
            } while (cursor1.moveToNext());
        }

        Obj2.close();

        return Nro;
    }


}