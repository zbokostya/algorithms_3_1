package Tree;

public class MyBinaryTree {

    public static class Tree {

        Node root;

        public Tree() {
            this.root = null;
        }

        public void printPostorderRoot() {
            printPostorder(root);
        }

        private void printPostorder(Node node) {
            if (node == null)
                return;

            printPostorder(node.left);
            printPostorder(node.right);

            System.out.print(node.val + " ");
        }

        public int lookupFromTop(int value) {
            return lookupFromTop(root, value);
        }

        private int lookupFromTop(Node current, int value) {
            if (current.val == value) return value;
            if (current.val > value) {
                if (current.left != null && lookupFromTop(current.left, value) >= value)
                    return lookupFromTop(current.left, value);
                else return current.val;
            } else {
                if (current.right != null) return lookupFromTop(current.right, value);
                else return current.val;
            }
        }

        public int lookupFromBottom(int value) {
            return lookupFromBottom(root, value);
        }
        private int lookupFromBottom(Node current, int value) {
            if (current.val == value) return value;
            if (current.val < value) {
                if (current.right != null && lookupFromBottom(current.right, value) <= value)
                    return lookupFromBottom(current.right, value);
                else return current.val;
            } else {
                if (current.left != null) return lookupFromBottom(current.left, value);
                else return current.val;
            }
        }

        public static class Node {
            Node left;
            Node right;
            int val;

            public Node(int val) {
                this.val = val;
                left = null;
                right = null;
            }

            public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
                if (right != null) {
                    right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
                }
                sb.append(prefix).append(isTail ? "└── " : "┌── ").append(val).append("\n");
                if (left != null) {
                    left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
                }
                return sb;
            }

            @Override
            public String toString() {
                return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
            }

        }

        public void insert(int val) {
            root = insert(root, val);
        }

        public Node insert(Node node, int val) {
            if (node == null) {
                node = new Node(val);
                return node;
            }
            if (val < node.val) {
                node.left = insert(node.left, val);
            } else if (val > node.val) {
                node.right = insert(node.right, val);
            }
            return node;
        }

        public Node find(int key, Node node) {
            if (node == null || node.val == key) return node;
            if (node.val > key) return find(key, node.left);
            return find(key, node.right);
        }

        public Node find(int val) {
            return find(val, root);
        }


    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = (int) (Math.random() * 180);
        }

        for (int i = 0; i < 20; i++) {
            tree.insert(arr[i]);
        }

        System.out.println(tree.root.toString());
        System.out.println(tree.find(arr[19]));
    }
}
