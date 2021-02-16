package ca.josue.projetandroid.navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import ca.josue.projetandroid.MainActivity;
import ca.josue.projetandroid.R;
import ca.josue.projetandroid.model.Personne;
import ca.josue.projetandroid.viewmodel.PersonneViewModel;

public class Contact extends Fragment {
    private PersonneViewModel personneViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_layout, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        personneViewModel = new ViewModelProvider(this).get(PersonneViewModel.class);
        personneViewModel.getAllPersonnes().observe(getActivity(), personnes ->
                Toast.makeText(getActivity(), "onChanged", Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity)context).setTitle(R.string.contact);
    }
}
