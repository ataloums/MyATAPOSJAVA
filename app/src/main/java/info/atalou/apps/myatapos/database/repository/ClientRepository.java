package info.atalou.apps.myatapos.database.repository;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.AppDatabase;
import info.atalou.apps.myatapos.database.entity.ClientEntity;

public class ClientRepository {
    private static ClientRepository ourInstance ;
    public LiveData<List<ClientEntity>> mClients;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static ClientRepository getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new ClientRepository(context);
        }
        return ourInstance;
    }

    private ClientRepository(Context context) {
       // mDb = AppDatabase.getInstance(context);
        mDb = AppDatabase.create(context,false);
        mClients = getAllClients();
    }

    private LiveData<List<ClientEntity>> getAllClients(){
        return mDb.clientDao().getAll();
    }

    public ClientEntity getClientById(int clientId) {
        return mDb.clientDao().getClientById(clientId);
    }

    public void insertClient(final ClientEntity client) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.clientDao().insertClient(client);
            }
        });
    }

    public void updateClient(final ClientEntity client) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.clientDao().updateClient(client);
            }
        });
    }

    public void deleteClient(final ClientEntity client) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.clientDao().deleteClient(client);
            }
        });
    }
}
