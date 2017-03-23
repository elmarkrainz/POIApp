package at.fhj.mobappdev.poiapp.db;

/**
 * Simple base Object for a Point-Of-Interest
 *
 * @author EKrainz
 */

public class PoiObject {

    private long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;

    public PoiObject(){
    }

    public PoiObject(long id, String name, String address, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PoiObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public String toShortString() {
        return  address + ',' +"lat.=" + latitude + "/ long." + longitude;

    }
}
