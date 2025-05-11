// arquivo: src/apl2/DLinkedList.java

//Daniel Borges Valentim - 10427564 
//Enzo Pinheiro de Oliveira - 10434443
//João Vitor Golfieri Mendonça - 10435460

package apl2;

// -- A classe DLinkedList (que pertence ao pacote apl2) deve implementar uma
// lista duplamente encadeada. Os nós dessa lista são do tipo [da classe] Node.
// -- A classe deve possuir dois nós especiais, head e tail, que são
// referências para o primeiro e último nó da lista, respectivamente.
// -- A classe deve possuir um contador de quantos nós existem na lista.
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com o conteúdo da lista.
// -- A classe deve implementar as operações a seguir, respeitando o
// comportamento descrito em cada operação.

public class DLinkedList {
	private Node head;
	private Node tail;
	private int count;

// OPERAÇÃO:		Método construtor
// COMPORTAMENTO:	Cria uma lista vazia.
	public DLinkedList() {
		head = null;
		tail = null;
		count = 0;
	}

// OPERAÇÃO:		insert(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no início da lista.
	public void insert(String id, String nome, float nota) {
		Node node = new Node(id, nome, nota, null, head);

		if (isEmpty()) {
			tail = node;
		} 
		else {
			head.setPrev(node);
		}
		head = node;
		count++;
	}

// OPERAÇÃO:		append(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no final da lista.
	public void append(String id, String nome, float nota) {
		Node node = new Node(id, nome, nota, tail, null);

		if (isEmpty()) {
			head = node;
		}
		else { 
			tail.setNext(node);
		}
		tail = node;
		count++;
	}


// OPERAÇÃO: 		removeHead()
// COMPORTAMENTO:	Remove o nó do início da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeHead() {
		if (isEmpty()) {
			return null;
		}

		Node removed = head; 
		head = head.getNext();

		if (head != null) {
			head.setPrev(null);
		}
		else {
			tail = null;
		}

		removed.setNext(null);
		count--;
		return removed;
	}

// OPERAÇÃO:		removeTail()
// COMPORTAMENTO:	Remove o nó do final da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeTail() {
		if (isEmpty()) {
			return null;
		}

		Node removed = tail; 
		tail = tail.getPrev();

		if (tail != null) {
			tail.setNext(null);
		} 
		else {
			head = null;
		}

		removed.setPrev(null); 
		count--;
		return removed;
	}

// OPERAÇÃO:		removeNode(<ID da pessoa>)
// COMPORTAMENTO:	Remove o nó que contém o <ID da pessoa> da lista e retorna
//					a referência do nó removido.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node removeNode(String id) {
		Node current = head; 

		while (current != null) {
			if (current.getId().equals(id)) {
				if (current == head) return removeHead();
				if (current == tail) return removeTail();

				current.getPrev().setNext(current.getNext());
				current.getNext().setPrev(current.getPrev());
				current.setNext(null);
				current.setPrev(null);
				count--;
				return current;
			}
			current = current.getNext();
		}

		return null;
	}

// OPERAÇÃO:		getHead()
// COMPORTAMENTO:	Retorna uma referência para o nó do início da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getHead() {
		return head;
	}

// OPERAÇÃO:		getTail()
// COMPORTAMENTO:	Retorna uma referência para o nó do final da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getTail() {
		return tail;
	}

// OPERAÇÃO:		getNode(<ID da pessoa>)
// COMPORTAMENTO:	Retorna uma referência para o nó que contém o <ID da pessoa>
//					da lista.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node getNode(String id) {
		Node current = head;
		while (current != null) {
			if(current.getId().equals(id)) {
				return current;
			}
			current = current.getNext();
		}
		return null;
	}

// OPERAÇÃO:		count()
// COMPORTAMENTO:	Retorna a quantidade de nós da lista.
	public int count() {
		return count;
	}

// OPERAÇÃO:		isEmpty()
// COMPORTAMENTO:	Retorna true se a lista estiver vazia ou false, caso contrário.
	public boolean isEmpty() {
		return count == 0;
	}

// OPERAÇÃO:		clear()
// COMPORTAMENTO:	Esvazia a lista, liberando a memória de todos os nós da lista.
	public void clear() {
		while(!isEmpty()) {
			removeHead();
		}
	}

// OPERAÇÃO:		toString()
// COMPORTAMENTO:	Retorna uma string com o conteúdo da lista (caso queira, use o
//					exemplo do método toString() da classe LinkedListOriginal).
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(count).append(") \n");

		Node current = head;
		while (current != null) {
			sb.append(current.toString()).append("\n");
			current = current.getNext();
		}
		return sb.toString();
	}
}