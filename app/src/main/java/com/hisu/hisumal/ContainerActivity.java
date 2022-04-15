package com.hisu.hisumal;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.hisu.hisumal.fragment.ProductDetailFragment;
import com.hisu.hisumal.fragment.ShoppingCartFragment;
import com.hisu.hisumal.entity.Product;

public class ContainerActivity extends AppCompatActivity {

    public static final String CONTAINER_KEY = "container_data";
    public static final String CONTAINER_TOOLBAR_MENU_MODE = "menu";

    private FrameLayout mainContainer;
    private AppBarLayout appBarLayout;
    private int modeMenu;
    private Menu myMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        appBarLayout = findViewById(R.id.app_bar_2);

        Toolbar mToolbar = findViewById(R.id.my_toolbar_2);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        productDetailToolbarBackIcon();

        mainContainer = findViewById(R.id.product_container);

        Bundle bundle = getIntent().getBundleExtra(CONTAINER_KEY);
        Product product = (Product) bundle.getSerializable(ProductDetailFragment.PRODUCT_DETAIL_KEY);

        int mode = (int) bundle.get(CONTAINER_TOOLBAR_MENU_MODE);
        modeMenu = mode;

        if (mode == 1)
            setFragment(new ProductDetailFragment(product));
        else
            setFragment(new ShoppingCartFragment());
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(mainContainer.getId(), fragment)
                .commit();
    }

    public void hideToolBarTitle() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void cartToolbarBackground() {
        appBarLayout.setBackgroundColor(getResources().getColor(R.color.secondaryColor));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white);
        setToolBarTitle("My Cart");
    }

    public void setToolBarTitle(String title) {
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void productDetailToolbarBackIcon() {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
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
        myMenu = menu;
        if (modeMenu != 0) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.toolbar_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedItemId = item.getItemId();
        switch (selectedItemId) {
            case R.id.toolbar_cart: {
                myMenu.findItem(R.id.toolbar_cart).setVisible(false);
                openCartLayout();
                break;
            }
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else {
            myMenu.findItem(R.id.toolbar_cart).setVisible(true);
            getSupportFragmentManager().popBackStack();
        }
    }

    public void openCartLayout() {
        getSupportFragmentManager().beginTransaction()
                .replace(mainContainer.getId(), new ShoppingCartFragment())
                .addToBackStack(null)
                .commit();
    }
}