package com.frederickz.jme.component;

import com.frederickz.jme.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MatchEngine {

    private static final float IN_MEMORY_OFFSET = 1.00f;

    private OrdersPriorityQueue buyOrders = new OrdersPriorityQueue(true);
    private OrdersPriorityQueue sellOrders = new OrdersPriorityQueue(false);

    public void distribute(Order order) {
        OrdersPriorityQueue ordersPriorityQueue = order.isBuyOder() ? buyOrders : sellOrders;
        ordersPriorityQueue.offer(order);
        buyOrders.display();
        sellOrders.display();
    }

    private static class OrdersPriorityQueue extends PriorityQueue<OrderQueue> {

        @Getter
        boolean buy;
        Map<Float, OrderQueue> orderQueueMap;

        OrdersPriorityQueue(boolean buy) {
            super((oq1, oq2) -> {
                if (oq1.getPrice() == oq2.getPrice()) {
                    return 0;
                }
                int buyOffset = buy ? -1 : 1;
                return oq1.getPrice() > oq2.getPrice() ? buyOffset : -buyOffset;
            });
            this.buy = buy;
            this.orderQueueMap = new HashMap<>();
        }

        boolean offer(Order order) {
            float price = order.getPrice();
            OrderQueue orderQueue = orderQueueMap.getOrDefault(price, new OrderQueue(price));
            if (!orderQueue.offer(order)) {
                return false;
            }
            if (!orderQueueMap.containsKey(price)) {
                if (!this.offer(orderQueue)) {
                    return false;
                }
                orderQueueMap.put(price, orderQueue);
            }

            return true;
        }

        void display() {
            System.out.printf("---------------- %s orders ----------------%n", buy ? "buy" : "sell");
            for (float price : orderQueueMap.keySet()) {
                System.out.println("price: " + price);
                for (Order order : orderQueueMap.get(price)) {
                    System.out.println(order.toString());
                }
            }
            System.out.println("-------------------------------------------");
        }
    }

    @AllArgsConstructor
    private static class OrderQueue extends ArrayDeque<Order> implements Queue<Order> {

        @Getter private float price;

    }

}
