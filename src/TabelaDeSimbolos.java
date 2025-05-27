import java.util.HashMap;

public class TabelaDeSimbolos 
{
	private HashMap <Integer, String> tabela; //chave -> valor
	
		public TabelaDeSimbolos()
		{
			this.tabela = new HashMap<>();
		}
		
		public void adicionarSimbolo(int token, String simbolo)
		{
			tabela.put(token, simbolo);
		}
		
		public String buscarValor (Integer token)
		{
			return tabela.get(token);
		}
		
		public boolean contemToken(Integer token)
		{
			return tabela.containsKey(token);
		}
		
		public boolean contemSimbolo (String simbolo)
		{
			return tabela.containsValue(simbolo);
		}
}