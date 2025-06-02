import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexer {

    public TabelaDeSimbolos objTabelaDeSimbolos = new TabelaDeSimbolos();
    public static ArrayList<ClassificacaoLexica> ArrayListAnaliseLexica = new ArrayList<>();

    // Método para inicializar a tabela de símbolos
    void gerarTabelaDeSimbolos() {
        objTabelaDeSimbolos.adicionarSimbolo(Token.VOID, "COMANDO VOID");
        objTabelaDeSimbolos.adicionarSimbolo(Token.MAIN, "COMANDO MAIN");
        objTabelaDeSimbolos.adicionarSimbolo(Token.INT, "TIPO INT");
        objTabelaDeSimbolos.adicionarSimbolo(Token.FLOAT, "TIPO FLOAT");
        objTabelaDeSimbolos.adicionarSimbolo(Token.PRINTF, "COMANDO PRINTF");
        objTabelaDeSimbolos.adicionarSimbolo(Token.SCANF, "COMANDO SCANF");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ATRIBUICAO, "ATRIBUIÇÃO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.PONTO_VIRGULA, "PONTO E VIRGULA");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ENDERECO, "ENDEREÇO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.INTEIRO_DECIMAL, "INTEIRO DECIMAL");
        objTabelaDeSimbolos.adicionarSimbolo(Token.PONTO_FLUTUANTE, "PONTO FLUTUANTE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ABRE_CHAVE, "ABRE CHAVE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.FECHA_CHAVE, "FECHA CHAVE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ABRE_PARENTESES, "ABRE PARENTESES");
        objTabelaDeSimbolos.adicionarSimbolo(Token.FECHA_PARENTESES, "FECHA PARENTESES");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ASPAS, "ASPAS");
        objTabelaDeSimbolos.adicionarSimbolo(Token.MENSAGEM, "MENSAGEM");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ADICAO, "ADIÇÃO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.DOIS_PONTOS, "DOIS PONTOS");
        objTabelaDeSimbolos.adicionarSimbolo(Token.VIRGULA, "VIRGULA");
        objTabelaDeSimbolos.adicionarSimbolo(Token.NUMERO_INTEIRO, "NUMERO INTEIRO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.NUMERO_FLOAT, "NUMERO FLOAT");
        objTabelaDeSimbolos.adicionarSimbolo(Token.VARIAVEL, "VARIÁVEL");
        objTabelaDeSimbolos.adicionarSimbolo(Token.CHAR, "TIPO CHAR");
        objTabelaDeSimbolos.adicionarSimbolo(Token.FOR, "COMANDO FOR");
        objTabelaDeSimbolos.adicionarSimbolo(Token.IF, "COMANDO IF");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ELSE, "COMANDO ELSE");
        objTabelaDeSimbolos.adicionarSimbolo(Token.COUT, "COMANDO COUT");
		objTabelaDeSimbolos.adicionarSimbolo(Token.RETURN, "COMANDO RETURN");
		objTabelaDeSimbolos.adicionarSimbolo(Token.OU, "COMANDO OU");
        objTabelaDeSimbolos.adicionarSimbolo(Token.OPERADOR_MAIOR, "OPERADOR MAIOR");
        objTabelaDeSimbolos.adicionarSimbolo(Token.OPERADOR_MENOR, "OPERADOR MENOR");
        objTabelaDeSimbolos.adicionarSimbolo(Token.OPERADOR_SAIDA, "OPERADOR SAIDA");
        objTabelaDeSimbolos.adicionarSimbolo(Token.SUBTRACAO, "SUBTRAÇÃO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.MULTIPLICACAO, "MULTIPLICAÇÃO");
        objTabelaDeSimbolos.adicionarSimbolo(Token.ERRO_DESCONHECIDO, " *** ERRO DESCONHECIDO! ***");
    }

    // Função que recebe um lexema e retorna o token correspondente
    int classificarLexema(String lexema) {
        switch (lexema) {
            case "void": return Token.VOID;
            case "main": return Token.MAIN;
            case "int": return Token.INT;
            case "char": return Token.CHAR;
            case "float": return Token.FLOAT;
            case "printf": return Token.PRINTF;
            case "scanf": return Token.SCANF;
            case "for": return Token.FOR;
            case "if": return Token.IF;
            case "else": return Token.ELSE;
            case "cout": return Token.COUT;
			case "return": return Token.RETURN;
			case "||": return Token.OU;
            case "=": return Token.ATRIBUICAO;
            case ";": return Token.PONTO_VIRGULA;
            case "&": return Token.ENDERECO;
            case "%d": return Token.INTEIRO_DECIMAL;
            case "%f": return Token.PONTO_FLUTUANTE;
            case "{": return Token.ABRE_CHAVE;
            case "}": return Token.FECHA_CHAVE;
            case "(": return Token.ABRE_PARENTESES;
            case ")": return Token.FECHA_PARENTESES;
            case "\"": return Token.ASPAS;
            case ":": return Token.DOIS_PONTOS;
            case "+": return Token.ADICAO;
            case ",": return Token.VIRGULA;
            case ">": return Token.OPERADOR_MAIOR;
            case "<": return Token.OPERADOR_MENOR;
            case ">>": return Token.OPERADOR_SAIDA;
            case "-": return Token.SUBTRACAO;
            case "*": return Token.MULTIPLICACAO;
        }

		// Verifica se é uma mensagem entre aspas
		if (lexema.startsWith("\"") && lexema.endsWith("\"")) {
			return Token.MENSAGEM;
		}

        // Verifica se é número inteiro
        if (tokenNumeroInteiro(lexema)) return Token.NUMERO_INTEIRO;
        // Verifica se é número float
        if (tokenNumeroFloat(lexema)) return Token.NUMERO_FLOAT;
        // Verifica se é variável válida
        if (tokenVariavel(lexema)) return Token.VARIAVEL;

		

        // Se não reconhecido, retorna erro
        return Token.ERRO_DESCONHECIDO;
    }

    // Métodos auxiliares para verificar número inteiro, float e variável
    boolean tokenNumeroInteiro(String lexema) {
        if (lexema.isEmpty()) return false;
        for (char c : lexema.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    boolean tokenNumeroFloat(String lexema) {
        if (lexema.isEmpty()) return false;
        int countPoints = 0;
        for (char c : lexema.toCharArray()) {
            if (c == '.') countPoints++;
            else if (!Character.isDigit(c)) return false;
        }
        return countPoints == 1;
    }

    boolean tokenVariavel(String lexema) {
        if (lexema.isEmpty()) return false;
        char first = lexema.charAt(0);
        if (!(first == '_' || Character.isLetter(first))) return false;
        for (int i = 1; i < lexema.length(); i++) {
            char c = lexema.charAt(i);
            if (!(Character.isLetterOrDigit(c) || c == '_')) return false;
        }
        return true;
    }

    // Método principal de análise léxica
    public boolean analisar(File arquivo) throws FileNotFoundException {
        gerarTabelaDeSimbolos();

        Scanner scanner = new Scanner(arquivo);
        int linha = 1;

        System.out.println("\n\n ******* ANÁLISE LÉXICA ******* \n\n");

        while (scanner.hasNextLine()) {
            String linhaTexto = scanner.nextLine().trim();
            if (linhaTexto.isEmpty()) {
                linha++;
                continue;
            }

            // Lidar com comentários e strings:
            ArrayList<String> lexemas = quebrarLinhaEmLexemas(linhaTexto);

            for (String lexema : lexemas) {
                int token = classificarLexema(lexema);
                ArrayListAnaliseLexica.add(new ClassificacaoLexica(lexema, token, linha));
            }
            linha++;
        }
        scanner.close();

        gerarRelatorioAnaliseLexica();
        return true;
    }

    // Método para quebrar a linha em lexemas, reconhecendo strings e ignorando comentários
    ArrayList<String> quebrarLinhaEmLexemas(String linha) {
        ArrayList<String> lexemas = new ArrayList<>();

        int i = 0;
        while (i < linha.length()) {
            char c = linha.charAt(i);

            // Ignorar comentários de linha //
            if (c == '/' && i + 1 < linha.length() && linha.charAt(i + 1) == '/') {
                break; // resto da linha é comentário, ignora
            }

            // Ignorar comentários de bloco /* ... */
            if (c == '/' && i + 1 < linha.length() && linha.charAt(i + 1) == '*') {
                i += 2;
                while (i + 1 < linha.length() && !(linha.charAt(i) == '*' && linha.charAt(i + 1) == '/')) {
                    i++;
                }
                i += 2; // pula '*/'
                continue;
            }

            // Reconhecer strings entre aspas ""
            if (c == '"') {
                StringBuilder mensagem = new StringBuilder();
                mensagem.append(c);
                i++;
                while (i < linha.length() && linha.charAt(i) != '"') {
                    mensagem.append(linha.charAt(i));
                    i++;
                }
                if (i < linha.length()) {
                    mensagem.append('"'); // fecha aspas
                    i++;
                }
                lexemas.add(mensagem.toString());
                continue;
            }

            // Se for caractere separador (espaço ou simbolos), separa
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            // Símbolos únicos ou compostos
            if (isSimboloEspecial(c)) {
                // Tenta verificar simbolos duplos como ">>"
                if (i + 1 < linha.length()) {
                    String doisChars = "" + c + linha.charAt(i + 1);
                    if (doisChars.equals(">>")) {
                        lexemas.add(doisChars);
                        i += 2;
                        continue;
                    }
                }
                lexemas.add("" + c);
                i++;
                continue;
            }

            // Senão, é um identificador, número, palavra - lê até próximo separador
            StringBuilder sb = new StringBuilder();
            while (i < linha.length() && !Character.isWhitespace(linha.charAt(i)) && !isSimboloEspecial(linha.charAt(i))) {
                sb.append(linha.charAt(i));
                i++;
            }
            lexemas.add(sb.toString());
        }

        return lexemas;
    }

    boolean isSimboloEspecial(char c) {
        return "{}();=&:+,<>-*/\"".indexOf(c) >= 0;
    }

    // Método para imprimir o relatório da análise léxica
    void gerarRelatorioAnaliseLexica() {
        System.out.println("Classificação Léxica:");
        for (ClassificacaoLexica item : ArrayListAnaliseLexica) {
            System.out.println("Linha " + item.Linha + ": [ " + item.Lexema + " ] -> Token: " + item.Token + " --->  " + objTabelaDeSimbolos.buscarValor(item.Token));
        }
    }
    
    boolean GerarAnaliseLexica ()
    {
    	boolean ResultadoAnaliseLexica = true;
    	
    	//varrer todo o arrayList ArrayListAnaliseLexica
    	// Classificar os lexemas da linha lida do arquivo
    	for (ClassificacaoLexica obj : ArrayListAnaliseLexica)
    	{
    		String Lexema = obj.Lexema;
    		int Linha = obj.Linha;
    		int CodigoToken = obj.Token;
    		String Simbolo = objTabelaDeSimbolos.buscarValor(CodigoToken);
    		
    		if(obj.Token == Token.ERRO_DESCONHECIDO)
    		{
    			ResultadoAnaliseLexica = false;
    		}
    		
    		//mostrar a classificação léxica na tela
    		System.out.println("Linha: " + Linha + " - Lexema: " + Lexema + " Símbolo: " +
    		Simbolo	+ " Token: " + CodigoToken);
    	}
    	return ResultadoAnaliseLexica; //TODOS OS LEXEMAS FORAM RECONHECIDOS
    }

}