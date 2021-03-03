//import com.conference.entity.Event;
//import com.conference.exceptions.EventServiceException;
//import com.conference.service.EventService;
//import com.conference.service.ServiceFactory;
//import org.apache.log4j.Logger;
//import org.junit.*;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class EventServiceTest {
////
//    private static EventService eventService;
//    private static Event event;
////
//    private static final Logger log = Logger.getLogger(EventServiceTest.class);
////
//    @Before
//    public static void setUp() throws EventServiceException {
//        log.info("Starting tests");
//        eventService = ServiceFactory.getEventService();
//        event = new Event();
//    }
////
//    @After
//    public static void close() throws EventServiceException {
//        log.info("Finishing tests");
//        event = null;
//    }
//
//    @Test
//    public void findAllEvents() throws EventServiceException {
////        assertTrue(eventService.findAllEvents().size() > 0);
//    }
////
//    @Test
//    public void findEventByTitle() throws EventServiceException {
////        assertEquals(event.getTitle(), eventService.findEventByTitle(event.getTitle()).getTitle());
//    }
//}