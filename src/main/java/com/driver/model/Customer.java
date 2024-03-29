package com.driver.model;
import javax.persistence.*;import java.util.ArrayList;import java.util.List;

@Entity
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    private String password;
    private String mobile;

  public Customer(int customerId, String password, String mobile) {
    this.customerId = customerId;
    this.password = password;
    this.mobile = mobile;
  }
  public Customer() {}

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public List<TripBooking> getTripBookings() {
    return tripBookings;
  }

  public void setTripBookings(List<TripBooking> tripBookings) {
    this.tripBookings = tripBookings;
  }

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  List<TripBooking> tripBookings = new ArrayList<>();
}
