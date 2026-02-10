public class Main {

    public static void main(String[] args) {

        // Basic printer
        Printer printer = new BasicPrinter();
        printer.print("Hello World!");

        // Decorated printer
        Printer printer2 =
                new EncryptedPrinter(
                        new XMLPrinter(
                                new BasicPrinter()
                        )
                );

        printer2.print("Hello World!");
    }
}
