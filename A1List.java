// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }   
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key) //follows the semantic
    {
        A1List newdict = new A1List(address,size,key);
        newdict.next = this.next;
        this.next.prev = newdict;
        newdict.prev = this;
        this.next = newdict;
        return newdict;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List current = this.getFirst();
        while (current != null){
            if (d.key == current.key && d.address == current.address && d.size == current.size){
                current.prev.next = current.next;
                current.next.prev = current.prev;
                return true;                
            }
            else {
                current = current.getNext();
            }
        }
        /*boolean found = false, done_prev = false, done_next = false;
        A1List current = this;
        while (!done_prev){
            if (current.prev == null){
                done_prev = true;
            }
            else if (d.key == current.key && d == current){
                current.prev.next = current.next;
                current.next.prev = current.prev;
                done_prev = true;
                found = true;
            }
            else {
                current = current.prev;
            }
        }
        if (!found){
            current = this;
        }
        while (!done_next && !found){
            if (current.next == null){
                done_next = true;
            }
            else if(d.key == current.key && d == current){
                done_next = true;
                found = true;
            }
            else{
                current = current.next;
            }
        }
        if (found){
            return true;
        }
        else {
            return false;
        }*/
    return false;
    }

    public A1List Find(int k, boolean exact){ 
        A1List current = this.getFirst();
        while (current != null){
            if (exact == true && current.key == k){
                return current;
            }
            else if (exact == false && current.key >= k){
                return current;
            }
            else {
                current = current.getNext();
            }
        }
        return null;
        /*Boolean found = false, done_prev = false, done_next = false;
        A1List current = this;
        while (!done_prev){
            if (current.prev == null){
                done_prev = true;
            }
            else if (current.key >= k){
                found = true;
                done_prev = true;
            }
            else{
                current = current.prev;
            }
        }
        if (!found){
            current = this;
        } 
        while (!found && !done_next){
            if (current.next == null){
                done_next = true;
            }
            else if (current.key >= k){
                found = true;
                done_next = true;
            }
            else{
                current = current.next;
            }
        }
        if (!found){
            return null;
        }
        else if (exact && current.key == k){
            return current;
        }
        else if (!exact){
            return current;
        }
        else{
            return null;
        }*/
    }

    public A1List getFirst()
    {
        A1List current = this;
        while (current.prev  != null){
            current = current.prev;
        }
        if (current.next.next == null){
            return null;
        }
        else {
            return current.next;
        }
    }
    
    private A1List getLast(){
        A1List current = this;
        while (current.next != null){
            current = current.next;
        }
        if (current.prev.prev == null){
            return null;
        }
        return current.prev;
    }
    public A1List getNext() 
    {
        if (this.next == null || this.next.next == null){
            return null;
        }
        else {
            return this.next;
        }
    }

    private boolean is_clockwiseloop(){
        A1List slow = this;
        A1List fast = this;
        while (slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }
    private boolean is_anticlockwiseloop(){
        A1List slow = this;
        A1List fast = this;
        while (slow != null && fast != null && fast.prev != null){
            slow = slow.prev;
            fast = fast.prev.prev;
            if (slow == fast){
                return true;
            }
        }
        return false;
    }
    private boolean checkloop(){
        if (this.is_anticlockwiseloop() == true || this.is_clockwiseloop() == true){
            return true;
        }
        else{
            A1List current1 = this;
            while (current1.prev != null){
                current1 = current1.prev;
            }
            if (current1.next == null){
                return true; // corner case head sentinel
            }
            current1 = this;
            while (current1.next != null){
                current1 = current1.next;
            }
            if (current1.prev == null){
                return true; //corner case for tail sentinel
            } 
            A1List front = this.getFirst();
            A1List rear = this.getLast();
            if (front != null && rear != null){
                if(front.is_clockwiseloop()== true || rear.is_anticlockwiseloop()== true){
                    return true;
                }
            }
            return false;
        }
    }
    public boolean sanity(){   
        if (this.checkloop()){
            return false;
        }
        A1List current = this.getFirst();
        while (current!= null){
            if(current.prev.next != current){
                return false;
            }
            else if (current.next.prev != current){
                return false;
            }
            else {
                current = current.getNext();
            }
        }
        current = this;
        while(current.prev != null){
            current = current.prev;
        }
        if (current.key != -1 || current.address != -1 || current.size != -1){
            return false;
        }
        current = this;
        while (current.next != null){
            current = current.next;
        }
        if (current.key != -1 || current.address != -1 || current.size != -1){
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        A1List ok = new A1List();
        System.out.println(ok.sanity());
    }

}