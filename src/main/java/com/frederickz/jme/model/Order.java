package com.frederickz.jme.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private Boolean buy;

    private Integer share;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime created;
    private Byte status;

}
