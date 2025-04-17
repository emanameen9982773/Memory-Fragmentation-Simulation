import java.util.LinkedList;

public class FirstFit extends AllocationStrategies {

    
    public boolean allocate(int pID, int pSize, LinkedList<Block> blocks) {

        if (isAllocated(pID, blocks) != -1) {
            System.out.printf("\nThis Process is already allocated at address %d\n", isAllocated(pID, blocks));
            return false;
        }

        if (isFull(blocks)) {
            System.out.println("Allocation Failed!! The memory is full, All Blocks are allocated\nYOU CAN DE-ALLOCATE SOME MEMORY BLOCKS");
            return false;
        }

        for (Block block : blocks) {
            if (block.getStatus().equals("free") && block.getSize() >= pSize) {
                block.setStatus("allocated");
                block.setPID(pID);
                block.setInternalFragmentation(block.getSize() - pSize);
               // System.out.println("Process " + pID + " allocated at block starting at " + block.getStart());
                return true;   
            }
        }

        System.out.println("The Process is too big to fit in any free block in the memory");
        return false;
    }
}
