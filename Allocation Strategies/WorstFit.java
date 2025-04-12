import java.util.LinkedList;

public class WorstFit extends AllocationStrategies {

    public void allocate(int pID, int pSize, LinkedList<Block> blocks) {

        if (isAllocated(pID, blocks) != -1)//makes sure that the process has not been allocated before
            System.out.printf("The Process P%d is already allocated in address %d", pID, isAllocated(pID, blocks));

        else {

            if (isFull(blocks))//ensures that the memory has avaliable blocks or not, if true prints a failer message
                System.out.println("Allocation Failed!! The memory is full, All Blocks are allocated\nYOU CAN DE-ALLOCATE SOME MEMORY BLOCKS");
            else {

                int largestSize = -1;
                for (Block block : blocks) {// checks all the blocks and search for the largest block size, it has to be larger than the process saze Psize                    if (block.getSize() > largestSize && block.getSize() >= pSize && block.getStatus().equals("free"))
                        largestSize = block.getSize();
                }

                if(largestSize == -1){// if he didnt find any suitable block(all blocks smaller than the process)prints a message and return
                    System.out.println("The Process is too big to fit in any free block in the memory :( ");
                    return;
                }

                for (Block block : blocks) {//allocate the largest block possiable, change its status to "allocated", Assign the process ID (pID) to it, Calculate internal fragmentation (block size - process size)
                    if (block.getSize() == largestSize && block.getStatus().equals("free")) {
                        block.setStatus("allocated");
                        block.setPID(pID);
                        block.setInternalFragmentation(block.getSize() - pSize);
                        break;
                    }
                }
            }
        }
    }
}
