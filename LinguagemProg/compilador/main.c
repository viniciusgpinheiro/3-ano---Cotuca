#include <stdio.h>
#include <ctype.h> 
#include <string.h>
#include <stdlib.h>

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
    virgula, ponto, pontoevirgula, doispontos, numero, identificador
};

Token codigo[1000];
int contAnalex = 0;

// Função para classificar o que foi lido
int AnalisadorLexico(char palavra[50]) {
    if (strlen(palavra) == 0) return -1;

    int totalPalavras = sizeof(palavras) / sizeof(palavras[0]);
    for (int i = 0; i < totalPalavras; i++) {
        if (strcmp(palavra, palavras[i]) == 0) {
            codigo[contAnalex++] = tokens[i];
            printf("<%s, %d> ", palavra, tokens[i]);
            return tokens[i];
        }
    }

    if (isdigit(palavra[0])) {
        codigo[contAnalex++] = tokens[totalPalavras];
        printf("<%s, %d> ", palavra, tokens[totalPalavras]);
        return(tokens[totalPalavras]);
    } else {
        codigo[contAnalex++] = tokens[totalPalavras+1];
        printf("<%s, %d> ", palavra, tokens[totalPalavras+1]);
        return(tokens[totalPalavras+1]);
    }
}

int Analex() {
    Token token = codigo[contAnalex++];
    return token;
}

void CompilaBloco() {

}

void CompilaPrograma() {
    Token token = Analex();
    if (token!=programa)
    {
        printf("Esperava-se a palavra PROGRAM!");
        exit(1);
    }
    token = Analex();
    if (token!=identificador)
    {
        printf("Esperava-se um identificador!");
        exit(1);
    }
    token = Analex();
    if (token!=abreparenteses)
    {
        printf("Esperava-se um abre parenteses!");
        exit(1);
    }
    while (token!=fechaparenteses)
    {
        token = Analex();
        if (token!=identificador)
        {
            printf("Esperava-se um identificador!");
            exit(1);
        }
        token = Analex();
        if (token!=virgula && token!=fechaparenteses)
        {
            printf("Esperava-se um virgula ou um fecha parenteses!");
            exit(1);
        }
    }
    token = Analex();
    if (token!=pontoevirgula)
    {
        printf("Esperava-se um ponto e virgula!");
        exit(1);
    }
    CompilaBloco();
    token = Analex();
    if (token!=ponto)
    {
        printf("Esperava-se um ponto final!");
        exit(1);
    }
    
    printf("Programa sintaticamente correto!");
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
            int token = -1;
            // Se encontrou algo que não é letra/número, processa o buffer acumulado
            if (cont > 0) {
                buffer[cont] = '\0';
                token = AnalisadorLexico(buffer);
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
                token = AnalisadorLexico(simbolo);
            }
        }
    }

    // Processa última palavra caso o arquivo não termine com espaço/símbolo
    if (cont > 0) {
        buffer[cont] = '\0';
        AnalisadorLexico(buffer);
    }

    fclose(arq);
    
    contAnalex = 0;
    CompilaPrograma();

    return 0;
}