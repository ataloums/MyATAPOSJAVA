package info.atalou.apps.myatapos.database.repository;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.AppDatabase;
import info.atalou.apps.myatapos.database.entity.TaxEntity;

public class TaxRepository {
    private static TaxRepository ourInstance ;
    public LiveData<List<TaxEntity>> mTaxes;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static TaxRepository getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new TaxRepository(context);
        }
        return ourInstance;
    }

    private TaxRepository(Context context) {
       // mDb = AppDatabase.getInstance(context);
        mDb = AppDatabase.create(context, false);
        mTaxes = getAllTaxes();
    }

    private LiveData<List<TaxEntity>> getAllTaxes(){
        return mDb.taxDao().getAll();
    }

    public TaxEntity getTaxById(int categoryId) {
        return mDb.taxDao().getTaxById(categoryId);
    }

    public void insertTax(final TaxEntity category) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.taxDao().insertTax(category);
            }
        });
    }


    public void deleteTax(final TaxEntity category) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.taxDao().deleteTax(category);
            }
        });
    }
}
