package ua.com.alevel.service;

import java.math.BigInteger;

public interface CalculatorService {
    BigInteger addition(BigInteger a, BigInteger b);
    BigInteger multiplication(BigInteger a, BigInteger b);
    BigInteger subtraction(BigInteger a, BigInteger b);
    BigInteger division(BigInteger a, BigInteger b);
    BigInteger exponentiation(BigInteger a);
}
