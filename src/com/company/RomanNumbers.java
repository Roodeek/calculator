package com.company;

enum RomanNumbers {
    C(100), XC(90), L(50), XL(40),
    X(10), IX(9), V(5), IV(4), I(1);
    private final int arabic;

    int getArabic() {
        return arabic;
    }

    RomanNumbers(int arabic) {
        this.arabic = arabic;
    }
}
