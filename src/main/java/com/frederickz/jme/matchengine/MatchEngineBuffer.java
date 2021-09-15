package com.frederickz.jme.matchengine;

import com.frederickz.jme.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Queue;

public class MatchEngineBuffer  {

    private OrdersDeque buyOrders;
    private OrdersDeque sellOrders;

    private static MatchEngineBuffer matchEngineBuffer;

    private MatchEngineBuffer() {}

    public MatchEngineBuffer getMatchEngineBuffer() {
        if (matchEngineBuffer == null) {
            matchEngineBuffer = new MatchEngineBuffer();
            buyOrders = new OrdersDeque(true);
            sellOrders = new OrdersDeque(false);
        }
        return matchEngineBuffer;
    }

    public void bufferOrder(Order order) {
        OrdersDeque ordersDeque = order.getBuy() ? buyOrders : sellOrders;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class OrdersDeque extends ArrayDeque<OrderQueue> implements Deque<OrderQueue> {

//        static Comparator<OrderQueue> comparator;

        boolean buy;

//        OrdersDeque(boolean buy) {
//            this.buy = buy;
////            comparator = (oq1, oq2) -> {
////                if (oq1.getPrice() == oq2.getPrice()) {
////                    return 0;
////                }
////                int buyOffset = buy ? 1 : -1;
////                return buyOffset * oq1.getPrice() > buyOffset * oq2.getPrice() ? -1 : 1;
////            };
//        }

        float getFirstPrice() {
            OrderQueue firstOrdersQueue = this.peekFirst();
            return firstOrdersQueue == null ? -1.0f : firstOrdersQueue.getPrice();
        }

        float getLastPrice() {
            OrderQueue lastOrdersQueue = this.peekLast();
            return lastOrdersQueue == null ? -1.0f : lastOrdersQueue.getPrice();
        }

        @Override
        public boolean offerFirst(OrderQueue orderQueue) {
            if (!this.isEmpty()) {
                int buyOffset = buy ? 1 : -1;
                float firstPrice = this.getFirstPrice();
                while (buyOffset * orderQueue.getPrice() > buyOffset * (firstPrice + buyOffset * 0.01)) {
                    firstPrice += buyOffset * 0.01;
                    super.offerFirst(new OrderQueue(firstPrice));
                };
            }
            return super.offerFirst(orderQueue);
        }

        @Override
        public boolean offerLast(OrderQueue orderQueue) {
            if (this.isEmpty()) {
                int buyOffset = buy ? -1 : 1;
                float lastPrice = this.getLastPrice();
                while (buyOffset * orderQueue.getPrice() > buyOffset * (lastPrice + buyOffset * 0.01)) {
                    lastPrice += buyOffset * 0.01;
                    super.offerLast(new OrderQueue(lastPrice));
                };
            }
            return super.offerLast(orderQueue);
        }

        @Override
        public OrderQueue pollFirst() {
            return super.pollFirst();
        }

        @Override
        public OrderQueue pollLast() {
            return super.pollLast();
        }

    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class OrderQueue extends ArrayDeque<Order> implements Queue<Order> {

        private float price;

    }

}
