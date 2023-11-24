package com.cogging.cogging.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "scrap")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scrap  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;
}
