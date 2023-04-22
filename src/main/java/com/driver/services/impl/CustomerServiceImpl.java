package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Customer customer=customerRepository2.findById(customerId).get();
		customerRepository2.deleteByMobile(customer.getMobile());

	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query

		List<Driver> driverList=driverRepository2.findAll();
		int min=Integer.MAX_VALUE;
		for (Driver free:driverList)
		{
			if (free.getCab().getAvailable() && free.getDriverId()<min)
			{
				min=free.getDriverId();
			}
		}
		if (min==Integer.MAX_VALUE)
		{
			throw new Exception("No cab available!");
		}


		Driver driver=driverRepository2.findById(min).get();
		driver.getCab().setAvailable(false);
		Customer customer=customerRepository2.findById(customerId).get();

		TripBooking tripBooking=new TripBooking();
		tripBooking.setFromLocation(fromLocation);
		tripBooking.setToLocation(toLocation);
		tripBooking.setDistanceInKm(distanceInKm);
		tripBooking.setStatus(TripStatus.CONFIRMED);
		tripBooking.setBill(driver.getCab().getPerKmRate()*distanceInKm);
		tripBooking.setDriver(driver);
		tripBooking.setCustomer(customer);
		driver.getTripBookingList().add(tripBooking);
		customer.getTripBookings().add(tripBooking);
		tripBookingRepository2.save(tripBooking);

		return tripBooking;

	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking=tripBookingRepository2.findById(tripId).get();
		tripBooking.getDriver().getCab().setAvailable(true);
		tripBooking.setStatus(TripStatus.CANCELED);

		tripBookingRepository2.save(tripBooking);

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking trip=tripBookingRepository2.findById(tripId).get();
		trip.getDriver().getCab().setAvailable(true);
		trip.setStatus(TripStatus.COMPLETED);
		tripBookingRepository2.save(trip);

	}
}
