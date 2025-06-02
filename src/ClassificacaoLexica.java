public class ClassificacaoLexica 
{
	public String Lexema;
	public int Token;
	public int Linha;
	
	public ClassificacaoLexica(String lexema, int token, int linha)
	{
		this.Lexema = lexema;
		this.Token = token;
		this.Linha = linha;
	}
}