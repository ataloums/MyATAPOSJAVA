package info.atalou.apps.myatapos.viewmodel.more;

import android.app.Application;
import android.text.TextUtils;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import info.atalou.apps.myatapos.database.entity.TaxEntity;
import info.atalou.apps.myatapos.database.repository.TaxRepository;

public class TaxEditorViewModel extends AndroidViewModel {

    public MutableLiveData<TaxEntity> mLiveTax = new MutableLiveData<>();
    private TaxRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public TaxEditorViewModel(@NonNull Application application) {
        super(application);

        mRepository = TaxRepository.getInstance(getApplication());

    }


    public void loadData(final int taxId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                TaxEntity note = mRepository.getTaxById(taxId);
                mLiveTax.postValue(note);
            }
        });
    }


    public void saveTax(String name, String code, double value) {
        TaxEntity tax = mLiveTax.getValue();

        if (tax == null){
            if (TextUtils.isEmpty(name.trim())){
                return;
            }
            tax = new TaxEntity();
            tax.setName(name.trim());
            tax.setCode(code.trim());
            tax.setValue(value);
            tax.setCreated(new Date());
        }else{
            tax.setCode(code.trim());
            tax.setName(name.trim());
            tax.setValue(value);
            tax.setUpdated(new Date());
        }

       mRepository.insertTax(tax);

    }

    public void deleteTax() {
        mRepository.deleteTax(mLiveTax.getValue());
    }

}
