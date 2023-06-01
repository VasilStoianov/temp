package com.home.work.entity;

import com.home.work.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application_user")
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser extends BaseEntity{

    private String name;

    private String username;

    private String password;

    private LocalDateTime lastConnectionDate;

    @Enumerated(EnumType.STRING)
    private UserRoles userType;

    @OneToMany(mappedBy = "user")
    private List<Item> basket = new ArrayList<>();
}
