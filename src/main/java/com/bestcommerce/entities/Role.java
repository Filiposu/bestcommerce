package com.bestcommerce.entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private Role_Enum name;


    public Role(){
    }

    public Role(Role_Enum role) {
        this.name = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role_Enum getName() {
        return name;
    }

    public void setName(Role_Enum name) {
        this.name = name;
    }
}
