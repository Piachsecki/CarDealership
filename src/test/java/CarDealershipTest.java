import lombok.extern.slf4j.Slf4j;
import org.example.business.dao.CarDAO;
import org.example.business.dao.CustomerDAO;
import org.example.business.dao.SalesmanDAO;
import org.example.business.dao.management.CarDealershipManagementDAO;
import org.example.business.services.app.CarPurchaseService;
import org.example.business.services.app.CarService;
import org.example.business.services.app.CustomerService;
import org.example.business.services.app.SalesmanService;
import org.example.business.services.init.CarDealershipManagementService;
import org.example.business.services.init.FileDataPreparationService;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.AddressEntity;
import org.example.infrastructure.database.entity.CustomerEntity;
import org.example.infrastructure.database.repository.CarDealershipManagementRepository;
import org.example.infrastructure.database.repository.CarRepository;
import org.example.infrastructure.database.repository.CustomerRepository;
import org.example.infrastructure.database.repository.SalesmanRepository;
import org.junit.jupiter.api.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarDealershipTest {

    private CarDealershipManagementService carDealershipManagementService;
    private CarPurchaseService carPurchaseService;
    private CustomerService customerService;
    private CarService carService;
    private SalesmanService salesmanService;


    @AfterAll
    static void afterAll() {
        HibernateUtil.closeFactory();
    }

    @BeforeEach
    void beforeEach() {
        FileDataPreparationService fileDataPreparationService = new FileDataPreparationService();
        CarDealershipManagementDAO carDealershipManagementRepository = new CarDealershipManagementRepository();
        carDealershipManagementService = new CarDealershipManagementService(
                carDealershipManagementRepository, fileDataPreparationService);

        CustomerDAO customerRepo = new CustomerRepository();
        SalesmanDAO salesmanDAO = new SalesmanRepository();
        CarDAO carDAO = new CarRepository();


        customerService = new CustomerService(customerRepo);
        salesmanService = new SalesmanService(salesmanDAO);
        carService = new CarService(carDAO);
        carPurchaseService = new CarPurchaseService(fileDataPreparationService, customerService, salesmanService, carService);
    }

    @Test
    @Order(1)
    void purge() {
        log.info("### RUNNING ORDER 1");
        carDealershipManagementService.purge();
    }

    @Test
    @Order(2)
    void init() {
        log.info("### RUNNING ORDER 2");
        carDealershipManagementService.init();
    }

    @Test
    @Order(3)
    void purchaseFirstTime() {
        log.info("### RUNNING ORDER 3");
        AddressEntity address = AddressEntity.builder()
                .country("Poland")
                .city("Wroclaw")
                .postalCode("28-982")
                .address("Peninska 15")
                .build();
        CustomerEntity customer = CustomerEntity.builder()
                .name("Kacper")
                .surname("Piasecki")
                .phone("+45 111 222 333")
                .email("kacperowy@gmail.com")
                .address(address)
                .build();
        //w programie customer wybiera auto do zakupu i sprzedawce ktory mu pokazywal auto
        //na potrzebe testow wybieramy

        customerService.saveCustomer(customer);
        String vin = "1G1PE5S97B7239380";
        String salesmanPesel = "73021314515";

        carPurchaseService.purchase(customer.getEmail(), vin, salesmanPesel);


    }

    @Test
    @Order(3)
    void purchaseSecondTime() {
        log.info("### RUNNING ORDER 4");

    }
}
