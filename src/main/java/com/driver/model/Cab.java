package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "cab")
public class Cab
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    private int perKmRate;
    private boolean available;
    public Cab() {}

  public int getId() {
    return Id;
  }

  public void setId(int id) {
    Id = id;
  }

  public int getPerKmRate() {
    return perKmRate;
  }

  public void setPerKmRate(int perKmRate) {
    this.perKmRate = perKmRate;
  }

  public boolean getAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  public Cab(int id, int perKmRate, boolean available) {
    Id = id;
    this.perKmRate = perKmRate;
    this.available = available;
  }

  @OneToOne
  @JoinColumn
  Driver driver;
}