public abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler next) {
        this.nextHandler = next;
    }

    public abstract void handleRequest(Message message);
}