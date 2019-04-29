package info.atalou.apps.myatapos.database.entity;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "roles")
public class RoleEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;


    @ColumnInfo(name = "created_at")
    private Date created;

    @ColumnInfo(name = "updated_at")
    private Date updated;


    @Ignore
    public RoleEntity(){}

    public RoleEntity(int id, String name, Date created, Date updated) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.updated = updated;
    }

    @Ignore
    public RoleEntity(int id, String name, Date created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    @Ignore
    public RoleEntity(String name, Date created) {
        this.name = name;
        this.created = created;
    }

    @Ignore
    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
