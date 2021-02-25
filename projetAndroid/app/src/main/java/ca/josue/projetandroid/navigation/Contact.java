package ca.josue.projetandroid.navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ca.josue.projetandroid.MainActivity;
import ca.josue.projetandroid.R;
import ca.josue.projetandroid.adapters.PersonneAdapter;
import ca.josue.projetandroid.model.Personne;
import ca.josue.projetandroid.viewmodel.PersonneViewModel;

public class Contact extends AppCompatActivity {
    private PersonneViewModel personneViewModel;
    private RecyclerView recycler_view_contact;
    public static final int ADD_PERSONNE_REQUEST = 1;
    private final String LOG_TAG = "contantModel";
    private PersonneAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle(R.string.contact);

        FloatingActionButton buttonAddPerson = findViewById(R.id.button_add_personne);
        buttonAddPerson.setOnClickListener(v -> {
            Intent intent = new Intent(this, Add.class);
            startActivityForResult(intent, ADD_PERSONNE_REQUEST);
            Log.i(LOG_TAG, "Floating button est executé");
        });

        recycler_view_contact = findViewById(R.id.recycler_view_contact);
        recycler_view_contact.setLayoutManager(new LinearLayoutManager(this));
        recycler_view_contact.setHasFixedSize(true);

        adapter = new PersonneAdapter();
        recycler_view_contact.setAdapter(adapter);

        personneViewModel = new ViewModelProvider(this).get(PersonneViewModel.class);
        personneViewModel.getAllPersonnes().observe(this, personnes -> {
            adapter.setListe_Personnes(personnes);
            Log.i(LOG_TAG, "adapter dans onChanged() des données (Personnes) - Okay");
        });
    }

    /**
     * Réaction face aux données reçu au travers un Intent
     *
     * @param requestCode correspond au code de reception définis dans cette Activity (== 1)
     * @param resultCode  correspond au code du result de l'Intent venu (Echec == 0  & Reussite == 1)
     * @param data        correspond à l'entity Intent reçu, pour extraction des données.
     *                    Return void
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PERSONNE_REQUEST && resultCode == RESULT_OK) {

            String fname = data.getStringExtra(Add.EXTRA_FNAME);
            String lname = data.getStringExtra(Add.EXTRA_LNAME);
            String profession = data.getStringExtra(Add.EXTRA_PROFESSION);

            // Données Correct ? On crée l'instance
            Personne person = new Personne(fname, lname, profession);
            personneViewModel.insert(person);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Echec", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Liaison de Menu.xml des options à l'Activité courante
     *
     * Return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_save, menu);
        return true;
    }


    /**
     * Fonction permettant de Lier le Menu "Home" lorsqu'on se trouve sur l'Activité <Contact>
     * Nous permet de revenir sur Le HomePage
     *
     * Return boolean
     * */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.optionHomeContact_) { // Bouton enregistrer
            Intent intent_ = new Intent(this, MainActivity.class);
            startActivity(intent_);
            Log.i(LOG_TAG, "bouton home - fonctionnel");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
