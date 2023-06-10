package com.alex.practice.CRUDpractice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "second_entrydate")
public class EntryTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "entry_date")
    private Timestamp datetime;

    public EntryTime(){
        this.datetime = new Timestamp(System.currentTimeMillis());
    }
    public EntryTime(Timestamp date){
        this.datetime = date;
    }
}
