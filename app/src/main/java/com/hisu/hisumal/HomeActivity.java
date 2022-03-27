package com.hisu.hisumal;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private CircleImageView btnFollowFb, btnFollowGit, btnFollowYT;
    private NavigationView mNavigationView;
    private FrameLayout mFragmentContainer;

    private CircleImageView[] socialMedia = null;

    private static final String FACEBOOK_URL = "Demo: Follow me on facebook";
    private static final String GITHUB_URL = "Demo: Follow me on github";
    private static final String YTB_URL = "Demo: Follow me on youtube";

    private String []followUrls = {FACEBOOK_URL, GITHUB_URL, YTB_URL};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initUI();
        initNavigationDrawer();

        for (int i = 0; i < socialMedia.length; i++) {
            int finalI = i;
            socialMedia[i].setOnClickListener(view -> openApp(followUrls[finalI]));
        }

        mNavigationView.setNavigationItemSelectedListener(item -> {
            int selectedItemID = item.getItemId();

            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void initUI() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.my_toolbar);

        btnFollowFb = mDrawerLayout.findViewById(R.id.follow_us_on_fb);
        btnFollowGit = mDrawerLayout.findViewById(R.id.follow_us_on_git);
        btnFollowYT = mDrawerLayout.findViewById(R.id.follow_us_on_yt);

        socialMedia = new CircleImageView[] {btnFollowFb, btnFollowGit, btnFollowYT};

        mNavigationView = findViewById(R.id.my_navigation_view);

        mFragmentContainer = findViewById(R.id.homepage_fragment_container);
    }

    private void initNavigationDrawer() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.open_navigation_drawer, R.string.close_navigation_drawer);

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void openApp(String appName) {
        //Todo: add code to open app, this line is just a mockup
        Toast.makeText(this, appName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}