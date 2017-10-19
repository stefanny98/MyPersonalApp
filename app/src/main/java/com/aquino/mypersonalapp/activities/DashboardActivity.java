package com.aquino.mypersonalapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.aquino.mypersonalapp.R;
import com.vstechlab.easyfonts.EasyFonts;

import org.polaric.colorful.CActivity;
import org.polaric.colorful.Colorful;

public class DashboardActivity extends CActivity {

    private static final String TAG = DashboardActivity.class.getSimpleName();

    // SharedPreferences
    private SharedPreferences sharedPreferences;

    private TextView usernameText;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, android.R.string.ok, android.R.string.cancel);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_inicio:
                        Toast.makeText(DashboardActivity.this, "Inicio", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_datos:
                        Toast.makeText(DashboardActivity.this, "Datos Personales", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_configuracion:
                        Intent intent= new Intent (DashboardActivity.this, MyPreferencesActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_cerrarsesion:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        boolean success = editor.putBoolean("islogged", false).commit();
                        finish();
                        break;
                }

                // Close drawer
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        usernameText = (TextView)findViewById(R.id.fullname_text);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String username = sharedPreferences.getString("username", null);

        String fullname = sharedPreferences.getString("fullname", null);

        String theme = sharedPreferences.getString("theme", null);

        String fonts = sharedPreferences.getString("fonts", null);

        if(fonts.equals("freedom")){
            usernameText.setTypeface(EasyFonts.freedom(this));
        }else if (fonts.equals("tan")){
            usernameText.setTypeface(EasyFonts.tangerineBold(this));
        }

        if ("dark".equals(theme)) {

            Colorful.defaults()
                    .primaryColor(Colorful.ThemeColor.TEAL)
                    .accentColor(Colorful.ThemeColor.BLUE)
                    .translucent(false)
                    .dark(true);
            Colorful.init(this);
        }else if ("light".equals(theme)){
            Colorful.defaults()
                    .primaryColor(Colorful.ThemeColor.LIGHT_BLUE)
                    .accentColor(Colorful.ThemeColor.LIME)
                    .translucent(false)
                    .dark(false);
            Colorful.init(this);
        }else{
            Colorful.defaults()
                    .primaryColor(Colorful.ThemeColor.ORANGE)
                    .accentColor(Colorful.ThemeColor.LIME)
                    .translucent(true)
                    .dark(false);
            Colorful.init(this);
        }

        usernameText.setText(username);

        TextView fullnameText = (TextView) navigationView.getHeaderView(0).findViewById(R.id.menu_fullname);
        fullnameText.setText(fullname);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // Option open drawer
                if(!drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.openDrawer(GravityCompat.START);   // Open drawer
                else
                    drawerLayout.closeDrawer(GravityCompat.START);    // Close drawer
                return true;
        }
        return super.onOptionsItemSelected(item);


}

}
