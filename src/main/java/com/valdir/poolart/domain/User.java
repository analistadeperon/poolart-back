package com.valdir.poolart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.valdir.poolart.domain.enums.PersonType;
import com.valdir.poolart.domain.enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "TB_USER")
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String name;

    protected PersonType personType;

    @Column(unique = true)
    protected String Phone;

    @Column(unique = true)
    protected String email;
    protected String password;
    protected Profile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    protected List<Offer> offers = new ArrayList<>();

    public User(Integer id, String name, String phone, String email, String password, Profile profile) {
        this.id = id;
        this.name = name;
        Phone = phone;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }
}