# Analisador Léxico em Java

Este projeto implementa um analisador léxico (lexer) em Java para análise de código fonte simples, reconhecendo palavras-chave, operadores, números, variáveis, strings, comentários, entre outros tokens. É uma etapa fundamental para construção de compiladores ou interpretadores.

---

## Funcionalidades

- Leitura de arquivos `.txt` com código fonte.
- Quebra da linha em lexemas (tokens) respeitando strings entre aspas e ignorando comentários de linha (`//`) e bloco (`/* ... */`).
- Classificação de lexemas em tokens usando uma tabela de símbolos.
- Reconhecimento de:
  - Palavras-chave (ex.: `void`, `int`, `printf`, `if`, `else`, etc).
  - Operadores e símbolos especiais (ex.: `=`, `;`, `{`, `}`, `+`, `-`, etc).
  - Números inteiros e floats.
  - Variáveis válidas.
  - Strings entre aspas como token `MENSAGEM`.
- Geração de relatório detalhado da análise léxica com linha, lexema, token e descrição.

---

## Estrutura do Projeto

- `Lexer.java`: classe principal do analisador léxico.
- `Token.java`: constantes inteiras que definem cada tipo de token.
- `TabelaDeSimbolos.java`: estrutura para armazenar tokens e suas descrições.
- `ClassificacaoLexica.java`: classe para armazenar cada lexema classificado com token e linha.
- `Principal.java`: classe main para execução do analisador.

---

## Como usar

#### 1. Clone o repositório:
   ```bash
   git clone https://github.com/gabryelhgb/AnalisadorSintatico.git
   ```


#### 2. Compile o projeto:

```bash
javac *.java
```

#### 3. Execute o analisador informando o arquivo de código fonte:

```bash
java Lexer caminho/para/arquivo.txt
```
#### 4. Veja o relatório da análise léxica no console.
-----

### Exemplo de código de entrada:

```c
void main() {
    printf("Olá, mundo!\n");
    int x = 10;
    if (x > 5) {
        printf("x é maior que 5");
    }
}
```


## Detalhes técnicos

- O método quebrarLinhaEmLexemas divide a linha de código em lexemas considerando:
    - Comentários de linha e bloco são ignorados.
    - Strings entre aspas são tratadas como um lexema único.
    - Operadores compostos como `>>` são identificados corretamente.
- O método classificarLexema associa cada lexema a um token baseado em palavras-chave, símbolos, números e variáveis.
- A tabela de símbolos armazena a descrição para cada token para facilitar o relatório.


## Futuras melhorias
- Análise sintática para validar a estrutura do código.

- Suporte para mais tipos de tokens e expressões.

- Melhor tratamento de erros léxicos com mensagens mais claras.

- Interface gráfica para facilitar o uso.

## Licença
Este projeto está sob a licença MIT.