package com.veontomo;

import java.util.concurrent.Callable;

abstract class Counterable implements Callable<Summary> {
    public abstract Summary call();
}
