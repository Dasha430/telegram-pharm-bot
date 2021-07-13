package ua.com.alevel.service.impl;

import ua.com.alevel.service.CalculatorService;

import java.math.BigInteger;

public class Calculator implements CalculatorService {
    @Override
    public BigInteger addition(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger multiplication(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger subtraction(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger division(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            System.out.println("You are dividing by 0. 0 is returned");
            return BigInteger.ZERO;
        }
        return a.divide(b);
    }

    @Override
    public BigInteger exponentiation(BigInteger a) {
        return a.multiply(a);
    }
}
