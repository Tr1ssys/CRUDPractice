package com.alex.practice.CRUDpractice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "second_entrydate")
public class EntryTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "entry_date")
    private Date datetime;

    public EntryTime(){
        this.datetime = new Date(System.currentTimeMillis());
    }
    public EntryTime(Date date){
        this.datetime = date;
    }
}
