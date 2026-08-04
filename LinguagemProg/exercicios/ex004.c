// Fazer um programa que recebe um símbolo de operação do usuário (+, -, / ou *)
// e dois números reais. O programa deve retornar o resultado da operação recebida
// sobre estes dois números.

#include <stdio.h>

int main() {
    int n1, n2;
    char op;

    printf("Digite o 1º número: ");
    scanf("%d", &n1);
    printf("Digite o 2º número: ");
    scanf("%d", &n2);
    printf("Digite a operação (+, -, /, *): ");
    scanf(" %c", &op);

    if (op == '+') {
        printf("%d + %d = %d", n1, n2, (n1 + n2));
    }
    else if (op == '-') {
        printf("%d - %d = %d", n1, n2, (n1 - n2));
    }
    else if (op == '/') {
        printf("%d / %d = %d", n1, n2, (n1 / n2));
    }
    else if (op == '*') {
        printf("%d * %d = %d", n1, n2, (n1 * n2));
    }

    return 0;
}