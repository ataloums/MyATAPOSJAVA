package info.atalou.apps.myatapos.viewmodel.more;


import android.app.Application;
import android.text.TextUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import info.atalou.apps.myatapos.database.entity.CompanyEntity;
import info.atalou.apps.myatapos.database.repository.CompanyRepository;

public class CompanyEditorViewModel extends AndroidViewModel {

    public MutableLiveData<CompanyEntity> mLiveCompany = new MutableLiveData<>();
    public LiveData<CompanyEntity> mCompany;
    private CompanyRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public CompanyEditorViewModel(@NonNull Application application) {
        super(application);

      //  mRepository = CompanyRepository.getInstance(getApplication());
        mRepository = CompanyRepository.getInstance(application.getApplicationContext());
        mCompany = mRepository.mCompany;


    }


    public void loadData(final int noteId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                CompanyEntity note = mRepository.getCompanyById(noteId);
                mLiveCompany.postValue(note);
            }
        });
    }


    public void saveCompany(String noteText) {
        CompanyEntity category = mLiveCompany.getValue();

        if (category == null){
            if (TextUtils.isEmpty(noteText.trim())){
                return;
            }
            category = new CompanyEntity();
            category.setName(noteText.trim());
        }else{
            category.setName(noteText.trim());
        }

        mRepository.insertCompany(category);

    }

    public void deleteCompany() {
        mRepository.deleteCompany(mLiveCompany.getValue());
    }

}
