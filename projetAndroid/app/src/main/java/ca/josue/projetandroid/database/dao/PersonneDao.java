package ca.josue.projetandroid.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ca.josue.projetandroid.model.Personne;

@Dao
public interface PersonneDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Personne personne);

    @Update
    void update(Personne personne);

    @Delete
    void delete(Personne personne);

    @Query("DELETE FROM personne_table")
    void deleteAllPersonnes();

    @Query("SELECT * FROM personne_table ORDER BY fname")
    LiveData<List<Personne>> getAllPersonnes();

}
