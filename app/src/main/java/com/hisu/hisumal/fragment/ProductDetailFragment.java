package com.hisu.hisumal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.hisu.hisumal.R;
import com.hisu.hisumal.model.Product;

import java.util.Random;

public class ProductDetailFragment extends Fragment {

    public static final String PRODUCT_DETAIL_KEY = "product";

    private ImageView productImg, productShip;
    private TextView productName, productPrice, productDiscount, productReviewQuantity;
    private RatingBar productRate;
    private MaterialButton btnBuy;

    public ProductDetailFragment(Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PRODUCT_DETAIL_KEY, product);
        setArguments(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View productDetailView = inflater
                .inflate(R.layout.fragment_product_detail, container, false);

        Product product = (Product) getArguments().getSerializable(PRODUCT_DETAIL_KEY);

        initFragmentUI(productDetailView);
        initFragmentData(product);

        return productDetailView;
    }

    private void initFragmentUI(View productDetailView) {
        productImg = productDetailView.findViewById(R.id.product_detail_img);
        productShip = productDetailView.findViewById(R.id.product_detail_ship);
        productName = productDetailView.findViewById(R.id.product_detail_name);
        productPrice = productDetailView.findViewById(R.id.product_detail_price);
        productDiscount = productDetailView.findViewById(R.id.product_detail_discount);
        productReviewQuantity = productDetailView.findViewById(R.id.product_detail_review);
        productRate = productDetailView.findViewById(R.id.product_detail_rate);
        btnBuy = productDetailView.findViewById(R.id.product_detail_btn_buy);
    }

    private void initFragmentData(Product product) {
        productImg.setImageResource(product.getImageResource());
        productName.setText(product.getProductName());
        productPrice.setText(product.getPriceFormat());
        productDiscount.setText(product.getDiscountFormat());
        productRate.setRating((float) product.getRate());
        productReviewQuantity.setText("(" + new Random().nextInt(30) + " reviews)");

        if(product.isFreeShipping())
            productShip.setImageResource(R.drawable.free_shipping);
    }
}