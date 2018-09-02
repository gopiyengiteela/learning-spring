package com.gopi.learning.data.repository;

import com.gopi.learning.data.entity.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Long> {

    Guest findById(long guestId);
}
