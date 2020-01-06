package com.zql.comm.dao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import com.zql.comm.bean.DaoMaster;
import com.zql.comm.bean.NoteBeanDao;

import org.greenrobot.greendao.database.Database;

public class UpDataDaoHelper extends DaoMaster.OpenHelper {
    public UpDataDaoHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, NoteBeanDao.class);
    }
}
