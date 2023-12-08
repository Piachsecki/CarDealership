import lombok.extern.slf4j.Slf4j;
import org.example.business.dao.*;
import org.example.business.dao.management.CarDealershipManagementDAO;
import org.example.business.services.app.*;
import org.example.business.services.init.CarDealershipManagementService;
import org.example.business.services.init.FileDataPreparationService;
import org.example.infrastructure.configuration.HibernateUtil;
import org.example.infrastructure.database.entity.AddressEntity;
import org.example.infrastructure.database.entity.CarToServiceEntity;
import org.example.infrastructure.database.entity.CustomerEntity;
import org.example.infrastructure.database.repository.*;
import org.junit.jupiter.api.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarDealershipTest {

    private CarDealershipManagementService carDealershipManagementService;
    private CarPurchaseService carPurchaseService;
    private CustomerService customerService;
    private CarService carService;
    private SalesmanService salesmanService;
    private CarServiceRequestService carServiceRequestService;
    private ProcessRequestService processRequestService;
    private PartService partService;
    private MechanicService mechanicService;
    private ServiceDoneService serviceDoneService;
    private ServiceMechanicService serviceMechanicService;


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
        CarServiceRequestDAO carServiceRequestDAO = new CarServiceRequestRepository();
        CarDAO carDAO = new CarRepository();
        PartDAO partDAO = new PartRepository();
        MechanicDAO mechanicDAO = new MechanicRepository();
        ServiceDAO serviceDAO = new ServiceRepository();
        ServiceMechanicDAO serviceMechanicDAO = new ServiceMechanicRepository();


        customerService = new CustomerService(customerRepo);
        salesmanService = new SalesmanService(salesmanDAO);
        mechanicService = new MechanicService(mechanicDAO);
        serviceMechanicService = new ServiceMechanicService(serviceMechanicDAO);
        serviceDoneService = new ServiceDoneService(serviceDAO);
        partService = new PartService(partDAO);
        carService = new CarService(carDAO);
        carPurchaseService = new CarPurchaseService(customerService, salesmanService, carService);

        carServiceRequestService = new CarServiceRequestService(customerService, carService, carServiceRequestDAO);

        processRequestService = new ProcessRequestService(
                partService,
                carServiceRequestService,
                mechanicService,
                serviceDoneService,
                serviceMechanicService
        );
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
    @Order(4)
    void purchaseSecondTime() {
        log.info("### RUNNING ORDER 4");
        String email = "kacperowy@gmail.com";
        String salesmanPesel = "73021314515";
        String vin = "1N6BD06T45C416702";
        carPurchaseService.purchase(email, vin, salesmanPesel);


    }


    @Test
    @Order(5)
    void makeServiceRequestForNewCustomer() {
        log.info("### RUNNING ORDER 5");
        AddressEntity address = AddressEntity.builder()
                .country("Poland")
                .city("Wroclaw")
                .postalCode("28-982")
                .address("Peninska 15")
                .build();
        CustomerEntity customer = CustomerEntity.builder()
                .name("Stefan")
                .surname("Stefanski")
                .phone("+45 111 222 333")
                .email("stefanowy@gmail.com")
                .address(address)
                .build();
        CarToServiceEntity car = CarToServiceEntity.builder()
                .brand("BMW")
                .model("M1")
                .year((short) 2015)
                .vin("1N4BA41E18C806520")
                .build();

        customerService.saveCustomer(customer);
        carService.saveCarToService(car);
        carServiceRequestService.request(customer.getEmail(), car.getVin(), "Wymiana kół");


    }


    @Test
    @Order(6)
    void aMechanicIsProcessingTheMadeServiceRequest() {
        log.info("### RUNNING ORDER 6");
        String carServiceRequestNumber =
                "b628d48e-8fca-43a2-abdd-d5e98f5d7458";
        String mechanicPesel = "52070997836";
        String serviceCode = "55319-866";
        String comment = "cos sie zepsulo";
        int hoursSpent = 5;

        processRequestService.processRequestService(
                carServiceRequestNumber,
                mechanicPesel,
                serviceCode,
                comment,
                hoursSpent
        );


    }

    @Test
    @Order(7)
    void aMechanicIsProcessingServiceRequestAndDefinesPartsThatWereNeeded() {
        log.info("### RUNNING ORDER 7");
        String carServiceRequestNumber =
                "b628d48e-8fca-43a2-abdd-d5e98f5d7458";
        String serialNumber = "11523-7310";
        int quantity = 5;

        processRequestService.processServicePart(carServiceRequestNumber, serialNumber, quantity);


    }

}
