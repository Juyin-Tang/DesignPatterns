public class GeneralFeedbackHandler extends Handler {
    @Override
    public void handleRequest(Message message) {
        if (message.getType() == MessageType.GENERAL_FEEDBACK) {
            System.out.println("General Feedback Handler: Analyzed feedback from " +
                    message.getSenderEmail() + " – " + message.getContent());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(message);
        }
    }
}