
public interface Mailbox<T> {
	
	public void put(T value);
	
	public T get();

}
