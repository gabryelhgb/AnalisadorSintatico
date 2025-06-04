import java.util.ArrayList;

//realiza a análise sintática de um código-fonte.
public class AnalisadorSintatico 
{
	// Objeto que contém as regras sintáticas a serem verificadas.
	RegrasSintaticas objetoRegrasSintaticas;
	// Vetor contendo a análise léxica realizada anteriormente (lista de tokens classificados)
	ArrayList <ClassificacaoLexica> VetorAnaliseLexica;
	
	public AnalisadorSintatico()
	{
	objetoRegrasSintaticas = new RegrasSintaticas();
	VetorAnaliseLexica = Lexer.ArrayListAnaliseLexica; // Usa os dados já processados pelo Lexer.
	}
	
	// Agora retorna 1 se houver erro de balanceamento nos simbolos, 0 se não houver
    int verificarBalanceamento() {
        int chaves = 0, parenteses = 0, colchetes = 0, aspas = 0;
        boolean erro = false;

     // Percorre todos os tokens encontrados pelo analisador léxico.
        for (ClassificacaoLexica obj : VetorAnaliseLexica) {
            switch (obj.Token) {
                case Token.ABRE_CHAVE: chaves++; break;
                case Token.FECHA_CHAVE: chaves--; break;
                case Token.ABRE_PARENTESES: parenteses++; break;
                case Token.FECHA_PARENTESES: parenteses--; break;
                case Token.ABRE_COLCHETE: colchetes++; break;
                case Token.FECHA_COLCHETE: colchetes--; break;
                case Token.ASPAS: aspas++; break;
            }
            // Se houver fechamento sem abertura correspondente
            if (chaves < 0 || parenteses < 0 || colchetes < 0) {
                erro = true;
                break;
            }
        }
     // Verifica se todos os símbolos foram fechados corretamente
        if (chaves != 0 || parenteses != 0 || colchetes != 0 || (aspas % 2 != 0)) {
            erro = true;
        }
     // Exibe mensagem de erro se houver desequilíbrio nos símbolos
        if (erro) {
            System.out.println("\n*** ERRO DE BALANCEAMENTO: Verifique se todas as chaves, parênteses, colchetes e aspas estão corretamente fechados! ***\n");
            return 1;
        }
        return 0;
    }

 // Função principal que realiza a análise sintática linha por linha
	void AnaliseSintatica()
	{
		//verificar a linha 1 - Inicialização do programa
		if (objetoRegrasSintaticas.INICIO (VetorAnaliseLexica) == true)
		{ 
			int Tamanho = VetorAnaliseLexica.size();
			int QuantidadeTotalLinhas = VetorAnaliseLexica.get (Tamanho-1).Linha;
			int LinhaAtual = 2; // Começa da linha 2, pois linha 1 é o cabeçalho
			boolean ProgramaSemErros = true;
			int QuantidadeErros = 0;
			
			// Percorre cada linha do código-fonte
			while (LinhaAtual <= QuantidadeTotalLinhas)
			{
				ArrayList<ClassificacaoLexica> VetorAnalise = new ArrayList<>();

				//recuperar os Lexemas da LinhaAtual
				for (ClassificacaoLexica obj : VetorAnaliseLexica)
				{    
					if(obj.Linha == LinhaAtual)
						VetorAnalise.add(obj);
				}

				// Ignora linhas vazias
				if (VetorAnalise.size() == 0) {
					LinhaAtual++;
					continue;
				}

				// verificar regra sintatica
				if (!objetoRegrasSintaticas.PROGRAMA(VetorAnalise))
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
			// Chame a verificação de balanceamento ao final
			int errosBalanceamento = verificarBalanceamento();
			QuantidadeErros += errosBalanceamento;

			 // Exibe mensagem de sucesso ou quantidade de erros
			if(ProgramaSemErros && errosBalanceamento == 0)
				System.out.println(" \n Analise Sintatica concluida com sucesso! ZERO erros");
			else
				System.out.println(" \n Quantidade de Erros: " + QuantidadeErros);
		}//if
		else
			System.out.println("Erro na linha 1 - Inicialização do programa");
	}
}