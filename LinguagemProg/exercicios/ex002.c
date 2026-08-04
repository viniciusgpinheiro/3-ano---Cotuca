// Fazer um programa para receber 3 valores inteiros do usuário e mostrar a sua
// média (que pode não ser inteira).

#include <stdio.h>

int main() {
    int valores[3];
    int temp;

    for (int i = 0; i < 3; i++) {
        printf("Digite o %dº valor: ", i+1);
        scanf("%d", &temp);
        valores[i] = temp;
    }

    int soma;
    for (int i = 0; i < 3; i++) {
        soma += valores[i];
    }
    double media = soma / 3;

    printf("Média: %f", media);
    return 0;
}