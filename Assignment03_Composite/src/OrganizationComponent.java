public abstract class OrganizationComponent {
    protected String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(OrganizationComponent component) {
        throw new UnsupportedOperationException("This component cannot have children.");
    }

    public void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException("This component cannot have children.");
    }

    public abstract double getSalary();
    public abstract void printXML();
}