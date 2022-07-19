package BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree1 {
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
    }
    public static Node construct(Integer[] arr){
        Stack<Pair> st=new Stack<>();
        Node root=new Node(arr[0]);
        Pair p=new Pair();
        int idx=1;
        p.node=root;
        st.push(p);
        while(st.size()>0){
            Pair peek=st.peek();
            if(peek.state==0){
                if(arr[idx]==null){
                    idx++;
                    peek.state++;
                }
                else{
                    Node node=new Node(arr[idx]);
                    idx++;
                    peek.state++;
                    peek.node.left=node;
                    Pair pre=new Pair();
                    pre.node=node;
                    st.push(pre);
                }

            }
            else if(peek.state==1){
                if(arr[idx]==null){
                    idx++;
                    peek.state++;
                }
                   else{
                    Node node=new Node(arr[idx]);
                    idx++;
                    peek.state++;
                    peek.node.right=node;
                    Pair pre=new Pair();
                    pre.node=node;
                    st.push(pre);
                }
            }
            else{
                st.pop();
            }
        }
        return root;
    }
    public static void display(Node node){
        if(node==null){
            return;
        }
        String str=""+node.data;
        String ldata=node.left==null?".":""+node.left.data;
        String rdata=node.right==null?".":""+node.right.data;
        System.out.println(ldata+" "+str+" "+rdata);
        display(node.left);
        display(node.right);
    }
    public static void levelOrder(Node node){
        Queue<Node> qu=new ArrayDeque<>();
        Queue<Node> helperqu=new ArrayDeque<>();
        qu.add(node);
        while(qu.size()!=0){
            Node next=qu.remove();
            if(next.left!=null) helperqu.add(next.left);
            if(next.right!=null) helperqu.add(next.right);
            System.out.print(next.data+" ");
            if(qu.size()==0){
                qu=helperqu;
                helperqu=new ArrayDeque<>();
                System.out.println();
            }
        }
    }

    public static void levelOrder1(Node node){
        Queue<Node> pq=new ArrayDeque<>();
        pq.add(node);
        while(pq.size()!=0){
            int size=pq.size();
            for(int i=0;i<size;i++){
                Node next=pq.peek();
                System.out.print(pq.remove().data+" ");
                if(next!=null && next.left!=null) pq.add(next.left);
                if(next!=null && next.right!=null) pq.add(next.right);
            }
            System.out.println();
        }
    }
    public static void levelOrder2(Node node){
        Queue<Node> pq=new ArrayDeque<>();
        pq.add(node);
        Node delimiter=new Node(-1);
        pq.add(delimiter);
        while(pq.size()!=0){
                Node next=pq.remove();
                if(next.data==-1){
                    System.out.println();
                    if(pq.size()>0){
                        pq.add(delimiter);
                    }
                }
                else{
                    System.out.print(next.data+" ");
                    if(next.left!=null) pq.add(next.left);
                    if(next.right!=null) pq.add(next.right);
                }

        }
    }
   public static class levelPair{
        int level=1;
        Node node;
   }

    public static void levelOrder3(Node node){
        Queue<levelPair> pq=new ArrayDeque<>();
        levelPair p=new levelPair();
        p.node=node;
        int level=1;
        pq.add(p);
        while(pq.size()>0){
            levelPair next=pq.remove();
            if(next.level>level){
                level= next.level;
                System.out.println();
            }
            System.out.print(next.node.data+" ");
            if(next.node.left!=null){
                levelPair leftPair=new levelPair();
                leftPair.node=next.node.left;
                leftPair.level= next.level+1;
                pq.add(leftPair);
            }
            if(next.node.right!=null){
                levelPair rightPair=new levelPair();
                rightPair.node=next.node.right;
                rightPair.level= next.level+1;
                pq.add(rightPair);
            }

        }
        System.out.println();

    }

    public static boolean find(Node node,int data){
        if(node==null){
            return false;
        }
        boolean lans=find(node.left,data);
        boolean rans=find(node.right,data);
        return (node.data == data) || lans || rans;
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data){
            if(node==null){
                return new ArrayList<>();
            }
            if(node.data==data){
                ArrayList<Integer> list=new ArrayList<>();
                list.add(node.data);
                return list;
            }
            ArrayList<Integer> list1=nodeToRootPath(node.left,data);
            if(list1.size()>0){
                list1.add(node.data);
                return list1;
            }
            ArrayList<Integer> list2=nodeToRootPath(node.right,data);
            if(list2.size()>0){
                list2.add(node.data);
                return list2;
            }
            return new ArrayList<>();
    }

    public static void kLeveldown(Node node,int k){
        if(node==null || k<0){
            return;
        }
        if(k==0){
            System.out.println(node.data);
            return;
        }
        kLeveldown(node.left,k-1);
        kLeveldown(node.right,k-1);

    }



    public static void main(String[] args){
        Integer[] arr=new Integer[]{50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root=construct(arr);
        display(root);
        levelOrder(root);
        levelOrder1(root);
        levelOrder2(root);
        levelOrder3(root);
        ArrayList<Integer> list=nodeToRootPath(root,55);
        System.out.println(list);
        System.out.println(find(root,75));
        kLeveldown(root,3);
    }
}
