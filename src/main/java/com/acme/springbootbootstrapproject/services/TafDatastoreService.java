package com.acme.springbootbootstrapproject.services;

import com.acme.springbootbootstrapproject.entity.Bookings;
import com.acme.springbootbootstrapproject.entity.Flights;
import com.acme.springbootbootstrapproject.entity.Users;
import com.acme.springbootbootstrapproject.repository.BookingsRepository;
import com.acme.springbootbootstrapproject.repository.FlightsRepository;
import com.acme.springbootbootstrapproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TafDatastoreService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private FlightsRepository flightsRepository;

    @Autowired
    private BookingsRepository bookingsRepository;

    // CRUD for Users
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    // Update user details
    public Users updateUser(Long userId, Users updatedUser) {
        return usersRepository.findById(userId).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            return usersRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }

    // CRUD for Flights
    public List<Flights> getAllFlights() {
        return flightsRepository.findAll();
    }

    public Optional<Flights> getFlightById(Long id) {
        return flightsRepository.findById(id);
    }

    public Flights saveFlight(Flights flight) {
        return flightsRepository.save(flight);
    }

    public void deleteFlightById(Long id) {
        flightsRepository.deleteById(id);
    }

    // CRUD for Bookings
    public List<Bookings> getAllBookings() {
        return bookingsRepository.findAll();
    }

    public Optional<Bookings> getBookingById(Long id) {
        return bookingsRepository.findById(id);
    }

    public Bookings saveBooking(Long userId, Long flightId) {
        Optional<Flights> flightOpt = flightsRepository.findById(flightId);
        Optional<Users> userOpt = usersRepository.findById(userId);

        if (flightOpt.isEmpty() || userOpt.isEmpty()) {
            throw new RuntimeException("User or Flight not found");
        }

        Flights flight = flightOpt.get();
        if (flight.getAvailableSeats() <= 0) {
            throw new RuntimeException("No available seats on this flight");
        }

        // Reduce available seats
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightsRepository.save(flight);

        // Create the booking
        Bookings booking = new Bookings();
        booking.setUser(userOpt.get());
        booking.setFlight(flight);
        booking.setStatus("Booked");
        return bookingsRepository.save(booking);
    }

    public Bookings deleteBookingById(Long bookingId) {
        return bookingsRepository.findById(bookingId).map(booking -> {
            booking.setStatus("Cancelled");
            return bookingsRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // Retrieve all bookings for a specific user
    public List<Bookings> getBookingsByUserId(Long userId) {
        return bookingsRepository.findByUserId(userId);
    }

    // Update flight details
    public Flights updateFlight(Long flightId, Flights updatedFlight) {
        return flightsRepository.findById(flightId).map(flight -> {
            flight.setFlightNumber(updatedFlight.getFlightNumber());
            flight.setDeparture(updatedFlight.getDeparture());
            flight.setArrival(updatedFlight.getArrival());
            flight.setDepartureTime(updatedFlight.getDepartureTime());
            flight.setArrivalTime(updatedFlight.getArrivalTime());
            flight.setPrice(updatedFlight.getPrice());
            flight.setAvailableSeats(updatedFlight.getAvailableSeats());
            return flightsRepository.save(flight);
        }).orElseThrow(() -> new RuntimeException("Flight not found"));
    }
}
