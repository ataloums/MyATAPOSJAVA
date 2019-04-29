package info.atalou.apps.myatapos.viewmodel.people;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.entity.ClientEntity;
import info.atalou.apps.myatapos.database.repository.ClientRepository;

public class ClientListViewModel extends AndroidViewModel {

    public LiveData<List<ClientEntity>> mClients;
    private ClientRepository mRepository;

    public ClientListViewModel(@NonNull Application application) {
        super(application);

        mRepository = ClientRepository.getInstance(application.getApplicationContext());
        mClients = mRepository.mClients;
    }
}
