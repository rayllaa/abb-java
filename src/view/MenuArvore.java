package view;

import java.util.Scanner;

import model.ArvoreBinariaBusca;
import model.No;

public class MenuArvore {
	private Scanner in;
	private ArvoreBinariaBusca arvore;
	
	public MenuArvore() {
		in = new Scanner(System.in);
		arvore = new ArvoreBinariaBusca();
	}
		
	public void menu() {
		int opcao;
	
		do {
			System.out.println("\n#### �rvore Bin�ria de Busca ####\n");
			System.out.println("=================================");
			System.out.println("| 1 - Inserir n�\t\t|");
			System.out.println("| 2 - Buscar n�\t\t\t|");
			System.out.println("| 3 - Excluir n�\t\t|");
			System.out.println("| 4 - Altura da �rvore\t\t|");
			System.out.println("| 5 - Profundidade de n�\t|");
			System.out.println("| 6 - Subn�s da �rvore\t\t|");
			System.out.println("| 7 - Imprimir\t\t\t|");
			System.out.println("| 8 - Realizar Carga Inicial\t|");
			System.out.println("| 9 - Sair\t\t\t|");
			System.out.println("=================================");
			System.out.print("\nOp��o - > ");
			
			opcao = in.nextInt();
			
			switch(opcao) {
				case 1: 
					inserirNo();
					break;
				case 2:
					buscarNo();
					break;
				case 3:
					excluirNo();
					break;
				case 4:
					obterAlturaArvore();
					break;
				case 5:
					obterProfundidadeNo();
					break;
				case 6:
					obterSubnosArvore();
					break;
				case 7:
					imprimir();
					break;
				case 8:
					inicializarArvore();
					break;
				case 9:
					sair();
					break;
				default:
					System.out.println("=================================");
					System.out.println("\tOp��o inv�lida!");
					System.out.println("=================================");
					break;
			}
		}while(opcao != 9);
	}

	private void inserirNo() {
		System.out.print("\nInforme o valor do n� (n�mero inteiro): ");
		int valor = in.nextInt();
		
		arvore.inserirNo(valor);
	}
	
	private void buscarNo() {
		System.out.print("\nInforme o valor do n� (n�mero inteiro): ");
		int valor = in.nextInt();
		
		if(arvore.buscarNo(valor))
			System.out.printf("\nN�mero %d foi encontrado na �rvore!\n",valor);
		else
			System.out.printf("\nN�mero %d n�o foi encontrado na �rvore!\n",valor);	
	}
	
	private void excluirNo() {
		System.out.print("\nInforme o valor do n� a ser exclu�do: ");
		int valor = in.nextInt();
		
		if(arvore.buscarNo(valor)) {
			arvore.excluirNo(valor);
			System.out.printf("\nN�mero %d foi exclu�do da �rvore!\n",valor);
		}
		else
			System.out.printf("\nN�mero %d n�o foi encontrado na �rvore!\n",valor);	
	}
	
	private void obterAlturaArvore() {
		System.out.printf("\nA altura da �rvore � %d!\n",arvore.obterAltura(arvore.getRaiz()));
	}
	
	private void obterProfundidadeNo() {
		
		System.out.print("\nInforme o valor do n� (n�mero inteiro): ");
		int valor = in.nextInt();
		No noRef = new No(valor, null, null);
		
		if(arvore.buscarNo(valor))
			System.out.printf("\nO n� de valor %d possui profundidade %d.\n",valor,arvore.obterProfundidade(noRef));
		else
			System.out.printf("\nN�mero %d n�o foi encontrado na �rvore!\n",valor);	
		
	}
	
	private void obterSubnosArvore() {
		System.out.printf("\nA �rvore possui %d subn�s!\n",arvore.quantidadeSubNos(arvore.getRaiz()));
	}

	private void imprimir() {
		int opcao;
		
		do {
			System.out.println("\nEscolha a forma de impress�o:");
			System.out.println("1 - PreOrder");
			System.out.println("2 - InOrder");
			System.out.println("3 - PosOrder");
			System.out.println("4 - Voltar");
			System.out.print("\nop��o: ");
			opcao = in.nextInt();
			
			switch(opcao) {
				case 1:
					arvore.imprimirPreOrder();
					break;
				case 2:
					arvore.imprimirInOrder();
					break;
				case 3: 
					arvore.imprimirPosOrder();
					break;
				case 4:
					menu();
					break;
				default:
					System.out.println("=================================");
					System.out.println("\tOp��o inv�lida!");
					System.out.println("=================================");
					break;
			}
			
		}while(opcao != 4);
		
	}

	private void sair() {
		System.out.println("\n=================================");
		System.out.println("\tSistema encerrado");
		System.out.println("=================================");
		Runtime.getRuntime().exit(0);
	}

	private void inicializarArvore() {
		arvore.inicializarArvore();
	}
}