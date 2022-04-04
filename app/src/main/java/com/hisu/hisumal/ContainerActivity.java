package com.hisu.hisumal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.hisu.hisumal.fragment.ProductDetailFragment;
import com.hisu.hisumal.model.Product;

public class ContainerActivity extends AppCompatActivity {

    private FrameLayout mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mainContainer = findViewById(R.id.product_container);

        Product product = (Product) getIntent().getSerializableExtra(ProductDetailFragment.PRODUCT_DETAIL_KEY);

        getSupportFragmentManager().beginTransaction()
                .add(mainContainer.getId(), new ProductDetailFragment(product)).commit();
    }
}