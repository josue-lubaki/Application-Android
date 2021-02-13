package ca.josue.projetandroid.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.josue.projetandroid.R;
import ca.josue.projetandroid.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductVH> {

    private final Context context;
    List<Product> data;

    public ProductAdapter(Context context){
        this.context = context;
        this.data = new ArrayList<>();
    }
    
    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductVH(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {
        Product product = data.get(position);
        holder.ivProductIcon.setImageResource(product.getIcon());
        holder.tvProductName.setText(product.getProductName());
        holder.tvProductDescription.setText(product.getProductDescription());
        holder.tvProductPrice.setText("$" + product.getProductPrice());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addProduct(List<Product> liste){
        data = liste;
        notifyDataSetChanged();
    }

    static class ProductVH extends RecyclerView.ViewHolder{
        ImageView ivProductIcon;
        TextView tvProductName, tvProductDescription, tvProductPrice;

        public ProductVH(@NonNull View itemView) {
            super(itemView);
            ivProductIcon = itemView.findViewById(R.id.product_icon);
            tvProductName = itemView.findViewById(R.id.product_name);
            tvProductDescription = itemView.findViewById(R.id.product_description);
            tvProductPrice = itemView.findViewById(R.id.product_price);
        }
    }

}
