package com.joy.joyapi.seller.adapters.out.persistence.jpa.entity;

import com.joy.joycommon.hibernate.annotations.UuidV7Generator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ValueGenerationType;

@Entity
@Getter
@Table(name="seller")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellerEntity {
    @Id
    @UuidV7Generator
    private String id;
    private String username;
    private String password;
    private String name;
    private String gender;
}
