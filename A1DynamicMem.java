// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        Dictionary req_block = this.freeBlk.Find(blockSize, false);
        if (blockSize == 0){
            return -1;
        }
        else if (req_block == null){
            return -1;
        }
        else if (req_block.size > blockSize){
            this.freeBlk.Delete(req_block);
            this.freeBlk.Insert(req_block.address+blockSize, req_block.size-blockSize , req_block.size-blockSize);
            this.allocBlk.Insert(req_block.address, blockSize, req_block.address);
            return req_block.address;
        }
        else{
            this.freeBlk.Delete(req_block);
            this.allocBlk.Insert(req_block.address, req_block.size, req_block.address);
            return req_block.address;
        }
    } 
    
    public int Free(int startAddr) {
        Dictionary req_block = this.allocBlk.Find(startAddr, true);
        if (req_block == null){
            return -1;
        }
        else {
            this.allocBlk.Delete(req_block);
            this.freeBlk.Insert(req_block.address, req_block.size, req_block.size);
            return 0;
        }
    }
}