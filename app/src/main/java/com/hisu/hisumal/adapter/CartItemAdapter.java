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
import com.hisu.hisumal.entity.Product;
import com.hisu.hisumal.fragment.ShoppingCartFragment;
import com.hisu.hisumal.myInterface.ICheckBoxChangedListener;
import com.hisu.hisumal.util.ImageConverterHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {

    private final Context context;
    private final List<Product> productList;
    private final List<CartItemViewHolder> cartItemViewHolders;
    private final ICheckBoxChangedListener checkBoxChangedListener;
    private final ShoppingCartFragment cartFragment;

    public CartItemAdapter(Context context, List<Product> productList, ICheckBoxChangedListener checkBoxChangedListener) {
        this.context = context;
        this.productList = productList;
        cartItemViewHolders = new ArrayList<>();
        this.checkBoxChangedListener = checkBoxChangedListener;
        cartFragment = (ShoppingCartFragment) checkBoxChangedListener;
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
        holder.itemImg.setImageResource(
                ImageConverterHelper.getResourceIdFromString(context,
                        product.getProductImages().get(0))
        );
        holder.itemName.setText(product.getProductName());
        holder.itemDiscount.setText(product.getDiscountFormat());
        holder.itemPrice.setText(product.getPriceFormat());
        holder.price = product.getPrice();

        cartItemViewHolders.add(holder);

        holder.itemCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
            cartFragment.toggleCheckOutAllCheckBox(isCheckOutAll());
            checkBoxChangedListener.updateTotal(cartSumTotal());
        });
    }

    @Override
    public int getItemCount() {
        if (productList == null || productList.isEmpty()) return 0;
        return productList.size();
    }

    public void toggleAllCheckBox(boolean isChecked) {
        cartItemViewHolders.forEach(holder -> {
            holder.itemCheckBox.setChecked(isChecked);
        });
    }

    public double cartSumTotal() {
        double sumTotal = 0;

        for (CartItemViewHolder holder : cartItemViewHolders)
            if (holder.itemCheckBox.isChecked())
                sumTotal += (holder.price
                        * Integer.parseInt(holder.itemQuantity.getText().toString()));

        return sumTotal;
    }

    private boolean isCheckOutAll() {
        for (CartItemViewHolder cartItemViewHolder : cartItemViewHolders)
            if (!cartItemViewHolder.itemCheckBox.isChecked()) return false;
        return true;
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {

        private final ConstraintLayout parent;
        private final ImageView itemImg;
        private final TextView itemName, itemDiscount, itemPrice;
        private final CheckBox itemCheckBox;
        private final EditText itemQuantity;
        private final ImageButton btnMinus, btnPlus;
        private double price;

        public CartItemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.cart_item_parent);
            itemImg = itemView.findViewById(R.id.cart_item_img);
            itemName = itemView.findViewById(R.id.cart_item_name);
            itemDiscount = itemView.findViewById(R.id.cart_item_discount);
            itemPrice = itemView.findViewById(R.id.cart_item_price);
            itemCheckBox = itemView.findViewById(R.id.cart_item_check_box);
            itemQuantity = itemView.findViewById(R.id.cart_item_edt_quantity);
            btnMinus = itemView.findViewById(R.id.cart_item_btn_minus);
            btnPlus = itemView.findViewById(R.id.cart_item_btn_add);
        }
    }
}