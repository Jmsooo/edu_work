package com.lagou.dao;

public class Love {
    public static void main(String[] args) {
        float x, y, a;
        for (y = 1.5f; y > -1.5; y -= 0.1) {
            for (x = -1.5f; x < 1.5; x += 0.05) {
                a = x * x + y * y - 1;
                System.out.print(a * a * a - x * x * y * y * y <= 0.0 ? "*" : " ");
            }
            System.out.println();
        }
    }
}
