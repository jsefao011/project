package com.example.jse.mycleanarchitecture.model.dbflow.config;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ConflictAction;
import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Jse on 01/10/2018.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, insertConflict = ConflictAction.REPLACE)
public class AppDatabase {
    public static final String NAME = "CRMEfinal";
    public static final int VERSION = 3;
}