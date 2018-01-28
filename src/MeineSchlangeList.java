import java.util.NoSuchElementException;

public class MeineSchlangeList<A> implements Warteschange<A> {

    private Link<A> first;

    public MeineSchlangeList() {
        first = null;
    }

    @Override
    public boolean push(A a) {
        if(isEmpty()){
            first = new Link<>(a, null);
            return true;
        }else {
            Link<A> newLink = new Link<>(a, this.first);
            this.first = newLink;
            return true;
        }
    }

    @Override
    public A pop() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        MeineSchlangeIterator<A> iter = this.iterator();
        A res;
        Link<A> pos = null;
        int i = 0;
        while (i <size()-1 && iter.hasNext()){
           pos = iter.getPosition();
           iter.next();
           i++;
        }

        if(i == size()-1){
            res = pos.next.data;
            pos.next = null;
        } else throw new NoSuchElementException();

        return res;
    }






    @Override
    public boolean isEmpty() {
       return first == null;
    }

    @Override
    public int size() {
        int i = 0;
        MeineSchlangeIterator<A> iter = this.iterator();
        while (iter.hasNext()) {
            iter.next();
            i++;
        }
        return i;
    }

    private static class Link<A>{
       private A data;
       private Link<A> next;

        public Link(A data, Link<A> next) {
            this.data = data;
            this.next = next;
        }
    }

    private class MeineSchlangeIterator<A> {
        private Link<A> position; // Position des Iterators

        MeineSchlangeIterator(Link<A> position) {
            this.position = position;
        }


        public boolean hasNext() {
            return position != null;
        }


        public A next() {
            if (position==null)
                throw new NoSuchElementException();
            A res = position.data;
            position = position.next;
            return res;
        }

        public Link<A> getPosition() {
            return position;
        }
    }


    public MeineSchlangeIterator<A> iterator() {
        return new MeineSchlangeIterator<>(this.first);
    }

    @Override
    public String toString() {
        MeineSchlangeIterator<A> iter = this.iterator();
        String res = "";
        while (iter.hasNext()) {
            res += iter.next() + ",";
        }
        return res;
    }
}
