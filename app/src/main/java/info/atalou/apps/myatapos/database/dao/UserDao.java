package info.atalou.apps.myatapos.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import info.atalou.apps.myatapos.database.entity.UserEntity;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity clientEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserEntity> clients);

    @Delete
    void deleteUser(UserEntity clientEntity);

    @Update
    void updateUser(UserEntity noteEntity);

    @Query("SELECT * FROM users WHERE id = :id")
    UserEntity getUserById(int id);

    @Query("SELECT * FROM users WHERE role_id = :role")
    LiveData<List<UserEntity>> getUserByRole(int role);

    @Query("SELECT * FROM users ORDER BY created_at DESC")
    LiveData<List<UserEntity>> getAll();

}
