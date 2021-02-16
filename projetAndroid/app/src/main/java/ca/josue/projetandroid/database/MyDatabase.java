package ca.josue.projetandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ca.josue.projetandroid.database.dao.PersonneDao;
import ca.josue.projetandroid.model.Personne;

@Database(entities = {Personne.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public static MyDatabase INSTANCE;
    public abstract PersonneDao personneDao();

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
                    .build();
        }
        return INSTANCE;
    }


}
