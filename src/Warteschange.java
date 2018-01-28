public interface Warteschange<A> {

    boolean push(A a);

    A pop();

    boolean isEmpty();

    int size();

}
