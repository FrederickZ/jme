package com.frederickz.jme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper=true)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private Boolean buy;

    private Integer share;
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

//    private LocalDateTime created;
//    private Byte status;

}
