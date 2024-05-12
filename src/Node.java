public class Node<T> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private Node<T> parentNode;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }
    public Node<T> getGrandParent() {
        return parentNode != null ? parentNode.getParentNode() : null;
    }
    public boolean isLeftChild() {
        return this == parentNode.getLeftChild();
    }

    public boolean isRightChild() {
        return this == parentNode.getRightChild();
    }
    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public Node<T> getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node<T> parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return "value=" + value;
    }
}
