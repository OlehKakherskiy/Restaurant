package com.springapp.orm.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;

/**
 * Created by oleg on 28.11.15.
 */
@Entity
public class Order implements Serializable {

    @OneToMany
    private User customer;

    private User waiter;

    private short dishCount;
    //TODO: annotate, add set of Order positions, Add class OrderPosition
}
