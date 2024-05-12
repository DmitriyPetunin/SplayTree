
public interface Tree<T extends Comparable<T>> {
    Node<T> insert(T t);
    Node<T> delete(T t);
    Node<T> search(T t);
    Node<T> searchRecurs(T t);
    T getMin();
    T getMax();
    boolean isEmpty();
}
