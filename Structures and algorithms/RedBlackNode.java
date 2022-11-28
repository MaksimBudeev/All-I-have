import java.util.ArrayList;
import java.util.TreeMap;

public class redBlackNode {
    public redBlackNode left;
    public redBlackNode right;
    public redBlackNode parent;
    public boolean isRed = false;
    public int data;
    public static redBlackNode nullNode;
    public boolean isNull = false;

    public redBlackNode(int data, redBlackNode parent) {
        this.data = data;
        left = nullNode;
        right = nullNode;
        this.parent = parent;
        isRed = true;
    }

    static {
        nullNode = new redBlackNode();
        nullNode.right = nullNode;
        nullNode.left = nullNode;
        nullNode.isRed = false;
    }

    public redBlackNode() {
        this.left = null;
        this.right = null;
        this.isRed = false;
        this.isNull = true;
    }
}

class redBlackTree {
    public redBlackNode root;

    public redBlackTree(int data) {
        root = new redBlackNode(data, null);
        root.isRed = false;
    }

    public redBlackTree() {
        root = null;
    }

    public void add(int data) {
        if (root == null) {
            root = new redBlackNode(data, null);
            root.isRed = false;
            return;
        }

        redBlackNode n = AddNode(data, root);

        AddFixup(n);
        root.isRed = false;
    }

    protected void AddFixup(redBlackNode z) {
        redBlackNode u, g;

        while (z.parent.isRed == true) {
            g = Grandparent(z);
            u = Uncle(z);

            if (z.parent == g.left) {
                if (u.isRed == true) {
                    z.parent.isRed = false;
                    u.isRed = false;
                    g.isRed = true;
                    z = g;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        RotateLeft(z);
                    }

                    z.parent.isRed = false;
                    g.isRed = true;
                    RotateRight(g);
                }
            } else {
                if (u.isRed == true) {
                    z.parent.isRed = false;
                    u.isRed = false;
                    g.isRed = true;
                    z = g;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        RotateRight(z);
                    }

                    z.parent.isRed = false;
                    g.isRed = true;
                    RotateLeft(g);
                }
            }

