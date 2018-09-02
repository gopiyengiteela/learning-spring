package com.gopi.learning.data.webservice;

import com.gopi.learning.data.entity.Room;
import com.gopi.learning.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomRepository roomRepo;

    @RequestMapping(value="/rooms", method = RequestMethod.GET)
    List<Room> findAll(@RequestParam(required=false)String roomNumber){
        List<Room> rooms=new ArrayList<>();
        if(null==roomNumber){
            Iterable<Room> results=this.roomRepo.findAll();
            results.forEach(room -> {rooms.add(room);});
        }else{
            Room room=this.roomRepo.findByNumber(roomNumber);
            if(null!=room){
                rooms.add(room);
            }
        }
        return rooms;
    }
}
