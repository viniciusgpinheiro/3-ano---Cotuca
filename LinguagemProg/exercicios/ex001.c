// Fazer um programa para receber um número inteiro de segundos do usuário e
// imprimir a quantidade correspondente em horas, minutos e segundos.

#include <stdio.h>

int main() {
    int tempoSegundos;

    printf("Digite um número em segundos: ");
    scanf("%d", &tempoSegundos);

    int tempoMinutos = tempoSegundos / 60;
    tempoSegundos = tempoSegundos % 60;
    
    int tempoHoras = tempoMinutos / 60;
    tempoMinutos = tempoMinutos % 60;

    printf("\nHoras: %d\nMinutos: %d\nSegundos: %d\n", tempoHoras, tempoMinutos, tempoSegundos);
    return 0;
}