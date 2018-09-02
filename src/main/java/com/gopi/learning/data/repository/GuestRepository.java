package com.gopi.learning.data.repository;

import com.gopi.learning.data.entity.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {
    Guest findOne(long id);
}
