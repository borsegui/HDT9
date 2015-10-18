//Crhistopher Chiroy
//Boris Cifuentes
//Diego de León


//Algoritmos y estructura de datos
//Hoja de trabajo 9

import java.awt.Color;



public class RedBlackTreeNode{
    Word data;
    Color color = Color.black;
    RedBlackTreeNode parent;
    RedBlackTreeNode left;
    RedBlackTreeNode right;

   
    public RedBlackTreeNode(Word data) {
        this.data = data;
    }

  
    public Word getData() {
        return data;
    }

    
    public void setData(Word data) {
        this.data = data;
    }

   
    public RedBlackTreeNode getParent() {
      return parent;
    }

   
    public RedBlackTreeNode getLeft() {
      return left;
    }

   
    public void setLeft(RedBlackTreeNode child) {
       
        for (RedBlackTreeNode n = this; n != null; n = n.parent) {
            if (n == child) {
                throw new IllegalArgumentException();
            }
        }

        
        RedBlackTreeNode childNode = (RedBlackTreeNode)child;

        
        if (this.left != null) {
            left.parent = null;
        }
        if (childNode != null) {
            childNode.removeFromParent();
            childNode.parent = this;
        }
        this.left = childNode;
    }

    public RedBlackTreeNode getRight() {
      return right;
    }

    
    public void setRight(RedBlackTreeNode child) {
        
        for (RedBlackTreeNode n = this; n != null; n = n.parent) {
            if (n == child) {
                throw new IllegalArgumentException();
            }
        }

        
        RedBlackTreeNode childNode = (RedBlackTreeNode)child;

       
        if (right != null) {
            right.parent = null;
        }
        if (childNode != null) {
            childNode.removeFromParent();
            childNode.parent = this;
        }
        this.right = childNode;
    }

   
    public void removeFromParent() {
        if (parent != null) {
            if (parent.left == this) {
                parent.left = null;
            } else if (parent.right == this) {
                parent.right = null;
            }
            this.parent = null;
        }
    }

}
