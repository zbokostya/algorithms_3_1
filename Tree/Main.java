package Tree;

public class Main {
    public static void main(String[] args) {
        MyBinaryTree.Tree tree = new MyBinaryTree.Tree();
        for (int j = 0; j < 20; j++) {
            tree.insert((int) (Math.random() * 180));
        }
        tree.printPostorderRoot();
        System.out.println();
        int cnt = (int)(Math.random() * 180);
        System.out.println(cnt + " " + tree.lookupFromBottom(cnt));
        System.out.println();

        System.out.println(tree.root.toString());
//        long[] time = new long[20];
//        for (int i = 0; i < 20; i++) {
//            tree = new Lab5.Tree();
//            int nodeCount = 100 + i * 10000;
//            for (int j = 0; j < nodeCount; j++) {
//                tree.insert((int) (Math.random() * 180));
//            }
//            long timeStart = System.nanoTime();
//            for (int j = 0; j < nodeCount; j++) {
//                tree.find((int) (Math.random() * 180));
//
//            }
//            long timeEnd = System.nanoTime();
//            time[i] = timeEnd - timeStart;
//        }

//        Arrays.sort(time);
//        System.out.println(Arrays.toString(time));
    }
}
