package com.example.jse.mycleanarchitecture.base;

/**
 * Created by Jse on 30/09/2018.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    /**
     * Created by Jse on 30/09/2018.
     */
}
