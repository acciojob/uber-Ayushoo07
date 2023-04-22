package com.driver.model;

import javax.persistence.*;

@Entity
public class TripBooking
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tripbookingId;

    private String fromLocation;
    private String toLocation;
    private int distanceInKm;

    @Enumerated(EnumType.STRING)
    TripStatus tripStatus;

    private int bill;

  public int getTripbookingId() {
    return tripbookingId;
  }

  public String getFromLocation() {
    return fromLocation;
  }

  public String getToLocation() {
    return toLocation;
  }

  public int getDistanceInKm() {
    return distanceInKm;
  }

  public TripStatus getTripStatus() {
    return tripStatus;
  }

  public int getBill() {
    return bill;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Driver getDriver() {
    return driver;
  }

  public void setBill(int bill) {
    this.bill = bill;
  }

  public void setTripbookingId(int tripbookingId) {
    this.tripbookingId = tripbookingId;
  }

  public void setFromLocation(String fromLocation) {
    this.fromLocation = fromLocation;
  }

  public void setToLocation(String toLocation) {
    this.toLocation = toLocation;
  }

  public void setDistanceInKm(int distanceInKm) {
    this.distanceInKm = distanceInKm;
  }

  public void setTripStatus(TripStatus tripStatus) {
    this.tripStatus = tripStatus;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  @ManyToOne @JoinColumn Customer customer;

    @ManyToOne
    @JoinColumn
    Driver driver;
}