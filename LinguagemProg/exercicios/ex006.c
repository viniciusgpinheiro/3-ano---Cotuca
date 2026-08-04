// Escrever um programa para ler um número inteiro do usuário e exibir o maior
// número primo que seja menor do que o número digitado.

#include <stdio.h>

int main() {
    int num, maior;

    printf("Digite um número: ");
    scanf("%d", &num);

    for (int i = 2; i < num; i++) {
        int ehPrimo = 1;
        for (int j = 2; j < i; j++) {
            if (i % j == 0) {
                ehPrimo = 0;
            }
        }
        if (ehPrimo == 1) {
            maior = i;
        }
    }

    printf("%d", maior);
    return 0;
}