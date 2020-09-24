package com.bestcommerce;

import com.bestcommerce.entities.*;
import com.bestcommerce.repository.*;
import com.bestcommerce.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class BestCommerceApp implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private CountryRepository countryRepository;

    public static void main(String[] args) {
        SpringApplication.  run(BestCommerceApp.class, args);
    }


    //Bootstrapping default database values
    @Override
    @Transactional
    public void run(String... args) throws Exception {


        for (Role_Enum role : Role_Enum.values()
        ) {
            if (roleRepository.findByName(role).isPresent()) {

            } else {
                roleRepository.save(new Role(role));
            }
        }

        List<User> users = new ArrayList();

        User user1 = new User("togrul125@gmail.com", encoder.encode("1234567"));
        user1.setRoles(new HashSet<Role>() {{
            add(roleRepository.findByName(Role_Enum.ROLE_ADMIN).get());
            add(roleRepository.findByName(Role_Enum.ROLE_ADMIN).get());
        }});
        User user2 = new User("togrul126@gmail.com", encoder.encode("1234567"));
        user2.setRoles(new HashSet<Role>() {{
            add(roleRepository.findByName(Role_Enum.ROLE_ADMIN).get());
            add(roleRepository.findByName(Role_Enum.ROLE_ADMIN).get());
        }});
        User user3 = new User("togrul127@gmail.com", encoder.encode("1234567"));
        user3.setRoles(new HashSet<Role>() {{
            add(roleRepository.findByName(Role_Enum.ROLE_ADMIN).get());
            add(roleRepository.findByName(Role_Enum.ROLE_ADMIN).get());
        }});
        users.add(user1);
        users.add(user2);
        users.add(user3);
        for (User user : users
        ) {
            Optional<User> userFound = userRepository.findByUsername(user.getUsername());

            if (userFound.isPresent()) {
            } else {
                userRepository.save(user);
            }
        }

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Mobile"));
        categories.add(new Category("Book"));
        categories.add(new Category("Food"));

        for (Category c : categories
        ) {
           if(!categoryRepository.findByName(c.getName()).isPresent()){
               categoryRepository.save(c);
           }

        }

        List<Merchant> merchants = new ArrayList<>();

        Merchant merchant = new Merchant();
        merchant.setUser(userRepository.findByUsername("togrul125@gmail.com").get());
        merchant.setAddress("Yasamal1");
        merchant.setOwner_name("Toghrul");
        merchant.setPhone_number("123123123123");
        merchant.setMerchant_name("Toghrul125");

        Merchant merchant2 = new Merchant();
        merchant2.setUser(userRepository.findByUsername("togrul126@gmail.com").get());
        merchant2.setAddress("Yasamal2");
        merchant2.setOwner_name("Tural");
        merchant2.setPhone_number("123123123123");
        merchant2.setMerchant_name("Toghrul126");

        Merchant merchant3 = new Merchant();
        merchant3.setUser(userRepository.findByUsername("togrul127@gmail.com").get());
        merchant3.setAddress("Yasamal3");
        merchant3.setOwner_name("Orxan");
        merchant3.setPhone_number("123123123123");
        merchant3.setMerchant_name("Toghrul127");

        merchants.add(merchant);
        merchants.add(merchant2);
        merchants.add(merchant3);

        for (Merchant m : merchants
        ) {
            if(!merchantRepository.findByMerchant_name(m.getMerchant_name()).isPresent()){
                merchantRepository.save(m);
            }
        }


        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setInventory(6);
        product1.setDiscount(10.0);
        product1.setCategory(categoryRepository.findByName("Mobile").get());
        product1.setName("Galaxy 10");
        product1.setDiscount_end(LocalDate.of(2020, 9, 29));
        product1.setDiscount_start(LocalDate.of(2020, 9, 20));
        product1.setPrice((double) 700);
        product1.setMerchant(merchantRepository.findMerchantByOwner_name("Toghrul").get());
        System.out.println( product1.toString());

        Product product2 = new Product();
        product2.setInventory(10);
        product2.setDiscount(5.0);
        product2.setCategory(categoryRepository.findByName("Book").get());
        product2.setName("Harry Potter");
        product2.setDiscount_end(LocalDate.of(2020, 9, 18));
        product2.setDiscount_start(LocalDate.of(2020, 9, 11));
        product2.setPrice((double) 100);
        product2.setMerchant(merchantRepository.findMerchantByOwner_name("Tural").get());
        System.out.println( product2.toString());

        Product product3 = new Product();
        product3.setInventory(4);
        product3.setDiscount(20.0);
        product3.setCategory(categoryRepository.findByName("Food").get());
        product3.setName("Chocolate");
        product3.setDiscount_end(LocalDate.of(2020, 9, 30));
        product3.setDiscount_start(LocalDate.of(2020, 9, 23));
        product3.setPrice((double) 50);
        product3.setMerchant(merchantRepository.findMerchantByOwner_name("Orxan").get());

        System.out.println( product3.toString());

        products.add(product1);
        products.add(product2);
        products.add(product3);


        for (Product p : products
        ) {
            if(!productRepository.findByName(p.getName()).isPresent()){
                productRepository.save(p);
            }

        }

        List<Country> countries = new ArrayList<>();
        countries.add(new Country("Azerbaijan"));
        countries.add(new Country("Russia"));
        countries.add(new Country("Turkey"));

        for (Country c: countries
             ) {
            if(!countryRepository.findByName(c.getName()).isPresent()){
                countryRepository.save(c);
            }
        }



    }

}
