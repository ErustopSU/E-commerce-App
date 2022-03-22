package com.hisu.hisumal;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {

    private FrameLayout frameLayout;
    private TextView txtNoAccunt;

    public LoginFragment(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container,false);

        txtNoAccunt = view.findViewById(R.id.txt_have_account);
        txtNoAccunt.append(Html.fromHtml("<u>Sign up now!</u>"));

        txtNoAccunt.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                    .replace(frameLayout.getId(), new RegisterFragment(frameLayout)).commit();
        });

        return view;
    }
}