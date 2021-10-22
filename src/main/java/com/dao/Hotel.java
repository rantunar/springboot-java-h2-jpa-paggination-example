package com.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {

	@Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String city;
    @Column
    private Integer ratings;
	
}
