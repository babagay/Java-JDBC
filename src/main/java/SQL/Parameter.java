package SQL;

public class Parameter<T> {

    public String type;

    public T value;

    public Parameter(T value)
    {
        this.value = value;
    }
}
