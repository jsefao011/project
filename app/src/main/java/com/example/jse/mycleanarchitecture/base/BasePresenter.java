package com.example.jse.mycleanarchitecture.base;

/**
 * Created by Jse on 30/09/2018.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onBackPressed();

}
