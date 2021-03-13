import java.util.Scanner;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

public class lru{
    
    public static void main(String args[]){
        Scanner reader = new Scanner(System.in);
        
        
        int page_hits=0, page_faults=0, pages[], capacity, npages;
        
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
        HashMap<Integer,Integer> indexes = new HashMap<>();
                
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
                
                indexes.put(element,i);
            
            }
            
            else{
                if(!s.contains(element)){
                    int lru = Integer.MAX_VALUE, val = 0;
                    
                    Iterator<Integer> it = s.iterator();
                    
                    while(it.hasNext()){
                        int temp = it.next();
                        
                        if(indexes.get(temp) < lru){
                            lru = indexes.get(temp);
                            val = temp;
                        }
                    }
                    
                    s.remove(val);
                    s.add(pages[i]);
                    page_faults++;
               }
               
               else{
                    page_hits++;
               }

               indexes.put(element, i);
            }
            
        }
        
        System.out.println("\nNumber of page faults: "+ page_faults);
        System.out.println("Number of page hits: "+ page_hits);
        System.out.println("Hit ratio: "+((double)page_faults)/(double)npages);
        
    }
}