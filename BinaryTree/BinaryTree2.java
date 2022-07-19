package BinaryTree;
import java.util.*;

public class BinaryTree2 {
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

    public static ArrayList<Node> nodeToRootPath(Node node, int data){
        if(node==null){
            return new ArrayList<>();
        }
        if(node.data==data){
            ArrayList<Node> list=new ArrayList<>();
            list.add(node);
            return list;
        }
        ArrayList<Node> list1=nodeToRootPath(node.left,data);
        if(list1.size()>0){
            list1.add(node);
            return list1;
        }
        ArrayList<Node> list2=nodeToRootPath(node.right,data);
        if(list2.size()>0){
            list2.add(node);
            return list2;
        }
        return new ArrayList<>();
    }

    public static void kLeveldown(Node node,int k,Node blocker){
        if(node==null || k<0 || node==blocker){
            return;
        }
        if(k==0){
            System.out.println(node.data);
            return;
        }
        kLeveldown(node.left,k-1,blocker);
        kLeveldown(node.right,k-1,blocker);

    }

    public static void kLevelfar(Node node,int data,int k){
        ArrayList<Node> list=nodeToRootPath(node,data);
        for(int i=0;i<list.size();i++){
            kLeveldown(list.get(i),k-i,i>0?list.get(i-1):null);
        }
    }




    public static void main(String[] args){
        Integer[] arr=new Integer[]{50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root=construct(arr);
        display(root);
        kLevelfar(root,37,2);

    }
}
