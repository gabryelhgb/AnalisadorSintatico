import java.util.HashMap;

public class TabelaDeSimbolos 
{
	private HashMap <Integer, String> tabela; //chave -> valor
	//declara o HashMap que guarda tokens(chaves) e simbolos(valores)
	
		public TabelaDeSimbolos()
		{
			this.tabela = new HashMap<>(); // Inicializa o HashMap vazio
		}
		
		public void adicionarSimbolo(int token, String simbolo)
		 // Método para adicionar um par token-símbolo na tabela
		{
			tabela.put(token, simbolo); // Insere o token como chave e o símbolo como valor no HashMap
		}
		
		public String buscarValor (Integer token) //busca o símbolo associado a um token
		{
			return tabela.get(token);// Retorna o valor (símbolo) correspondente à chave (token) fornecida
		}
		
		public boolean contemToken(Integer token) //verifica se um token está na tabela
		{
			return tabela.containsKey(token);//Retorna true se o token existir como chave no HashMap
		}
		
		public boolean contemSimbolo (String simbolo)// Método para verificar se um símbolo está na tabela
		{
			return tabela.containsValue(simbolo);// Retorna true se o símbolo existir como valor no HashMap
		}
}