package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            // Database adında bir obje oluştur (varsa aç yoksa oluştur) Adı:Musicians Mod: MODE_PRIVATE imleç:null
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            // Tablo oluşturmak için SQL komutları yazıyoruz. (Daha önce oluşturulmadıysa oluştur)
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INT)");

            /* Otomatik olarak ID eklemek için:
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY, name VARCHAR, age INT)");
            */

            // Tanımlamaları yapalım
            database.execSQL("INSERT INTO musicians (name, age) VALUES ('Alperen',23)");
            database.execSQL("INSERT INTO musicians (name, age) VALUES ('Mehmet',14)");

            /* Güncelleme işlemleri için
            database.execSQL("UPDATE musicians SET age = 27 WHERE name = 'Alperen'");
            database.execSQL("UPDATE musicians SET name = 'Ali' WHERE id = 2");
             */

            /* Silme işlemi için
            database.execSQL("DELETE FROM musicians WHERE id = 2");
            */

            // Verileri okumak için
            Cursor cursor = database.rawQuery("SELECT * FROM musicians", null );

            /* Eğer filtreleme işlemi yapmak istiyorsak (id'si 2 olanları getir, yaşı 20 den büyük olanları getir, adı Alperen olanları getir)
            Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE id = 2", null );
            Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE age > 20", null );
            Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name = 'Alperen'", null );
            Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%n'", null ); --> sonu n ile bitenler
            Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'A%'", null ); --> A ile başlayanlar
            */


            // Cursor'un hangi sütunlara gideceğini göstermek için
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");

            // Cursor ilerlediği sürece(değerleri okuduğu sürece)
            while (cursor.moveToNext()) {

                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("Id: " + cursor.getInt(idIx));

            }

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}