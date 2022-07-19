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

    public static int size(Node node,int level){
        if(node==null){
            return 0;
        }
        int ans1=size(node.left,level+1);
        int ans2= size(node.right,level+1);
        return ans1+ans2+1;
    }

    public static int height(Node node,int level){
        if(node==null){
            return -1;
        }
        int ans=height(node.left,level+1);
        int ans2=height(node.right,level+1);
        return Math.max(ans,ans2)+1;
    }
 static int total=0;
    public static int sum(Node node){
        if(node==null){
            return 0;
        }
        total=total+node.data;
        int ans=sum(node.left);
        int ans2=sum(node.right);
        return total;
    }

    public static int findsum(Node node ,int level){
        if(node==null){
            return 0;
        }
        int lsum=findsum(node.left,level+1);
        int rsum=findsum(node.right,level+1);
        return lsum+rsum+node.data;
    }

    public static int maximum(Node node ,int level){
        if(node==null){
            return Integer.MIN_VALUE;
        }
        int lmax=maximum(node.left,level+1);
        int rmax=maximum(node.right,level+1);
        return Math.max(node.data,Math.max(lmax,rmax));
    }
    public static int findme(Node node){
        if(node==null){
            return 0;
        }
        int ans1=findme(node.left);
        int ans2=findme(node.right);
        return ans1+ans2+node.data;
    }


    public static void main(String[] args){
        Integer[] arr=new Integer[]{50,25,12,null,null,75,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Node root=construct(arr);
        display(root);
        System.out.println(size(root,0));
        System.out.println(height(root,0));
        System.out.println(sum(root));
        System.out.println(findsum(root,0));
        System.out.println(maximum(root,0));
        System.out.println(findme(root));

    }
}

