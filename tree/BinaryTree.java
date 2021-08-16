package tree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree<T> {
    private Node<T> root;
    private int size;

    public BinaryTree() {
        root = new Node<>();
    }

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    /**
     * 遍历二叉树
     *
     * @recursionPreorder 递归实现前序遍历
     * @recursionInorder 递归实现中序遍历
     * @recursionPostorder 递归实现后序遍历
     * @iterativePreorder 非递归实现前序遍历
     */
    // 递归实现前序遍历
    public ArrayList<T> recursionPreorder(Node<T> root, ArrayList<T> ans) {
        if (root != null) {
            ans.add(root.binaryTree);
            recursionPreorder(root.left, ans);
            recursionPreorder(root.right, ans);
            return ans;
        } else {
            return null;
        }
    }

    // 递归实现中序遍历
    public ArrayList<T> recursionInorder(Node<T> root, ArrayList<T> ans) {
        if (root != null) {
            recursionInorder(root.left, ans);
            ans.add(root.binaryTree);
            recursionInorder(root.right, ans);
            return ans;
        } else {
            return null;
        }
    }

    // 递归实现后序遍历
    public ArrayList<T> recursionPostorder(Node<T> root, ArrayList<T> ans) {
        if (root != null) {
            recursionPostorder(root.left, ans);
            recursionPostorder(root.right, ans);
            ans.add(root.binaryTree);
            return ans;
        } else {
            return null;
        }
    }

    // 非递归实现前序遍历(迭代)
    public ArrayList<T> iterativePreorder() {
        Stack<Node> stack = new Stack<>();
        ArrayList<T> ans = new ArrayList<>();
        Node<T> temp = root;
        if (temp != null) {
            stack.push(temp);
            while (!stack.empty()) {
                temp = stack.pop();
                ans.add(temp.binaryTree);
                if (temp.getRight() != null) {
                    stack.push(temp.getRight());
                }
                if (temp.getLeft() != null) {
                    stack.push(temp.getLeft());
                }
            }
            return ans;
        } else {
            return null;
        }

    }

    // 非递归实现 前序遍历(Morris)
    public ArrayList<T> MorrisPreorder(){
        ArrayList<T> ans=new ArrayList<>();
        if(root==null){
            return ans;
        }else {
            Node<T> move = root;
            Node<T> temp = null;
            while (move!=null){
                temp = move.left;
                if(temp!=null){
                    while (temp.right!=null&&temp.right!=move){
                        temp=temp.right;
                    }
                    if(temp.right==null){
                        ans.add(move.binaryTree);
                        temp.right=move;
                        move = move.left;
                        continue;
                    }else {
                        temp.right = null;
                    }

                }else {
                    ans.add(move.binaryTree);
                }
                move = move.right;
            }
        return ans;
        }


    }

    // 非递归实现后序遍历
    public ArrayList<T> iterativePostorder() {
        if (root == null) {
            return null;
        } else {
            Stack<Node> stack = new Stack<>();
            ArrayList<T> ans = new ArrayList<>();
            Node<T> write = root;
            Node<T> temp = root;
            while (temp != null) {
                // 左子树入栈
                while (temp.getLeft() != null) {
                    stack.push(temp);
                    temp = temp.getLeft();
                }
                // 当前结点无右子结点或右子结点已经输出
                while (temp != null && (temp.getRight() == null || temp.getRight() == write)) {
                    ans.add(temp.binaryTree);
                    write = temp;
                    if (stack.empty()) {
                        return ans;
                    }
                    temp = stack.pop();
                }
                //处理右节点
                stack.push(temp);
                temp = temp.getRight();

            }
            return ans;
        }
    }

    // 非递归实现 后序遍历(Morris)
    public ArrayList<T> MorrisPostorder(){
        ArrayList<T> ans = new ArrayList<>();
        if (root==null){
            return null;
        }else {
            Node<T> move = root;
            Node<T> temp = null;
            while (move!=null){
                temp=move.left;
                if(temp!=null){
                    while (temp.right!=null&&temp.right!=move){
                        temp=temp.right;
                    }
                    if(temp.right==null){
                        temp.right=move;
                        move=move.left;
                        continue;
                    }else {
                        temp.right=null;
                        MorrisPostorderAddPath(ans,move.left);
                    }
                }
                move= move.right;
            }
            MorrisPostorderAddPath(ans,root);
            return ans;
        }
    }

    private void MorrisPostorderAddPath(ArrayList<T> ans, Node<T> node) {
        int count = 0;
        while (node != null) {
            ++count;
            ans.add(node.binaryTree);
            node = node.right;
        }
        int left = ans.size() - count, right = ans.size() - 1;
        while (left < right) {
            T temp = ans.get(left);
            ans.set(left, ans.get(right));
            ans.set(right, temp);
            left++;
            right--;
        }
    }

    // 非递归实现中序遍历
    public ArrayList<T> iterativeInorder() {
        if (root == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        ArrayList<T> ans = new ArrayList<>();
        Node<T> temp = root;

        while (temp != null) {
            while (temp != null) {
                if (temp.getRight() != null) {
                    stack.push(temp.getRight());
                }
                stack.push(temp);
                temp = temp.getLeft();
            }
            temp = stack.pop();
            while (!stack.empty() && temp.getRight() == null) {
                ans.add(temp.binaryTree);
                temp = stack.pop();
            }
            ans.add(temp.binaryTree);
            if (!stack.empty()) {
                temp = stack.pop();
            } else {
                temp = null;
            }


        }
        return null;
    }

    //非递归实现中序遍历
    public ArrayList<T> MorrisInorder(){
        ArrayList<T> ans = new ArrayList<>();
        Node predecessor = null;
        while (root!=null){
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    ans.add(root.binaryTree);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                ans.add(root.binaryTree);
                root = root.right;
            }

        }
        return ans;

    }


    /**
     * 清空树
     *
     * @recursionClear 递归清空
     * @iterativeClear 非递归
     * @clear 清除根节点
     */
    public void recursionClear(Node<T> node) {
        if (node != null) {
            recursionClear(node.left);
            recursionClear(node.right);
            node = null;
        }
    }

    public void iterativeClear(Node<T> node) {
        Stack<Node> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
            while (!stack.empty()) {
                node = stack.pop();
                //ans.add(temp.binaryTree);
                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
                if (node.getLeft() != null) {
                    stack.push(node.getLeft());
                }
                if (node.getRight() == null && node.getLeft() == null) {
                    node = null;
                }
            }
        }

    }

    public void clear() {
        recursionClear(root);
    }

    //判断二叉树是否为空
    public boolean isEmpty() {
        return root == null;
    }

    //获取以某节点为子树的高度
    public int height(Node<T> node) {
        if (node == null) {
            return 0; //递归结束,空子树高度为0
        } else {
            //递归获取左子树高度
            int l = height(node.getLeft());
            //递归获取右子树高度
            int r = height(node.getRight());
            //高度应该算更高的一边，（+1是因为要算上自身这一层）
            return l > r ? (l + 1) : (r + 1);
        }
    }

    //获取结点的子结点树
    public int size(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.getLeft()) + size(node.getRight());
        }
    }

    //node节点在root树中的父节点
    public Node<T> getParent(Node<T> root, Node<T> node) {
        if (root == null) {
            return null;
        }
        if (root.getLeft() == node || root.getRight() == node) {
            return root;   //如果子树的根节点的左右孩子之一是待查节点，则返回子树的根节点
        }
        Node<T> parent = null;
        if (getParent(root.getLeft(), node) != null) {
            parent = getParent(root.getLeft(), node);
            return parent;
        } else {
            //递归左右子树
            return getParent(root.getRight(), node);
        }
    }

    public void insert(T t) {
        Node<T> newNode = new Node<>(t);
        if (root == null) {
            root = newNode;
            root.setLeft(null);
            root.setRight(null);
        } else {
            Node<T> moveNode = root;
            Node<T> parentNode;
            while (true) {
                parentNode = moveNode;
                // 往右放
                if (newNode.compareNode(moveNode)) {
                    moveNode = moveNode.getRight();
                    if (moveNode == null) {
                        parentNode.setRight(newNode);
                        return;
                    }
                } else {
                    // 往左放
                    moveNode = moveNode.getLeft();
                    if (moveNode == null) {
                        parentNode.setLeft(newNode);
                        return;
                    }
                }
            }
        }
    }

    //查找节点(不比较)
    public Node<T> Select(Node<T> root, T t) {
        Node<T> ans = null;
        Stack<Node<T>> stack = new Stack<>();
        while (!stack.empty() || root != null) {
            if (root != null) {
                if (root.binaryTree.equals(t)) {
                    ans = root;
                }
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (root.binaryTree.equals(t)) {
                    ans = root;
                }
                root = root.right;
            }
        }
        return ans;
    }

    //删除节点
    public void Delete(Node<T> root, T t) {
        Node<T> delete = new Node<>(t);
        Node<T> move = root;
        Node<T> parent = root;
        if (move == null) {
            System.out.println("null");
        } else {
            while (move != null) {
                if (move.compareNode(delete)) {
                    parent = move;
                    move = move.left;
                } else if (delete.compareNode(move)) {
                    parent = move;
                    move = move.right;
                } else {
                    delete = move;
                    if (move.left != null && move.right != null) {
                        Node<T> write = move;
                        //删除的节点同时存在左子树和右子树
                        //将删除节点的左子树的最右节点或右子树的最左节点替换删除节点
                        while (move.right != null) {
                            write = move.right;
                            move = move.left;
                        }
                        delete.setBinaryTree(write.binaryTree);
                        write = null;
                        size--;
                        break;
                    } else if (move.left != null || move.right != null) {
                        if (move.right != null) {
                            if (parent.right == move) {
                                parent.right = delete.right;
                            } else {
                                parent.left = delete.right;
                            }

                        } else {
                            if (parent.right == move) {
                                parent.right = delete.left;
                            } else {
                                parent.left = delete.left;
                            }

                        }
                        delete = null;
                    } else {
                        move = null;
                    }
                    size--;
                }
            }

        }
    }

    public class Node<T> {
        private T binaryTree;
        private Node<T> left, right;

        public Node() {
            binaryTree = null;
            left = null;
            right = null;
        }

        public Node(T t) {
            this(t, null, null);
        }

        public Node(T t, Node left, Node right) {
            binaryTree = t;
            this.left = left;
            this.right = right;
        }

        public boolean compareNode(Node<T> compare) {
            return binaryTree.hashCode() > compare.binaryTree.hashCode();
        }

        public T getBinaryTree() {
            return binaryTree;
        }

        public void setBinaryTree(T binaryTree) {
            this.binaryTree = binaryTree;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }
    }
}