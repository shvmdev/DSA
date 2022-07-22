package BinaryTree;

import java.util.Stack;
import java.lang.Math;

public class BinaryTree3 {
    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }

    }

    public static class Pair{
        int state=0;
        Node node;
        Pair(Node node){
            this.node=node;
        }
    }

    public static Node construct(Integer[] arr){
        Stack<Pair> st=new Stack<>();
        Node root=new Node(arr[0]);
        int idx=1;
        Pair rootPair=new Pair(root);
        st.push(rootPair);
        while(st.size()>0){
            Pair peek=st.peek();
            if(peek.state==0){
                if(arr[idx]!=null){
                    peek.state++;
                    Node lNode=new Node(arr[idx]);
                    peek.node.left=lNode;
                    idx++;
                    Pair newPair=new Pair(lNode);
                    st.push(newPair);
                }
                else{
                    peek.state++;
                    idx++;
                }

            }
            else if(peek.state==1){
                if(arr[idx]!=null){
                    peek.state++;
                    Node rNode=new Node(arr[idx]);
                    peek.node.right=rNode;
                    idx++;
                    Pair newPair=new Pair(rNode);
                    st.push(newPair);
                }
                else{
                    peek.state++;
                    idx++;
                }
            }
            else{
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node node){
        if(node==null) return;
        String str=node.data+" ";
        String lStr=node.left==null?". ":node.left.data+" ";
        String rStr=node.right==null?". ":node.right.data+" ";
        System.out.println(lStr+str+rStr);
        display(node.left);
        display(node.right);
    }

    public static int height(Node node){
        if(node==null){
            return -1;
        }
        int lh=height(node.left);
        int rh=height(node.right);
        return Math.max(lh,rh)+1;
    }
    static int diameter=0;

    public static int diameter(Node node){
        if(node==null){
            return -1;
        }

        int lh=diameter(node.left);
        int rh=diameter(node.right);
        if(lh+rh+2>diameter){
            diameter=lh+rh+2;
        }
        return Math.max(lh,rh)+1;
    }

    public static int diameter2(Node node){
        if(node==null){
            return 0;
        }
        int ld=diameter2(node.left);
        int rd=diameter2(node.right);
        int lh=height(node.left);
        int rh=height(node.right);
        int rDia=lh+rh+2;
        return Math.max(Math.max(ld,rd),rDia);
    }

    static class DiaPair{
        int height=-1;
        int dia=0;
    }

    public static DiaPair diameter3(Node node,DiaPair dia){
        if(node==null){
            return new DiaPair();
        }
        DiaPair lDia=diameter3(node.left,dia);
        DiaPair rDia=diameter3(node.right,dia);
        DiaPair resDia=new DiaPair();
        resDia.dia=Math.max(Math.max(lDia.dia,rDia.dia),lDia.height+rDia.height+2);
        resDia.height=Math.max(lDia.height,rDia.height)+1;
        return resDia;
    }

    public static class tiltPair{
        int tiltSum=0;
        int sum=0;
    }

    public static tiltPair tilt(Node node,tiltPair tp){
        if(node==null){
            return new tiltPair();
        }
        tiltPair lPair=tilt(node.left,tp);
        tiltPair rPair=tilt(node.right,tp);
        tiltPair newPair=new tiltPair();
        newPair.sum=lPair.sum+rPair.sum+node.data;
        newPair.tiltSum=lPair.tiltSum+rPair.tiltSum+Math.abs(lPair.sum - rPair.sum);
        return newPair;
    }


    // Travel and Tweek Approach

    static boolean isTree=true;

    public static int balanced(Node node){
        if(node==null){
            return -1;
        }
        int lheight=balanced(node.left);
        int rheight=balanced(node.right);
        int height=Math.max(lheight,rheight)+1;
        if(Math.abs(lheight-rheight)>1){
            isTree=false;
        }
        return height;
    }
    public static class TreePair{                 //Pair Approach
        int height=-1;
        boolean state=true;
    }

    public static TreePair isTreeBalanced(Node node,TreePair tp){
        if(node==null){
            return new TreePair();
        }
        TreePair leftPair= isTreeBalanced(node.left,tp);
        if(!leftPair.state){
            return leftPair;
        }
        TreePair rightPair = isTreeBalanced(node.right,tp);
        if(!rightPair.state){
            return rightPair;
        }
        TreePair newPair=new TreePair();
        newPair.height=Math.max(leftPair.height, rightPair.height)+1;
        if(!leftPair.state || !rightPair.state){
            newPair.state=false;
            return newPair;
        }
        else if(Math.abs(leftPair.height- rightPair.height)>1){
            newPair.state=false;
        }

        return newPair;

    }

    public static class BSTPair{
        boolean isBst=true;
        int Min=Integer.MAX_VALUE;
        int Max=Integer.MIN_VALUE;
        int size=0;
        Node root=null;
    }

    public static BSTPair isBST(Node node,BSTPair bp){
        if(node==null){
            return new BSTPair();
        }
        BSTPair leftPair=isBST(node.left,bp);
        BSTPair rightPair=isBST(node.right,bp);
        BSTPair newPair=new BSTPair();
        newPair.Min=Math.min(node.data,Math.min(leftPair.Min, rightPair.Min));
        newPair.Max=Math.max(node.data,Math.max(leftPair.Max, rightPair.Max));
        if(leftPair.isBst || !rightPair.isBst){
            newPair.isBst=false;
            return newPair;
        }
        else if(node.data< leftPair.Max || node.data > rightPair.Min){
            newPair.isBst=false;
            return newPair;
        }

       return newPair;

    }


    public static BSTPair LargestBSTSubtree(Node node,BSTPair bp){
        if(node==null){
            return new BSTPair();
        }
        BSTPair leftPair=LargestBSTSubtree(node.left,bp);
        BSTPair rightPair=LargestBSTSubtree(node.right,bp);
        BSTPair newPair=new BSTPair();
        newPair.Min=Math.min(node.data,Math.min(leftPair.Min, rightPair.Min));
        newPair.Max=Math.max(node.data,Math.max(leftPair.Max, rightPair.Max));
        if(!leftPair.isBst || !rightPair.isBst){
            newPair.isBst=false;
            if(leftPair.size> rightPair.size){
                newPair.size= leftPair.size;
                newPair.root=leftPair.root;
            }
            else{
                newPair.size= rightPair.size;
                newPair.root=rightPair.root;
            }
            return newPair;
        }
        else if(node.data< leftPair.Max || node.data > rightPair.Min){
            newPair.isBst=false;
            if(leftPair.size> rightPair.size){
                newPair.size= leftPair.size;
                newPair.root=leftPair.root;
            }
            else{
                newPair.size= rightPair.size;
                newPair.root=rightPair.root;
            }
            return newPair;
        }
        if(newPair.isBst){
            newPair.root=node;
            newPair.size=leftPair.size+rightPair.size+1;
        }

        return newPair;


    }



    public static void main(String[] args){
        Integer[] arr=new Integer[]{50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root=construct(arr);
        display(root);
        diameter(root);
        System.out.println(diameter);
        System.out.println(diameter2(root));
        DiaPair d=new DiaPair();
        d=diameter3(root,d);
        System.out.println(d.dia);
        tiltPair tiltP=new tiltPair();
        tiltP=tilt(root,tiltP);
        System.out.println(tiltP.tiltSum);
        TreePair tp=new TreePair();
        tp=isTreeBalanced(root,tp);
        System.out.println(tp.state);
        balanced(root);
        System.out.println(isTree);
        BSTPair bp=new BSTPair();
        bp=isBST(root,bp);
        System.out.println(bp.isBst);
        BSTPair lbsubtree=new BSTPair();
        lbsubtree=LargestBSTSubtree(root,lbsubtree);
        System.out.println(lbsubtree.root.data+"@"+lbsubtree.size);

    }
}
