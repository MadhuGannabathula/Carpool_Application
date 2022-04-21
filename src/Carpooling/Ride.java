package Carpooling;

import java.util.ArrayList;

public class Ride {
    private String source;
    private String destination;
    private int fare;
    private int id;
    private int createrID;

    public Ride(String source,String destination,int fare,int id,int createrID){
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.id = id;
        this.createrID = createrID;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", fare=" + fare +
                ", id=" + id +
                '}';
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getId() {
        return id;
    }

    public int getFare() {
        return fare;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreaterID() {
        return createrID;
    }

    public void setCreaterID(int createrID) {
        this.createrID = createrID;
    }
}
