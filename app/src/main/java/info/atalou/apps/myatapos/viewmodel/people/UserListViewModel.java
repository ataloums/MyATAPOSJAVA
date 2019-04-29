package info.atalou.apps.myatapos.viewmodel.people;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.entity.UserEntity;
import info.atalou.apps.myatapos.database.repository.UserRepository;

public class UserListViewModel extends AndroidViewModel {

    public LiveData<List<UserEntity>> mUsers;
    private UserRepository mRepository;

    public UserListViewModel(@NonNull Application application) {
        super(application);

        mRepository = UserRepository.getInstance(application.getApplicationContext());
        mUsers = mRepository.mUsers;
    }
}
