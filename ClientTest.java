import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {

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
  public void Client_instantiatesCorrectly_true() {
    Client myClient = new Client("Jane Doe", "5035551233", 1);
    assertEquals(true, myClient instanceof Client);
  }

  @Test
  public void getName_clientInstantiatesWithName_String() {
    Client myClient = new Client("Jane Doe", "5035551233", 1);
    assertEquals("Jane Doe", myClient.getName());
  }

  @Test
  public void allClients_emptyAtFirst() {
    assertEquals(Client.allClients().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfStylistsAretheSame() {
    Client firstClient = new Client("Jane Doe", "5035551233", 1);
    Client secondClient = new Client("Jane Doe", "5035551233", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnsTrueTheSame() {
    Client myClient = new Client("Jane Doe", "5035551233", 1);
    myClient.save();
    assertTrue(Client.allClients().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Jane Doe", "5035551233", 1);
    myClient.save();
    Client savedClient = Client.allClients().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client myClient = new Client("Jane Doe", "5035551233", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist myStylist = new Stylist("John Doe", "5031235555");
    myStylist.save();
    Client myClient = new Client("Jane Doe", "5035551233", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylist_Id(), myStylist.getId());
  }

}
