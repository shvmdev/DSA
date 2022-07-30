package HashmapAndHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Heap {

    public static void kLargest(Integer[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(Integer num:arr){
            pq.add(num);          //add operation  -logn
                                     //total complexity-nlogn
        }
        for(int i=0;i<k;i++){
            System.out.println(pq.remove());             //klogn
        }
    }

    //for O(k) space complexity

    public static void kLargestOptimum(Integer[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<k;i++){
            pq.add(arr[i]);
        }
        for(int i=k;i<arr.length;i++){
            if(pq.peek()<arr[i]){
                pq.remove();
                pq.add(arr[i]);
            }
        }
        PriorityQueue<Integer> newpq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<k;i++){
           Integer val=pq.remove();
           newpq.add(val);
        }

        for(int i=0;i<k;i++){
            System.out.println(newpq.remove());
        }
    }

    static class Pair implements Comparable<Pair>{
        int data;
        int li;
        int di;
        Pair(int data,int li,int di){
            this.data=data;
            this.li=li;
            this.di=di;
        }
        public int compareTo(Pair o){
            return this.data-o.data;
        }
    }


    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> list){
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        for(int li=0;li<list.size();li++){
            Pair p=new Pair(list.get(li).get(0),li,0);
            pq.add(p);
        }
        ArrayList<Integer> mergeList=new ArrayList<>();
        while(pq.size()>0){
            Pair peekPair=pq.remove();
            mergeList.add(peekPair.data);
            if(peekPair.di<list.get(peekPair.li).size()){
                Pair nextPair=new Pair(list.get(peekPair.li).get(peekPair.di+1),peekPair.li, peekPair.di+1);
                pq.add(nextPair);
            }

        }
        return mergeList;
    }


    public static void sortKSortedList(Integer[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<=k;i++){
           pq.add(arr[i]);
       }
       for(int i=k+1;i<arr.length;i++){
           System.out.println(pq.remove());
           pq.add(arr[i]);
       }
       while(pq.size()>0){
           System.out.println(pq.remove());
       }


    }


    public static class MedianPriorityQueue {
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianPriorityQueue() {
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }

        public void add(int val) {
            // write your code here
           if(right.size()>0 && val>right.peek()){
               right.add(val);
           }
           else{
               left.add(val);
           }
          if(left.size()- right.size()==2){
              int ans=left.remove();
              right.add(ans);
          }
          else if(right.size()- left.size()==2){
              int ans=right.remove();
              left.add(ans);
          }

        }


        public int remove() {
            // write your code here
              if(left.size()>=right.size()){
                  if(left.size()!=0){
                      return left.remove();
                  }
                  else{
                      System.out.println("Underflow");
                      return -1;
                  }
              }
              else{
                  return right.remove();
              }

        }

        public int peek() {
            // write your code here
            if(left.size()>= right.size()){
                if(left.size()!=0){
                    return left.peek();
                }
                else{
                    System.out.println("Underflow");
                    return -1;
                }
            }
            else{
                return right.peek();
            }
        }

        public int size() {
            // write your code here
            return left.size()+right.size();
        }
    }





    public static void main(String[] args){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        pq.add(19);
        pq.add(24);
        pq.add(67);
        pq.add(20);
        pq.add(10);
        System.out.println(pq.peek());// peek -smallest element =10
        System.out.println(pq.remove()+" "+pq.size());// peek -smallest element =10
        System.out.println(pq.remove() +" "+pq.size());// peek -smallest element =19
        System.out.println(pq.remove()+" "+pq.size());// peek -smallest element =20
        System.out.println(pq.remove()+" "+pq.size());// peek -smallest element =24
        System.out.println(pq.remove()+" "+pq.size());// peek -smallest element =67


    }
}
