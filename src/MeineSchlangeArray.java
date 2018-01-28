import java.util.Arrays;
import java.util.NoSuchElementException;

public class MeineSchlangeArray<A> implements Warteschange<A>{

    private int nextIn = 0;
    private int nextOut= 0;
    private int size;
    private Pointer<A>[] ringBuffer;


    public MeineSchlangeArray(int size) {
        this.size = size;
        Pointer<A>[] ringBuffer = new Pointer[size];
        for (int i = 0; i < this.size ; i++) {
            ringBuffer[i] = new Pointer<>(null);
        }
        this.ringBuffer = ringBuffer;
    }

    private class Pointer<A>{
        private A data;

        public Pointer(A data) {
            this.data = data;
        }
    }



    @Override
    public boolean push(A a) {
        if(ringBuffer[nextIn].data == null) {
            ringBuffer[nextIn] = new Pointer<>(a);
            nextIn = (nextIn+1) % size;
            return true;
        }else return false;
    }

    @Override
    public A pop() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        A res = ringBuffer[nextOut].data;
        ringBuffer[nextOut].data = null;
        nextOut = (nextOut+1)%size;

        return res;
    }

    @Override
    public boolean isEmpty() {
        return ringBuffer[(nextIn+size-1)%size].data == null;
    }

    @Override
    public int size() {
        return ringBuffer.length;
    }

    @Override
    public String toString() {
        String res= "[";
        for (int i = 0; i < size-1 ; i++) {
            res += ringBuffer[i].data + ", ";
        }
        return res + ringBuffer[size-1].data + "]";
    }
}
