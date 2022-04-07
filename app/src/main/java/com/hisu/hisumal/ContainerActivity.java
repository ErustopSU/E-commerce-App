package com.hisu.hisumal;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.hisu.hisumal.fragment.ProductDetailFragment;
import com.hisu.hisumal.model.Product;

public class ContainerActivity extends AppCompatActivity {

    private FrameLayout mainContainer;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        appBarLayout = findViewById(R.id.app_bar);

        Toolbar mToolbar = findViewById(R.id.my_toolbar_2);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        mainContainer = findViewById(R.id.product_container);

        Product product = (Product) getIntent().getSerializableExtra(ProductDetailFragment.PRODUCT_DETAIL_KEY);

        getSupportFragmentManager().beginTransaction()
                .add(mainContainer.getId(), new ProductDetailFragment(product)).commit();
    }

    public void showBackground() {
        appBarLayout.setBackgroundColor(Color.WHITE);
        appBarLayout.setElevation(40f);
    }

    public void hideBackground() {
        appBarLayout.setBackground(null);
        appBarLayout.setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}