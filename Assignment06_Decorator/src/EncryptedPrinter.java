public class EncryptedPrinter extends PrinterDecorator {

    public EncryptedPrinter(Printer printer) {
        super(printer);
    }

    @Override
    public void print(String message) {
        String encryptedMessage = encrypt(message);
        printer.print(encryptedMessage);
    }

    private String encrypt(String message) {
        StringBuilder encrypted = new StringBuilder();

        for (char c : message.toCharArray()) {
            encrypted.append((char) (c + 3)); // encryption by shifting characters by 3
        }

        return encrypted.toString();
    }
}
