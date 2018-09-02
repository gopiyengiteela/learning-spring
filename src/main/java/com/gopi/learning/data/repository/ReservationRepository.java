package com.gopi.learning.data.repository;

import com.gopi.learning.data.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {
    List<Reservation> findByDate(Date date);
}
