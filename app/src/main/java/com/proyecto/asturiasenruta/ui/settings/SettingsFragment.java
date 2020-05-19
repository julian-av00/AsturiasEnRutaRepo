package com.proyecto.asturiasenruta.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceFragmentCompat;

import com.proyecto.asturiasenruta.R;
import com.proyecto.asturiasenruta.SettingsActivity;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SettingsViewModel toolsViewModel;


    public SettingsFragment(){
    //constructor
    }


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String key) {
        setPreferencesFromResource(R.xml.preferencias, key );
    }

}