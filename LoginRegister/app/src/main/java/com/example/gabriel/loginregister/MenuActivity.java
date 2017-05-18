package com.example.gabriel.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MenuActivity extends AppCompatActivity





        implements NavigationView.OnNavigationItemSelectedListener {

     private static final String TAG = "MenuActivity";

     private SectionPageAdapter mSectionsPageAdapter;

     private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);




        //toolbar
        Log.d(TAG,"onCreate: Starting.");

       mSectionsPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
       mViewPager = (ViewPager) findViewById(R.id.container);
       setupViewPager(mViewPager);

       TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
       tabLayout.setupWithViewPager(mViewPager);







        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

     //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
     //fab.setOnClickListener(new View.OnClickListener() {
     //    @Override
     //    public void onClick(View view) {
     //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
     //                .setAction("Action", null).show();
     //    }
     //});

      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
              this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
      drawer.setDrawerListener(toggle);
      toggle.syncState();

      NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
      navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Aun no estan Listas" , Toast.LENGTH_LONG).show();
                break;
        }
        return true;


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cardio) {

            Intent b = new Intent(this, HeartRateMonitor.class);
            this.startActivity(b);


        } else if (id == R.id.nav_presion) {

            Intent b = new Intent(this, Presion.class);
            this.startActivity(b);

        } else if (id == R.id.nav_tratamiento) {

            Intent b = new Intent(this, ingresoTratamiento.class);
            this.startActivity(b);

        } else if (id == R.id.nav_dieta){

        } else if (id == R.id.nav_agenda){

        }else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Medicamento");
        adapter.addFragment(new Tab2Fragment(), "Mi dieta");
        adapter.addFragment(new Tab3Fragment(), "Agenda");
        viewPager.setAdapter(adapter);


    }





}
