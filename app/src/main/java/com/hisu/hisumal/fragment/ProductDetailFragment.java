package com.hisu.hisumal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.hisu.hisumal.ContainerActivity;
import com.hisu.hisumal.R;
import com.hisu.hisumal.adapter.SliderAdapter;
import com.hisu.hisumal.model.Product;
import com.hisu.hisumal.model.SliderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator3;

public class ProductDetailFragment extends Fragment {

    public static final String PRODUCT_DETAIL_KEY = "product";

    private ContainerActivity activity;

    private ViewPager2 productImg;
    private CircleIndicator3 productIndicator;
    private List<SliderItem> sliderItems;

    private ImageView productShip;
    private TextView productName, productPrice, productDiscount,
            productReviewQuantity, txtFreeShipping, productQuantityInStock;

    private RatingBar productRate;
    private FrameLayout productSpecificationsContainer;
    private ScrollView scrollView;

    private LinearLayout btnAddToCart;
    private TextView btnBuyNow;

    private View bottomSheetLayout;
    private ImageView orderProductImg;
    private TextView orderProductName, orderProductDiscount,
            orderProductPrice, orderProductInStock;
    private ImageButton btnQuantityMinus, btnQuantityPlus;
    private EditText edtQuantity;
    private BottomSheetDialog orderBottomSheetDialog;
    private Button btnOder;

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

        initBottomSheetUI();
        addActionForBottomSheetOrderButtons();
        setBottomSheetData(product);

        toggleDisplayToolbarOnScroll();
        toggleDisplayCheckOutBottomSheetLayout();

        return productDetailView;
    }

    private void initFragmentUI(View productDetailView) {
        activity = (ContainerActivity) getActivity();
        activity.hideToolBarTitle();

        productImg = productDetailView.findViewById(R.id.product_detail_img);
        productIndicator = productDetailView.findViewById(R.id.product_detail_slider_indicator);

        productShip = productDetailView.findViewById(R.id.product_detail_ship);
        productName = productDetailView.findViewById(R.id.product_detail_name);
        productPrice = productDetailView.findViewById(R.id.product_detail_price);
        productDiscount = productDetailView.findViewById(R.id.product_detail_discount);
        productReviewQuantity = productDetailView.findViewById(R.id.product_detail_review);
        productRate = productDetailView.findViewById(R.id.product_detail_rate);
        productQuantityInStock = productDetailView.findViewById(R.id.product_detail_quantity_in_stock);
        txtFreeShipping = productDetailView.findViewById(R.id.txt_free_shipping);

        scrollView = productDetailView.findViewById(R.id.scroll_view);
        productSpecificationsContainer = productDetailView.findViewById(R.id.product_detail_desc_container);

        btnAddToCart = productDetailView.findViewById(R.id.btn_add_to_cart);
        btnBuyNow = productDetailView.findViewById(R.id.btn_buy_now);
    }

    private List<SliderItem> initSliderItem() {
        List<SliderItem> items = new ArrayList<>();

        items.add(new SliderItem(R.drawable.laptop_1_slide_1));
        items.add(new SliderItem(R.drawable.laptop_1_slide_2));
        items.add(new SliderItem(R.drawable.laptop_1_slide_3));
        items.add(new SliderItem(R.drawable.laptop_1_slide_4));

        return items;
    }

    private void initFragmentData(Product product) {
        sliderItems = initSliderItem();
        sliderItems.add(0, new SliderItem(product.getImageResource()));
        productImg.setAdapter(new SliderAdapter(sliderItems));
        productIndicator.setViewPager(productImg);

        productName.setText(product.getProductName());
        productPrice.setText(product.getPriceFormat());
        productDiscount.setText(product.getDiscountFormat());
        productRate.setRating((float) product.getRate());
        productQuantityInStock.append(" " + product.getQuantityInStock());
        productReviewQuantity.setText("(" + new Random().nextInt(30) + " reviews)");

        if (product.isFreeShipping()) {
            productShip.setVisibility(ImageView.VISIBLE);
            txtFreeShipping.setVisibility(TextView.VISIBLE);
        }

        getChildFragmentManager()
                .beginTransaction()
                .add(productSpecificationsContainer.getId(),
                        new ProductSpecificationsFragment(product))
                .commit();
    }

    private void initBottomSheetUI() {
        bottomSheetLayout = getLayoutInflater()
                .inflate(R.layout.layout_checkout_bottom_sheet, null);

        orderBottomSheetDialog = new BottomSheetDialog(getContext());
        orderBottomSheetDialog.setContentView(bottomSheetLayout);

        orderProductImg = bottomSheetLayout.findViewById(R.id.order_product_img);
        orderProductName = bottomSheetLayout.findViewById(R.id.order_product_name);
        orderProductDiscount = bottomSheetLayout.findViewById(R.id.order_product_discount);
        orderProductPrice = bottomSheetLayout.findViewById(R.id.order_product_price);
        orderProductInStock = bottomSheetLayout.findViewById(R.id.order_product_in_stock);

        btnQuantityMinus = bottomSheetLayout.findViewById(R.id.btn_order_minus);
        btnQuantityMinus.setClickable(false);
        btnQuantityPlus = bottomSheetLayout.findViewById(R.id.btn_order_plus);
        edtQuantity = bottomSheetLayout.findViewById(R.id.edt_order_quantity);

        btnOder = bottomSheetLayout.findViewById(R.id.btn_order);
    }

    private void addActionForBottomSheetOrderButtons() {
        btnQuantityMinus.setOnClickListener(view -> {
            int currentQuantity = getCurrentOrderQuantity();
            if (currentQuantity > 1) {
                currentQuantity--;
                edtQuantity.setText(String.valueOf(currentQuantity));
            } else if (currentQuantity == 1) {
                btnQuantityMinus.setClickable(false);
                return;
            }
        });

        btnQuantityPlus.setOnClickListener(view -> {
            if (!btnQuantityMinus.isClickable())
                btnQuantityMinus.setClickable(true);
            edtQuantity.setText(String.valueOf(getCurrentOrderQuantity() + 1));
        });
    }

    private void setBottomSheetData(Product product) {
        orderProductImg.setImageResource(product.getImageResource());
        orderProductName.setText(product.getProductName());
        orderProductPrice.setText(product.getPriceFormat());
        orderProductDiscount.setText(product.getDiscountFormat());
        orderProductInStock.append(" " + product.getQuantityInStock());
    }

    private int getCurrentOrderQuantity() {
        return Integer.parseInt(edtQuantity.getText().toString());
    }

    private void toggleDisplayToolbarOnScroll() {
        scrollView.setOnScrollChangeListener((view, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY >= 150) //if user scroll for 150 px or whatever -> set elevation
                activity.showBackground();
            else
                activity.hideBackground();
        });
    }

    private void toggleDisplayCheckOutBottomSheetLayout() {
        btnBuyNow.setOnClickListener(view -> {
            orderBottomSheetDialog.show();
            changeButtonState(R.string.buy_now, R.color.secondaryColor);
        });

        btnAddToCart.setOnClickListener(view -> {
            orderBottomSheetDialog.show();
            changeButtonState(R.string.add_to_cart, R.color.teal_500);
        });
    }

    private void changeButtonState(int buttonText, int color) {
        btnOder.setText(getResources().getString(buttonText));
        btnOder.setBackgroundColor(ContextCompat.getColor(getContext(), color));
    }
}