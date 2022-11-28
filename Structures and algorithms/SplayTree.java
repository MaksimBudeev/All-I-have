import java.util.ArrayList;

public class Node {
    Node left, right, parent;
    int value;

    public Node(){

    }

    public Node(int value){
        this(value,null,null,null);
    }

    public Node(int value, Node left, Node right, Node parent)
    {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.value = value;
    }
}

class SplayTree{
    Node root;

    public SplayTree()
    {
        root = null;
    }

    public void insert(int val)
    {
        Node z = root;
        Node p = null;
        while (z != null)
        {
            p = z;
            if (val > p.value)
                z = z.right;
            else
                z = z.left;
        }
        z = new Node();
        z.value = val;
        z.parent = p;
        if (p == null) {
            root = z;
        } else if (val > p.value) {
            p.right = z;
        } else {
            p.left = z;
        }
        Splay(z);
    }

    public void makeLeftChildParent(Node c, Node p)
    {

        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.right != null) {
            c.right.parent = p;
        }

        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }
    
    public void makeRightChildParent(Node c, Node p)
    {
        if (p.parent != null)
        {
            if (p == p.parent.left)
                p.parent.left = c;
            else
                p.parent.right = c;
        }
        if (c.left != null) {
            c.left.parent = p;
        }
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    private void Splay(Node x) {

        while (x.parent != null) {
            Node Parent = x.parent;
            Node GrandParent = Parent.parent;
            //zig
            if (GrandParent == null) {
                if (x == Parent.left)
                    makeLeftChildParent(x, Parent);
                else
                    makeRightChildParent(x, Parent);
            } else {
                if (x == Parent.left) {
                    //zig-zig
                    if (Parent == GrandParent.left) {
                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    }
                    //zig-zag
                    else {
                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                }
                else {
                    //zig-zag
                    if (Parent == GrandParent.left) {
                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    }
                    //zig-zig
                    else {
                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }

    public void delete(int val)
    {
        Node node = lookup(val);
        delete(node);
    }

    private void delete(Node node)
    {
        if (node == null)
            return;

        Splay(node);
        if(node.left != null && node.right !=null) {
            Node min = node.left;
            while(min.right!=null) {
                min = min.right;
            }
            node.left.parent = null;
            Splay(min);
            min.right = node.right;
            node.right.parent = min;
        }
        else if (node.right != null) {
            node.right.parent = null;
            root = node.right;
        }
        else if( node.left !=null) {
            node.left.parent = null;
            root = node.left;
        }
        else {
            root = null;
        }
        node.parent = null;
        node.left = null;
        node.right = null;
        node = null;
    }

    private Node lookup(int val) {
        Node PrevNode = null;
        Node z = root;
        while (z != null) {
            PrevNode = z;
            if (val > z.value)
                z = z.right;
            else if (val < z.value)
                z = z.left;
            else if(val == z.value) {
                Splay(z);
                return z;
            }

        }
        if(PrevNode != null)
        {
            Splay(PrevNode);
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        SplayTree splayTree = new SplayTree();
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Добавление");
        int i = 0;
        int j = 49999;
        long startTime = System.nanoTime();
        while (i!=24000){
            splayTree.insert(i);
            splayTree.insert(j);
            i++;
            j--;
        }

        System.out.println(System.nanoTime() - startTime);

        System.out.println("Поиск");
        startTime = System.nanoTime();
        splayTree.lookup(23000);
        System.out.println(System.nanoTime() - startTime);

        System.out.println("Поиск последнего");
        startTime = System.nanoTime();
        splayTree.lookup(splayTree.root.value);
        System.out.println(System.nanoTime() - startTime);

    }
}
