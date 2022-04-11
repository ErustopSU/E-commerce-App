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
import com.hisu.hisumal.model.Product;

import java.util.List;

public class ShoppingCartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private CheckBox cbxCheckOutAll;
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

        activity = (ContainerActivity) getActivity();
        activity.showBackground();
        activity.setToolBarTitle("My Cart");

//        Toast.makeText(getContext(), getActivity().getClass().toString(), Toast.LENGTH_SHORT).show();

        cartRecyclerView.setAdapter(new CartItemAdapter(
                getContext(),
                List.of(new Product(1, R.drawable.laptop_1,
                                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                                "ASUS", 20000000, 15, true, 4.5, 2, null),
                        new Product(1, R.drawable.laptop_1,
                                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                                "ASUS", 20000000, 15, true, 4.5, 2, null),
                        new Product(1, R.drawable.laptop_1,
                                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W",
                                "Laptop Asus Gaming Rog Strix G15 G513IH HN015W bla bla desc",
                                "ASUS", 20000000, 15, true, 4.5, 2, null))
        ));

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        cartRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return cartView;
    }
}