            if (z.parent == null) {
                break;
            }
        }
        this.root.isRed = false;
    }

    protected void InsertCase1(redBlackNode n) {
        if (n.parent == null) {
            n.isRed = false;
        } else {
            this.InsertCase2(n);
        }
    }

    protected void InsertCase2(redBlackNode n) {
        if (n.parent.isRed == false) {
            return;
        } else {
            this.InsertCase3(n);
        }
    }

    protected void InsertCase3(redBlackNode n) {
        redBlackNode u = this.Uncle(n);

        if ((u != null) && (u.isRed == true)) {
            n.parent.isRed = false;
            u.isRed = false;

            redBlackNode g = Grandparent(n);
            g.isRed = true;

            this.InsertCase1(g);
        } else {
            this.InsertCase4(n);
        }
    }

    protected void InsertCase4(redBlackNode n) {
        redBlackNode g = Grandparent(n);

        if (n == n.parent.right && n.parent == g.left) {
            RotateLeft(n.parent);
            n = n.left;
        } else {
            if (n == n.parent.left && n.parent == g.right) {
                RotateRight(n.parent);
                n = n.right;
            }
        }

        this.InsertCase5(n);
    }

    protected void InsertCase5(redBlackNode n) {
        redBlackNode g = Grandparent(n);

        n.parent.isRed = false;
        n.isRed = true;

        if (n == n.parent.left && n.parent == g.left) {
            RotateRight(g);
        } else {
            RotateLeft(g);
            g.isRed = true;
        }
    }

    protected void RotateRight(redBlackNode n) {
        redBlackNode y = new redBlackNode(0, null);
        if (n.left != redBlackNode.nullNode) {
            y = n.left;
            n.left = y.right;


            if (y.right != null)
                y.right.parent = n;
            y.parent = n.parent;
            if (n.parent == null)
                root = y;
            else if (n == n.parent.right)
                n.parent.right = y;
            else
                n.parent.left = y;
            y.right = n;
            n.parent = y;
        } else {
            y = n.parent;
            y.parent.right = n;
            n.parent = n;
            n.parent.right = y;
            n.parent = y.parent;
            y.parent = n;
            y.left = null;
        }
    }

    protected void RotateLeft(redBlackNode n) {
        redBlackNode y = new redBlackNode(0, null);
        if (n.right != null) {
            y = n.right;
            n.right = y.left;

            if (y.left != null)
                y.left.parent = n;
            y.parent = n.parent;
            if (n.parent == null)
                root = y;
            else if (n == n.parent.left)
                n.parent.left = y;
            else
                n.parent.right = y;
            y.left = n;
            n.parent = y;
        } else {
            y = n.parent;

            y.parent.left = n;
            n.parent = n;
            n.parent.left = y;
            n.parent = y.parent;
            y.parent = n;
            y.right = null;
        }
    }

    protected redBlackNode Grandparent(redBlackNode n) {
        if ((n != null) && (n.parent != null))
            return n.parent.parent;
        else
            return null;
    }

    protected redBlackNode Uncle(redBlackNode n) {
        redBlackNode g = Grandparent(n);
        if (g == null)
            return null;
        if (n.parent == g.left)
            return g.right;
        else
            return g.left;
    }

    protected redBlackNode AddNode(int data, redBlackNode to) {
        if (data == to.data) {
            return to;
        }

        if (data < to.data) {
            if (to.left != redBlackNode.nullNode)
                return AddNode(data, to.left);
            return to.left = new redBlackNode(data, to);
        } else {
            if (to.right != redBlackNode.nullNode)
                return AddNode(data, to.right);
            return to.right = new redBlackNode(data, to);
        }
    }

    public void printTree() {
        if (root == null)
            return;

        print(root);
    }

    protected void print(redBlackNode redBlackNode) {
        if (redBlackNode == null || redBlackNode == redBlackNode.nullNode)
            return;

        print(redBlackNode.left);
        String line = String.valueOf(redBlackNode.data);

        if (redBlackNode.isRed == true) {
            line += " red";
        } else {
            line += " black";
        }

        if (redBlackNode.parent != null)
            line += " parent: " + String.valueOf(redBlackNode.parent.data);

        System.out.println(line);//?????

        print(redBlackNode.right);
    }

    public redBlackNode Lookup(int data, redBlackNode root) {
        if (root.data == data)
            return root;

        if (data < root.data)
            return Lookup(data, root.left);

        if (data > root.data)
            return Lookup(data, root.right);
        else
            return null;
    }

    private redBlackNode Sibling(redBlackNode n) {
        if (n == n.parent.left) {
            return n.parent.right;
        } else {
            return n.parent.left;
        }
    }

    private redBlackNode min(redBlackNode root) {
        if (root.left != redBlackNode.nullNode) {
            return min(root.left);
        } else {
            return root;
        }
    }

    public redBlackNode Min() {
        return min(this.root);
    }

    public redBlackNode Max() {
        return max(this.root);
    }

    private redBlackNode max(redBlackNode root) {
        if (root.right != redBlackNode.nullNode) {
            return max(root.right);
        } else {
            return root;
        }
    }

    public void delete(int data) {
        redBlackNode z = Lookup(data, this.root);

        if (z == null) {
            return;
        }

        redBlackNode y = z;
        redBlackNode x;
        boolean yColor = y.isRed;

        if (z.left == redBlackNode.nullNode) {
            x = z.right;
            replaceNode(z, z.right);
        } else {
            if (z.right == redBlackNode.nullNode) {
                x = z.left;
                replaceNode(z, z.left);
            } else {
                y = min(z.right);
                yColor = y.isRed;
                x = y.right;

                if (y.parent == z) {
                    x.parent = y;
                } else {
                    replaceNode(y, y.right);
                    y.right = z.right;
                    y.right.parent = y;
                }
            }
            replaceNode(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.isRed = z.isRed;
        }

        if (yColor == false) {
            DeleteFixup(x);
        }
    }

    private void DeleteFixup(redBlackNode x) {
        redBlackNode w;

        while (x != this.root && x.isRed == false) {
            w = Sibling(x);

            if (w.isRed == true) {
                w.isRed = false;
                x.parent.isRed = true;

                if (x == x.parent.left) {
                    RotateLeft(x.parent);
                    w = x.parent.right;
                } else {
                    RotateRight(x.parent);
                    w = x.parent.left;
                }
            }


            if (w.left.isRed == false &&
                    w.right.isRed == false) {
                w.isRed = true;
                x = x.parent;
            } else {
                if (w.right.isRed == false) {
                    w.left.isRed = false;
                    w.isRed = true;

                    if (x == x.parent.left) {
                        RotateRight(w);
                        w = x.parent.right;
                    } else {
                        RotateLeft(w);
                        w = x.parent.left;
                    }
                }

                w.isRed = x.parent.isRed;
                x.parent.isRed = false;
                w.right.isRed = false;

                if (x == x.parent.left) {
                    RotateLeft(x.parent);
                } else {
                    RotateRight(x.parent);
                }
                x = this.root;
            }
        }
        x.isRed = false;
    }

    private void replaceNode(redBlackNode u, redBlackNode v) {
        if (u.parent == null) {
            this.root = v;
        } else {
            if (u == u.parent.left) {
                u.parent.left = v;
            } else {
                u.parent.right = v;
            }
        }

        v.parent = u.parent;
    }

    protected int maxDepth(redBlackNode node) {
        if (node.isNull == true) {
            return 0;
        } else {
            int rDepth = maxDepth(node.left);
            int lDepth = maxDepth(node.right);

            if (lDepth > rDepth) {
                return lDepth + 1;
            } else {
                return rDepth + 1;
            }
        }
    }

}


class Tree {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        redBlackTree tree = new redBlackTree();

        for (int i = 0; i < 50000; i++) {
            list.add(i);
        }
        for (int i = 0; i < list.size(); i++) {
            tree.add(list.get(i));
        }

        System.out.println(tree.maxDepth(tree.root));
        //tree.printTree();

    }
}

