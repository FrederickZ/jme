package com.frederickz.jme.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = User.class)
public class Order implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private Boolean buy;

    private Integer share;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    private LocalDateTime created;
    private Byte status = 0;

    public boolean isBuyOder() {
        return buy;
    }

    public String toMessage() {
        return id + "|" +
                buy + "|" +
                share + "|" +
                price + "|" +
                user.getId() + "|" +
                created + "|" +
                status;
    }

}
