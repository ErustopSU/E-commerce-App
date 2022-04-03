package com.hisu.hisumal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.hisumal.R;
import com.hisu.hisumal.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @NotNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product, parent, false);
        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productImg.setImageResource(product.getImageResource());
        holder.productName.setText(product.getProductName());
        holder.productPrice.setText(product.getPriceFormat());

        if (product.isFreeShipping())
            holder.productFreeShipIcon.setImageResource(R.drawable.free_shipping);

        holder.parent.setOnClickListener(view -> {
            Toast.makeText(context, product.getBrand(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        if (productList == null || productList.isEmpty()) return 0;
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private CardView parent;
        private ImageView productImg, productFreeShipIcon;
        private TextView productName, productPrice;

        public ProductViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.product_card_container);
            productImg = itemView.findViewById(R.id.product_img);
            productFreeShipIcon = itemView.findViewById(R.id.product_free_ship);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }
}