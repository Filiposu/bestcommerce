package com.bestcommerce.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "merchants",uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id")
})
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String merchant_name;

    @Column(name = "owner_name")
    private String owner_name;

    @Column
    private String address;

    @Column
    private String phone_number;

    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchant merchant = (Merchant) o;
        return Objects.equals(id, merchant.id) &&
                Objects.equals(merchant_name, merchant.merchant_name) &&
                Objects.equals(owner_name, merchant.owner_name) &&
                Objects.equals(address, merchant.address) &&
                Objects.equals(phone_number, merchant.phone_number) &&
                Objects.equals(user, merchant.user);
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", merchant_name='" + merchant_name + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", user=" + user +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchant_name, owner_name, address, phone_number, user);
    }
}
