package com.bestcommerce.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String description;

    @Column
    private Integer inventory;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private Double price;

    @Column
    private Boolean delivery;

    @Column
    private Integer discount;

    @Column
    private LocalDate discount_start;

    @Column
    private LocalDate discount_end;

    @Transient
    private Double discountedPrice;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_rollout", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"))
    private Set<Country> rollout_countries;

    public Set<Country> getRollout_countries() {
        return rollout_countries;
    }

    public void setRollout_countries(Set<Country> rollout_countries) {
        this.rollout_countries = rollout_countries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public LocalDate getDiscount_start() {
        return discount_start;
    }

    public void setDiscount_start(LocalDate discount_start) {
        this.discount_start = discount_start;
    }

    public LocalDate getDiscount_end() {
        return discount_end;
    }

    public void setDiscount_end(LocalDate discount_end) {
        this.discount_end = discount_end;
    }

    public Double getDiscountedPrice() {

        LocalDate startDate = this.getDiscount_start();
        LocalDate endDate = this.getDiscount_end();
        Integer discount = this.getDiscount();
        Double discountedPrice = this.getPrice();
        Double originalPrice = this.getPrice();



        try {
            if(LocalDate.now().isAfter(startDate)&LocalDate.now().isBefore(endDate)){
                Double percentage = discount/originalPrice;
                discountedPrice = originalPrice - (originalPrice * percentage);

                System.out.println(discountedPrice);
                return discountedPrice;
            }
        }
        catch (Exception ex){
            discountedPrice = this.getPrice();
            return discountedPrice;
        }

        return discountedPrice;
    }



}
