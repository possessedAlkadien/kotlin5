package com.petshop.PetShop_management.config;

import com.petshop.PetShop_management.entity.*;
import com.petshop.PetShop_management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Arrays;


@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    
    

    @Override
    public void run(String... args) throws Exception 
    {

    productRepository.deleteAll();
    categoryRepository.deleteAll();
    animalRepository.deleteAll();
    supplierRepository.deleteAll();
    customerRepository.deleteAll();
    employeeRepository.deleteAll();
    productOrderRepository.deleteAll();

    Category dogs = new Category();
    dogs.setCategoryName("Собаки");
    dogs.setDescript("Категория товаров и животных для собак");

    Category cats = new Category();
    cats.setCategoryName("Кошки");
    cats.setDescript("Категория товаров и животных для кошек");

    Category birds = new Category();
    birds.setCategoryName("Птицы");
    birds.setDescript("Категория товаров и животных для птиц");

    categoryRepository.saveAll(Arrays.asList(dogs, cats, birds));


    Supplier petSuppliesCo = new Supplier();
    petSuppliesCo.setCompanyName("Pet Supplies Co.");
    petSuppliesCo.setPhoneNumber("+7-123-456-7890");
    petSuppliesCo.setAddress("г. Москва, ул. Ленина, д. 10");

    Supplier animalBreeders = new Supplier();
    animalBreeders.setCompanyName("Animal Breeders Inc.");
    animalBreeders.setPhoneNumber("+7-098-765-4321");
    animalBreeders.setAddress("г. СПб, пр. Невский, д. 25");

    supplierRepository.saveAll(Arrays.asList(petSuppliesCo, animalBreeders));

    
    Product dogFood = new Product();
    dogFood.setProductName("Корм для собак Premium");
    dogFood.setDescript("Высококачественный корм для взрослых собак");
    dogFood.setPrice(1500);
    dogFood.setRemnant(100);
    dogFood.setCategory(dogs);
    dogFood.setSupplier(petSuppliesCo);

    Product catToy = new Product();
    catToy.setProductName("Игрушка для кошек Мышка");
    catToy.setDescript("Мягкая игрушка-мышонок для кошек");
    catToy.setPrice(300);
    catToy.setRemnant(50);
    catToy.setCategory(cats);
    catToy.setSupplier(petSuppliesCo);

    productRepository.saveAll(Arrays.asList(dogFood, catToy));


    Animal parrot = new Animal();
    parrot.setName("Суп");
    parrot.setType("Птица");
    parrot.setAge(2);
    parrot.setGender("М");
    parrot.setPrice(25000);
    parrot.setStatus("В наличии");
    parrot.setSupplier(animalBreeders);

    Animal rabbit = new Animal();
    rabbit.setName("Буба");
    rabbit.setType("Кролик");
    rabbit.setAge(1);
    rabbit.setGender("Ж");
    rabbit.setPrice(15000);
    rabbit.setStatus("В наличии");
    rabbit.setSupplier(animalBreeders);

    animalRepository.saveAll(Arrays.asList(parrot, rabbit));


    Customer johnDoe = new Customer();
    johnDoe.setFirstName("Иван");
    johnDoe.setLastName("Иванов");
    johnDoe.setSurName("Иванович");
    johnDoe.setEmail("ivan.ivanov@example.com");
    johnDoe.setPhoneNumber("+7-555-123-4567");
    johnDoe.setAddress("г. СПб, пр. Невский, д. 25");

    Customer janeSmith = new Customer();
    janeSmith.setFirstName("Анна");
    janeSmith.setLastName("Смирнова");
    janeSmith.setSurName("Петровна");
    janeSmith.setEmail("anna.smith@example.com");
    janeSmith.setPhoneNumber("+7-555-987-6543");
    janeSmith.setAddress("г. СПб, пр. Невский, д. 25");

    customerRepository.saveAll(Arrays.asList(johnDoe, janeSmith));

    Employee johnDoe1 = new Employee();
    johnDoe1.setFirstName("Делать");
    johnDoe1.setLastName("Деньги");
    johnDoe1.setSurName("Иванович");
    johnDoe1.setJobTitle("Кассир");
    johnDoe1.setPhoneNum("+7-585-123-4567");
    johnDoe1.setAddress("г. СПб, пр. Невский, д. 25");

    Employee janeSmith1 = new Employee();
    janeSmith1.setFirstName("Купи");
    janeSmith1.setLastName("Слона");
    janeSmith1.setSurName("Петровна");
    janeSmith1.setJobTitle("Менеджер по продажам");
    janeSmith1.setPhoneNum("+7-585-987-6543");
    janeSmith1.setAddress("г. СПб, пр. Невский, д. 25");

    employeeRepository.saveAll(Arrays.asList(johnDoe1, janeSmith1));


    ProductOrder order1 = new ProductOrder();
    order1.setOrderDate(LocalDate.now());
    order1.setOrderPrice(1800);
    order1.setStatus("Новый");
    order1.setCustomer(johnDoe);
    order1.setSupplier(petSuppliesCo);

    ProductOrder order2 = new ProductOrder();
    order2.setOrderDate(LocalDate.now());
    order2.setOrderPrice(25000);
    order2.setStatus("В пути");
    order2.setCustomer(janeSmith);
    order2.setSupplier(animalBreeders);

    productOrderRepository.saveAll(Arrays.asList(order1, order2));

    System.out.println("Тестовые данные для PetShop загружены успешно!");
    
    }
}