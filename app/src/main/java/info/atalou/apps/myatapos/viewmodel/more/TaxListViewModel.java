package info.atalou.apps.myatapos.viewmodel.more;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.entity.TaxEntity;
import info.atalou.apps.myatapos.database.repository.TaxRepository;

public class TaxListViewModel extends AndroidViewModel {

    public LiveData<List<TaxEntity>> mTaxes;
    private TaxRepository mRepository;

    public TaxListViewModel(@NonNull Application application) {
        super(application);

        mRepository = TaxRepository.getInstance(application.getApplicationContext());
        mTaxes = mRepository.mTaxes;
    }
}
