// arquivo: src/apl2/Node.java

//Daniel Borges Valentim - 10427564 
//Enzo Pinheiro de Oliveira - 10434443
//João Vitor Golfieri Mendonça - 10435460

package apl2;

// -- A classe Node (que pertence ao pacote apl2) deve conter os atributos que
// representam a nova versão dos dados de uma pessoa, conforme descrito no
// enunciado da atividade Apl2.
// -- A classe deve conter os construtores apropriados, assim como os métodos
// getters e setters.
// -- A classe também representa um nó que é usado na implementação da lista
// duplamente encadeada (classe DLinkedList).
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com os valores dos atributos da classe.

public class Node {
	
	private String id;
	private String nome;
	private float nota;
	private Node prev;
	private Node next;


	public Node() {
		this("", "", 99.9f, null, null);
	}

	public Node(String id, String nome, float nota, Node prev, Node next) {
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.prev = prev;
		this.next = next;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}
	
	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	@Override 
	public String toString() {
		String idPrev = (prev != null) ? prev.getId() : "null";
		String idNext = (next != null) ? next.getId() : "null"; 
		return idPrev + " <- (" + id + "; " + nome + "; " + nota + ") -> " + idNext;
	}
}