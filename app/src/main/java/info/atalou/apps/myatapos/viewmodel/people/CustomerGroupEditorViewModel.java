package info.atalou.apps.myatapos.viewmodel.people;

import android.app.Application;
import android.text.TextUtils;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import info.atalou.apps.myatapos.database.entity.CustomerGroupEntity;
import info.atalou.apps.myatapos.database.repository.CustomerGroupRepository;

public class CustomerGroupEditorViewModel extends AndroidViewModel {

    public MutableLiveData<CustomerGroupEntity> mLiveCustomerGroup = new MutableLiveData<>();
    private CustomerGroupRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public CustomerGroupEditorViewModel(@NonNull Application application) {
        super(application);

        mRepository = CustomerGroupRepository.getInstance(getApplication());

    }


    public void loadData(final int groupId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                CustomerGroupEntity note = mRepository.getGroupById(groupId);
                mLiveCustomerGroup.postValue(note);
            }
        });
    }


    public void saveCustomerGroup(String name,  double value) {
        CustomerGroupEntity tax = mLiveCustomerGroup.getValue();

        if (tax == null){
            if (TextUtils.isEmpty(name.trim())){
                return;
            }
            tax = new CustomerGroupEntity();
            tax.setName(name.trim());
            tax.setValue(value);
            tax.setCreated(new Date());
        }else{
            tax.setName(name.trim());
            tax.setValue(value);
            tax.setUpdated(new Date());
        }

       mRepository.insertGroup(tax);

    }

    public void deleteCustomerGroup() {
        mRepository.deleteGroup(mLiveCustomerGroup.getValue());
    }

}
