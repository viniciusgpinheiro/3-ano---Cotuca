#include <stdio.h>
#include <ctype.h> 
#include <string.h>

// 1. Definição dos Enums (Classes dos Tokens)
typedef enum {
    programa, rotulo, tipo, variavel, procedimento, funcao, inicio, fim, 
    atribuicao, se, entao, senao, enquanto, faca, vapara, mais, menos, 
    vezes, dividir, igual, diferente, menor, menorigual, maior, maiorigual, 
    e, ou, nao, abreparenteses, fechaparenteses, abrecolchetes, fechacolchetes, 
    virgula, ponto, pontoevirgula, doispontos, numero, identificador
} Token;

// 2. Dicionário de strings para comparação
char *palavras[] = {
    "program", "label", "type", "var", "procedure", "function", "begin", "end",
    ":=", "if", "then", "else", "while", "do", "goto", "+", "-", "*", "div",
    "=", "<>", "<", "<=", ">", ">=", "and", "or", "not", "(", ")", "[", "]",
    ",", ".", ";", ":"
};

// 3. Mapeamento das strings para os Enums (deve seguir a mesma ordem acima)
Token tokens[] = {
    programa, rotulo, tipo, variavel, procedimento, funcao, inicio, fim, 
    atribuicao, se, entao, senao, enquanto, faca, vapara, mais, menos, 
    vezes, dividir, igual, diferente, menor, menorigual, maior, maiorigual, 
    e, ou, nao, abreparenteses, fechaparenteses, abrecolchetes, fechacolchetes, 
    virgula, ponto, pontoevirgula, doispontos
};

// Função para classificar o que foi lido
int AnalisadorLexico(char palavra[50]) {
    if (strlen(palavra) == 0) return -1;

    int totalPalavras = sizeof(palavras) / sizeof(palavras[0]);
    
    for (int i = 0; i < totalPalavras; i++) {
        if (strcmp(palavra, palavras[i]) == 0) {
            printf("<%s, %d> ", palavra, tokens[i]);
            return tokens[i];
        }
    }

    if (isdigit(palavra[0])) {
        printf("<%s, %d> ", palavra, tokens[totalPalavras-2]);
        return(tokens[totalPalavras-2]);
    } else {
        printf("<%s, %d> ", palavra, tokens[totalPalavras-1]);
        return (tokens[totalPalavras-1]);
    }
}

int main() {
    FILE *arq = fopen("arquivo.txt", "r");
    if (arq == NULL) {
        perror("Erro ao abrir arquivo.txt");
        return 1;
    }

    char buffer[50];
    int cont = 0;
    int ch;

    while ((ch = fgetc(arq)) != EOF) {
        // Se for letra ou número, acumula no buffer
        if (isalnum(ch)) {
            buffer[cont++] = (char)ch;
        } 
        else {
            // Se encontrou algo que não é letra/número, processa o buffer acumulado
            if (cont > 0) {
                buffer[cont] = '\0';
                AnalisadorLexico(buffer);
                cont = 0;
            }

            // Se não for espaço, trata como símbolo
            if (!isspace(ch)) {
                char simbolo[3];
                simbolo[0] = (char)ch;
                simbolo[1] = '\0';

                // Lógica para símbolos compostos (Lookahead)
                if (ch == ':') {
                    int proximo = fgetc(arq);
                    if (proximo == '=') {
                        strcpy(simbolo, ":=");
                    } else {
                        ungetc(proximo, arq);
                    }
                } 
                else if (ch == '<') {
                    int proximo = fgetc(arq);
                    if (proximo == '=') strcpy(simbolo, "<=");
                    else if (proximo == '>') strcpy(simbolo, "<>");
                    else ungetc(proximo, arq);
                } 
                else if (ch == '>') {
                    int proximo = fgetc(arq);
                    if (proximo == '=') strcpy(simbolo, ">=");
                    else ungetc(proximo, arq);
                }                

                AnalisadorLexico(simbolo);
            }
        }
    }

    // Processa última palavra caso o arquivo não termine com espaço/símbolo
    if (cont > 0) {
        buffer[cont] = '\0';
        AnalisadorLexico(buffer);
    }

    printf("\nLeitura concluida.\n");
    fclose(arq);
    return 0;
}