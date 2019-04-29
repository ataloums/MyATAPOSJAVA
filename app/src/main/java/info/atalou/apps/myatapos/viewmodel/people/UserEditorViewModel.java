package info.atalou.apps.myatapos.viewmodel.people;

import android.app.Application;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import info.atalou.apps.myatapos.database.entity.RoleEntity;
import info.atalou.apps.myatapos.database.entity.UserEntity;
import info.atalou.apps.myatapos.database.repository.UserRepository;

public class UserEditorViewModel extends AndroidViewModel {

    public MutableLiveData<UserEntity> mLiveUser = new MutableLiveData<>();
    private List<RoleEntity>  results = new ArrayList<>();
    private int roleQty;
    private UserRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public UserEditorViewModel(@NonNull Application application) {
        super(application);
        mRepository = UserRepository.getInstance(getApplication());
    }


    public void loadData(final int userId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                UserEntity user = mRepository.getUserById(userId);
                mLiveUser.postValue(user);
            }
        });
    }


    public void saveUser(String name, String email, String username, int roleid) {
        UserEntity user = mLiveUser.getValue();

        if (user == null){
            if (TextUtils.isEmpty(name.trim())){
                return;
            }
            user = new UserEntity();
            user.setName(name.trim());
            user.setEmail(email.trim());
            user.setRole(roleid);
            user.setUsername(username.trim());
            user.setCreated(new Date());
        }else{
            user.setName(name.trim());
            user.setUsername(username.trim());
            user.setRole(roleid);
            user.setEmail(email.trim());
            user.setUpdated(new Date());
        }

       mRepository.insertUser(user);

    }

    public void deleteUser() {
        mRepository.deleteUser(mLiveUser.getValue());
    }

    public  void  addSampleRole(){ mRepository.addSampleRole(); }

    public List<RoleEntity> populateRoleSpinner(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                results = mRepository.populateRoleSpinner();
            }
        });

       return results;
    }

    public    int  roleQty(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                roleQty = mRepository.roleQty();
            }
        });
        return roleQty;
    }

}
