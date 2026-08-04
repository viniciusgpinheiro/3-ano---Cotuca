// Implementar a função INVERTE que recebe um número unsigned int como
// parâmetro e retorna este número escrito ao contrário. Ex: 431 &lt;-&gt; 134.

#include <stdio.h>
#include <math.h>

int inverte(int numero) {
    int num = numero;
    int numeroInvertido = 0;
    int alg = ((int)log10(num)) + 1;

    for (int i=alg; i > 0; i--) {
        int temp = (num % 10);
        num = num / 10;
        numeroInvertido += (temp * (pow(10, i)));
    }

    return numeroInvertido/10;
}


int main() {
    printf("%d", inverte(8673457));
    return 0;
}