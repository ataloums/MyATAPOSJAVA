package info.atalou.apps.myatapos.viewmodel.people;

import android.app.Application;
import android.text.TextUtils;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import info.atalou.apps.myatapos.database.entity.ClientEntity;
import info.atalou.apps.myatapos.database.repository.ClientRepository;

public class ClientEditorViewModel extends AndroidViewModel {

    public MutableLiveData<ClientEntity> mLiveClient = new MutableLiveData<>();
    private ClientRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public ClientEditorViewModel(@NonNull Application application) {
        super(application);

        mRepository = ClientRepository.getInstance(getApplication());

    }


    public void loadData(final int clientId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ClientEntity client = mRepository.getClientById(clientId);
                mLiveClient.postValue(client);
            }
        });
    }


    public void saveClient(String name, String email, String phone) {
        ClientEntity client = mLiveClient.getValue();

        if (client == null){
            if (TextUtils.isEmpty(name.trim())){
                return;
            }
            client = new ClientEntity();
            client.setName(name.trim());
            client.setEmail(email.trim());
            client.setPhone(phone.trim());
            client.setCreated(new Date());
        }else{
            client.setName(name.trim());
            client.setPhone(phone.trim());
            client.setEmail(email.trim());
            client.setUpdated(new Date());
        }

       mRepository.insertClient(client);

    }

    public void deleteClient() {
        mRepository.deleteClient(mLiveClient.getValue());
    }

}
