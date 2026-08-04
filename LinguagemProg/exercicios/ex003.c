// Fazer um programa para ler um número do usuário e determinar se este número
// é par ou não par.

#include <stdio.h>

int main() {
    int numero;
    printf("Digite um número: ");
    scanf("%d", &numero);

    if (numero % 2 == 0) {
        printf("par");
    } else {
        printf("impar");
    }

    return 0;
}
