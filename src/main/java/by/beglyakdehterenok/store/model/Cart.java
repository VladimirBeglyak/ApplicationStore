package by.beglyakdehterenok.store.model;

import by.beglyakdehterenok.store.entity.Order;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Order> orders=new ArrayList<>();

    private Double totalSum;

}
