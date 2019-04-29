package info.atalou.apps.myatapos.database.repository;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.AppDatabase;
import info.atalou.apps.myatapos.database.entity.CustomerGroupEntity;

public class CustomerGroupRepository {
    private static CustomerGroupRepository ourInstance ;
    public LiveData<List<CustomerGroupEntity>> mGroups;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static CustomerGroupRepository getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new CustomerGroupRepository(context);
        }
        return ourInstance;
    }

    private CustomerGroupRepository(Context context) {
        mDb = AppDatabase.create(context, false);
        mGroups = getAllGroups();
    }

    private LiveData<List<CustomerGroupEntity>> getAllGroups(){
        return mDb.customerGroupDao().getAll();
    }

    public CustomerGroupEntity getGroupById(int groupId) {
        return mDb.customerGroupDao().getGroupById(groupId);
    }

    public void insertGroup(final CustomerGroupEntity group) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.customerGroupDao().insertGroup(group);
            }
        });
    }


    public void deleteGroup(final CustomerGroupEntity group) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.customerGroupDao().deleteGroup(group);
            }
        });
    }
}
