public class SplayTree<T extends Comparable<T>> implements Tree<T>{
    private Node<T> root;
    public Node<T> getRoot() {
        return root;
    }
    @Override
    public Node<T> insert(T value) {
        root = insert(root,new Node<>(value));
        return root;
    }
    private Node<T> insert(Node<T> node, Node<T> nodeAdded){
        if (node == null) {
            return nodeAdded;
        }
        if (nodeAdded.getValue().compareTo(node.getValue()) < 0) {
            node.setLeftChild(insert(node.getLeftChild(), nodeAdded));
            node.getLeftChild().setParentNode(node);
        } else if (nodeAdded.getValue().compareTo(node.getValue()) > 0) {
            node.setRightChild(insert(node.getRightChild(), nodeAdded));
            node.getRightChild().setParentNode(node);
        }
        return node;
    }
    @Override
    public Node<T> delete(T value) {
        root = delete(value,root);
        return root;
    }
    public Node<T> delete(T data, Node<T> node) {
        if (node == null) return null;

        if (data.compareTo(node.getValue()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
            if (node.getLeftChild() != null) node.getLeftChild().setParentNode(node);
        } else if (data.compareTo(node.getValue()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
            if (node.getRightChild() != null) node.getRightChild().setParentNode(node);
        } else {
            if (node.getLeftChild() == null) return node.getRightChild();
            else if (node.getRightChild() == null) return node.getLeftChild();
            node.setValue(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getValue(), node.getLeftChild()));
            if (node.getLeftChild() != null) node.getLeftChild().setParentNode(node);
        }
        return node;
    }

    @Override
    public Node<T> search(T value) {
        Node<T> node = root;
        while (node != null) {
            if (node.getValue().compareTo(value) == 0) {
                splay(node);
                return node;
            }
            node = value.compareTo(node.getValue()) < 0 ? node.getLeftChild() : node.getRightChild();
        }
        return null;
    }
    @Override
    public Node<T> searchRecurs(T value) {
        return search(root,value);
    }
    public Node<T> search(Node<T> node, T data) {
        if (node != null) {
            if (node.getValue().compareTo(data) == 0) {
                splay(node);
                return node;
            }
            Node<T> nextNode = data.compareTo(node.getValue()) > 0 ? node.getRightChild() : node.getLeftChild();
            search(nextNode, data);
        }
        return null;
    }
    private void splay(Node<T> node) {
        while (node != root) {
            Node<T> parent = node.getParentNode();
            if (parent != root) {
                Node<T> grandParent = parent.getParentNode();
                if (grandParent == null) {
                    if (node.isLeftChild()) {
                        rotateRight(parent);
                    } else {
                        rotateLeft(parent);
                    }
                } else if (node.isLeftChild() && parent.isLeftChild()) {
                    rotateRight(grandParent);
                    rotateRight(parent);
                } else if (node.isRightChild() && parent.isRightChild()) {
                    rotateLeft(grandParent);
                    rotateLeft(parent);
                } else if (node.isLeftChild() && parent.isRightChild()) {
                    rotateRight(parent);
                    rotateLeft(grandParent);
                } else {
                    rotateLeft(parent);
                    rotateRight(grandParent);
                }
            } else {
                if (node.isLeftChild()) {
                    rotateRight(parent);
                } else {
                    rotateLeft(parent);
                }
            }
        }
    }

    private void rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeftChild();
        node.setLeftChild(leftNode.getRightChild());
        if (node.getLeftChild() != null) {
            node.getLeftChild().setParentNode(node);
        }
        updateChildrenOfParentNode(node, leftNode);
        leftNode.setParentNode(node.getParentNode());
        leftNode.setRightChild(node);
        node.setParentNode(leftNode);
    }

    private void rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRightChild();
        node.setRightChild(rightNode.getLeftChild());
        if (node.getRightChild() != null) {
            node.getRightChild().setParentNode(node);
        }
        updateChildrenOfParentNode(node, rightNode);
        rightNode.setParentNode(node.getParentNode());
        rightNode.setLeftChild(node);
        node.setParentNode(rightNode);
    }
    private void updateChildrenOfParentNode(Node<T> node, Node<T> tempNode) {
        if (node.getParentNode() == null) {
            root = tempNode;
        } else if (node.isLeftChild()) {
            node.getParentNode().setLeftChild(tempNode);
        } else {
            node.getParentNode().setRightChild(tempNode);
        }
    }
    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node.getValue();
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getValue();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}