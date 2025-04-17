import java.util.LinkedList;

public class BestFit extends AllocationStrategies {

    public boolean allocate(int pID, int pSize, LinkedList<Block> blocks) {

        if (isAllocated(pID, blocks) != -1){
            System.out.printf("\nThis Process is already allocated at address %d\n", isAllocated(pID, blocks));
            return false;
        }

        else {

            if (isFull(blocks)){
                System.out.println("\nAllocation Failed!! The memory is full, All Blocks are allocated\nYOU CAN DE-ALLOCATE SOME MEMORY BLOCKS");
                return false;
            }
            else {

                int smallestSize = Integer.MAX_VALUE;
                for (Block block : blocks) {
                    if (block.getSize() < smallestSize && block.getSize() >= pSize && block.getStatus().equals("free"))
                        smallestSize = block.getSize();
                }

                if(smallestSize==Integer.MAX_VALUE){
                    System.out.println("The Process is too big to fit in any free memory block :( ");
                    return false;
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
            return true;
        }

    }

}