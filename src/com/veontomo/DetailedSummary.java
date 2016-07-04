package com.veontomo;

import java.util.HashMap;

/**
 * A detailed summary of the actions' output.
 * The summary is represented as key-value pairs: the key corresponds to event (an http response code
 * or an exception message) occurred during the action execution, the value - the number of times the
 * event has occurred.
 */
public class DetailedSummary implements Summary {
    private final HashMap<String, Integer> core = new HashMap<>();

    @Override
    public HashMap<String, Integer> getSummary() {
        return core;
    }

    @Override
    public void store(String key) {
        Integer value;
        value = (core.containsKey(key)) ? core.get(key) : 0;
        core.put(key, value + 1);
    }

    @Override
    public void merge(final Summary target) {
        HashMap<String, Integer> core2 = target.getSummary();
        for (String key : core2.keySet()) {
            if (core.containsKey(key)) {
                core.put(key, core.get(key) + core2.get(key));
            } else {
                core.put(key, core2.get(key));
            }
        }
    }

    @Override
    public String toString() {
        String res = "Summary:\n";
        for (String key :
                core.keySet()) {
            res += key + " -> " + String.valueOf(core.get(key)) + " times\n";
        }
        return res;
    }
}
