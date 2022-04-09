package com.hisu.hisumal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.hisumal.R;
import com.hisu.hisumal.adapter.SliderAdapter;
import com.hisu.hisumal.model.SliderItem;

import java.util.List;

public class ShoppingCartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private CheckBox cbxCheckOutAll;
    private TextView btnCheckOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View cartView = inflater.
                inflate(R.layout.fragment_shopping_cart, container, false);

        cartRecyclerView = cartView.findViewById(R.id.cart_recycler_view);
        cbxCheckOutAll = cartView.findViewById(R.id.cbx_buy_all);
        btnCheckOut = cartView.findViewById(R.id.check_out);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

        return cartView;
    }
}