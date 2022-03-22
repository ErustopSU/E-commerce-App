package com.hisu.hisumal;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    private FrameLayout frameLayout;
    private TextView txtHaveAccount;

    public RegisterFragment(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        txtHaveAccount = view.findViewById(R.id.txt_have_account);
        txtHaveAccount.append(Html.fromHtml("<u>Login now!</u>"));

        txtHaveAccount.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                    .replace(frameLayout.getId(), new LoginFragment(frameLayout)).commit();
        });

        return view;
    }
}