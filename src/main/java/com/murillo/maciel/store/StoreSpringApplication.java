package com.murillo.maciel.store;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.murillo.maciel.store.domain.*;
import com.murillo.maciel.store.domain.enums.ClientType;
import com.murillo.maciel.store.domain.enums.PaymentStatus;
import com.murillo.maciel.store.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreSpringApplication implements CommandLineRunner
{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public static void main(String[] args)
    {
        SpringApplication.run(StoreSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().add(p2);

        p1.getCategories().add(cat1);
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().add(cat1);

        State state1 = new State(null, "Minas Gerais");
        State state2 = new State(null, "São Paulo");

        City city1 = new City(null, "Uberlândia", state1);
        City city2 = new City(null, "São Paulo", state2);
        City city3 = new City(null, "Campinas", state2);

        state1.getCities().add(city1);
        state1.getCities().addAll(Arrays.asList(city2, city3));

        Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.INDIVIDUAL_ENTITY);
        client1.getPhones().addAll(Arrays.asList("123456789", "987654321"));

        Address address1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "93260010", client1, city1);
        Address address2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "90270010", client1, city2);

        client1.getAdresses().addAll(Arrays.asList(address1, address2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Order order1 = new Order(null, sdf.parse("30/09/2019 10:32"), client1, address1);
        Order order2 = new Order(null, sdf.parse("10/10/2017 19:35"), client1, address2);

        Payment payment1 = new CardPayment(null, PaymentStatus.CONFIRMED, order1, 6);
        order1.setPayment(payment1);

        Payment payment2 = new BankSlipPayment(null, PaymentStatus.PENDING, order2, sdf.parse("20/10/2017 00:00"), null);
        order2.setPayment(payment2);

        client1.getOrders().addAll(Arrays.asList(order1, order2));

        OrderItem orderItem1 = new OrderItem(order1, p1, 0.00, 1, 2000.00);
        OrderItem orderItem2 = new OrderItem(order1, p3, 0.00, 2, 80.00);
        OrderItem orderItem3 = new OrderItem(order2, p2, 100.00, 1, 800.00);

        order1.getItens().addAll(Arrays.asList(orderItem1, orderItem2));
        order2.getItens().addAll(Arrays.asList(orderItem3));

        p1.getItens().addAll(Arrays.asList(orderItem1));
        p2.getItens().addAll(Arrays.asList(orderItem3));
        p3.getItens().addAll(Arrays.asList(orderItem2));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));
        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));
        clientRepository.saveAll(Arrays.asList(client1));
        addressRepository.saveAll(Arrays.asList(address1, address2));
        orderRepository.saveAll(Arrays.asList(order1, order2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));
        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
    }

}
