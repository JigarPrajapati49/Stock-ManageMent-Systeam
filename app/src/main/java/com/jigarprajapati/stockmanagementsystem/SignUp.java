package com.jigarprajapati.stockmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText usernamesign, emailid, emailpassword, conformpassword;
    Button registrationbtn;
    AwesomeValidation awesomeValidation;
    private FirebaseAuth auth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();

        usernamesign = findViewById(R.id.usernamesign);
        emailpassword = findViewById(R.id.emailpassword);
        conformpassword = findViewById(R.id.conformpassword);
        emailid = findViewById(R.id.emailid);
        registrationbtn = findViewById(R.id.registrationbtn);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        String regexPassword = ".{8}";
        awesomeValidation.addValidation(this, R.id.usernamesign, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
//        awesomeValidation.addValidation(this, R.id.emailpassword, ".{6}", R.string.invalid_password);
        awesomeValidation.addValidation(this, R.id.emailid, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
//        awesomeValidation.addValidation(this, R.id.conformpassword, ".{6}", R.string.invalid_conform_password);

        awesomeValidation.addValidation(SignUp.this, R.id.emailpassword, regexPassword, R.string.invalid_password);
// to validate a confirmation field (don't validate any rule other than confirmation on confirmation field)
        awesomeValidation.addValidation(SignUp.this, R.id.conformpassword, R.id.emailpassword, R.string.invalid_conform_password);


        registrationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {

                    String email = emailid.getText().toString().trim();
                    String password = emailpassword.getText().toString().trim();
                    String cpassword = conformpassword.getText().toString().trim();
                    String username = usernamesign.getText().toString().trim();


                    reference = FirebaseDatabase.getInstance().getReference("Login");
                    String key = reference.push().getKey();

                    Map map = new HashMap();
                    map.put("Emailid", email);
                    map.put("password", password);
                    map.put("Conformpassword", cpassword);
                    map.put("Username", username);



                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    Toast.makeText(SignUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();


                                    } else {
                                        Toast.makeText(getApplicationContext(), "Signup successfully...", Toast.LENGTH_SHORT).show();

                                        //startActivity(new Intent(SignUp.this, MainActivity.class));
                                        //finish();
                                    }

                                }
                            });
                    reference.child(key).setValue(map);

                    Toast.makeText(getApplicationContext(), "Form validate successfully...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Home.class);
                    startActivity(intent);
                    finish();
                } else {
                   // Toast.makeText(getApplicationContext(), "validation failed", Toast.LENGTH_SHORT).show();

                }


            }


        });
    }

}