import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;

public class StylistTest {

  @Before
    public void setUp() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
    }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteClientsQuery = "DELETE FROM clients *;";
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteClientsQuery).executeUpdate();
      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist myStylist = new Stylist("Jane Doe", "5035551234");
    assertEquals(true, myStylist instanceof Stylist);
  }

  @Test
  public void getName_clientInstantiatesWithName_String() {
    Stylist myStylist = new Stylist("Jane Doe", "5035551234");
    assertEquals("Jane Doe", myStylist.getName());
  }

  @Test
  public void allStylists_emptyAtFirst() {
    assertEquals(Stylist.allStylists().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStylistAretheSame() {
    Stylist firstStylist = new Stylist("Jane Doe", "5035551234");
    Stylist secondStylist = new Stylist("Jane Doe", "5035551234");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_returnsTrueTheSame() {
    Stylist myStylist = new Stylist("Jane Doe", "5035551234");
    myStylist.save();
    assertTrue(Stylist.allStylists().get(0).equals(myStylist));
  }

  @Test
  public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Jane Doe", "5035551234");
    myStylist.save();
    Stylist savedStylist = Stylist.allStylists().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

  @Test
  public void find_findsStylistInDatabase_true() {
    Stylist myStylist = new Stylist("Jane Doe", "5035551234");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesALlClientsFromDatabase_tasksList() {
    Stylist myStylist = new Stylist("Jane Doe", "5035551234");
    myStylist.save();
    Client firstClient = new Client("John Doe", "5031235555", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Jim Doe", "5031235555", myStylist.getId());
    secondClient.save();
    Client[] tasks = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(tasks)));
  }
}
