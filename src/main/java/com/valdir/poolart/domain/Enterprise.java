package com.valdir.poolart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "ENTERPRISE")
public class Enterprise extends User{
    private static final long serialVersionUID = 1L;

    private String about;

    @Column(unique = true)
    private String cnpj;

    public Enterprise(Integer id, String name, String phone, String email, String password, String about, String cnpj) {
        super(id, name, phone, email, password);
        this.about = about;
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Enterprise that = (Enterprise) o;

        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 810565874;
    }
}
