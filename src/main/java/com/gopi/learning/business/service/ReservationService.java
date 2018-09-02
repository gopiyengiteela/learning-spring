package com.gopi.learning.business.service;

import com.gopi.learning.business.domain.RoomReservation;
import com.gopi.learning.data.entity.Guest;
import com.gopi.learning.data.entity.Reservation;
import com.gopi.learning.data.entity.Room;
import com.gopi.learning.data.repository.GuestRepository;
import com.gopi.learning.data.repository.ReservationRepository;
import com.gopi.learning.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private RoomRepository roomRepo;
    private GuestRepository guestRepo;
    private ReservationRepository reservationRepo;

    @Autowired
    public ReservationService(RoomRepository roomRepo, GuestRepository guestRepo, ReservationRepository reservationRepo) {
        this.roomRepo = roomRepo;
        this.guestRepo = guestRepo;
        this.reservationRepo = reservationRepo;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Room> rooms=this.roomRepo.findAll();
        Map<Long,RoomReservation> roomReservationMap=new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation=new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNum(room.getNumber());
            roomReservationMap.put(room.getId(),roomReservation);
        });
        Iterable<Reservation> reservations=this.reservationRepo.findByDate(new java.sql.Date(date.getTime()));
        if(null!=reservations){
            reservations.forEach(reservation -> {
                Guest guest=this.guestRepo.findById(reservation.getGuestId());
                if(null!=guest){
                    RoomReservation roomReservation=roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());
                }
            });
        }
        List<RoomReservation> roomReservations=new ArrayList<>();
        for(Long roomId:roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));
        }
        return roomReservations;
    }
}
