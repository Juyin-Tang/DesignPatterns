public class Main {
    public static void main(String[] args) {
        // Create handlers
        Handler compensationHandler = new CompensationClaimHandler();
        Handler contactHandler = new ContactRequestHandler();
        Handler suggestionHandler = new DevelopmentSuggestionHandler();
        Handler generalHandler = new GeneralFeedbackHandler();

        // Build the chain (order can be adjusted)
        compensationHandler.setNextHandler(contactHandler);
        contactHandler.setNextHandler(suggestionHandler);
        suggestionHandler.setNextHandler(generalHandler);
        // generalHandler is the last, no next handler

        // Create some test messages
        Message[] messages = {
                new Message(MessageType.COMPENSATION_CLAIM,
                        "My flight was delayed, I want a refund.", "john@example.com"),
                new Message(MessageType.CONTACT_REQUEST,
                        "Please call me back regarding my account.", "jane@example.com"),
                new Message(MessageType.DEVELOPMENT_SUGGESTION,
                        "Add dark mode to the app.", "dev@example.com"),
                new Message(MessageType.GENERAL_FEEDBACK,
                        "Love your service, keep it up!", "sam@example.com"),
                new Message(MessageType.COMPENSATION_CLAIM,
                        "Product arrived damaged.", "alice@example.com")
        };

        // Send each message to the start of the chain
        for (Message msg : messages) {
            System.out.println("\n--- Processing new message ---");
            compensationHandler.handleRequest(msg);
        }
    }
}