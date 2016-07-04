package com.veontomo;

import java.util.HashMap;

/**
 * A brief summary of actions' outcome.
 */
public interface Summary {
    HashMap<String, Integer> getSummary();

    void store(String key);

    void merge(Summary execute);

    String toString();
}
