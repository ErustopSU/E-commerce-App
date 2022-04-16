package com.hisu.hisumal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.hisumal.ContainerActivity;
import com.hisu.hisumal.R;
import com.hisu.hisumal.adapter.CartItemAdapter;
import com.hisu.hisumal.database.AppDatabase;
import com.hisu.hisumal.entity.Product;
import com.hisu.hisumal.myInterface.ICheckBoxChangedListener;

import java.text.DecimalFormat;
import java.util.List;

public class ShoppingCartFragment extends Fragment implements ICheckBoxChangedListener {

    private RecyclerView cartRecyclerView;
    private CheckBox cbxCheckOutAll;
    private TextView txtSubTotal;
    private TextView btnCheckOut;
    private ContainerActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cartView = inflater.
                inflate(R.layout.fragment_shopping_cart, container, false);

        cartRecyclerView = cartView.findViewById(R.id.cart_recycler_view);
        cbxCheckOutAll = cartView.findViewById(R.id.cbx_buy_all);
        btnCheckOut = cartView.findViewById(R.id.check_out);
        txtSubTotal = cartView.findViewById(R.id.cart_sub_total);

        activity = (ContainerActivity) getActivity();
        activity.cartToolbarBackground();

        CartItemAdapter adapter = new CartItemAdapter(
                getContext(),
                AppDatabase.getInstance(getContext()).productDAO().getAllProducts()
                , this);

        cartRecyclerView.setAdapter(adapter);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        cartRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        cbxCheckOutAll.setOnClickListener(view -> {
            adapter.toggleAllCheckBox(cbxCheckOutAll.isChecked());
            updateTotal(adapter.cartSumTotal());
        });

        return cartView;
    }

    public void toggleCheckOutAllCheckBox(boolean isCheck) {
        cbxCheckOutAll.setChecked(isCheck);
    }

    @Override
    public void updateTotal(double total) {
        txtSubTotal.setText(String.format("đ %s", new DecimalFormat("#,###").format(total)));
    }
}