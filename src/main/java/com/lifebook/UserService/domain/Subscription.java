package com.lifebook.UserService.domain;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity(name = "Subscriptions")
@Table(name = "Subscriptions")
public class Subscription {

    @Id
    @Column(name = "group_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="pg-uuid")
    private UUID id;

    private String key;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public String getKey() {
        return key;
    }

    public Subscription(String key, User user) {
        this.key = key;
        this.user = user;
    }
}
