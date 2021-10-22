package com.repository;

import com.dao.Hotel;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long>{

}
