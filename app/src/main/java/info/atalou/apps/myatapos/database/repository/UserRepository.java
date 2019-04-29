package info.atalou.apps.myatapos.database.repository;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.AppDatabase;
import info.atalou.apps.myatapos.database.entity.RoleEntity;
import info.atalou.apps.myatapos.database.entity.UserEntity;
import info.atalou.apps.myatapos.utility.sample.RoleData;

public class UserRepository {
    private static UserRepository ourInstance ;
    public LiveData<List<UserEntity>> mUsers;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static UserRepository getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new UserRepository(context);
        }
        return ourInstance;
    }

    private UserRepository(Context context) {
       // mDb = AppDatabase.getInstance(context);
        mDb = AppDatabase.create(context,false);
        mUsers = getAllUsers();
    }

    private LiveData<List<UserEntity>> getAllUsers(){
        return mDb.userDao().getAll();
    }

    private LiveData<List<UserEntity>> getUserByRole(int role){
        return mDb.userDao().getUserByRole(role);
    }

    public UserEntity getUserById(int clientId) {
        return mDb.userDao().getUserById(clientId);
    }

    public void insertUser(final UserEntity client) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().insertUser(client);
            }
        });
    }

    public void updateUser(final UserEntity client) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().updateUser(client);
            }
        });
    }

    public void deleteUser(final UserEntity client) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().deleteUser(client);
            }
        });
    }

    public void addSampleRole() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.roleDao().insertAll(RoleData.getRoles());
            }
        });
    }

    public List<RoleEntity> populateRoleSpinner(){
        return mDb.roleDao().selectAll();
    }


    public  int roleQty(){
        return  mDb.roleDao().getCount();
    }


}
