import java.util.LinkedList;

public class FirstFit extends AllocationStrategies {

    
    public void allocate(int pID, int pSize, LinkedList<Block> blocks) {

        if (isAllocated(pID, blocks) != -1) {
            System.out.printf("The Process P%d is already allocated in address %d\n", pID, isAllocated(pID, blocks));
            return;
        }

        if (isFull(blocks)) {
            System.out.println("Allocation Failed!! The memory is full, All Blocks are allocated\nYOU CAN DE-ALLOCATE SOME MEMORY BLOCKS");
            return;
        }

        for (Block block : blocks) {
            if (block.getStatus().equals("free") && block.getSize() >= pSize) {
                block.setStatus("allocated");
                block.setPID(pID);
                block.setInternalFragmentation(block.getSize() - pSize);
                System.out.println("Process " + pID + " allocated at block starting at " + block.getStart());
                return;   
            }
        }

        System.out.println("The Process is too big to fit in any free block in the memory");
    }
}
