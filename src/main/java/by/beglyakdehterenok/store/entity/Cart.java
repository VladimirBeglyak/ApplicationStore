package by.beglyakdehterenok.store.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Order> orders=new ArrayList<>();

    private Double totalSum;

}
