package com.hisu.hisumal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.hisumal.R;
import com.hisu.hisumal.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {

    private Context context;
    private List<Product> productList;

    public CartItemAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @NotNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View cartItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_cart_item, parent, false);
        return new CartItemViewHolder(cartItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartItemViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.itemImg.setImageResource(product.getImageResource());
        holder.itemName.setText(product.getProductName());
        holder.itemDiscount.setText(product.getDiscountFormat());
        holder.itemPrice.setText(product.getPriceFormat());
    }

    @Override
    public int getItemCount() {
        if (productList == null || productList.isEmpty()) return 0;
        return productList.size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout parent;
        private ImageView itemImg;
        private TextView itemName, itemDiscount, itemPrice;
        private CheckBox itemCheckBox;
        private EditText itemQuantity;
        private ImageButton btnMinus, btnPlus;

        public CartItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.cart_item_parent);
            itemImg = itemView.findViewById(R.id.cart_item_img);
            itemName = itemView.findViewById(R.id.cart_item_name);
            itemDiscount = itemView.findViewById(R.id.cart_item_discount);
            itemPrice = itemView.findViewById(R.id.cart_item_price);
        }
    }
}