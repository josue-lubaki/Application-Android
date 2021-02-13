package ca.josue.projetandroid.navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.josue.projetandroid.MainActivity;
import ca.josue.projetandroid.R;
import ca.josue.projetandroid.adapters.ProductAdapter;
import ca.josue.projetandroid.model.Product;

public class Home extends Fragment {
    private RecyclerView listView;
    private ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_layout, container, false);
    }

    private List<Product> getProductModels(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(
                "Pizza",
                "Que demander de plus, un delicieux Pizza, je suis comblé",
                10, R.raw.pizza));

        products.add(new Product(
                "Poulet",
                "Mon meilleur ami, après la job, elle m'attend sagement",
                12, R.raw.poulet));

        products.add(new Product(
                "Frite",
                "Devant la télé, je te préfère plutôt que du pop-corn",
                8, R.raw.frite2));

        products.add(new Product(
                "Burger",
                "Même si je prend du poids, je ne peux m'en passer de toi",
                5, R.raw.burger));

        products.add(new Product(
                "Trio Burger",
                "Très économique, je te le recommande vivement, surtout toi étudiant",
                10, R.raw.trio));

        products.add(new Product(
                "Pizza",
                "Que demander de plus, un delicieux Pizza, je suis comblé",
                10, R.raw.pizza));

        products.add(new Product(
                "Poulet",
                "Mon meilleur ami, après la job, elle m'attend sagement",
                12, R.raw.poulet));

        products.add(new Product(
                "Frite",
                "Devant la télé, je te préfère plutôt que du pop-corn",
                8, R.raw.frite2));

        products.add(new Product(
                "Burger",
                "Même si je prend du poids, je ne peux m'en passer de toi",
                5, R.raw.burger));

        products.add(new Product(
                "Trio Burger",
                "Très économique, je te le recommande vivement, surtout toi étudiant",
                10, R.raw.trio));

        return products;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.list_all_items);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProductAdapter(getActivity());
        listView.setAdapter(adapter);
        adapter.addProduct(getProductModels());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity)context).setTitle(R.string.home);
    }
}
