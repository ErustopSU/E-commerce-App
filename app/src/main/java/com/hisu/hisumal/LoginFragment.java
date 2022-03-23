package com.hisu.hisumal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {

    private FrameLayout frameLayout;
    private TextInputEditText mEdtUserName, mEdtPwd;
    private TextView txtNoAccount;
    private Button btnLogin;

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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        initUI(view);
        addActionForNoAccountEvent();
        addActionForBtnLogin();

        return view;
    }

    private void initUI(View view) {
        txtNoAccount = view.findViewById(R.id.txt_have_account);
        txtNoAccount.append(Html.fromHtml("<u>Sign up now!</u>"));

        mEdtUserName = view.findViewById(R.id.edt_username);
        mEdtPwd = view.findViewById(R.id.edt_pwd);

        btnLogin = view.findViewById(R.id.btn_login);
    }

    private void addActionForNoAccountEvent() {
        txtNoAccount.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                    .replace(frameLayout.getId(), new RegisterFragment(frameLayout)).commit();
        });
    }

    private void addActionForBtnLogin() {
        btnLogin.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.putExtra(HomeActivity.TEST_INTENT_KEY, "Login Successfully!");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        });
    }
}