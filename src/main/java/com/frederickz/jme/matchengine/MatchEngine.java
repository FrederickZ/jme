package com.frederickz.jme.matchengine;

import com.frederickz.jme.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

public class MatchEngine {

    private MatchEngine matchEngine;

    private MatchEngine() {};

    public MatchEngine getMatchEngine() {
        if (matchEngine == null) {
            matchEngine = new MatchEngine();
        }
        return matchEngine;
    }



}
