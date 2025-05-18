//*************************** ATENÇÃO! ****************************
// O método main() deve ser alterado somente nos comentários TODO.
// Todas as outras instruções devem permanecer intactas e o código
// deve funcionar conforme descrito no enunciado da atividade.
//*************************** ATENÇÃO! ****************************
// arquivo: src/MainApl2.java

//Daniel Borges Valentim - 10427564 
//Enzo Pinheiro de Oliveira - 10434443
//João Vitor Golfieri Mendonça - 10434460

// - Referências:
// - Enunciado da Atividade APL2 2025-1 (PDF fornecido pelo professor)
// - Slides e códigos de aula sobre listas encadeadas e duplamente encadeadas
// - Documentação oficial do Java SE 17:
//   https://docs.oracle.com/en/java/javase/17/docs/api/
// - Exemplos de uso de FileInputStream, FileOutputStream, Scanner e String.split() da documentação Java

import apl2.DLinkedList;
import apl2.LinkedListOriginal;
import apl2.Node;
import apl2.Operation;
import java.util.Scanner;

public class MainApl2 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		LinkedListOriginal list = new LinkedListOriginal();

		
		try {
			String conteudo = apl2.Data.loadTextFileToString("src/dados.txt");
			String[] linhas = conteudo.split("\\r?\\n|\\r");

			for (String linha : linhas) {
				String[] partes = linha.split("#");
				int id = Integer.parseInt(partes[0]);
				String nome = partes[1];
				int inteiro = Integer.parseInt(partes[2]);
				int decimo = Integer.parseInt(partes[3]);

				list.append(id, nome, inteiro, decimo);
			}
		}
		catch (Exception e) {
			System.err.println("Erro ao carregar dados.txt");
			e.printStackTrace();
			System.exit(-1);
		}

		DLinkedList fixedList = Operation.map(list);
		DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
		DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
		float average = Operation.reduce(filteredGradedList);
		DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
		String contents = Operation.mapToString(fixedList);

		try {
			apl2.Data.saveStringToTextFile("dados.csv", contents);
		} catch(Exception e) {
			System.err.println("Erro ao salvar dados.csv");
			e.printStackTrace();
		}

		while (true) {
			System.out.println("\nSistema Conversor de Notas");
			System.out.println("1) Dados originais");
			System.out.println("2) Dados convertidos");
			System.out.println("3) Lista notas filtradas válidas");
			System.out.println("4) Lista notas filtradas inválidas");
			System.out.println("5) Média de notas válidas");
			System.out.println("6) Notas acima da média");
			System.out.println("7) Lista mapeada para uma única string");
			System.out.println("8) Finaliza sistema");
			System.out.println("9) Executar testes extras");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine(); 

			switch (opcao) {
				case 1:
					System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
					System.out.println(list);
					System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
					break;

				case 2:
					System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
					System.out.println(fixedList);
					System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
					break;

				case 3:
					System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
					System.out.println(filteredGradedList);
					System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
					break;

				case 4:
					System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
					System.out.println(filteredNonGradedList);
					System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");
					break;

				case 5:
					System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
					System.out.println(average);
					System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
					break;

				case 6:
					System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
					System.out.println(aboveAverageList);
					System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
					break;

				case 7:
					System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
					System.out.println(contents);
					System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
					break;

				case 8:
					System.out.println("Sistema finalizado.");
					scanner.close();
					return;
				
				case 9:
					Node test1 = fixedList.getNode("23.S1-999");
					System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");

					Node test2 = fixedList.removeNode("23.S1-999");
					System.out.println(">>>>>>>>>> test2 >>>>>>>>>>\n" + test2 + "\n<<<<<<<<<< test2 <<<<<<<<<<\n");

					Node test3 = fixedList.getNode("23.S1-999");
					System.out.println(">>>>>>>>>> test3 >>>>>>>>>>\n" + test3 + "\n<<<<<<<<<< test3 <<<<<<<<<<\n");

					aboveAverageList.clear();
					System.out.println(">>>>>>>>>> aboveAverageList.clear() >>>>>>>>>>\n" + aboveAverageList + "\n<<<<<<<<<< aboveAverageList.clear() <<<<<<<<<<\n");

					DLinkedList testList = new DLinkedList();
					testList.insert("ABC", "John Doe", 4.7f);
					testList.append("XYZ", "Jane Doe", 9.9f);
					testList.insert("321", "Test", 2.3f);
					testList.append("Nothing", "Yada yada yada", 99.9f);

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

					testList.insert("qwerty", "QWERTY", 1.2f);
					testList.append("WASD", "wasd", 3.4f);
					testList.insert("ijkl", "IJKL", 5.6f);
					testList.append("1234", "Um Dois Tres Quatro", 7.8f);

					System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
					testList.clear();
					System.out.println(">>>>>>>>>> testList.clear() >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList.clear() <<<<<<<<<<\n");

				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}
}
