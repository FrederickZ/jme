package com.frederickz.jme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class MatchEngine {

    private static final float IN_MEMORY_OFFSET = 1.00f;

    @Getter private OrdersPriorityQueue buyOrders;
    @Getter private OrdersPriorityQueue sellOrders;

    private MatchEngine matchEngine;

    private MatchEngine() {};

    public MatchEngine getMatchEngine() {
        if (matchEngine == null) {
            matchEngine = new MatchEngine();
            buyOrders = new OrdersPriorityQueue(true);
            sellOrders = new OrdersPriorityQueue(false);
        }
        return matchEngine;
    }

    private static class OrdersPriorityQueue extends PriorityQueue<OrderQueue> {

        @Getter boolean buy;

        OrdersPriorityQueue(boolean buy) {
            super((Comparator<OrderQueue>) (oq1, oq2) -> {
                if (oq1.getPrice() == oq2.getPrice()) {
                    return 0;
                }
                int buyOffset = buy ? -1 : 1;
                return oq1.getPrice() > oq2.getPrice() ? buyOffset : -buyOffset;
            });
            this.buy = buy;
        }

    }

    @AllArgsConstructor
    private static class OrderQueue extends ArrayDeque<Order> implements Queue<Order> {

        @Getter private float price;

    }

}
