package com.veontomo.requests;

import java.util.HashMap;

/**
 * A brief summary of actions' outcome.
 */
interface Summary {
    HashMap<String, Integer> getSummary();

    void store(String key);

    void merge(Summary execute);

    String toString();
}
