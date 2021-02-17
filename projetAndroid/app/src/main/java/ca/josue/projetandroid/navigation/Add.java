package ca.josue.projetandroid.navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import ca.josue.projetandroid.MainActivity;
import ca.josue.projetandroid.R;

public class Add extends AppCompatActivity {

    public static final String EXTRA_FNAME = "ca.josue.projetandroid.navigation.EXTRA_FNAME";
    public static final String EXTRA_LNAME = "ca.josue.projetandroid.navigation.EXTRA_LNAME";
    public static final String EXTRA_PROFESSION = "ca.josue.projetandroid.navigation.EXTRA_PROFESSION";
    private static final String LOG_TAG = "addModel" ;

    private EditText editTextFname;
    private EditText editTextLname;
    private EditText editTextProfession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        editTextFname = findViewById(R.id.edit_text_fname);
        editTextLname = findViewById(R.id.edit_text_lname);
        editTextProfession = findViewById(R.id.edit_text_profession);
        MaterialButton btn_enregistrer = findViewById(R.id.enregistrer);
        MaterialButton btn_annuler = findViewById(R.id.annuler);

        /* Evenement au Click */
        btn_enregistrer.setOnClickListener(e -> savePersonne());
        btn_annuler.setOnClickListener(this::cancelSave);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle(R.string.add);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_save, menu);
        return true;
    }

    /**
     * Fonction permettant de Lier le Menu "Home" lorsqu'on se trouve sur l'Activité <Add>
     * Nous permet de revenir sur Le HomePage
     *
     * Return boolean
     * */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.optionHomeContact_) { // Bouton Home
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Log.i(LOG_TAG, "bouton home - fonctionnel");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Fonction permettant de Canceler l'inscription et Revenir sur la Page de contact
     * Return void
     * */
    public void cancelSave(View view) {
        Intent _intent = new Intent(this, Contact.class);
        startActivity(_intent);
        Log.i(LOG_TAG, "bouton annuler - fonctionnel");
    }

    /**
     * Fonction permettant d'enregistrer un Contact en cliquant sur le button "Enregistrer"
     * Et Revenir sur la Page de contact.
     *
     * Return void
     * */
    private void savePersonne() {

        /* Lier avec les champs des données */
        String fname = editTextFname.getText().toString();
        String lname = editTextLname.getText().toString();
        String profession = editTextProfession.getText().toString();

        /* Vérifiacation des données sur les champs */
        if (TextUtils.isEmpty(fname) || TextUtils.isEmpty(lname) || TextUtils.isEmpty(profession)) {
            Toast.makeText(this, "Please insert a Text", Toast.LENGTH_SHORT).show();
            return;
        }

        /* Création de l'intent transporteur des données */
        Intent intent = new Intent();

        /* Insertion des données à l'Intent */
        intent.putExtra(EXTRA_FNAME, fname);
        intent.putExtra(EXTRA_LNAME, lname);
        intent.putExtra(EXTRA_PROFESSION, profession);

        setResult(RESULT_OK, intent);
        finish();

        Log.i(LOG_TAG, "Transfert des données effectuées avec succès.");
    }


}
