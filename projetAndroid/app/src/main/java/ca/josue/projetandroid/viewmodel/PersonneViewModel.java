package ca.josue.projetandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.projetandroid.model.Personne;
import ca.josue.projetandroid.repository.PersonneRepository;

public class PersonneViewModel extends AndroidViewModel {

    private final PersonneRepository repository;
    private final LiveData<List<Personne>> allPersonnes;

    public PersonneViewModel(@NonNull Application application) {
        super(application);

        repository = new PersonneRepository(application);
        allPersonnes = repository.getAllPersonnes();
    }

    /************** Les Methodes de communication ****************/
    public void insert(Personne personne){
        repository.insert(personne);
    }

    public void update(Personne personne){
        repository.update(personne);
    }

    public void delete(Personne personne){
        repository.delete(personne);
    }

    public void deleteAllPersonnes(){
        repository.deleteAllPersonnes();
    }

    public LiveData<List<Personne>> getAllPersonnes() {
        return allPersonnes;
    }
}
