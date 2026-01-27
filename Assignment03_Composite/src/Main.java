public class Main {
    public static void main(String[] args) {
        Department headOffice = new Department("Headquarters");
        Department itDept = new Department("IT Dept");
        Department salesDept = new Department("Sales Dept");

        Employee boss = new Employee("The Boss", 100000);
        Employee dev1 = new Employee("Alice", 5000);
        Employee dev2 = new Employee("Bob", 6000);
        Employee salesman = new Employee("Charlie", 4000);

        headOffice.add(boss);
        headOffice.add(itDept);
        headOffice.add(salesDept);

        itDept.add(dev1);
        itDept.add(dev2);

        salesDept.add(salesman);

        System.out.println("Total Salary Calculation ");
        // 100000 + 5000 + 6000 + 4000 = 115000
        System.out.println("Total Salary: " + headOffice.getSalary());

        System.out.println("\n - XML Structure - ");
        headOffice.printXML();
    }
}