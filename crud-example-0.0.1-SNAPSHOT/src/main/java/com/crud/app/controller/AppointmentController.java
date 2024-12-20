package com.crud.app.controller;


//import com.crud.app.model.Admin;
import com.crud.app.model.Appointment;
import com.crud.app.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("appointment")
    public List<Appointment> list() {
        try {
            System.out.println("Fetching appointments...");
            List<Appointment> appointments = appointmentService.listAll();
            System.out.println("Appointments fetched: " + appointments);
            return appointments;
        } catch (Exception e) {
            System.out.println("Error fetching appointments: " + e.getMessage());
            throw e;  // Re-throw to let the GlobalExceptionHandler handle it
        }
    }


    @GetMapping("appointment/{id}")
    public ResponseEntity<Appointment> get(@PathVariable Integer id){
        try{
            Appointment appointment  = appointmentService.get(id);
            return new ResponseEntity<Appointment>(appointment, HttpStatus.OK);

        } catch (NoSuchElementException e){
            return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("newappointment")
    public ResponseEntity<Appointment> add(@RequestBody Appointment appointment){
        appointmentService.save(appointment);
        return new ResponseEntity<Appointment>(appointment,HttpStatus.CREATED);
    }


    @PutMapping("appointment")
    public ResponseEntity<?> update(@RequestBody Appointment appointment) {
        try {
            appointmentService.update(appointment); // Assuming this method updates the appointment
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("appointment/{id}")
    public void delete(@PathVariable Integer id) {
        appointmentService.delete(id);
    }

}
