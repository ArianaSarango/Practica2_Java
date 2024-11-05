package controller.tda.list;

import controller.tda.list.LinkedList;

public class LinkedList<E> {
    private Node<E> header;
    private Node<E> last;
    private Integer size;

    public LinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    public Boolean isEmpty() {
        return (this.header == null || this.size == 0);
    }

    protected void addHeader(E dato) { 
        Node<E> help; 

        if (isEmpty()) {
            help = new Node<>(dato);
            this.header = help;
            this.last = help;
        } else {
            Node<E> healpHeader = this.header;
            help = new Node<>(dato, healpHeader);
            this.header = help;
        }
        this.size++;
    }

    private void addLast(E info) {
        Node<E> help; 
        if (isEmpty()) {
            help = new Node<>(info);
            this.header = help;
            this.last = help;
        } else {
            help = new Node<>(info, null);
            last.setNext(help);
            last = help;
        }
        this.size++;
    }

    public void add(E info) {
        addLast(info);
    }

    private Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, List empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == (this.size - 1)) {
            return last;
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    public E getFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        }
        return header.getInfo();
    }

    public E getLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista Vacia");
        }
        return last.getInfo();
    }

    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, list empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header.getInfo();
        } else if (index.intValue() == (this.size - 1)) {
            return last.getInfo();
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search.getInfo();
        }
    }

    public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty() || index.intValue() == 0) {
            addHeader(info);
        } else if (index.intValue() == this.size.intValue()) {
            addLast(info);
        } else {
            Node<E> search_preview = getNode(index - 1);
            Node<E> search = getNode(index);
            Node<E> help = new Node<>(info, search);
            search_preview.setNext(help);
            this.size++;
        }
    }

    public void update(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("La lista está vacía");
        }
        
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Índice fuera de límites");
        }
    
        Node<E> help = getNode(index);
        help.setInfo(info); 
    }

    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("List data");
        try {
            Node<E> help = header;
            while (help != null) {
                sb.append(help.getInfo()).append(" ->");
                help = help.getNext();
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    public Integer getSize() {
        return this.size;
    }

    public Node<E> getHeader() {
        return header;
    }

    public E[] toArray() {
        E[] matrix = null;
        if (!isEmpty()) {
            Class clazz = header.getInfo().getClass();
            matrix = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
            Node<E> aux = header;
            for (int i = 0; i < this.size; i++) {
                matrix[i] = aux.getInfo();
                aux = aux.getNext();
            }
        }
        return matrix;
    }

    public LinkedList<E> toList(E[] matrix) {
        reset();
        for (int i = 0; i < matrix.length; i++) {
            this.add(matrix[i]);
        }
        return this;
    }

    public int getLength() {
        return this.size.intValue();
    }

    protected void removeLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, no puede eliminar datos de una lista vacia.");
        } else {
            Node<E> nodo_last = getNode((getLength() - 2));
            nodo_last.setNext(null);
            this.last = nodo_last;
            this.size--;
        }
    }

    public void removeFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, no puede eliminar datos de una lista vacia.");
        } else {
            Node<E> help = this.header;
            Node<E> nextHeader = help.getNext();
            this.header = nextHeader;
            this.size--;
        }
    }

    public void remove(int index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Lista vacia, no puede eliminar elementos");
        } else if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Índice fuera de límites: " + index);
        } else if (index == 0) {
            removeFirst();
        } else if (index == (this.size - 1)) {
            removeLast();
        } else {
                Node <E> nodoDeath = getNode(index);
                Node <E> previousNode = getNode(index - 1);
                previousNode.setNext(nodoDeath.getNext());
                this.size--;
        }
    }

    protected E deleteLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, no puede eliminar datos de una lista vacia.");
        } else {
            E element = last.getInfo();
            Node<E> aux = getNode(size - 2);
            if(aux == null){
                last = null;
                if(size == 2) {
                    last = header;
                } else {
                    header = null;
                }
            } else {
                last = null;
                last = aux;
                last.setNext(null);
            }
            size--;
            return element;
        }
    }

    public E deleteFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, no puede eliminar datos de una lista vacia.");
        } else {
            E element = header.getInfo();
            Node<E> aux = header.getNext();
            header = aux;
            if(size.intValue() == 1){
                last = null;
            }
            size--;
            return element;
        }
    }

    public E delete(Integer post) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Lista vacia, no puede eliminar elementos");
        } else if (post < 0 || post >= this.size) {
            throw new IndexOutOfBoundsException("Índice fuera de límites: " + post);
        } else if (post == 0) {
            return deleteFirst();
        } else if (post == (this.size - 1)) {
            return deleteLast();
        } else {
            Node <E> preview = getNode(post - 1);
            Node <E> actually = getNode(post);
            E element = preview.getInfo();
            Node <E> next = actually.getNext();
            actually = null;
            preview.setNext(next);
            size--;
            return element;
        }
    }
}