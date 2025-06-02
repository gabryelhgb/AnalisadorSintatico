import java.util.ArrayList;


public class AnalisadorSintatico 
{
	RegrasSintaticas objetoRegrasSintaticas;
	ArrayList <ClassificacaoLexica> VetorAnaliseLexica;
	
	public AnalisadorSintatico()
	{
	objetoRegrasSintaticas = new RegrasSintaticas();
	VetorAnaliseLexica = Lexer.ArrayListAnaliseLexica;
	}
	
	void AnaliseSintatica()
	{
	//verificar a linha 1 - Inicialização do programa
	if (objetoRegrasSintaticas.INICIO (VetorAnaliseLexica) == true)
	{ 
		int Tamanho = VetorAnaliseLexica.size();
		int QuantidadeTotalLinhas = VetorAnaliseLexica.get (Tamanho-1).Linha;
		int LinhaAtual = 3;
		boolean ProgramaSemErros = true;
		int QuantidadeErros = 0;
		
		while (LinhaAtual <= QuantidadeTotalLinhas-1)
		{
			ArrayList<ClassificacaoLexica> VetorAnalise = new ArrayList<>();
	
			
			//recuperar os Lexemas da LinhaAtual
	for (ClassificacaoLexica obj : VetorAnaliseLexica)
	{    
	 
		if(obj.Linha == LinhaAtual)
		VetorAnalise.add(obj);
	}
	
	/* verificar regra sintatica
	PROGRAMА -> {DECLARACAO_VARIAVEL | COMANDO_ATRIBUICAO | COMANDO_ESPECIFICO}
	*/
	if (objetoRegrasSintaticas. PROGRAMA (VetorAnalise)== false)
	{
	ProgramaSemErros = false;
	QuantidadeErros++;
	System.out.print("***ERRO SINTATICO ");
	
	}
	
	System.out.print("Linha: " + LinhaAtual + " ");
	//mostrar os lexemas da linha
	for (ClassificacaoLexica obj: VetorAnalise)
	{
		System.out.print(obj.Lexema + " ");
	}
	
	System.out.println(" ");
	 LinhaAtual++;
		}//while
		
		if(ProgramaSemErros == true)
			System.out.println(" \n Analise Sintatica concluida com sucesso! ZERO erros");
		else
			System.out.println(" \n Quantidade de Erros: " + QuantidadeErros);
	}//if
	else
		System.out.println("Erro na linha 1 - Inicialização do programa");
	}
}