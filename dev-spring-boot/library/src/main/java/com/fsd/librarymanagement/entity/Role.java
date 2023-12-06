package com.fsd.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data // lombok's annotation to generate getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // lombok's annotation to generate a no-argument constructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles") // Specifies a many-to-many relationship with the User entity
    private Set<User> users = new HashSet<>(); // Collection of users associated with this role
}
