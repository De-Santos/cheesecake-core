package com.user.sevice.userservice.entities;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "wishList")
public class WishList {
    @Id
    private Long id;

    @ElementCollection
    private List<String> productIds;

}
