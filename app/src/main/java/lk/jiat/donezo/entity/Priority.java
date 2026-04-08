package lk.jiat.donezo.entity;

import android.provider.ContactsContract;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "priority")
public class Priority {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
}
