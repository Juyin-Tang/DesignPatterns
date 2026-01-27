public class Interfaces {
    public static void main(String[] args) {

        System.out.println("==========  A Style  ==========");

        UIFactory factoryA = new AFactory();


        Button btn = factoryA.createButton("Click Me");
        TextField tf = factoryA.createTextField("Enter Name");
        Checkbox cb = factoryA.createCheckbox("I agree");


        btn.display();
        tf.display();
        cb.display();

        System.out.println("\n--- New Content ---");
        btn.setText("Submit");
        tf.setText("James Bond");

        btn.display();
        tf.display();
        cb.display();




        System.out.println("\n==========  B Style  ==========");
        UIFactory factoryB = new BFactory();
        Button btnB = factoryB.createButton("Login");
        TextField tfB = factoryB.createTextField("Password");
        Checkbox cbB = factoryB.createCheckbox("Remember me");

        btnB.display();
        tfB.display();
        cbB.display();

    }
}


abstract class UIElement {
    protected String text;

    public UIElement(String text) {
        this.text = text;
    }


    public void setText(String text) {
        this.text = text;
    }

    public abstract void display();
}


abstract class Button extends UIElement {
    public Button(String text) {
        super(text);
    }
}
abstract class TextField extends UIElement {
    public TextField(String text) {
        super(text);
    }
}
abstract class Checkbox extends UIElement {
    public Checkbox(String text) {
        super(text);
    }
}


class ButtonA extends Button {
    public ButtonA(String text) {
        super(text);
    }
    @Override public void display() {
        System.out.println("  [ " + text + " ]");
    }
}

class TextFieldA extends TextField {
    public TextFieldA(String text) {
        super(text);
    }
    @Override public void display() {
        System.out.println("  ____ " + text + " ____");
    }
}

class CheckboxA extends Checkbox {
    public CheckboxA(String text) {
        super(text);
    }
    @Override public void display() {
        System.out.println("  [x] " + text);
    }
}


class ButtonB extends Button {
    public ButtonB(String text) {
        super(text);
    }
    @Override public void display() {
        System.out.println("  << " + text + " >>");
    }
}

class TextFieldB extends TextField {
    public TextFieldB(String text) {
        super(text);
    }
    @Override public void display() {
        System.out.println("  **** " + text + " ****");
    }
}

class CheckboxB extends Checkbox {
    public CheckboxB(String text) {
        super(text);
    }
    @Override public void display() {
        System.out.println("  (*) " + text);
    }
}

abstract class UIFactory {
    public abstract Button createButton(String text);
    public abstract TextField createTextField(String text);
    public abstract Checkbox createCheckbox(String text);
}

class AFactory extends UIFactory {
    @Override public Button createButton(String text) {
        return new ButtonA(text);
    }
    @Override public TextField createTextField(String text) {
        return new TextFieldA(text);
    }
    @Override public Checkbox createCheckbox(String text) {
        return new CheckboxA(text);
    }
}


class BFactory extends UIFactory {
    @Override public Button createButton(String text) {
        return new ButtonB(text);
    }
    @Override public TextField createTextField(String text) {
        return new TextFieldB(text);
    }
    @Override public Checkbox createCheckbox(String text) {
        return new CheckboxB(text);
    }
}