//*************************** ATENÇÃO! ****************************
// O método main() deve ser alterado somente nos comentários TODO.
// Todas as outras instruções devem permanecer intactas e o código
// deve funcionar conforme descrito no enunciado da atividade.
//*************************** ATENÇÃO! ****************************
// arquivo: src/MainApl2.java

// TODO: Colocar a identificação dos(as) integrantes aqui.

//Daniel Borges Valentim 
//Enzo Pinheiro de Oliveira
//João Vitor Golfieri Mendonça

// TODO: Listar todas as referências consultadas para solucionar a atividade.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import apl2.DLinkedList;
import apl2.Data;
import apl2.LinkedListOriginal;
import apl2.Node;
import apl2.Operation;

public class MainApl2 {

	public static void MostrarArquivo(String intermediador) {
    try {
        String conteudo = Data.loadTextFileToString(intermediador);
        System.out.println(conteudo);
    } catch (Exception e) {
        System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
    }
}

	public static void ConverteAntigo(String arquivoAlterado){
		String dadosTxt = "dados.txt";
		String dadosCsv = "dados.csv";

		  try (
            BufferedReader leitor = Files.newBufferedReader(Paths.get(dadosTxt));
            BufferedWriter escritor = Files.newBufferedWriter(Paths.get(dadosCsv));
        ) {
			String linha;
			while((linha = leitor.readLine()) != null){
				String[] partes = linha.split("#");

				String id = "sf1-" + partes[0];
				String nome = partes[1];
				int inteiro = Integer.parseInt(partes[2]);
				int decimal = Integer.parseInt(partes[3]);

				float nota;
				if((inteiro == -1 && decimal == 0) || (inteiro == -1 && decimal == -1)){
					nota = 99.9f;

				}else if(decimal == -1){
					nota = 0.0f;
				} else{
					nota = Float.parseFloat(inteiro + "." + decimal);
				}
				String linhaCsv = id + ";" + nome + ";" + nota;

				escritor.write(linhaCsv);
				escritor.newLine();
			}
			System.out.println("Lista feita com suceso");
	} catch(IOException e){
		System.err.println("Erro"+ e.getMessage());
	}
}

public static void ListarValidas(){
	String dadoscsv = "dados.csv";

	try(BufferedReader leitor = Files.newBufferedReader(Paths.get(dadoscsv))){
		String linha;
		while((linha  = leitor.readLine()) != null){
			String[] partes = linha.split(";");
			if(partes.length ==3){
				float nota = Float.parseFloat(partes[2].replace(",", "."));
				if(Math.abs(nota - 99.9f) > 0.0001f){
					System.out.println(linha);
				}

			}
		}
	}catch (IOException e) {
        System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
	}
}

public static void ListarInvalidas(){
  try(BufferedReader leitor = Files.newBufferedReader(Paths.get("dados.csv"))){
	String linha;
	while((linha = leitor.readLine()) != null){
		String[] partes = linha.split(";");
		if(partes.length == 3){
			float nota = Float.parseFloat(partes[2].replace(",", "."));
			if(Math.abs(nota - 99.9f) < 0.0001f){
				System.out.println(linha);
			}
		}
	}
	}catch(IOException e){
		System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
	}
} 
	public static void main(String[] args) {

		LinkedListOriginal list = new LinkedListOriginal();
		int escolha;
		do{
			
			System.out.println("1 - Apresentar Dados originais");
			System.out.println("2 - Arquivo convertido");
			System.out.println("3 - Notas validas");
			System.out.println("4 - Notas invalidas");
			System.out.println("5 - Media nota válida");
			System.out.println("6 - Nota acima da média");
			System.out.println("7 - Dados Mapeadas");
			System.out.println("8 - Sair");
			escolha = Integer.parseInt(System.console().readLine());
			switch (escolha) {
				case 1:
				try {
					String conteudo = Data.loadTextFileToString("dados.txt");
					System.out.println("COnteudo do arquivo: " + conteudo);

				} catch (Exception e) {
					MostrarArquivo("dados.txt");
				}
					
					break;
				case 2:
					 ConverteAntigo("dados.txt");
					 MostrarArquivo("dados.csv");
					break;
				case 3:
						ListarValidas();
					break;
				case 4:
						ListarInvalidas();	
					break;
				case 5:
					
					break;
				case 6:
					
					break;
				case 7:
					
					break;

				case 8:
					System.out.println("Finalizando operação...");
					break;

				default:
					break;
			}



		}
		while(escolha !=8);

		
		// TODO: Carregar o conteúdo do arquivo "dados.txt" e adicionar cada linha como um nó na LinkedListOriginal list.

		
		System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
		System.out.println(list);
		System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
		
		DLinkedList fixedList = Operation.map(list);
		System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
		System.out.println(fixedList);
		System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
		
		DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
		System.out.println(filteredGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
		
		DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
		System.out.println(filteredNonGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");

		float average = Operation.reduce(filteredGradedList);
		System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
		System.out.println(average);
		System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
		
		DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
		System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
		System.out.println(aboveAverageList);
		System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
		
		String contents = Operation.mapToString(fixedList);
		System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
		System.out.println(contents);
		System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
		
		
		// TODO: Salvar o conteúdo da String contents em um arquivo chamado "dados.csv".

		
		Node test1 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");

		Node test2 = fixedList.removeNode("23.S1-999");
		System.out.println(">>>>>>>>>> test2 >>>>>>>>>>\n" + test2 + "\n<<<<<<<<<< test2 <<<<<<<<<<\n");

		Node test3 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test3 >>>>>>>>>>\n" + test3 + "\n<<<<<<<<<< test3 <<<<<<<<<<\n");

		aboveAverageList.clear();
		System.out.println(">>>>>>>>>> aboveAverageList.clear() >>>>>>>>>>\n" + aboveAverageList + "\n<<<<<<<<<< aboveAverageList.clear() <<<<<<<<<<\n");

		DLinkedList testList = new DLinkedList();
		// TODO: Inserir um nó no início da lista testList com os dados ("ABC", "John Doe", 4.7f).
		// TODO: Inserir um nó no final da lista testList com os dados ("XYZ", "Jane Doe", 9.9f).
		// TODO: Inserir um nó no início da lista testList com os dados ("321", "Test", 2.3f).
		// TODO: Inserir um nó no final da lista testList com os dados ("Nothing", "Yada yada yada", 99.9f).
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail());
		System.out.println("testList.removeHead(): " + testList.removeHead());
		System.out.println("testList.removeTail(): " + testList.removeTail() + '\n');
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail());
		System.out.println("testList.removeNode(\"ABC\"): " + testList.removeNode("ABC") + '\n');
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail() + '\n');
	
		// TODO: Inserir um nó no início da lista testList com os dados ("qwerty", "QWERTY", 1.2f).
		// TODO: Inserir um nó no final da lista testList com os dados ("WASD", "wasd", 3.4f).
		// TODO: Inserir um nó no início da lista testList com os dados ("ijkl", "IJKL", 5.6f).
		// TODO: Inserir um nó no final da lista testList com os dados ("1234", "Um Dois Tres Quatro", 7.8f).
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		testList.clear();
		System.out.println(">>>>>>>>>> testList.clear() >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList.clear() <<<<<<<<<<\n");
	}

	

}
