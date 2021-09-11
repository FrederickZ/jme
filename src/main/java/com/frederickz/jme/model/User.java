package com.frederickz.jme.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper=true)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @ToString.Exclude
    private Set<Order> orders = new HashSet<>();

}
