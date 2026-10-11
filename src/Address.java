import java.util.Objects;

public class Address {
    private int pincode;
    private City city;

    @Override
    public String toString() {
        return "Address{" + "pincode=" + pincode + ", city=" + city + '}';
    }

    public Address(int pincode, City city) {
        this.pincode = pincode;
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return pincode == address.pincode && city == address.city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pincode, city);
    }
}
