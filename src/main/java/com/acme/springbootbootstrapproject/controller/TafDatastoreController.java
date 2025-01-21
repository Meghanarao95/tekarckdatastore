package com.acme.springbootbootstrapproject.controller;

import com.acme.springbootbootstrapproject.entity.Bookings;
import com.acme.springbootbootstrapproject.entity.Flights;
import com.acme.springbootbootstrapproject.entity.Users;
import com.acme.springbootbootstrapproject.services.TafDatastoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datastore")
public class TafDatastoreController {

    @Autowired
    private TafDatastoreService datastoreService;

    // Endpoints for Users
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(datastoreService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = datastoreService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = datastoreService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody Users updatedUser) {
        Users createdUser = datastoreService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        datastoreService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints for Flights
    @GetMapping("/flights")
    public ResponseEntity<List<Flights>> getAllFlights() {
        return ResponseEntity.ok(datastoreService.getAllFlights());
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flights> getFlightById(@PathVariable Long id) {
        Optional<Flights> flight = datastoreService.getFlightById(id);
        return flight.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/flights")
    public ResponseEntity<Flights> createFlight(@RequestBody Flights flight) {
        Flights createdFlight = datastoreService.saveFlight(flight);
        return ResponseEntity.ok(createdFlight);
    }

    @PutMapping("/flights/{flightId}")
    public ResponseEntity<Flights> updateFlight(@PathVariable Long flightId, @RequestBody Flights updatedFlight) {
        try {
            Flights flight = datastoreService.updateFlight(flightId, updatedFlight);
            return ResponseEntity.ok(flight);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        datastoreService.deleteFlightById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints for Bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<Bookings>> getAllBookings() {
        return ResponseEntity.ok(datastoreService.getAllBookings());
    }

    @PostMapping("/bookings/{userId}/{flightId}")
    public ResponseEntity<Bookings> createBooking(@PathVariable Long userId, @PathVariable Long flightId) {
        Bookings createdBooking = datastoreService.saveBooking(userId, flightId);
        return ResponseEntity.ok(createdBooking);
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Bookings> getBookingById(@PathVariable Long id) {
        Optional<Bookings> booking = datastoreService.getBookingById(id);
        return booking.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve all bookings for a specific user
    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<List<Bookings>> getBookingsByUserId(@PathVariable Long userId) {
        List<Bookings> bookings = datastoreService.getBookingsByUserId(userId);
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Bookings> deleteBooking(@PathVariable Long id) {
        Bookings booking = datastoreService.deleteBookingById(id);
        return ResponseEntity.ok(booking);
    }


}
