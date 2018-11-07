package com.example.jse.mycleanarchitecture.chat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.jse.mycleanarchitecture.R;
import com.example.jse.mycleanarchitecture.chat.adapter.MyFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatClean extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_clean);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupViewPager();

    }


    protected void setContentView() {


    }

    private void setupViewPager() {
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        //fragmentAdapter.addFragment(new RubricaCabeceraFragment(), getString(R.string.fragment_rubrica_bidimensional_cabecera));
        //fragmentAdapter.addFragment(new RubricaDetalleFragment(), getString(R.string.fragment_rubrica_bidimensional_detalle));
        viewpager.setOffscreenPageLimit(2);
        viewpager.setAdapter(fragmentAdapter);
        //viewpager.setCurrentItem(0);
        tabs.setupWithViewPager(viewpager);
    }




}
