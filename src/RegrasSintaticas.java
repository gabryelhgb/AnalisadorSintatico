import java.util.ArrayList;

public class RegrasSintaticas {

	boolean INICIO(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
	{
		/* --- REGRA SINTATICA ---
		 * INICIO -> VOID MAIN ABRE_PARENTESES FECHA_PARENTESES ABRE-CHAVE PROGRAMA FECHA_CHAVE
		 
		 */
		
		int Tamanho = VetorAnaliseLexica.size();
		
		if( (VetorAnaliseLexica.get(0).Token == Token.VOID) || (VetorAnaliseLexica.get(0).Token == Token.INT) &&
			(VetorAnaliseLexica.get(1).Token == Token.MAIN) &&
			(VetorAnaliseLexica.get(2).Token == Token.ABRE_PARENTESES) &&
			(VetorAnaliseLexica.get(3).Token == Token.FECHA_PARENTESES) &&
			(VetorAnaliseLexica.get(4).Token == Token.ABRE_CHAVE) &&
			(VetorAnaliseLexica.get(Tamanho-1).Token == Token.FECHA_CHAVE))
			
			return true;
		
		else return false;
			
	}
	
	boolean DECLARACAO_VARIAVEL(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
	{
		/*
		DECLARACAO VARIAVEL -> TIPO VARIAVEL VARIAVEL PONTO VIRGULA |
		TIPO VARIAVEL VARIAVEL VIRGULA VARIAVEL PONTO VIRGULA
		TIPO VARIAVEL -> INT FLOAT
		*/

		if (VetorAnaliseLexica.size() >= 3) {
			if (((VetorAnaliseLexica.get(0).Token == Token.INT) || (VetorAnaliseLexica.get(0).Token == Token.FLOAT)) &&
				(VetorAnaliseLexica.get(1).Token == Token.VARIAVEL) &&
				(VetorAnaliseLexica.get(2).Token == Token.PONTO_VIRGULA)) {
				return true;
			}
		}

		if (VetorAnaliseLexica.size() >= 5) {
			if ((VetorAnaliseLexica.get(0).Token == Token.INT) &&
				(VetorAnaliseLexica.get(1).Token == Token.VARIAVEL) &&
				(VetorAnaliseLexica.get(2).Token == Token.VIRGULA) &&
				(VetorAnaliseLexica.get(3).Token == Token.VARIAVEL) &&
				(VetorAnaliseLexica.get(4).Token == Token.PONTO_VIRGULA)) {
				return true;
			}
		}

		if (VetorAnaliseLexica.size() >= 7) {
			if ((VetorAnaliseLexica.get(0).Token == Token.INT) &&
				(VetorAnaliseLexica.get(1).Token == Token.VARIAVEL) &&
				(VetorAnaliseLexica.get(2).Token == Token.VIRGULA) &&
				(VetorAnaliseLexica.get(3).Token == Token.VARIAVEL) &&
				(VetorAnaliseLexica.get(4).Token == Token.VIRGULA) &&
				(VetorAnaliseLexica.get(5).Token == Token.VARIAVEL) &&
				(VetorAnaliseLexica.get(6).Token == Token.PONTO_VIRGULA)) {
				return true;
			}
		}

		return false;
	}
	
		boolean COMANDO_ATRIBUICAO(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
		{
			/* COMANDO ATRIBUICAO -> VARIAVEL ATRIBUICAO INT PONTO VIRGULA
			VARIAVEL ATRIBUICAO VARIAVEL ADICAO NUMERO INTEIRO PONTO VIRGULA
			*/
			
			int tamanho = VetorAnaliseLexica.size();
			if (tamanho < 4) //se a quantidade lexemas nao e o suficiente para analisar a regra abaixo, é pq nao é atribuicao
				return false;
			
			if ((VetorAnaliseLexica.get(0).Token == Token.VARIAVEL ) &&
				(VetorAnaliseLexica.get(1).Token == Token.ATRIBUICAO) &&
				(VetorAnaliseLexica.get(2).Token == Token.NUMERO_INTEIRO) &&
				(VetorAnaliseLexica.get(3).Token == Token.PONTO_VIRGULA))
						return true;
					
			if(tamanho > 6) //se a quantidade lexemas nao é o suficiente para analisar a regra abaixo, é porque nao é atribuicao
			   return false;
			
			if ((VetorAnaliseLexica.get(0).Token == Token.VARIAVEL ) &&
				(VetorAnaliseLexica.get(1).Token == Token.ATRIBUICAO) &&
				(VetorAnaliseLexica.get(2).Token == Token.VARIAVEL) &&
				(VetorAnaliseLexica.get(3).Token == Token.ADICAO) &&
				(VetorAnaliseLexica.get(4).Token == Token.NUMERO_INTEIRO) &&
				(VetorAnaliseLexica.get(5).Token == Token.PONTO_VIRGULA))
						return true;
			else return false;
		}
		
		boolean COMANDO_ESPECIFICO (ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
		{
			
		/*
		COMANDO ESPECIFICO -> COMANDO PRINTLN
		COMANDO PRINTLN -> PRINTLN ABRE PARENTESES VARIAVEL FECHA CHAVE PONTO VIRGULA
		*/
			
		int tamanho = VetorAnaliseLexica.size ();
		if (tamanho < 5) //se a quantidade lexemas não é o suficiente para analisar a regra
		return false;
		
		if ((VetorAnaliseLexica.get (0). Token == Token. PRINTF ) &&
		(VetorAnaliseLexica.get (1).Token == Token. ABRE_PARENTESES) &&
		(VetorAnaliseLexica.get(2).Token == Token. VARIAVEL ) &&
		(VetorAnaliseLexica.get (3).Token == Token. FECHA_PARENTESES ) &&
		(VetorAnaliseLexica.get (4).Token == Token. PONTO_VIRGULA))
						return true;
		else return false;
		}
		
		boolean PROGRAMA(ArrayList<ClassificacaoLexica> VetorAnaliseLexica)
		{
			/*
			PROGRAMA -> {DECLARACAO_VARIAVEL | COMANDO_ATRIBUICAO COMANDO ESPECIFICO}
			*/
			
		if (DECLARACAO_VARIAVEL(VetorAnaliseLexica) == true)
		{ System.out.print ("DECLARACAO VARIAVEL - ");
		return true;
		}
		
		if (COMANDO_ATRIBUICAO (VetorAnaliseLexica) == true)
		{ System.out.print("COMANDO ATRIBUICAO - ");
		return true;
		}
		
		if (COMANDO_ESPECIFICO (VetorAnaliseLexica) == true)
		{ System.out.print ("COMANDO PRINT -");
		return true;
		}
		
		else return false;

	}
}