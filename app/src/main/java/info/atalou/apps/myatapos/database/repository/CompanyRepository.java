package info.atalou.apps.myatapos.database.repository;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.AppDatabase;
import info.atalou.apps.myatapos.database.entity.CompanyEntity;

public class CompanyRepository {

    private static CompanyRepository ourInstance ;
    public LiveData<CompanyEntity> mCompany;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static CompanyRepository getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new CompanyRepository(context);
        }
        return ourInstance;
    }

    private CompanyRepository(Context context) {
        //mDb = AppDatabase.getInstance(context);
        mDb = AppDatabase.create(context, false);
        mCompany = getCompanyDetails();
    }

    private LiveData<CompanyEntity> getCompanyDetails() {
        return mDb.companyDao().getCompany();
    }


    public CompanyEntity getCompanyById(int companyID) {
        return mDb.companyDao().getCompanyById(companyID);
    }


    public void insertCompany(final CompanyEntity company) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.companyDao().insertCompany(company);
            }
        });
    }

    public void deleteCompany(final CompanyEntity company) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.companyDao().deleteCompany(company);
            }
        });
    }
}
