package com.yusufcakal.ra.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yusufcakal.ra.R;
import com.yusufcakal.ra.fragment.CategoryFragment;
import com.yusufcakal.ra.interfaces.CategoryCallback;

public class UserActivity extends AppCompatActivity implements View.OnClickListener, CategoryCallback{

    private TextView tvActionBar;
    private ImageView imCart;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeStaffLogin);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        fragmentManager = getSupportFragmentManager();
        startFragment(new CategoryFragment());

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbaruser);
        tvActionBar = (TextView)getSupportActionBar().getCustomView().findViewById(R.id.tvActionBar);
        imCart = (ImageView) getSupportActionBar().getCustomView().findViewById(R.id.imCart);
        imCart.setOnClickListener(this);
        tvActionBar.setText(getResources().getString(R.string.orderCategory));

    }

    private void startFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(imCart)){
            Toast.makeText(this, "Sepetim", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void call(Fragment fragment) {
        startFragment(fragment);
        tvActionBar.setText(getApplicationContext().getResources().getString(R.string.product));
    }
}
