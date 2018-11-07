package com.example.jse.mycleanarchitecture.chat.cleanArchitecure.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jse.mycleanarchitecture.R;
import com.example.jse.mycleanarchitecture.chat.cleanArchitecure.ChatCleanPresenter;
import com.example.jse.mycleanarchitecture.chat.cleanArchitecure.ChatCleanPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChatCleanFrament extends Fragment implements ChatCleanView {

    @BindView(R.id.btn_send)
    FloatingActionButton btnSend;
    @BindView(R.id.btn_emoji)
    ImageView btnEmoji;
    @BindView(R.id.edt_message)
    EditText edtMessage;
    @BindView(R.id.layout_bottom)
    CardView layoutBottom;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    Unbinder unbinder;
    private ChatCleanPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupPresenter();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_chat_clean, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupAdapter();
        return view;
    }

    private void setupAdapter() {

    }

    private void setupPresenter() {
        this.presenter = new ChatCleanPresenterImpl();
        setPresenter(presenter);
    }


    @Override
    public void setPresenter(ChatCleanPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
