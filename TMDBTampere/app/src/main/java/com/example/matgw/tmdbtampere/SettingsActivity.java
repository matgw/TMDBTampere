package com.example.matgw.tmdbtampere;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private static final String SETTINGS = "SETTINGS";
    private static final String LANG = "LANG";
    private static final String RAT = "RAT";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btn=(Button)findViewById(R.id.buttonAPropos);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "Appli réalisée par M. Tampère - 2017", Toast.LENGTH_SHORT).show();
            }
        });

        setTitle("Paramètres");

    }

    public void putLangFR(View v)
    {
        sharedPreferences = v.getContext().getSharedPreferences(SETTINGS, MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString(LANG, "fr-FR")
                .apply();
        Toast.makeText(this, "Paramètre de langue changé en : Français", Toast.LENGTH_SHORT).show();
    }

    public void putLangEN(View v)
    {
        sharedPreferences = v.getContext().getSharedPreferences(SETTINGS, MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString(LANG, "en-US")
                .apply();
        Toast.makeText(this, "Language set : English ", Toast.LENGTH_SHORT).show();
    }


}
