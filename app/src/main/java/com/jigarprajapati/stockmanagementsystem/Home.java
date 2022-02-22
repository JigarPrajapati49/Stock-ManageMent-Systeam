package com.jigarprajapati.stockmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jigarprajapati.stockmanagementsystem.Fragment.AboutApp;
import com.jigarprajapati.stockmanagementsystem.Fragment.RateApp;
import com.jigarprajapati.stockmanagementsystem.Fragment.ShareApp;
import com.jigarprajapati.stockmanagementsystem.Fragment.UserGuidelines;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {

    CircleImageView profileImageView;

    DatabaseReference databaseReference;

    Button button;
    Toolbar toolbar;
    FrameLayout frameLayout;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Fragment fragment = null;

    FirebaseAuth mAuth;
    LinearLayout PurchaseEntry;
    LinearLayout SalesEntry;
    LinearLayout ProductList;
    LinearLayout VendorDetail;
    LinearLayout CustomerDetail;
    LinearLayout PurchaseOrder;
    LinearLayout SalesOrder;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        PurchaseEntry=findViewById(R.id.purchaseentry);
        SalesEntry=findViewById(R.id.salesentry);
        ProductList=findViewById(R.id.productlist);
        VendorDetail=findViewById(R.id.vendordetails);
        CustomerDetail=findViewById(R.id.customerdetails);
        PurchaseOrder=findViewById(R.id.purchaseorder);
        SalesOrder=findViewById(R.id.salesorder);
        button = findViewById(R.id.btneditprofile);
        profileImageView = findViewById(R.id.circulorimage);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Profile");
        frameLayout = findViewById(R.id.fragment);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        //  setSupportActionBar(toolbar);

        fragment = new com.jigarprajapati.stockmanagementsystem.Fragment.Home();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
        initNavigationMenu();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile = new Intent(Home.this, EditProfile.class);
                startActivity(profile);
            }
        });


        getUserinfo();

        PurchaseEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,PurchaseEntry.class);
                startActivity(intent);
               // finish();
            }
        });

        SalesEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,SalesEntry.class);
                startActivity(intent);
               // finish();

            }
        });
        VendorDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Home.this,VendorDetails.class);
                startActivity(intent);
                //finish();
            }
        });

        ProductList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,ProductList.class);
                startActivity(intent);
                //finish();
            }
        });
        CustomerDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,CustomerDetail.class);
                startActivity(intent);
               // finish();
            }
        });
        PurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,PurchaseOrders.class);
                startActivity(intent);
                //finish();
            }
        });
        SalesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,SalesOrder.class);
                startActivity(intent);
                //finish();
            }
        });
    }
    private void getUserinfo() {
        databaseReference.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    if (dataSnapshot.hasChild("image")) {
                        String image = dataSnapshot.child("image").getValue().toString();
                        Picasso.get().load(image).into(profileImageView);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initNavigationMenu() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        final DrawerLayout drawerLayout = findViewById(R.id.drawable_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigationOn,R.string.NavigationOF) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.AboutApp:
                    fragment = new AboutApp();
                    toolbar.setTitle("About App");
                    navigationView.setCheckedItem(R.id.AboutApp);
                    break;
                case R.id.HOME:
                    toolbar.setTitle("Home");
                    fragment = new com.jigarprajapati.stockmanagementsystem.Fragment.Home();
                    navigationView.setCheckedItem(R.id.HOME);
                    break;
                case R.id.RateApp:
                    toolbar.setTitle("Rate App");
                    fragment = new RateApp();
                    navigationView.setCheckedItem(R.id.RateApp);
                    break;
                case R.id.ShareApp:
                    toolbar.setTitle("Share App");
                    fragment = new ShareApp();
                    navigationView.setCheckedItem(R.id.ShareApp);
                    break;
                case R.id.UserGuidlines:
                    toolbar.setTitle("User Guidlines");
                    fragment = new UserGuidelines();
                    navigationView.setCheckedItem(R.id.UserGuidlines);
                    break;
            }
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment, fragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            drawerLayout.closeDrawers();
            return true;
        });
    }
}