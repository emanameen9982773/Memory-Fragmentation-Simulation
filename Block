public class Block {
    private int size;
    private int start;
    private int end;
    private String status;
    private int PID; 
    private int internalFragmentation;

    public Block(int size, int start) {
        this.size = size;
        this.start = start;
        this.end = start + size - 1;
        this.status = "free";
        this.PID = -1; 
        this.internalFragmentation = 0;
    }

    public int getSize() {
        return size;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getStatus() {
        return status;
    }

    public int getPID() {
        return PID;
    }

    public int getInternalFragmentation() {
        return internalFragmentation;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStart(int start) {
        this.start = start;
        this.end = start + size - 1;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public void setInternalFragmentation(int internalFragmentation) {
        this.internalFragmentation = internalFragmentation;
    }
}
