package de.pfann.budgetmanager.activities;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.pfann.budgetmanager.R;

public class PlanetFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater aInflater, ViewGroup aContainer,
                             Bundle aSavedInstanceState){
        return aInflater.inflate(R.layout.planet_fragment,aContainer,false);
    }
}
