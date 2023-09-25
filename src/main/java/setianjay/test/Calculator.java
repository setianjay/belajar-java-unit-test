package setianjay.test;

public class Calculator {

    public int add(int value1, int value2){
        return value1 + value2;
    }

    public double divide(int value1, int value2){
        if(value2 == 0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }else{
            return (double) value1 / value2;
        }
    }
}
