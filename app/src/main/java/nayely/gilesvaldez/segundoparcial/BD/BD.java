package nayely.gilesvaldez.segundoparcial.BD;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BD  extends SQLiteOpenHelper {


    public BD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Lamparas (CodLam INTEGER primary key autoincrement,CodZon int, Capacidad int,Lat double, Lon double,Estado Text,Barrio Text)");
        db.execSQL("create table Zonas (CodZon int primary key , Nombre Text)");
        db.execSQL("create table Vecinos (CodVec int primary key , Nombre Text,Telefono int,Genero Text,Denuncias int)");
        db.execSQL("create table Denuncias (CodDen INTEGER primary key autoincrement , Fecha date,CodVec int ,CodLam int,Tipo Text)");

        ContentValues Regi = new ContentValues();
        Regi.put("CodZon",1);
        Regi.put("Nombre","OESTE");
        db.insert("Zonas",null,Regi);

        Regi.put("CodZon",2);
        Regi.put("Nombre","ESTE");
        db.insert("Zonas",null,Regi);

        Regi.put("CodZon",3);
        Regi.put("Nombre","NORTE");
        db.insert("Zonas",null,Regi);

        Regi.put("CodZon",4);
        Regi.put("Nombre","SUR");
        db.insert("Zonas",null,Regi);

        ContentValues Regi2 = new ContentValues();
        Regi2.put("CodVec",1);
        Regi2.put("Nombre","Juan");
        Regi2.put("Telefono",11111111);
        Regi2.put("Genero","Masculino");
        Regi2.put("Denuncias",0);
        db.insert("Vecinos",null,Regi2);

        Regi2.put("CodVec",2);
        Regi2.put("Nombre","Ana");
        Regi2.put("Telefono",22222222);
        Regi2.put("Genero","Femenino");
        Regi2.put("Denuncias",0);
        db.insert("Vecinos",null,Regi2);

        Regi2.put("CodVec",3);
        Regi2.put("Nombre","Belen");
        Regi2.put("Telefono",33333333);
        Regi2.put("Genero","Femenino");
        Regi2.put("Denuncias",0);
        db.insert("Vecinos",null,Regi2);

        Regi2.put("CodVec",4);
        Regi2.put("Nombre","Rosa");
        Regi2.put("Telefono",44444444);
        Regi2.put("Genero","Femenino");
        Regi2.put("Denuncias",0);
        db.insert("Vecinos",null,Regi2);

        Regi2.put("CodVec",5);
        Regi2.put("Nombre","Luz");
        Regi2.put("Telefono",55555555);
        Regi2.put("Genero","Femenino");
        Regi2.put("Denuncias",0);
        db.insert("Vecinos",null,Regi2);

        Regi2.put("CodVec",6);
        Regi2.put("Nombre","Rita");
        Regi2.put("Telefono",66666666);
        Regi2.put("Genero","Femenino");
        Regi2.put("Denuncias",0);
        db.insert("Vecinos",null,Regi2);

        Regi2.put("CodVec",7);
        Regi2.put("Nombre","Estefania");
        Regi2.put("Telefono",77777777);
        Regi2.put("Genero","Femenino");
        Regi2.put("Denuncias",0);
        db.insert("Vecinos",null,Regi2);

        Regi2.put("CodVec",8);
        Regi2.put("Nombre","Carlos");
        Regi2.put("Telefono",88888888);
        Regi2.put("Genero","Masculino");
        Regi2.put("Denuncias",0);
        db.insert("Vecinos",null,Regi2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
