package HashmapAndHeap;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Hashmap {


    public static Character highestFcharacter(String s){
        HashMap<Character,Integer> hfc=new HashMap<>();
        int value=1;
        for(int i=0;i<s.length();i++){
          if(hfc.containsKey(s.charAt(i))){
              int val=hfc.get(s.charAt(i))+1;
              hfc.put(s.charAt(i),val);
          }
          else{
              hfc.put(s.charAt(i),value);
          }
        }
        Set<Character> sset=hfc.keySet();
        Character newans=null;
        int ans=Integer.MIN_VALUE;
        for(Character c:sset){
            if(hfc.get(c)>ans){
                ans=hfc.get(c);
                newans=c;
            }
        }
        return newans;

    }

    public static void getCommonElement1(Integer[] arr1, Integer[] arr2){
        HashMap<Integer,Integer> g1hmap=new HashMap<>();
        HashMap<Integer,Integer> g2hmap=new HashMap<>();
        for(Integer num: arr1){
            g1hmap.put(num,0);
        }for(Integer num: arr2){
            g2hmap.put(num,0);
        }

//        Set<Integer> gset=g1hmap.keySet();
        for(Integer num:arr2){
            if(g1hmap.containsKey(num)){
                System.out.println(num);
                g1hmap.remove(num);
            }
        }

    }


    public static void getCommonElement2(Integer[] arr1,Integer[] arr2){
        HashMap<Integer,Integer> hMap=new HashMap<>();
        for(Integer num:arr1){
            if(hMap.containsKey(num)){
                Integer val=hMap.get(num)+1;
                hMap.put(num,val);
            }
            else{
                hMap.put(num,1);
            }
        }
        for(Integer num:arr2){
            if(hMap.containsKey(num) && hMap.get(num)!=0){
                Integer val=hMap.get(num)-1;
                System.out.println(num);
                hMap.put(num,val);
            }
            else if(hMap.containsKey(num) && hMap.get(num)==0){
                hMap.remove(num);
            }
        }

    }


    public static void LCS(Integer[] arr){
        HashMap<Integer,Boolean> hlcs=new HashMap<>();
        for(Integer num:arr){
            hlcs.put(num,true);
        }
        Set<Integer> hset=hlcs.keySet();
        for(Integer num:hset){
            if(hlcs.containsKey(num-1)){
                hlcs.put(num,false);
            }
        }

        HashMap<Integer,Integer> newhlcs=new HashMap<>();
        for(Integer num:hset){
            if(hlcs.get(num)){
                newhlcs.put(num,1);
                int ans=num;
                while(hlcs.containsKey(ans+1)){
                    Integer val=newhlcs.get(num)+1;
                    newhlcs.put(num,val);
                    ans++;
                }
            }
        }
        int ans=0;
        int value=0;
        Set<Integer> newhset=newhlcs.keySet();
        for(Integer num:newhset){
            if(newhlcs.get(num)>ans){
                ans=newhlcs.get(num);
                value=num;

            }
        }
        for(int i=0;i<ans;i++){
            System.out.println(value);
            value++;
        }

    }



    




    public static void main(String[] args){
        HashMap<String,Integer> hmap=new HashMap<>();
        hmap.put("India",130);
        hmap.put("USA",50);
        Set<String> hset=hmap.keySet();
        for(String key:hset){
            if(hmap.containsKey(key)){
                System.out.println(key+" "+hmap.get(key));
            }
        }
        String s="abbcbddabccbb";
        Character ans=highestFcharacter(s);
        System.out.println(ans);

    }
}
