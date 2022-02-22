package com.jigarprajapati.stockmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class ProductAddUpdate extends AppCompatActivity {
    EditText Productname;
    EditText Productcolor;
    EditText Producrsize;
    EditText Productdescription;
    ImageButton productaddupdate;
    String pname, pcolor, psize, description;
    DatabaseReference databaseReference;
    ImageView imageView;
    TextView textView;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add_update);
        Productname = findViewById(R.id.ETproductnameinadd);
        Productcolor = findViewById(R.id.ETproductcolor);
        Producrsize = findViewById(R.id.ETproductsize);
        Productdescription = findViewById(R.id.ETproductdescription);
        productaddupdate = findViewById(R.id.IBproductaddupdate);
        textView=findViewById(R.id.ProductAddUpdatebackArrow);
        imageView = findViewById(R.id.imageview);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ProductAddUpdate.this,Home.class);
                startActivity(intent);
            }
        });


        // Data Add In Firebase
        productaddupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pname = Productname.getText().toString();
                pcolor = Productcolor.getText().toString();
                psize = Producrsize.getText().toString();
                description = Productdescription.getText().toString();


                boolean Check = ValidateInfo(pname, pcolor, psize, description);

                if (Check == true) {

                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();


                    uploadvideo();
                    Toast.makeText(getApplicationContext(), "Data Insert Successfully", Toast.LENGTH_SHORT).show();

                } else {


                }


            }
        });


        // Choice Photo
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(ProductAddUpdate.this);
                choose_video();


            }
        });


    }

    private void choose_video() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 5);
    }

    Uri videouri;


    private Boolean ValidateInfo(String pname, String pcolor, String psize, String description) {
        if (pname.length() == 0) {
            Productname.requestFocus();
            Productname.setError("Product Name is Required");
            return false;
        } else if (pcolor.length() == 0) {
            Productcolor.requestFocus();
            Productcolor.setError(" Product Color is Required");
            return false;
        } else if (psize.length() == 0) {
            Producrsize.requestFocus();
            Producrsize.setError("Product Size is Required");
            return false;
        } else if (description.length() == 0) {
            Productdescription.requestFocus();
            Productdescription.setError("Product Description is Required");
            return false;
        } else {
            return true;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            videouri = data.getData();

            imageView.setImageURI(videouri);

        }
    }

    private String getfiletype(Uri videouri) {
        ContentResolver r = getContentResolver();
        // get the file type ,in this case its mp4
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(r.getType(videouri));
    }

    private void uploadvideo() {
        if (videouri != null) {
            final StorageReference reference = FirebaseStorage.getInstance().getReference("Files/" + System.currentTimeMillis() + "." + getfiletype(videouri));
            reference.putFile(videouri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful()) ;
                    // get the link of video
                    String downloadUri = uriTask.getResult().toString();
                    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("User");
                    HashMap<String, String> map = new HashMap<>();
                    map.put("Photo", downloadUri);
                    map.put("ProductName", pname);
                    map.put("ProductColor", pcolor);
                    map.put("ProductSize", psize);
                    map.put("ProductDescription", description);
                    reference1.child("" + System.currentTimeMillis()).setValue(map);
                    // Video uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss();
                    Toast.makeText(ProductAddUpdate.this, "Data Upload Successfully !!!", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Error, Image not uploaded
                    progressDialog.dismiss();
                    Toast.makeText(ProductAddUpdate.this, "Failed " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                // Progress Listener for loading
                // percentage on the dialog box
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    // show the progress bar
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded " + (int) progress + "%");

                }
            });
        }
    }

}