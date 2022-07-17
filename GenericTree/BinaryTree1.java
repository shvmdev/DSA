import java.util.Scanner;
public class BinaryTree1 {
    public static class Node{
        static int data;
        static Node next;
        Node(int data){
            Node.data=data;
            Node.next=null;
        }
    }
    public static void main(String[] args){
        Scanner scn=new Scanner(System.in);
        String s=scn.next();
        System.out.println("Hello, "+s );
    }
}
