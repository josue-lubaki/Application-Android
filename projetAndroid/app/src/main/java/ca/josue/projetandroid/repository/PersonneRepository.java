package ca.josue.projetandroid.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.projetandroid.database.MyDatabase;
import ca.josue.projetandroid.database.dao.PersonneDao;
import ca.josue.projetandroid.model.Personne;

public class PersonneRepository {
    private final PersonneDao personneDao;
    private final LiveData<List<Personne>> allPersonnes;

    public PersonneRepository(Application application) {
        // relier la Base de donnees
        MyDatabase database = MyDatabase.getInstance(application);

        // relier le DAO
        personneDao = database.personneDao();
        allPersonnes = personneDao.getAllPersonnes();
    }

    /********* Executer ces differentes methodes en Arrière-plan ************/
    public  void insert(Personne personne){
        MyDatabase.databaseWriteExecutor.execute(()-> personneDao.insert(personne));
    }

    public  void update(Personne personne){
        MyDatabase.databaseWriteExecutor.execute(()-> personneDao.update(personne));
    }

    public  void delete(Personne personne){
        MyDatabase.databaseWriteExecutor.execute(()-> personneDao.delete(personne));
    }

    public  void deleteAllPersonnes(){
        MyDatabase.databaseWriteExecutor.execute(personneDao::deleteAllPersonnes);
    }

    public LiveData<List<Personne>> getAllPersonnes() {
        return allPersonnes;
    }

}
