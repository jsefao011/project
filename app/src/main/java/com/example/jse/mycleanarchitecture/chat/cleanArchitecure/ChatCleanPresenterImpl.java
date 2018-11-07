package com.example.jse.mycleanarchitecture.chat.cleanArchitecure;

import com.example.jse.mycleanarchitecture.chat.cleanArchitecure.view.ChatCleanView;

public class ChatCleanPresenterImpl implements ChatCleanPresenter {

    private ChatCleanView view;

    @Override
    public void attachView(ChatCleanView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }


    @Override
    public void onDestroy() {
        view = null;
    }

}
