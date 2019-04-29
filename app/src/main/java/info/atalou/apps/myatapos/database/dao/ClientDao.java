package info.atalou.apps.myatapos.database.dao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import info.atalou.apps.myatapos.database.entity.ClientEntity;

@Dao
public interface ClientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClient(ClientEntity clientEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ClientEntity> clients);

    @Delete
    void deleteClient(ClientEntity clientEntity);

    @Update
    void updateClient(ClientEntity noteEntity);

    @Query("SELECT * FROM clients WHERE id = :id")
    ClientEntity getClientById(int id);

    @Query("SELECT * FROM clients ORDER BY created_at DESC")
    LiveData<List<ClientEntity>> getAll();

}
