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

    public static void inorderTraversal(Node node){
        if(node==null){
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.data+" ");
        inorderTraversal(node.right);
    }
    public static void preorderTraversal(Node node){
        if(node==null){
            return;
        }
        System.out.print(node.data+" ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }
    public static void postorderTraversal(Node node){
        if(node==null){
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.data+" ");
    }

    public static void iterativePrePostInTraversal(Node node){
        Stack<Pair> stack=new Stack<>();
        Pair rootPair=new Pair();
        StringBuilder pre=new StringBuilder("");
        StringBuilder in= new StringBuilder("");
        StringBuilder post=new StringBuilder("");
        rootPair.node=node;
        stack.push(rootPair);
        while(stack.size()!=0){
            Pair peek=stack.peek();
            if(peek.state==0){
                pre.append(peek.node.data).append(" ");
                if(peek.node.left!=null){
                    Pair leftpair=new Pair();
                    leftpair.node=peek.node.left;
                    stack.push(leftpair);
                }

                peek.state++;
            }
            else if(peek.state==1){
                in.append(peek.node.data).append(" ");
                if(peek.node.right!=null){
                    Pair rightpair=new Pair();
                    rightpair.node=peek.node.right;
                    stack.push(rightpair);
                }

                peek.state++;
            }
            else{
                post.append(peek.node.data).append(" ");
                stack.pop();
            }
        }
        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }

    public static void toleftClone(Node node){
        if(node==null){
            return;
        }
        Node nnode=new Node(node.data);
        Node temp=node.left;
        node.left=nnode;
        nnode.left=temp;
        node=nnode;
        toleftClone(node.left);
        toleftClone(node.right);
    }


    public static Node createNormal(Node node){
        if(node==null) return null;
        Node left=createNormal(node.left.left);
        Node right=createNormal(node.right);
        node.left=null;
        return node;
    }

    public static Node removeLeaves(Node node){
        if(node==null){
            return null;
        }
        if(node.left==null && node.right==null){
            return null;
        }
        Node lnode=removeLeaves(node.left);
        Node rnode=removeLeaves(node.right);
        if(lnode==null){
            node.left=lnode;
        }
        if(rnode==null){
            node.right=rnode;
        }
        if(lnode==null && rnode==null){
            return node;
        }

        return node;
    }

    public static void printsingleChildNodes(Node node){
        if(node==null){
            return;
        }
        printsingleChildNodes(node.left);
        printsingleChildNodes(node.right);
        if(node.left==null && node.right!=null){
            System.out.println(node.right.data);
        }
        else if(node.right==null && node.left!=null){
            System.out.println(node.left.data);
        }
    }

    static int sum=0;
    static ArrayList<Integer> path=new ArrayList<>();
    public static void printPathToleaf(Node node,ArrayList<Integer> path,int sum,int ss,int es){
        if(node==null) return;
        sum=sum+node.data;
        path.add(node.data);
        if(sum>ss && sum<es && node.left==null && node.right==null){
            System.out.println(path);
            sum=sum-node.data;
            path.remove(path.size()-1);
            return;
        }
        printPathToleaf(node.left,path,sum,ss,es);
        printPathToleaf(node.right,path,sum,ss,es);
        sum=sum-node.data;
        path.remove(path.size()-1);

    }


    public static void main(String[] args){
//        Integer[] arr=new Integer[]{50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Integer[] arr=new Integer[]{50,25,12,null,null,37,30,null,null,40,null,null,75,62,60,null,null,70,null,null,87,null,null};
        Node root=construct(arr);
        display(root);
        kLevelfar(root,37,2);
        inorderTraversal(root);
        System.out.println();
        preorderTraversal(root);
        System.out.println();
        postorderTraversal(root);
        System.out.println();
        iterativePrePostInTraversal(root);
        toleftClone(root);
        display(root);
        printPathToleaf(root,path,0,150,250);



    }
}
