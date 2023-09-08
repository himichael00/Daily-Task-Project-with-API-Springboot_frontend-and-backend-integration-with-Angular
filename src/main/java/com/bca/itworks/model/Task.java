package com.bca.itworks.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Table(name = "task")
@Data
@ToString
@EqualsAndHashCode
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    @JdbcTypeCode(SqlTypes.UUID)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    @Column(name = "title")
    private String title;

    @Column(name = "is_done")
    @ColumnDefault("true")
    private Boolean isDone;

    @Column(name = "created_date")
    @ColumnDefault("NOW()")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiDate;

    public Task(String title) {
        this.title = title;
        this.isDone = true;
        this.createdDate = Calendar.getInstance().getTime();
    }

    public Task() {

    }
}
