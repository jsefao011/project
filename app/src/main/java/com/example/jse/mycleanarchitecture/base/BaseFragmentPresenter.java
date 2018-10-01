package com.example.jse.mycleanarchitecture.base;

/**
 * Created by Jse on 30/09/2018.
 */

public interface BaseFragmentPresenter<T extends BaseView> extends BaseView.BasePresenter<T> {
    void onAttach();

    void onCreateView();

    void onViewCreated();

    void onActivityCreated();

    void onDestroyView();

    void onDetach();
}
