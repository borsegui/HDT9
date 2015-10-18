//Crhistopher Chiroy
//Boris Cifuentes
//Diego de León


//Algoritmos y estructura de datos
//Hoja de trabajo 9

import java.awt.Color;
import java.util.Comparator;

public class RedBlackTree implements WordSet{
    
    private Comparator comparator;
    protected RedBlackTreeNode root = null;

   
    public RedBlackTree() {
    }



   
     
    public void add(Word data) {
        if (root == null) {
            root = new RedBlackTreeNode(data);
        }
        RedBlackTreeNode n = root;
        while (true) {
            int comparisonResult = compare((Word)data, (Word)n.getData());
            if (comparisonResult == 0) {
                n.setData(data);
                return;
            } else if (comparisonResult < 0) {
                if (n.getLeft() == null) {
                    n.setLeft( new RedBlackTreeNode(data));
                    adjustAfterInsertion( n.getLeft());
                    break;
                }
                n = n.getLeft();
            } else { 
                if (n.getRight() == null) {
                    n.setRight( new RedBlackTreeNode(data));
                    adjustAfterInsertion( n.getRight());
                    break;
                }
                n = n.getRight();
            }
        }
    }

    
    private void adjustAfterInsertion(RedBlackTreeNode n) {
       
        setColor(n, Color.red);

     
        if (n != null && n != root && isRed(parentOf(n))) {

           
            if (isRed(siblingOf(parentOf(n)))) {
                setColor(parentOf(n), Color.black);
                setColor(siblingOf(parentOf(n)), Color.black);
                setColor(grandparentOf(n), Color.red);
                adjustAfterInsertion(grandparentOf(n));
            }

           
            else if (parentOf(n) == leftOf(grandparentOf(n))) {
                if (n == rightOf(parentOf(n))) {
                    rotateLeft(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateRight(grandparentOf(n));
            }

   
            else if (parentOf(n) == rightOf(grandparentOf(n))) {
                if (n == leftOf(parentOf(n))) {
                    rotateRight(n = parentOf(n));
                }
                setColor(parentOf(n), Color.black);
                setColor(grandparentOf(n), Color.red);
                rotateLeft(grandparentOf(n));
            }
        }

   
        setColor(root, Color.black);
    }

    private Color colorOf(RedBlackTreeNode n) {
        return n == null ? Color.black : n.color;
    }

    private boolean isRed(RedBlackTreeNode n) {
        return n != null && colorOf(n) == Color.red;
    }

    private boolean isBlack(RedBlackTreeNode n) {
        return n == null || colorOf(n) == Color.black;
    }

    private void setColor(RedBlackTreeNode n, Color c) {
        if (n != null)
            n.color = c;
    }

    private RedBlackTreeNode parentOf(RedBlackTreeNode n) {
        return n == null ? null :  n.getParent();
    }

    private RedBlackTreeNode grandparentOf(RedBlackTreeNode n) {
        return (n == null || n.getParent() == null) ? null : n
                .getParent().getParent();
    }

    private RedBlackTreeNode siblingOf(RedBlackTreeNode n) {
        return (n == null || n.getParent() == null) ? null : (n == n
                .getParent().getLeft()) ? n.getParent().getRight()
                :  n.getParent().getLeft();
    }

    private RedBlackTreeNode leftOf(RedBlackTreeNode n) {
        return n == null ? null :  n.getLeft();
    }

    private RedBlackTreeNode rightOf(RedBlackTreeNode n) {
        return n == null ? null :  n.getRight();
    }
    
    protected int compare(Word x, Word y) {
        if (comparator == null) {
            return ((Comparable)x).compareTo(y);
        } else {
            return comparator.compare(x, y);
        }
    }

 
    protected RedBlackTreeNode getRoot() {
        return root;
    }

   
    protected void setRoot(RedBlackTreeNode node) {
        if (node != null) {
            node.removeFromParent();
        }
        root = node;
    }

    
    protected void rotateLeft(RedBlackTreeNode n) {
        if (n.getRight() == null) {
            return;
        }
        RedBlackTreeNode oldRight = n.getRight();
        n.setRight(oldRight.getLeft());
        if (n.getParent() == null) {
            root = oldRight;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(oldRight);
        } else {
            n.getParent().setRight(oldRight);
        }
        oldRight.setLeft(n);
    }

   
    protected void rotateRight(RedBlackTreeNode n) {
        if (n.getLeft() == null) {
            return;
        }
        RedBlackTreeNode oldLeft = n.getLeft();
        n.setLeft(oldLeft.getRight());
        if (n.getParent() == null) {
            root = oldLeft;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(oldLeft);
        } else {
            n.getParent().setRight(oldLeft);
        }
        oldLeft.setRight(n);
    }

    
    protected RedBlackTreeNode predecessor(RedBlackTreeNode node) {
        RedBlackTreeNode n = node.getLeft();
        if (n != null) {
            while (n.getRight() != null) {
                n = n.getRight();
            }
        }
        return n;
    }

    
    
    public Word get(Word data)
    {
        for (RedBlackTreeNode n = root; n != null;) {
            int comparisonResult = compare(data, n.getData());
            if (comparisonResult == 0) {
                return n.getData();
            } else if (comparisonResult < 0) {
                n = n.getLeft();
            } else {
                n = n.getRight();
            }
        }
        return null;
        
    }
    protected RedBlackTreeNode nodeContaining(Word data) {
        for (RedBlackTreeNode n = root; n != null;) {
            int comparisonResult = compare(data, n.getData());
            if (comparisonResult == 0) {
                return n;
            } else if (comparisonResult < 0) {
                n = n.getLeft();
            } else {
                n = n.getRight();
            }
        }
        return null;
    }
}
