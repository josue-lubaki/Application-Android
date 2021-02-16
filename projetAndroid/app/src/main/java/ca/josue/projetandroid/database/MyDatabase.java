package ca.josue.projetandroid.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.josue.projetandroid.database.dao.PersonneDao;
import ca.josue.projetandroid.model.Personne;

@Database(entities = {Personne.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public static MyDatabase INSTANCE;
    public abstract PersonneDao personneDao();

    /*
        Nous avons créé un ExecutorService avec un pool de threads fixe que vous utiliserez
        pour exécuter des opérations de base de données de manière asynchrone sur un thread d'arrière-plan.
        On fixe le nombrer des Threads pouvant éetre lancées en Arrière-plan
    */
    private static final int NUMBER_OF_THREAD = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREAD);


    /*
        Permet à Room de recréer de manière destructive des tables de base de données
        si les Migrations qui migreraient d'anciens schémas de base de données vers la dernière
        version de schéma sont introuvables.
    */
    public static synchronized MyDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class,
                    "gestion_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(()->{
                PersonneDao personneDao = INSTANCE.personneDao();

                personneDao.deleteAllPersonnes();
                personneDao.insert(new Personne("Prenom 1", "Nom 1", "Profession 1"));
                personneDao.insert(new Personne("Prenom 2", "Nom 2", "Profession 2"));
                personneDao.insert(new Personne("Prenom 3", "Nom 2", "Profession 3"));
            });
        }
    };



}
