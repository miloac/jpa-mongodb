package eci.cosw;

import eci.cosw.data.AppConfiguration;
import eci.cosw.data.CustomerRepository;
import eci.cosw.data.TodoRepository;
import eci.cosw.data.UserRepository;
import eci.cosw.data.model.Customer;
import eci.cosw.data.model.Todo;
import eci.cosw.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {




        customerRepository.deleteAll();

        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        /**System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();**/

        userRepository.deleteAll();

        userRepository.save(new User("1","Pepito","pepito@mail.com"));
        userRepository.save(new User("2","Andres","and@mail.com"));
        userRepository.save(new User("3","Juan","juan@mail.com"));
        userRepository.save(new User("4","Camilo","camo@mail.com"));
        userRepository.save(new User("5","Daniel","dani@mail.com"));
        userRepository.save(new User("6","Santiago","santi@mail.com"));
        userRepository.save(new User("7","Jorge","jorge@mail.com"));
        userRepository.save(new User("8","Luis","luis@mail.com"));
        userRepository.save(new User("9","Mario","mario@mail.com"));
        userRepository.save(new User("10","Dana","dana@mail.com"));

        todoRepository.deleteAll();
        todoRepository.save(new Todo("homework1 job about tech companies on the videogame market",3, new Date(2018,9,30),"pepito@mail.com","pending"));
        todoRepository.save(new Todo("homework2",3, new Date(2018,10,30),"and@mail.com","pending"));
        todoRepository.save(new Todo("homework3",3, new Date(2018,11,30),"juan@mail.com","pending"));
        todoRepository.save(new Todo("homework4",3, new Date(2018,12,30),"and@mail.com","pending"));
        todoRepository.save(new Todo("homework5",3, new Date(2018,9,20),"juan@mail.com","pending"));
        todoRepository.save(new Todo("homework6",3, new Date(2018,9,21),"and@mail.com","pending"));
        todoRepository.save(new Todo("homework7",3, new Date(2018,9,22),"juan@mail.com","pending"));
        todoRepository.save(new Todo("homework8",3, new Date(2018,9,23),"and@mail.com","pending"));
        todoRepository.save(new Todo("homework9",3, new Date(2018,9,24),"juan@mail.com","pending"));
        todoRepository.save(new Todo("homework11",3, new Date(2018,9,25),"and@mail.com","pending"));
        todoRepository.save(new Todo("homework12",3, new Date(2018,9,26),"juan@mail.com","pending"));
        todoRepository.save(new Todo("homework13",3, new Date(2018,9,27),"mario@mail.com","pending"));
        todoRepository.save(new Todo("homework14",6, new Date(2018,9,28),"mario@mail.com","pending"));
        todoRepository.save(new Todo("homewor15k",5, new Date(2018,9,29),"mario@mail.com","pending"));
        todoRepository.save(new Todo("homework16",3, new Date(2018,9,1),"mario@mail.com","pending"));
        todoRepository.save(new Todo("homework",3, new Date(2018,9,2),"luis@mail.com","pending"));
        todoRepository.save(new Todo("homework17 job about a corporate analysis to determine strategies",3, new Date(2018,9,3),"luis@mail.com","pending"));
        todoRepository.save(new Todo("homework18",3, new Date(2018,9,4),"luis@mail.com","pending"));
        todoRepository.save(new Todo("homework19",3, new Date(2018,9,5),"luis@mail.com","pending"));
        todoRepository.save(new Todo("homewor22k",3, new Date(2018,9,6),"santi@mail.com","pending"));
        todoRepository.save(new Todo("homework33",3, new Date(2018,9,7),"santi@mail.com","pending"));
        todoRepository.save(new Todo("homework44",3, new Date(2018,9,9),"santi@mail.com","pending"));
        todoRepository.save(new Todo("homework55",3, new Date(2018,9,8),"santi@mail.com","pending"));
        todoRepository.save(new Todo("homewor66k",3, new Date(2018,9,10),"santi@mail.com","pending"));
        todoRepository.save(new Todo("homework66",3, new Date(2018,9,11),"santi@mail.com","pending"));

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Alice"));

        Customer customer = mongoOperation.findOne(query, Customer.class);

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("dueDate").lt(new Date(2018,9,20)));
        List<Todo> todoL1 = mongoOperation.find(query2,Todo.class);

        System.out.println("Expired Todos:");
        for (Todo td: todoL1){
            System.out.println(td.getDescription() + " Due date: " + td.getDueDate().toString());
        }

        Query query3 = new Query();
        query3.addCriteria(Criteria.where("responsible").is("mario@mail.com").andOperator(Criteria.where("priority").gt(4)));
        List<Todo> todoL2 = mongoOperation.find(query3,Todo.class);

        System.out.println("Important Todos by Mario:");
        for (Todo td: todoL2){
            System.out.println(td.getDescription() + " Due date: " + td.getDueDate().toString() + "priority: " + td.getPriority());
        }

        Query query4 = new Query();
        List<Todo> todoL3 = mongoOperation.find(query4,Todo.class);

        System.out.println("Long descriptions:");
        for (Todo td: todoL3){
            if(td.getDescription().length()>30) {
                System.out.println(td.getDescription() + " Due date: " + td.getDueDate().toString());
            }
        }




    }

}