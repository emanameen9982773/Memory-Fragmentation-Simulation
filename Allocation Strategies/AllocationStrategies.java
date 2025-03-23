import java.util.LinkedList;

public abstract class AllocationStrategies {

    public abstract void allocate(int pID, int pSize, LinkedList<Block> blocks );

    protected int isAllocated(int pID, LinkedList<Block> blocks){
        for (Block block : blocks){
            if(block.getPID()==pID)
            return block.getStart();
        }
        return -1;
    }

    protected boolean isFull(LinkedList<Block> blocks){
        for (Block block : blocks)
            if(block.getStatus().equals("free"))
                return false;
        return true;
    }
        
}