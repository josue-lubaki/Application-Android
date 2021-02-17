package ca.josue.projetandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.josue.projetandroid.R;
import ca.josue.projetandroid.model.Personne;

public class PersonneAdapter extends RecyclerView.Adapter<PersonneAdapter.PersonneHolder>{

    private List<Personne> liste_Personnes = new ArrayList<>();

    @NonNull
    @Override
    public PersonneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.personne_item_layout, parent, false);
        return new PersonneHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonneHolder holder, int position) {
        Personne current = liste_Personnes.get(position);
        holder.tvFname.setText(current.getFname());
        holder.tvLname.setText(current.getLname());
        holder.tvProfession.setText(current.getProfession());
    }

    @Override
    public int getItemCount() {
        return liste_Personnes.size();
    }

    public void setListe_Personnes(List<Personne> manyPersonnes){
        liste_Personnes = manyPersonnes;
        notifyDataSetChanged();
    }

    static class PersonneHolder extends RecyclerView.ViewHolder{
        // Faire Correspondre les champs
        private final TextView tvFname;
        private final TextView tvLname;
        private final TextView tvProfession;

        public PersonneHolder(@NonNull View itemView) {
            super(itemView);
            tvFname = itemView.findViewById(R.id.tv_fname);
            tvLname = itemView.findViewById(R.id.tv_lname);
            tvProfession = itemView.findViewById(R.id.tv_profession);
        }
    }
}
