
/******************************************************
 * AVL ADT.
 * Supported operations:
 * Insert
 * Delete
 * Find
 * Min
 * Max
 * Depth
 * Print
 ******************************************************/
public class AVL {
    private AVLNode root;      /* Pointer to the root of the tree */
    private int noOfNodes;     /* No of nodes in the tree */
    private AVLNode removal;

    /*******************************************************
     * Constructor: Initializes the AVL
     *******************************************************/
    public AVL() { root = null;noOfNodes = 0;}

    /*******************************************************
     * Returns a pointer to the root of the tree
     *******************************************************/
    public AVLNode Root() { return root;}

    /*******************************************************
     * Returns the number of nodes in the tree
     *******************************************************/
    public int NoOfNodes() { return noOfNodes;}

  AVLNode leftRotate(AVLNode y) {
    AVLNode x = y.right;
    y.right = x.left;
    x.left = y;
    if (y == root) {
      root = x;
    }
    return x;
  }

  AVLNode rightRotate(AVLNode y) {
    AVLNode x = y.left;
    y.left = x.right;
    x.right = y;
    if (y == root) {
      root = x;
    }
    return x;
  }

  public int getBalance(AVLNode node) {
    if (node == null) {
      return 0;
    }
    return maxDepth(node.left) - maxDepth(node.right);
  }
  public AVLNode minValueNode(AVLNode node) {
    AVLNode current = node;
    while (current.right != null) {
      current = current.right;
    }
    return current;
  }
    /*******************************************************
     * Inserts the key into the AVL. Returns a pointer to
     * the inserted node
     *******************************************************/
    public AVLNode Insert(int key) {
      return inst(root, key);
    } //end-Insert

  AVLNode inst(AVLNode node, int key) {

    if (node == null) {
      node = new AVLNode(key);
      AVLNode temp = Find(key);
      if (temp != null && temp.key == key) {
        node.count++;
      } else noOfNodes++;
    } else if (key < node.key) {
      node.left = inst(node.left, key);
      if (maxDepth(node.left) - maxDepth(node.right) == 2)
        if (key < node.left.key)
          node = rightRotate(node);
        else
          node = doubleRotateLeft(node);
    } else {
      if (node.key == key) {
        node.count++;
      }
      node.right = inst(node.right, key);
      if (maxDepth(node.right) - maxDepth(node.left) == 2)
        if (key >= node.right.key)
          node = leftRotate(node);
        else
          node = doubleRotateRight(node);
    }
    if (root == null)
      root = node;
    return node;
  }

    /*******************************************************
     * Deletes the key from the tree (if found). Returns
     * 0 if deletion succeeds, -1 if it fails
     *******************************************************/
    public int Delete(int key) {
        AVLNode node = root;
        dlt(node, key);
        if (removal == null) {
            return -1;
        } else {
            removal = null;
            return 0;
        }
    } //end-Delete

  AVLNode doubleRotateLeft(AVLNode node) {
    node.left = leftRotate(node.left);
    return rightRotate(node);
  }

  AVLNode doubleRotateRight(AVLNode node) {
    node.right = rightRotate(node.right);
    return leftRotate(node);
  }

  AVLNode dlt(AVLNode node, int key) {
    if (node == null)
      return null;
    if (key < node.key)
      node.left = dlt(node.left, key);
    else if (key > node.key)
      node.right = dlt(node.right, key);
    else {
      if (node.count > 1) {
        node.count--;
        noOfNodes++;
      }
      if ((node.left == null) || (node.right == null)) {
        AVLNode temp = node.left != null ? node.left : node.right;
        noOfNodes--;
        if (temp == null) {
          temp = node;
          removal = temp;
          node = null;
        } else {
          removal = node;
          node = temp;
        }
      } else {
        AVLNode temp = minValueNode(node.left);
        node.key = temp.key;
        node.count = temp.count;
        temp.count = 1;
        dlt(node.left, temp.key);
      }
    }

    if (node == null)
      return null;
    int balance = getBalance(node);
    if (balance > 1 && getBalance(node.left) >= 0)
      return rightRotate(node);
    if (balance > 1 && getBalance(node.left) < 0) {
      return doubleRotateRight(node);
    }
    if (balance < -1 && getBalance(node.right) <= 0)
      return leftRotate(node);
    if (balance < -1 && getBalance(node.right) > 0) {
      return doubleRotateLeft(node);
    }
    return node;
  }

    /*******************************************************
     * Searches the AVL for a key. Returns a pointer to the
     * node that contains the key (if found) or NULL if unsuccessful
     *******************************************************/
    public AVLNode Find(int key){
      AVLNode current = root;
      while (current != null) {
        if (current.key == key) {
          break;
        }
        current = current.key < key ? current.right : current.left;
      }
      return current;
    }

    /*******************************************************
     * Returns a pointer to the node that contains the minimum key
     *******************************************************/
    public AVLNode Min(){
      if (root == null)
        return null;
      AVLNode p = root;
      while (p.left != null){
        p = p.left;
      } //end-while
      return p;
    } //end-Min

    /*******************************************************
     * Returns a pointer to the node that contains the maximum key
     *******************************************************/
    public AVLNode Max(){
      if (root == null)
        return null;
      AVLNode p = root;
      while (p.right != null){
        p = p.right;
      } //end-while
      return p;
    } //end-Max

    /*******************************************************
     * Returns the depth of tree. Depth of a tree is defined as
     * the depth of the deepest leaf node. Root is at depth 0
     *******************************************************/
    public int Depth() {
        return maxDepth(root) - 1;
    } //end-Depth

  int maxDepth(AVLNode node) {
    if (node == null)
      return 0;
    else {
      int lDepth = maxDepth(node.left);
      int rDepth = maxDepth(node.right);

      if (lDepth > rDepth)
        return (lDepth + 1);
      else
        return (rDepth + 1);
    }
  }

    /*******************************************************
     * Performs an inorder traversal of the tree and prints [key, count]
     * pairs in sorted order
     *******************************************************/
    public void Print() {
        println(root);
    } //end-Print

    void println(AVLNode node) {
      if (node == null) {
        return;
      }
      println(node.left);
      System.out.println(node.key);
      println(node.right);
    }

}