public class Employee extends OrganizationComponent {
    private double salary;

    public Employee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return this.salary;
    }

    @Override
    public void printXML() {
        System.out.println("<Employee name=\"" + getName() + "\" salary=\"" + this.salary + "\" />");
    }
}