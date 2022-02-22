package com.jigarprajapati.stockmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.jigarprajapati.stockmanagementsystem.Utility.NetworkChangeListner;

public class Login extends AppCompatActivity {
    EditText etusername, etpassword;
    TextView login, forgotp, signup;

    Button LoginButton, logingogle;
    //    ImageView logingoogle;
    FirebaseAuth fAuth;
    FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    NetworkChangeListner networkChangeListner = new NetworkChangeListner();


    AwesomeValidation awesomeValidation;

    private static void onClick(View v) {
//        final GoogleSignIn googleSignIn = new GoogleSignIn();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        signup = findViewById(R.id.tvsignup);
        etusername = findViewById(R.id.et_username);
        etpassword = findViewById(R.id.et_password);
        forgotp = findViewById(R.id.forgotp);

        fAuth = FirebaseAuth.getInstance();
        LoginButton = findViewById(R.id.loginbutton);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        String regexPassword = ".{8,}";
        awesomeValidation.addValidation(this, R.id.et_username, Patterns.EMAIL_ADDRESS, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.et_password, ".{8}", R.string.invalid_password);
        awesomeValidation.addValidation(this, R.id.emailid, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomeValidation.addValidation(Login.this, R.id.etpassword, regexPassword, R.string.invalid_password);
// to validate a confirmation field (don't validate any rule other than confirmation on confirmation field)


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {

                    String email = etusername.getText().toString().trim();
                    String password = etpassword.getText().toString().trim();


                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            Toast.makeText(Login.this, "signinwithemailpassword" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            if (task.isSuccessful()) {

                                Intent intent = new Intent(Login.this, Home.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(Login.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(MainActivity.this, HomePage.class);
//                                startActivity(intent);
                                Toast.makeText(Login.this, "provide correct email", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                } else {
                    Toast.makeText(getApplicationContext(), "validation failed", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(MainActivity.this, HomePage.class));

                }

            }
        });


//        LoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (awesomeValidation.validate()) {
//
//                    String email = etusername.getText().toString().trim();
//                    String password = etpassword.getText().toString().trim();
//
//
//                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//
//                            Toast.makeText(Login.this, "signinwithemailpassword" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
//                            if (task.isSuccessful()) {
//
//                                Intent intent = new Intent(Login.this, Home.class);
//                                startActivity(intent);
//
//                            } else {
//                                Toast.makeText(Login.this, "Authentication failed." + task.getException(),
//                                        Toast.LENGTH_SHORT).show();
////                                Intent intent = new Intent(MainActivity.this, HomePage.class);
////                                startActivity(intent);
//                                Toast.makeText(Login.this, "provide correct email", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    });
//
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "validation failed", Toast.LENGTH_SHORT).show();
//                    // startActivity(new Intent(MainActivity.this, HomePage.class));
//
//                }
//
//            }
//        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);

            }
        });

        forgotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
//                startActivity(intent);
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "Error ! Reset Link is Not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                passwordResetDialog.create().show();

            }
//
        });


    }

    private void firebaseAuthWithGoogle(String idToken) {
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListner, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListner);
        super.onStop();
    }


    public void callForgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
    }
}
