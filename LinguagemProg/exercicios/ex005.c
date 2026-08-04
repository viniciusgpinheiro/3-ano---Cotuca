// O número 3025 possui a seguinte característica: 30 + 25 = 55 -&gt; 55*55 = 3025.
// Fazer um programa para obter todos os números de 4 algarismos com a mesma
// característica do número 3025.

#include <stdio.h>

int main() {
    int soma;
    
    for (int i = 1000; i < 10000; i++) {
        soma = i % 100 + i / 100;
        if (soma * soma == i) {
            printf("%d\n", i);
        }
    }
    
    return 0;
}