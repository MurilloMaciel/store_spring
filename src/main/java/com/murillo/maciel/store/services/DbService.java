package com.murillo.maciel.store.services;

import com.murillo.maciel.store.domain.*;
import com.murillo.maciel.store.domain.enums.ClientType;
import com.murillo.maciel.store.domain.enums.PaymentStatus;
import com.murillo.maciel.store.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DbService
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


    public void instantiateTestDatabase() throws ParseException
    {

        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama, Mesa e Banho");
        Category cat4 = new Category(null, "Eletrônicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoração");
        Category cat7 = new Category(null, "Perfumaria");
        Category cat8 = new Category(null, "Gamer");
        Category cat9 = new Category(null, "Carros");
        Category cat10 = new Category(null, "Borrachas");
        Category cat11 = new Category(null, "Escolar");
        Category cat12 = new Category(null, "Banheiro");
        Category cat13 = new Category(null, "Construção");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Mesa de escritório", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "TV", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampoo", 9.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

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
//
        p1.getItens().addAll(Arrays.asList(orderItem1));
        p2.getItens().addAll(Arrays.asList(orderItem3));
        p3.getItens().addAll(Arrays.asList(orderItem2));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7,cat8,cat9,cat10,cat11,cat12,cat13));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));
        clientRepository.saveAll(Arrays.asList(client1));
        addressRepository.saveAll(Arrays.asList(address1, address2));
        orderRepository.saveAll(Arrays.asList(order1, order2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));
        orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
    }
}
