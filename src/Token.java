//A classe Token atribui códigos numericos fixos para identificar 
//os elementos do código, ajudando o compilador a entender a linguagem.

//Implementação da classe Token
//A classe Token define constantes que representam diferentes tipos de tokens
public class Token 
{
	public static final int VOID = 0;
	public static final int MAIN = 1;
	public static final int INT = 2;
	public static final int FLOAT = 3;
	public static final int PRINTF = 4;
	public static final int SCANF = 5;
	public static final int COUT = 6;
	public static final int OPERADOR_SAIDA = 7;
	public static final int FOR = 8;
	public static final int IF = 9;
	public static final int ELSE = 10;
	public static final int OPERADOR_MAIOR = 11;
	public static final int OPERADOR_MENOR = 12;
	public static final int ATRIBUICAO = 13;
	public static final int PONTO_VIRGULA = 14;
	public static final int ENDERECO = 15;
	public static final int INTEIRO_DECIMAL = 16;
	public static final int PONTO_FLUTUANTE = 17;
	public static final int ABRE_CHAVE = 18;
	public static final int FECHA_CHAVE = 19;
	public static final int ABRE_PARENTESES = 20;
	public static final int FECHA_PARENTESES = 21;
	public static final int ASPAS = 22;
	public static final int DOIS_PONTOS = 23;
	public static final int ADICAO = 24;
	public static final int SUBTRACAO = 25;
	public static final int MULTIPLICACAO = 26;
	public static final int VIRGULA = 27;
	public static final int NUMERO_INTEIRO = 28;
	public static final int NUMERO_FLOAT = 29;
	public static final int VARIAVEL = 30;
	public static final int CHAR = 31;
	public static final int RETURN = 32;
	public static final int OU = 33;
	public static final int MENSAGEM = 34;
	public static final int ERRO_DESCONHECIDO = 35;
	public static final int WHILE = 36;
	public static final int DEFAULT = 37;
	public static final int BREAK = 38;
	public static final int PORCENTAGEM_D = 39;      // %d
	public static final int PORCENTAGEM_2F = 40;     // %.2f
	public static final int PORCENTAGEM_F = 41;      // %f
	public static final int SYSTEM_CLS = 42;         // system("cls")
	public static final int SWITCH = 43;
	public static final int CASE = 44;
	public static final int ABRE_COLCHETE = 45;      // [
	public static final int FECHA_COLCHETE = 46;     // ]
	public static final int DO = 47;
}
