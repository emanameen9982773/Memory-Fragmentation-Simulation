import java.util.LinkedList;

public class BestFit extends AllocationStrategies {

    public void allocate(int pID, int pSize, LinkedList<Block> blocks) {

        if (isAllocated(pID, blocks) != -1)
            System.out.printf("\nThe Process P%d is already allocated in address %d", pID, isAllocated(pID, blocks));

        else {

            if (isFull(blocks))
                System.out.println("\nAllocation Failed!! The memory is full, All Blocks are allocated\nYOU CAN DE-ALLOCATE SOME MEMORY BLOCKS");
            else {

                int smallestSize = Integer.MAX_VALUE;
                for (Block block : blocks) {
                    if (block.getSize() < smallestSize && block.getSize() >= pSize && block.getStatus().equals("free"))
                        smallestSize = block.getSize();
                }

                if(smallestSize==Integer.MAX_VALUE){
                    System.out.println("The Process is too big to fit in any free memory block :( ");
                    return;
                }

                for (Block block : blocks) {
                    if (block.getSize() == smallestSize && block.getStatus().equals("free")) {
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