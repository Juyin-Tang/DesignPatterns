public class ContactRequestHandler extends Handler {
    @Override
    public void handleRequest(Message message) {
        if (message.getType() == MessageType.CONTACT_REQUEST) {
            System.out.println("Contact Request Handler: Forwarded request from " +
                    message.getSenderEmail() + " to customer service – " + message.getContent());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(message);
        }
    }
}

