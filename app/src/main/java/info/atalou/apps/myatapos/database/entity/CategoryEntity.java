package info.atalou.apps.myatapos.database.entity;


import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "categories",
        foreignKeys = @ForeignKey(
                entity = CategoryEntity.class,
                parentColumns = "id",
                childColumns = "parent_id",
                onDelete = CASCADE),
                indices = @Index(value = "parent_id", unique = true))

public class CategoryEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "parent_id")
    private Integer parent;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "code")
    private String code;

    @ColumnInfo(name = "color_id")
    private int color;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "created_at")
    private Date created;

    @ColumnInfo(name = "updated_at")
    private Date updated;

    @Ignore
    public CategoryEntity() {
    }

    public CategoryEntity(int id, Integer parent, String name, int color, String description,Date created, Date updated) {
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.color = color;
        this.description = description;
        this.created = created;
        this.updated = updated;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
