package info.atalou.apps.myatapos.viewmodel.people;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.entity.CustomerGroupEntity;
import info.atalou.apps.myatapos.database.repository.CustomerGroupRepository;

public class CustomerGroupListViewModel extends AndroidViewModel {

    public LiveData<List<CustomerGroupEntity>> mGroups;
    private CustomerGroupRepository mRepository;

    public CustomerGroupListViewModel(@NonNull Application application) {
        super(application);

        mRepository = CustomerGroupRepository.getInstance(application.getApplicationContext());
        mGroups = mRepository.mGroups;
    }
}
