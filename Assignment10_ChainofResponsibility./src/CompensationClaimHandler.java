public class CompensationClaimHandler extends Handler {
    @Override
    public void handleRequest(Message message) {
        if (message.getType() == MessageType.COMPENSATION_CLAIM) {
            // Simulate handling: approve or reject randomly
            boolean approved = Math.random() > 0.5;
            System.out.println("Compensation Claim Handler: " +
                    (approved ? "Approved" : "Rejected") +
                    " claim from " + message.getSenderEmail() +
                    " – " + message.getContent());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(message);
        }
    }
}