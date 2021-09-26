package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {  int SELECT_PHOTO = 2;
    Uri uri;
    ImageView imageView;
    EditText editTextTextPersonName;
    EditText editTextTextPersonName2;
    EditText editTextTextPersonName3;
    //Spinner spinner;
    Button button;
//    DatabaseReference dbRef;
    addProduct addPro;

//    private  void clearControls() {
//        editTextTextPersonName.setText("");
//        editTextTextPersonName2.setText("");
//        editTextTextPersonName3.setText("");
//    }


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName3);
        button = findViewById(R.id.button);

        addPro = new addProduct();



//        dbRef = FirebaseDatabase.getInstance().getReference().child("addProduct");

        if (TextUtils.isEmpty(editTextTextPersonName.getText().toString()))
            Toast.makeText(getApplicationContext(),"Please Enter Product Name", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(editTextTextPersonName2.getText().toString()))
            Toast.makeText(getApplicationContext(),"Please Enter Product price", Toast.LENGTH_SHORT).show();
        else if (TextUtils.isEmpty(editTextTextPersonName3.getText().toString()))
            Toast.makeText(getApplicationContext(),"Please Enter Product Description", Toast.LENGTH_SHORT).show();
        else {

            addPro.setProductName(editTextTextPersonName.getText().toString().trim());
            addPro.setProductPrice(editTextTextPersonName2.getText().toString().trim());
            addPro.setProductDescription(editTextTextPersonName3.getText().toString().trim());
//            dbRef.push().setValue(addPro);
            Toast.makeText(getApplicationContext(),"Data Add Successfull",Toast.LENGTH_SHORT).show();
            clearControls();

        }





        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Button Choose = findViewById(R.id.choose);
        imageView = findViewById(R.id.image);

        Choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK && data != null && data.getData() !=null){
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private  void clearControls() {
        editTextTextPersonName.setText("");
        editTextTextPersonName2.setText("");
        editTextTextPersonName3.setText("");
}