package info.atalou.apps.myatapos.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import info.atalou.apps.myatapos.database.entity.CategoryEntity;

@Dao
public  interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(CategoryEntity noteEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CategoryEntity> notes);

    @Delete
    void deleteCategory(CategoryEntity noteEntity);

    @Update
    void updateCategory(CategoryEntity noteEntity);

    @Query("SELECT * FROM categories WHERE id = :id")
    CategoryEntity getCategoryById(int id);

    @Query("SELECT * FROM categories ORDER BY created_at DESC")
    LiveData<List<CategoryEntity>> getAll();

    @Query("DELETE FROM categories")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM categories")
    int getCount();

    /**
     * Others Methods for categories
     */

    @Query("SELECT * FROM categories WHERE parent_id IS NULL")
    CategoryEntity findRootCategory();


    @Query("SELECT * FROM categories WHERE parent_id = :parent_id")
    LiveData<List<CategoryEntity>> findChildCategory(int parent_id);

}
