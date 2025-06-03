# Analisador Léxico e Sintático em Java

Este projeto implementa um analisador léxico e sintático em Java para análise de código fonte simples, reconhecendo palavras-chave, operadores, números, variáveis, strings, comentários, entre outros tokens, além de validar a estrutura sintática do código. É uma etapa fundamental para construção de compiladores ou interpretadores.

---

## Funcionalidades

- Leitura de arquivos `.txt` com código fonte.
- Quebra da linha em lexemas (tokens) respeitando strings entre aspas e ignorando comentários de linha (`//`) e bloco (`/* ... */`).
- Classificação de lexemas em tokens usando uma tabela de símbolos.
- Reconhecimento de:
  - Palavras-chave (ex.: `void`, `int`, `printf`, `if`, `else`, `switch`, `case`, `do`, `while`, `break`, `default`, etc).
  - Operadores e símbolos especiais (ex.: `=`, `;`, `{`, `}`, `[`, `]`, `(`, `)`, `+`, `-`, `*`, `>>`, etc).
  - Números inteiros e floats.
  - Variáveis válidas.
  - Strings entre aspas como token `MENSAGEM`.
  - Formatos de entrada/saída como `%d`, `%f`, `%.2f`.
  - Comando especial `system("cls")`.
- Geração de relatório detalhado da análise léxica com linha, lexema, token e descrição.
- **Análise sintática**: validação da estrutura do código, aceitando comandos de controle, blocos, comandos específicos, declarações de variáveis (inclusive arrays multidimensionais), e comandos compostos.
- **Verificação de balanceamento** de chaves, parênteses, colchetes e aspas, reportando erro se houver algum símbolo não fechado ou fechado em excesso.

---

## Estrutura do Projeto

- `Lexer.java`: classe principal do analisador léxico.
- `Token.java`: constantes inteiras que definem cada tipo de token.
- `TabelaDeSimbolos.java`: estrutura para armazenar tokens e suas descrições.
- `ClassificacaoLexica.java`: classe para armazenar cada lexema classificado com token e linha.
- `RegrasSintaticas.java`: regras para validação sintática das linhas do código.
- `AnalisadorSintatico.java`: classe principal do analisador sintático, responsável por percorrer o código, aplicar as regras e verificar balanceamento.
- `Principal.java`: classe main para execução do analisador.

---

## Como usar

#### 1. Clone o repositório:
   ```bash
   git clone https://github.com/gabryelhgb/AnalisadorSintatico.git
   ```


#### 2. Compile o projeto:

```bash
javac src/*.java
```

#### 3. Execute o analisador informando o arquivo de código fonte:

```bash
java -cp src Principal
```
> O programa abrirá uma janela para você selecionar o arquivo `.txt` a ser analisado.

#### 4. Veja o relatório da análise léxica e sintática no console.

-----

### Exemplo de código de entrada:

```c
int main() {
    int matriz[3][3];
    for (linha=0; linha<3; linha++) {
        for (coluna=0; coluna<3; coluna++) {
            printf("Digite: ");
            scanf("%d", &matriz[linha][coluna]);
        }
    }
    return 1;
}
```


## Detalhes técnicos

- O método `quebrarLinhaEmLexemas` divide a linha de código em lexemas considerando:
    - Comentários de linha e bloco são ignorados.
    - Strings entre aspas são tratadas como um lexema único.
    - Operadores compostos como `>>` são identificados corretamente.
    - Reconhecimento especial para `system("cls")`, `%d`, `%f`, `%.2f`.
- O método `classificarLexema` associa cada lexema a um token baseado em palavras-chave, símbolos, números e variáveis.
- A tabela de símbolos armazena a descrição para cada token para facilitar o relatório.
- O analisador sintático percorre o código linha a linha, aplicando regras para comandos, blocos, declarações, comandos compostos e verifica o balanceamento de símbolos.
- Caso haja erro de balanceamento de chaves, parênteses, colchetes ou aspas, o erro é reportado e contabilizado na quantidade total de erros sintáticos.

## Futuras melhorias

- Suporte para mais tipos de tokens e expressões.
- Melhor tratamento de erros léxicos e sintáticos com mensagens mais detalhadas.
- Interface gráfica para facilitar o uso.

## Licença
Este projeto está sob a licença MIT.