//responsável por armazenar as informações léxicas de um lexema encontrado durante a análise léxica.
public class ClassificacaoLexica 
{
	public String Lexema;
	public int Token;
	public int Linha;
	
	// Construtor da classe, que inicializa os atributos com os valores passados como argumentos.
	public ClassificacaoLexica(String lexema, int token, int linha)
	{
		this.Lexema = lexema;
		this.Token = token;
		this.Linha = linha;
	}
}