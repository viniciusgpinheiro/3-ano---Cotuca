// Implementar a função doublé POWER (double base, doublé expoente), que
// retorna o valor de base elevado a expoente. 


#include <stdio.h>
#include <math.h>

int ehInteiro(double num) {
    if (num == (int)num) {
        return 1;
    } 
    return 0;
}


double power(double base, double expoente) {
    double b = base;
    double expNum = expoente;
    int expDen = 1;
    double resultado = 1;

    while (ehInteiro(expNum) == 0)
    {
        expNum = expNum * 10;
        expDen *= 10;
    }

    for (int i = 0; i < (int)expNum; i++) {
        resultado = resultado * base;
    }

    return pow(resultado, 1.0/expDen);
}

int main() {
    printf("%f", power(5.0, 5.0/2.0));
    return 0;
}
