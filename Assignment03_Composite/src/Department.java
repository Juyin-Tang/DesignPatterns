import java.util.ArrayList;
import java.util.List;

public class Department extends OrganizationComponent {
    private List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    @Override
    public void add(OrganizationComponent component) {
        components.add(component);
    }

    @Override
    public void remove(OrganizationComponent component) {
        components.remove(component);
    }

    @Override
    public double getSalary() {
        double totalSalary = 0;
        for (OrganizationComponent component : components) {
            totalSalary += component.getSalary();
        }
        return totalSalary;
    }

    @Override
    public void printXML() {
        System.out.println("<Department name=\"" + getName() + "\">");

        for (OrganizationComponent component : components) {
            component.printXML();
        }

        System.out.println("</Department>");
    }
}