import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

public class optimal{
    
    public static int nextIndex(int pages[],int currIndex){
        for(int i=currIndex+1;i<pages.length;i++){
            if(pages[i]==pages[currIndex]){
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }
    
    public static void main(String args[]){
        Scanner reader = new Scanner(System.in);
                
        int page_hits=0, page_faults=0, pages[], capacity, npages;
        HashMap<Integer,Integer> indexes = new HashMap<>();
                
        System.out.print("Capacity of frame window: ");
        capacity = reader.nextInt();
        System.out.print("Number of pages in the sequence: ");
        npages = reader.nextInt();
        pages = new int[npages];
        
        for(int i=0;i<npages;i++){
            int data;
            System.out.print("Enter the frame value: ");
            data = reader.nextInt();
            pages[i] = data;
        }
        
        HashSet<Integer> s = new HashSet<>(capacity);
                
        for(int i=0;i<npages;i++){
            
            int element = pages[i];
            if(s.size() < capacity){
                if(!s.contains(element)){
                    page_faults++;
                    s.add(element);
                }
                
                else{ 
                    page_hits++;
                }
                indexes.put(element,nextIndex(pages,i));
            }
            
            else{
                if(!s.contains(element)){
                    int opt = Integer.MIN_VALUE, val = 0;
                    
                    Iterator<Integer> it = s.iterator();
                    
                    while(it.hasNext()){
                        int temp = it.next();
                         
                        if(indexes.get(temp) > opt){
                            opt = indexes.get(temp);
                            val = temp;
                        }
                    }
                    
                    s.remove(val);
                    s.add(element);
                    page_faults++;
               }
               
               else{
                    page_hits++;
               }
                indexes.put(element,nextIndex(pages,i));
            }            
        }
        
        System.out.println("\nNumber of page faults: "+ page_faults);
        System.out.println("Number of page hits: "+ page_hits);
        System.out.println("Hit ratio: "+((double)page_faults)/(double)npages);        
    }
}