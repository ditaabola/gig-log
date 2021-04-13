package gigLog;

import lv.dita.model.Artist;
import lv.dita.model.Manager;
import lv.dita.repositories.ArtistRepository;
import lv.dita.service.ArtistService;
import lv.dita.service.ManagerService;
import lv.dita.service.impl.ArtistServiceImpl;
import org.junit.*;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ArtistServiceImplIntegrationTest {

//    @Autowired
//    private ArtistService artistService;
//
//    @Autowired
//    private ManagerService managerService;
//
//    private static final List<Artist> artistList = new ArrayList<>();
//    private static final List<Manager> managerList = new ArrayList<>();
//
//    @BeforeClass
//    public static void setUp() {
//        Artist juuk = new Artist("Juuk", "juuk@juuk.com");
//        Artist manta = new Artist("Manta", "manta@manta.com");
//        Artist sniedze = new Artist("Sniedze", "sniedze@sniedze.com");
//
//        Manager manager = new Manager("John", "Manager", "john@manager.com");
//
//        artistList.add(juuk);
//        artistList.add(manta);
//        artistList.add(sniedze);
//
//        juuk.addManagers(manager);
//
//    }
   // }


}
