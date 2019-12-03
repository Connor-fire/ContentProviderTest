package com.example.contentprovidertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String newId;
    private String ENG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addWord = (Button) findViewById(R.id.addWord);
        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    EditText eng = (EditText) findViewById(R.id.eng);
                    EditText chn = (EditText) findViewById(R.id.chn);
                    //String ENG = eng.getText().toString();
                    ENG = eng.getText().toString();
                    String CHN = chn.getText().toString();
                    Uri uri = Uri.parse("content://com.example.mywordsbook.provider/word");
                    ContentValues values = new ContentValues();
                    values.put("ENG",ENG);
                    values.put("CHN",CHN);
                    Uri newUri = getContentResolver().insert(uri,values);
                    newId = newUri.getPathSegments().get(1);
                    Toast.makeText(MainActivity.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button queryWord = (Button) findViewById(R.id.queryWord);
        queryWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Uri uri = Uri.parse("content://com.example.mywordsbook.provider/word/"+ENG);
                    Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                    cursor.moveToFirst();
                    String ENG = cursor.getString(cursor.getColumnIndex("ENG"));
                    String CHN = cursor.getString(cursor.getColumnIndex("CHN"));
                    Toast.makeText(MainActivity.this,ENG+"  "+CHN,Toast.LENGTH_SHORT).show();
                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button updateWord = (Button) findViewById(R.id.updateWord);
        updateWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //EditText eng = (EditText) findViewById(R.id.eng);
                    EditText chn = (EditText) findViewById(R.id.chn);
                    //String ENG = eng.getText().toString();
                    String CHN = chn.getText().toString();
                    Uri uri = Uri.parse("content://com.example.mywordsbook.provider/word/"+ENG);
                    ContentValues values = new ContentValues();
                    values.put("ENG",ENG);
                    values.put("CHN",CHN);
                    getContentResolver().update(uri,values,null,null);
                    Toast.makeText(MainActivity.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"ERROR"+newId,Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button deleteWord = (Button) findViewById(R.id.deleteWord);
        deleteWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Uri uri = Uri.parse("content://com.example.mywordsbook.provider/word/"+ENG);
                    getContentResolver().delete(uri,null,null);
                    Toast.makeText(MainActivity.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
