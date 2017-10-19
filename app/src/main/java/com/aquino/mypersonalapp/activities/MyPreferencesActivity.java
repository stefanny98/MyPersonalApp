package com.aquino.mypersonalapp.activities;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.aquino.mypersonalapp.R;
import com.aquino.mypersonalapp.repository.UserRepository;


public class MyPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();

    }

    public static class MyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            String value = sharedPreferences.getString(s, null);

            if("fullname".equals(s)){
                String username = sharedPreferences.getString("username", null);
                UserRepository.update(username, value);
                editor.putString("fullname", value).apply();

            }else if("fonts".equals(s)){

                editor.putString("fonts", value).apply();

            }else if("theme".equals(s)){

                    editor.putString("theme", value).apply();
            }
        }

        @Override
        public void onResume() {
            super.onResume();

            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            super.onPause();
        }

    }


}
