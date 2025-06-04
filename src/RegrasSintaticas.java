import java.util.ArrayList;

//define regras sintáticas para analisar estruturas de um programa
public class RegrasSintaticas {

	// Verifica se a estrutura de início é válida
    boolean INICIO(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
    {
        // Aceita tanto VOID MAIN quanto INT MAIN
        int Tamanho = VetorAnaliseLexica.size();
        if (Tamanho < 6) return false;
        if (
            ((VetorAnaliseLexica.get(0).Token == Token.VOID) || (VetorAnaliseLexica.get(0).Token == Token.INT)) &&
            (VetorAnaliseLexica.get(1).Token == Token.MAIN) &&
            (VetorAnaliseLexica.get(2).Token == Token.ABRE_PARENTESES) &&
            (VetorAnaliseLexica.get(3).Token == Token.FECHA_PARENTESES) &&
            (VetorAnaliseLexica.get(4).Token == Token.ABRE_CHAVE)
        ) {
            return true;
        }
        return false;
    }

    // Aceita declaração de variáveis, inclusive arrays e múltiplas variáveis separadas por vírgula
    boolean DECLARACAO_VARIAVEL(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
    {
        if (VetorAnaliseLexica.size() < 3) return false;
        int tipo = VetorAnaliseLexica.get(0).Token;
        if (tipo != Token.INT && tipo != Token.FLOAT && tipo != Token.CHAR) return false;

        int i = 1;
        boolean esperandoVariavel = true;
        while (i < VetorAnaliseLexica.size()) {
            int tk = VetorAnaliseLexica.get(i).Token;
            if (esperandoVariavel) {
                if (tk == Token.VARIAVEL) {
                    i++;
                    // Permite múltiplos pares de colchetes (arrays multidimensionais)
                    while (i + 2 < VetorAnaliseLexica.size() &&
                           VetorAnaliseLexica.get(i).Token == Token.ABRE_COLCHETE &&
                           VetorAnaliseLexica.get(i+1).Token == Token.NUMERO_INTEIRO &&
                           VetorAnaliseLexica.get(i+2).Token == Token.FECHA_COLCHETE) {
                        i += 3;
                    }
                    esperandoVariavel = false;
                } else {
                    return false;
                }
            } else {
                if (tk == Token.VIRGULA) {// próxima variável
                    esperandoVariavel = true;
                    i++;
                } else if (tk == Token.PONTO_VIRGULA) {// fim da declaração
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    // Aceita comandos de atribuição simples e com operações
    boolean COMANDO_ATRIBUICAO(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
    {
        int tamanho = VetorAnaliseLexica.size();
        if (tamanho < 4) return false;
        if (VetorAnaliseLexica.get(0).Token == Token.VARIAVEL &&
            VetorAnaliseLexica.get(1).Token == Token.ATRIBUICAO) {
            // Aceita VARIAVEL = ... ;
            int i = 2;
            // Aceita expressões simples (número, variável, operação, chamada de função, etc)
            while (i < tamanho - 1) {
                int tk = VetorAnaliseLexica.get(i).Token;
                if (tk == Token.NUMERO_INTEIRO || tk == Token.NUMERO_FLOAT || tk == Token.VARIAVEL ||
                    tk == Token.ADICAO || tk == Token.SUBTRACAO || tk == Token.MULTIPLICACAO ||
                    tk == Token.PORCENTAGEM_D || tk == Token.PORCENTAGEM_2F || tk == Token.PORCENTAGEM_F ||
                    tk == Token.ABRE_COLCHETE || tk == Token.FECHA_COLCHETE || tk == Token.ABRE_PARENTESES ||
                    tk == Token.FECHA_PARENTESES || tk == Token.MENSAGEM || tk == Token.OPERADOR_MAIOR ||
                    tk == Token.OPERADOR_MENOR || tk == Token.OPERADOR_SAIDA || tk == Token.ENDERECO ||
                    tk == Token.VIRGULA) {
                    i++;
                } else {
                    break;
                }
            }
            if (VetorAnaliseLexica.get(tamanho - 1).Token == Token.PONTO_VIRGULA) {
                return true; // deve terminar com ;
            }
        }
        return false;
    }

    // Aceita comandos específicos: printf, scanf, system("cls"), break, return, etc.
    boolean COMANDO_ESPECIFICO(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
    {
        int t = VetorAnaliseLexica.size();
        if (t < 2) return false;

        int tk0 = VetorAnaliseLexica.get(0).Token;

        // printf, scanf, system("cls")
        if ((tk0 == Token.PRINTF || tk0 == Token.SCANF) && VetorAnaliseLexica.get(t-1).Token == Token.PONTO_VIRGULA) {
            return true;
        }
        // system("cls");
        if (tk0 == Token.SYSTEM_CLS && VetorAnaliseLexica.get(t-1).Token == Token.PONTO_VIRGULA) {
            return true;
        }
        // break;
        if (tk0 == Token.BREAK && VetorAnaliseLexica.get(t-1).Token == Token.PONTO_VIRGULA) {
            return true;
        }
        // return ...;
        if (tk0 == Token.RETURN && VetorAnaliseLexica.get(t-1).Token == Token.PONTO_VIRGULA) {
            return true;
        }
        return false;
    }

    // Aceita comandos de controle: for, while, if, else, switch, case, default, do-while
    boolean COMANDO_CONTROLE(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
    {
        if (VetorAnaliseLexica.size() == 1) {
            int tk0 = VetorAnaliseLexica.get(0).Token;
            if (tk0 == Token.DO) return true;
        }
        if (VetorAnaliseLexica.size() < 3) return false;
        int tk0 = VetorAnaliseLexica.get(0).Token;
        if (tk0 == Token.FOR || tk0 == Token.WHILE || tk0 == Token.IF || tk0 == Token.ELSE ||
            tk0 == Token.SWITCH || tk0 == Token.CASE || tk0 == Token.DEFAULT || tk0 == Token.DO) {
            return true;
        }
        return false;
    }

    // Aceita blocos de código { ... }
    boolean BLOCO(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
    {
        if (VetorAnaliseLexica.size() >= 2 &&
            VetorAnaliseLexica.get(0).Token == Token.ABRE_CHAVE &&
            VetorAnaliseLexica.get(VetorAnaliseLexica.size()-1).Token == Token.FECHA_CHAVE) {
            return true;
        }
        return false;
    }

    // Aceita chaves isoladas como comandos válidos
    boolean CHAVE_ISOLADA(ArrayList<ClassificacaoLexica> VetorAnaliseLexica) {
        return (VetorAnaliseLexica.size() == 1) &&
            (VetorAnaliseLexica.get(0).Token == Token.ABRE_CHAVE || VetorAnaliseLexica.get(0).Token == Token.FECHA_CHAVE);
    }

    // Aceita comando de controle seguido de comando específico (ex: else printf(...);)
    boolean CONTROLE_ESPECIFICO(ArrayList<ClassificacaoLexica> VetorAnaliseLexica) {
        if (VetorAnaliseLexica.size() < 2) return false;
        int tk0 = VetorAnaliseLexica.get(0).Token;
        if (tk0 == Token.ELSE || tk0 == Token.IF || tk0 == Token.FOR || tk0 == Token.WHILE || tk0 == Token.SWITCH || tk0 == Token.CASE || tk0 == Token.DEFAULT) {
            // Remove o primeiro token e testa se o resto é um comando específico
            ArrayList<ClassificacaoLexica> sub = new ArrayList<>(VetorAnaliseLexica.subList(1, VetorAnaliseLexica.size()));
            return COMANDO_ESPECIFICO(sub);
        }
        return false;
    }

    // Aceita } seguido de comando válido na mesma linha
    boolean FECHA_CHAVE_MAIS_COMANDO(ArrayList<ClassificacaoLexica> VetorAnaliseLexica) {
        if (VetorAnaliseLexica.size() < 2) return false;
        if (VetorAnaliseLexica.get(0).Token == Token.FECHA_CHAVE) {
            ArrayList<ClassificacaoLexica> sub = new ArrayList<>(VetorAnaliseLexica.subList(1, VetorAnaliseLexica.size()));
            // Tenta reconhecer o restante como comando válido
            return CONTROLE_ESPECIFICO(sub) || COMANDO_ESPECIFICO(sub) || COMANDO_CONTROLE(sub);
        }
        return false;
    }

    // Aceita qualquer linha válida do programa
    boolean PROGRAMA(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
    {
        if (DECLARACAO_VARIAVEL(VetorAnaliseLexica)) { System.out.print("DECLARACAO VARIAVEL - "); return true; }
        if (COMANDO_ATRIBUICAO(VetorAnaliseLexica)) { System.out.print("COMANDO ATRIBUICAO - "); return true; }
        if (COMANDO_ESPECIFICO(VetorAnaliseLexica)) { System.out.print("COMANDO ESPECIFICO - "); return true; }
        if (COMANDO_CONTROLE(VetorAnaliseLexica)) { System.out.print("COMANDO CONTROLE - "); return true; }
        if (BLOCO(VetorAnaliseLexica)) { System.out.print("BLOCO - "); return true; }
        if (CHAVE_ISOLADA(VetorAnaliseLexica)) { System.out.print("CHAVE ISOLADA - "); return true; }
        if (CONTROLE_ESPECIFICO(VetorAnaliseLexica)) { System.out.print("CONTROLE + ESPECIFICO - "); return true; }
        if (FECHA_CHAVE_MAIS_COMANDO(VetorAnaliseLexica)) { System.out.print("FECHA CHAVE + COMANDO - "); return true; }
        return false;
    }
}