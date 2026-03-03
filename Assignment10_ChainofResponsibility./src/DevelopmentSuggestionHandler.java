public class DevelopmentSuggestionHandler extends Handler {
    @Override
    public void handleRequest(Message message) {
        if (message.getType() == MessageType.DEVELOPMENT_SUGGESTION) {
            System.out.println("Development Suggestion Handler: Logged and prioritized suggestion from " +
                    message.getSenderEmail() + " – " + message.getContent());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(message);
        }
    }
}