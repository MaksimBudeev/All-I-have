class FibNode{
    int key;
    FibNode parent;
    FibNode child;
    FibNode left;
    FibNode right;
    int degree = 0;
    boolean mark;

    public FibNode(int key) {
        this.key = key;
    }
}


public class FibHeap {
    FibNode min;
    int size = 0;

    public void insert(int key){
        FibNode node = new FibNode(key);
        node.left = node;
        node.right = node;
        fibHeapAddNodeToRootList(node, min);

        if(min == null || node.key < min.key){
            min = node;
        }

        size = size + 1;

    }

    private void fibHeapAddNodeToRootList(FibNode node, FibNode h){

        if(h == null){
            return;
        }

        if(h.left == h){
            h.left = node;
            h.right = node;
            node.right = h;
            node.left = h;
        }else{
            FibNode lnode = h.left;
            h.left = node;
            node.right = h;
            node.left = lnode;
            lnode.right = node;
        }
    }

    public int Min(){
        return min.key;
    }

    public void Union(FibHeap second){
        if(second.size == 0){
            return;
        }

        if(this.size == 0){
            this.min = second.min;
            size = second.size;
        }else {
            UnionLists(min, second.min);
            this.size += second.size;
        }

        if(second.min.key < this.min.key){
            this.min = second.min;
        }
    }

    private void UnionLists(FibNode heap1, FibNode heap2){
        FibNode left1 = heap1.left;
        FibNode left2 = heap2.left;
        left1.right = heap2;
        heap2.left = left1;
        heap1.left = left2;
        left2.right = heap1;
    }

    public void deleteMin(){
        if(this.min == null){
            return;
        }
        FibNode x = min.child;
        if(min.child != null) {
            if (x.right == x) {
                fibHeapAddNodeToRootList(x, min);
                size++;
                x.parent = null;
            } else {
                for(int i = 0; i < min.degree; i++) {
                        FibNode next = x.right;
                        fibHeapAddNodeToRootList(x, min);
                        size++;
                        x.parent = null;
                        x = next;
                }
            }
        }
        if(min.right == min){
            min = null;
            size -= 1;
        }else {
            min.right.left = min.left;
            min.left.right = min.right;
            min = min.right;
            size -= 1;
            consolidate();
        }

    }

    private void consolidate(){
        FibNode[] fibNodes = new FibNode[100]; // log(2,n) + 1;
        FibNode current = min;
        int d;
        int initRoots = size;
        int maxDegree = 0;
        for(int i = 0; i < initRoots; i++){
            d = current.degree;
            FibNode next = current.right;
            while (fibNodes[d] != null){
                FibNode y = fibNodes[d];
                if(y.key < current.key){
                    FibNode tmp = current;
                    current = y;
                    y = tmp;
                }
                heapLink(y,current);
                fibNodes[d] = null;
                d++;
            }
            fibNodes[d] = current;
            if(maxDegree < d){
                maxDegree = d;
            }
            current = next;
        }
        min = null;
        size = 0;
        for (int i = 0; i <= maxDegree; i++){
            if(fibNodes[i] != null){
                fibNodes[i].right = fibNodes[i];
                fibNodes[i].left = fibNodes[i];
                fibHeapAddNodeToRootList(fibNodes[i], min);

                if(min == null || fibNodes[i].key < min.key){
                    min = fibNodes[i];
                }

                size = size + 1;
            }
        }

    }

    private void heapLink(FibNode y , FibNode x){
        x.degree = x.degree + 1;
        y.right.left = y.left;
        y.left.right = y.right;
        y.parent = x;
        if(x.child == null){
            x.child = y;
            y.right = y;
            y.left = y;
            return;
        }
        fibHeapAddNodeToRootList(y, x.child);
        y.mark = false;
    }

    public void DecreaseKey(FibNode x, int newkey){

        if(newkey > x.key){
            return;
        }
        x.key = newkey;
        FibNode y = x.parent;
        if(y != null && x.key < y.key){
            HeapCut(x,y);
            CascadingCut(y);
        }
        if(x.key < min.key){
            min = x;
        }
    }

    private void HeapCut(FibNode x, FibNode y){
        if(x.right == x){
            y.child = null;
        }else {
            if(y.child == x){
                y.child = x.right;
            }
            x.right.left = x.left;
            x.left.right = x.right;
        }
        y.degree = y.degree - 1;
        fibHeapAddNodeToRootList(x, min);
        size++;
        x.parent = null;
        x.mark = false;
    }

    private void CascadingCut(FibNode y){
        FibNode z = y.parent;
        if(z == null){
            return;
        }
        if(y.mark == false){
            y.mark = true;
        }else{
            HeapCut(y,z);
            CascadingCut(z);
        }
    }



}

class logic{
    public static void main(String[] args) {
        FibHeap fibHeap = new FibHeap();
        fibHeap.insert(2);
        fibHeap.insert(3);
        fibHeap.insert(1);
        fibHeap.insert(6);
        fibHeap.insert(7);
        FibHeap fibHeap1 = new FibHeap();
        fibHeap1.insert(10);
        fibHeap1.insert(11);
        fibHeap1.insert(8);
        fibHeap1.insert(9);
        fibHeap1.insert(100);
        fibHeap.Union(fibHeap1);
        System.out.println(fibHeap.min.key);
        fibHeap.DecreaseKey(fibHeap.min,0);
        System.out.println(fibHeap.min.key);

    }
}
