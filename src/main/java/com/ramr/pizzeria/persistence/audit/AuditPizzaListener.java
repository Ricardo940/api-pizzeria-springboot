package com.ramr.pizzeria.persistence.audit;

import com.ramr.pizzeria.persistence.entiity.Pizza;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private Pizza currentValue;

    @PostLoad
    public void postLoad(Pizza pizza){
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(pizza);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(Pizza pizza){
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + pizza.toString());
    }

    @PreRemove
    public void onPreDelete(Pizza pizza){
        System.out.println(pizza.toString());
    }
}
