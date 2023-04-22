package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Driver
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;

    private String mobileNo;
    private String password;

    public Driver(int driverId, String mobileNo, String password) {
    this.driverId=driverId;
    this.mobileNo=mobileNo;
    this.password=password;
    }
    public Driver() {}

    public int   getDriverId() {
        return driverId;
    }
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    public String   getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String   getPassword() {
          return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

  public Cab getCab() {
    return cab;
  }

  public List<TripBooking> getTripBookingList() {
    return tripBookingList;
  }

  @OneToOne @JoinColumn Cab cab;

    @OneToMany(mappedBy = "driver",cascade = CascadeType.ALL)
    List<TripBooking> tripBookingList=new ArrayList<>();

}