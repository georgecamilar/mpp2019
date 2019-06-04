package springclient;

public class ClientException extends RuntimeException {
    public ClientException(Exception e) {
        super(e);
    }

    public ClientException(String e) {
        super(e);
    }
}
