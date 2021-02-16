package ca.josue.projetandroid.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personne_table")
public class Personne {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String fname;

    private String lname;

    private String profession;

    public Personne(String fname, String lname, String profession) {
        this.fname = fname;
        this.lname = lname;
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
