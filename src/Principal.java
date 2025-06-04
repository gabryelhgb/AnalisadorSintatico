import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Principal {

	public static void main(String[] args) throws FileNotFoundException 
	{
		//Vai armazenar o caminho do arquivo que será analisado
		String CaminhoArquivoAnalisado = "";
		String TextoArquivoAnalisado;
		Lexer objetoAnalisadorLexico = new Lexer();
		
		//Objeto para caixa de diálogo de seleção de arquivos
		JFileChooser fileChooser = new JFileChooser();
		
		//Define o filtro para mostrar apenas arquivos .txt
		FileNameExtensionFilter filter = new FileNameExtensionFilter ("Arquivos de texto (.txt)", "txt");
		fileChooser.setFileFilter(filter);
		
		//Abre a janela de diálogo para selecionar o arquivo
		int returnValue = fileChooser.showOpenDialog(null);
		
		//Se o usuário selecionou m arquivo
		if (returnValue == JFileChooser.APPROVE_OPTION)
		{
			//Arquivo selecionado
			File selectedFile = fileChooser.getSelectedFile();
			
			//Mostrar o caminho do arquivo selecionado
			System.out.println("\n -----> Arquivo Selecionado: " + selectedFile.getAbsolutePath());
			
			//chamar o Lexer para fazer a AnáliseLéxica
			boolean resultado = objetoAnalisadorLexico.analisar(selectedFile);
			
			if(resultado == false)
			{
				System.out.println("\n #### Erro na ANALISE LEXICA ######");
			}
			
			else { //iniciar a análise sintática
				
				System.out.println("\n\n ********** ANALISE SINTATICA ********** \n\n");
				
				AnalisadorSintatico objSintatico = new AnalisadorSintatico();
				objSintatico.AnaliseSintatica();
				
			}
		}
		else // Se a pessoa não selecionou um arquivo
		{
			System.out.println("Nenhum arquivo selecionado."); 
		}
	}

}
