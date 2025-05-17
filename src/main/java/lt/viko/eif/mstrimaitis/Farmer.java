package lt.viko.eif.mstrimaitis;

import java.util.Objects;

public class Farmer {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;

    public Farmer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Farmer{id=" + id + ", name='" + name + "', address='" + address + "', phone='" + phone + "', email='" + email + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmer farmer = (Farmer) o;
        return id == farmer.id && Objects.equals(name, farmer.name) && Objects.equals(address, farmer.address) && Objects.equals(phone, farmer.phone) && Objects.equals(email, farmer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, email);
    }
}