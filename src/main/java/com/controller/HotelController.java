package com.controller;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import com.dao.Hotel;
import com.dto.HotelDto;
import com.repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
	private HotelRepository hotelRepository;

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateHotelResource(@PathVariable Integer id, @Valid @RequestBody HotelDto dto){
        try{
            Hotel dao = new Hotel();
            dao.setId(id);
            dao.setName(dto.getName());
            dao.setDescription(dto.getDescription());
            dao.setCity(dto.getCity());
            dao.setRatings(dto.getRatings());
            hotelRepository.save(dao);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHotels(@RequestParam(value = "page") Integer page, @RequestParam(value = "size") Integer size){
        List<Hotel> list = new ArrayList<>();
        try{
            Pageable paging = PageRequest.of(page, size);
            Page<Hotel> pagedResult = hotelRepository.findAll(paging);
            if(pagedResult.hasContent()){
                list = pagedResult.getContent();
            }
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}