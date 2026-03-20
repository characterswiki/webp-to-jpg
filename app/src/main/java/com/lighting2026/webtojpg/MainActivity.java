package com.lighting2026.webtojpg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends Activity {

    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selectBtn = findViewById(R.id.selectBtn);
        Button convertBtn = findViewById(R.id.convertBtn);

        selectBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        convertBtn.setOnClickListener(v -> convertImage());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            Toast.makeText(this,"Image Selected",Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void convertImage() {

        try {

            InputStream inputStream = getContentResolver().openInputStream(imageUri);

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            String output = getExternalFilesDir(null)+"/converted.jpg";

            FileOutputStream out = new FileOutputStream(output);

            bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);

            out.flush();
            out.close();

            Toast.makeText(this,"Saved: "+output,Toast.LENGTH_LONG).show();

        } catch(Exception e){
            Toast.makeText(this,"Error: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}
