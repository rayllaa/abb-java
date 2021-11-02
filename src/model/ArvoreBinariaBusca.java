package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArvoreBinariaBusca {
	private No raiz;
	private int no = 0;

	public ArvoreBinariaBusca() {
		raiz = null;
	}

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public boolean vazia() {
		return raiz == null;
	}

	// 1. Inserir nó
	public void inserirNo(int valor) {
		if (vazia()) {
			raiz = new No(valor, null, null);
		} else {
			percorreInsereArvore(raiz, valor);
		}
	}

	private void percorreInsereArvore(No noRef, int valor) {
		if (noRef != null) {
			if (valor < noRef.getValor()) {
				if (noRef.getEsquerdo() == null) {
					No novoNo = new No(valor, null, null);
					noRef.setEsquerdo(novoNo);
				} else {
					percorreInsereArvore(noRef.getEsquerdo(), valor);
				}
			} else {
				if (noRef.getDireito() == null) {
					No novoNo = new No(valor, null, null);
					noRef.setDireito(novoNo);
				} else {
					percorreInsereArvore(noRef.getDireito(), valor);
				}
			}
		}
	}

	// 2. Buscar nó
	public boolean buscarNo(int valor) {
		if (percorreBuscaArvore(raiz, valor) != null) {
			return true;
		}
		return false;
	}

	private No percorreBuscaArvore(No noRef, int valor) {
		if (noRef == null) {
			return null;
		}

		if (valor == noRef.getValor()) {
			return noRef;
		}

		if (valor < noRef.getValor()) {
			return percorreBuscaArvore(noRef.getEsquerdo(), valor);
		} else {
			return percorreBuscaArvore(noRef.getDireito(), valor);
		}
	}

	// 3. Excluir nó
	public void excluirNo(int valor) {
		raiz = percorrerRemoveArvore(raiz, valor);
	}

	private No percorrerRemoveArvore(No noRef, int valor) {

		if (noRef == null) {
			return null;
		}

		if (valor == noRef.getValor()) {
			if (noRef.folha()) {
				return null;
			} else if (somenteFilhoEsquerda(noRef)) {
				return noRef.getEsquerdo();
			} else if (somenteFilhoDireito(noRef)) {
				return noRef.getDireito();
			} else {
				int menorValor = pegaMenorValor(noRef.getDireito());
				noRef.setValor(menorValor);
				noRef.setDireito(percorrerRemoveArvore(noRef.getDireito(), menorValor));
				return noRef;
			}
		}

		if (valor < noRef.getValor()) {
			noRef.setEsquerdo(percorrerRemoveArvore(noRef.getEsquerdo(), valor));
		} else {
			noRef.setDireito(percorrerRemoveArvore(noRef.getDireito(), valor));
		}

		return noRef;
	}

	private boolean somenteFilhoEsquerda(No noRef) {
		return noRef.getEsquerdo() != null && noRef.getDireito() == null;
	}
	
	private boolean somenteFilhoDireito(No noRef) {
		return noRef.getDireito() != null && noRef.getEsquerdo() == null;
	}

	private int pegaMenorValor(No noRef) {
		if (noRef.getEsquerdo() == null) {
			return noRef.getValor();
		}
		return pegaMenorValor(noRef.getEsquerdo());
	}

	// 4. Altura da árvore
	public int obterAltura(No noRef) {
		if (noRef == null || (noRef.getDireito() == null && noRef.getEsquerdo() == null)) {
			return 0;
		}

		int alturaDireita = obterAltura(noRef.getDireito());
		int alturaEsquerda = obterAltura(noRef.getEsquerdo());

		return 1 + Math.max(alturaDireita, alturaEsquerda);
	}

	// 5. Profundidade de nó
	public int obterProfundidade(No noRef) {
		this.no = 0;

		if (vazia()) {
			throw new IllegalArgumentException("Árvore vazia");
		}

		return percorrerArvore(raiz, noRef);
	}

	private int percorrerArvore(No noPai, No noRef) {
		if (noPai != null) {
			if (noRef.getValor() < noPai.getValor()) {
				no++;
				if (noPai.getEsquerdo() != null) {
					if (noPai.getEsquerdo().getValor() == noRef.getValor()) {
						return no;
					} else {
						no = percorrerArvore(noPai.getEsquerdo(), noRef);
					}
				}
			} else if (noRef.getValor() > noPai.getValor()) {
				no++;
				if (noPai.getDireito() != null) {
					if (noPai.getDireito().getValor() == noRef.getValor()) {
						return no;
					} else {
						no = percorrerArvore(noPai.getDireito(), noRef);
					}
				}
			}
		}
		return no;
	}

	// 6. Subnós da árvore
	public int quantidadeSubNos(No noRef) {
		if (noRef == null) {
			return 0;
		} else {
			return quantidadeSubNos(noRef.getEsquerdo()) + 1 + quantidadeSubNos(noRef.getDireito());
		}
	}

	// 7. Imprimir

	// PreOrder
	public void imprimirPreOrder() {
		percorrerPreOrder(raiz);
	}

	private void percorrerPreOrder(No noRef) {
		if (noRef != null) {
			System.out.println(noRef.getValor());
			percorrerPreOrder(noRef.getEsquerdo());
			percorrerPreOrder(noRef.getDireito());
		}
	}

	// InOrder
	public void imprimirInOrder() {
		percorrerInOrder(raiz);
	}

	private void percorrerInOrder(No noRef) {
		if (noRef != null) {
			percorrerInOrder(noRef.getEsquerdo());
			System.out.println(noRef.getValor());
			percorrerInOrder(noRef.getDireito());
		}
	}

	// PosOrder
	public void imprimirPosOrder() {
		percorrerPosOrder(raiz);
	}

	private void percorrerPosOrder(No noRef) {
		if (noRef != null) {
			percorrerPosOrder(noRef.getEsquerdo());
			percorrerPosOrder(noRef.getDireito());
			System.out.println(noRef.getValor());
		}
	}

	// 8. Realizar carga inicial
	public void inicializarArvore() {

		if (raiz != null)
			raiz = null;

		gerarArquivoNos(gerarNosAleatorios(2000));
		List<Integer> nos = lerArquivoNos();
		
		for (int no : nos) {
			inserirNo(no);
		}
	}

	private List<Integer> gerarNosAleatorios(int qtd) {
		List<Integer> nos = new ArrayList<Integer>();
		Random random = new Random();
		int no = 0;
		
		for (int i = 0; i < qtd; i++) {
			no = random.nextInt(5000);			
			nos.add(no);
		}

		return nos;
	}

	private void gerarArquivoNos(List<Integer> nos) {
		PrintWriter arquivo;
		
		try {
			arquivo = new PrintWriter(new FileWriter("C:\\arvore\\arvore_binaria.txt"));

			for (int i = 0; i < nos.size(); i++) {
				arquivo.print(nos.get(i) + ";");
			}

			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private List<Integer> lerArquivoNos() {
		List<Integer> listaNos  = new ArrayList<Integer>();
		
		try {
			BufferedReader arquivo = new BufferedReader(new FileReader("C:\\arvore\\arvore_binaria.txt"));
			String[] nos = arquivo.readLine().split(";");
			
			for (int i = 0; i < nos.length; i++) {
				listaNos.add(Integer.parseInt(nos[i]));
			}
			
			arquivo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return listaNos;
	}
}