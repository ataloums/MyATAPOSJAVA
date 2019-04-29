package info.atalou.apps.myatapos.database.repository;

import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;
import info.atalou.apps.myatapos.database.AppDatabase;
import info.atalou.apps.myatapos.database.entity.CategoryEntity;

public class CategoryRepository {
    private static CategoryRepository ourInstance ;
    public LiveData<List<CategoryEntity>> mCategories;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static CategoryRepository getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = new CategoryRepository(context);
        }
        return ourInstance;
    }

    private CategoryRepository(Context context) {
       // mDb = AppDatabase.getInstance(context);
        mDb = AppDatabase.create(context, false);
        mCategories = getAllCategories();
    }

    private LiveData<List<CategoryEntity>> getAllCategories(){
        return mDb.categoryDao().getAll();
    }

    public CategoryEntity getCategoryById(int categoryId) {
        return mDb.categoryDao().getCategoryById(categoryId);
    }

    public void insertCategory(final CategoryEntity category) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.categoryDao().insertCategory(category);
            }
        });
    }

    public void updateCategory(final CategoryEntity category) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.categoryDao().updateCategory(category);
            }
        });
    }

    public void deleteCategory(final CategoryEntity category) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.categoryDao().deleteCategory(category);
            }
        });
    }
}
