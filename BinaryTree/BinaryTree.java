package BinaryTree;

import java.util.Stack;

public class BinaryTree {
    public static class Node{
        int data;
        Node left;
        Node right;
    }
    public static class Pair{
        int state=0;
        Node node;
    }
    public static Node construct(Integer[] arr){
        Stack<Pair> st=new Stack<>();
        Node node=new Node();
        node.data=arr[0];
        Pair root=new Pair();
        root.node=node;
        st.push(root);
        int idx=1;
        while(st.size()>0){
            Pair peek=st.peek();
            if(peek.state==0){
                if(arr[idx]==null){
                    peek.state++;
                    idx++;
                }
                else{
                    Node nnode=new Node();
                    nnode.data=arr[idx];
                    idx++;
                    peek.node.left=nnode;
                    peek.state++;
                    Pair pushme=new Pair();
                    pushme.node=nnode;
                    st.push(pushme);

                }
            }
            else if(peek.state==1){
                if(arr[idx]==null){
                    peek.state++;
                    idx++;
                }
                else{
                    Node nnode=new Node();
                    nnode.data=arr[idx];
                    idx++;
                    peek.node.right=nnode;
                    peek.state++;
                    Pair pushme=new Pair();
                    pushme.node=nnode;
                    st.push(pushme);


                }
            }
            else if(peek.state==2){
                st.pop();
            }
        }
        return node;
    }
    public static void display(Node node){
        if(node==null){
            return;
        }
        String str=""+node.data;
        String ln=node.left==null?".":""+ node.left.data;
        String rn=node.right==null?".":""+node.right.data;
        System.out.println(ln+" "+str+" "+rn);
        display(node.left);
        display(node.right);
    }
    public static void main(String[] args){
        Integer[] arr=new Integer[]{50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root=construct(arr);
        display(root);

    }
}

