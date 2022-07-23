package BinarySearchTree;

import java.util.ArrayList;

public class BinarySearchTree {

    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=this.right=null;
        }
    }

    public static Node construct(int[] arr,int low,int high){
        if(low>high){
            return null;
        }
        int mid=(low+high)/2;
        Node node=new Node(arr[mid]);
        node.left=construct(arr,low,mid-1);
        node.right=construct(arr,mid+1,high);
        return node;

    }

    public static void display(Node node){
        if(node==null){
            return;
        }
        String str=node.data+" ";
        String leftStr=node.left==null?". ":node.left.data+" ";
        String rightStr=node.right==null?". ":node.right.data+" ";
        System.out.println(leftStr+str+rightStr);
        display(node.left);
        display(node.right);

    }

    public static int sum(Node node){
        if(node==null){
            return 0;
        }
        int lsum=sum(node.left);
        int rsum=sum(node.right);
        return lsum+rsum+node.data;
    }

    public static int size(Node node){
        if(node==null){
            return 0;
        }
        int lsize=size(node.left);
        int rsize=size(node.right);
        return lsize+rsize+1;
    }

    public static int max(Node node){
        if(node.right==null){
            return node.data;
        }
        int rmax=max(node.right);
        return rmax;
    }

    public static boolean find(Node node,int data){
        if(node==null){
            return false;
        }
        if(data==node.data){
            return true;
        }
        else if(data>node.data){
            boolean fright=find(node.right,data);
            return fright;
        }
        else {
            boolean fleft=find(node.left,data);
            return fleft;
        }


    }

    public static int min(Node node){
        if(node.left==null){
            return node.data;
        }
        int lmin=min(node.left);
        return lmin;
    }

    static int sum=0;
    public static void replaceSum(Node node){
        if(node==null){
            return;
        }
        replaceSum(node.right);
        int osum=sum;
        sum=sum+node.data;
        node.data=osum;

        replaceSum(node.left);
    }


    public static void printInRange(Node node,int d1,int d2){
        if(node==null) return;
        if(node.data<d1 && node.data<d2){
            printInRange(node.right,d1,d2);
        }
        else if(node.data>d1 && node.data>d2){
            printInRange(node.left,d1,d2);
        }
        else{
            printInRange(node.left,d1,d2);
            System.out.print(node.data+" ");
            printInRange(node.right,d1,d2);
        }

    }

    public static int lca(Node node,int d1,int d2){
        if(node.data>d1 && node.data>d2){
            int leftLca=lca(node.left,d1,d2);
            return leftLca;
        }
        else if(node.data<d1 && node.data<d2){
            int rightLca=lca(node.right,d1,d2);
            return rightLca;
        }
        else{
            return node.data;
        }
    }

    static Node root=null;
   public static void tsp(Node root,Node node,int target){
        if(node==null) return;
        if(find(root,target-node.data) && node.data<target-node.data){
            System.out.println(node.data+" "+(target-node.data));
        }
        tsp(root,node.left,target);
        tsp(root,node.right,target);

   }



    public static void main(String[] args){
        int[] arr=new int[]{12,25,37,50,62,75,87};
        Node root=construct(arr,0,arr.length-1);
//        display(root);
//        System.out.println(sum(root));
//        System.out.println(size(root));
//        System.out.println(max(root));
//        System.out.println(min(root));
//        System.out.println(find(root,87));
////        replaceSum(root);
//        printInRange(root,87,87);
        tsp(root,root,99);

    }
}
