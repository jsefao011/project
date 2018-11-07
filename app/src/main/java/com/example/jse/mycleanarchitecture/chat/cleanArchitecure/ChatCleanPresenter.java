package com.example.jse.mycleanarchitecture.chat.cleanArchitecure;

import com.example.jse.mycleanarchitecture.chat.cleanArchitecure.view.ChatCleanView;

public interface ChatCleanPresenter {

    void attachView(ChatCleanView view);

    void onCreate();

    void onStart();

    void onResume();

    void onDestroy();

}
