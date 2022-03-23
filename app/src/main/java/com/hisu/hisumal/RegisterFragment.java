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

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment {

    private FrameLayout frameLayout;
    private TextInputEditText mEdtUserName, mEdtPwd, mEdtConfirmPwd;
    private TextView txtHaveAccount;
    private Button btnSignUp;

    public RegisterFragment(FrameLayout frameLayout) {
        this.frameLayout = frameLayout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        initUI(view);
        addActionForHaveAccountEvent();
        addActionForBtnSignUp();

        return view;
    }

    private void initUI(View view) {
        txtHaveAccount = view.findViewById(R.id.txt_have_account);
        txtHaveAccount.append(Html.fromHtml("<u>Login now!</u>"));

        btnSignUp = view.findViewById(R.id.btn_signup);
    }

    private void addActionForHaveAccountEvent() {
        txtHaveAccount.setOnClickListener(view1 -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                    .replace(frameLayout.getId(), new LoginFragment(frameLayout)).commit();
        });
    }

    private void addActionForBtnSignUp() {
        btnSignUp.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.putExtra(HomeActivity.TEST_INTENT_KEY, "Registered Successfully!");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            getActivity().finish();
        });
    }
}