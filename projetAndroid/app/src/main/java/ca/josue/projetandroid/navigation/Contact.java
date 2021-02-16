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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.josue.projetandroid.MainActivity;
import ca.josue.projetandroid.R;
import ca.josue.projetandroid.adapters.PersonneAdapter;
import ca.josue.projetandroid.model.Personne;
import ca.josue.projetandroid.viewmodel.PersonneViewModel;

public class Contact extends Fragment {
    private PersonneViewModel personneViewModel;
    private RecyclerView recycler_view_contact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.contact_layout, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler_view_contact = view.findViewById(R.id.recycler_view_contact);
        recycler_view_contact.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler_view_contact.setHasFixedSize(true);

        PersonneAdapter adapter = new PersonneAdapter();
        recycler_view_contact.setAdapter(adapter);

        personneViewModel = new ViewModelProvider(this).get(PersonneViewModel.class);
        personneViewModel.getAllPersonnes().observe(getActivity(), adapter::setListe_Personnes);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity)context).setTitle(R.string.contact);
    }
}
