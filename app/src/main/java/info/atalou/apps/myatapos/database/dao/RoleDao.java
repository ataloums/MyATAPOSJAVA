package info.atalou.apps.myatapos.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import info.atalou.apps.myatapos.database.entity.RoleEntity;

@Dao
public interface RoleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RoleEntity> role);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RoleEntity role);

    @Query("SELECT * FROM roles WHERE id = :id")
    RoleEntity getRoleById(int id);

    @Query("SELECT * FROM roles ORDER BY created_at DESC")
    LiveData<List<RoleEntity>> getAll();

    @Query("SELECT COUNT(*) FROM roles")
    int getCount();

    /// For Test------------------------------------------------------------------------------------
    @Query("SELECT * FROM roles ORDER BY created_at DESC")
    List<RoleEntity> selectAll();

    @Update
    void update(RoleEntity role);

    @Delete
    void  delete(RoleEntity role);
}
