import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private String phone;
  private int stylist_id;

  public Client(String name, String phone, int stylist_id){
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.stylist_id = stylist_id;
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getPhone(){
    return phone;
  }

  public int getStylist_Id(){
    return stylist_id;
  }

  public static List<Client> allClients() {
    String sql = "SELECT id, name, phone, stylist_id FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getId() == newClient.getId() &&
             this.getPhone().equals(newClient.getPhone()) &&
             this.getStylist_Id() == newClient.getStylist_Id();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients(name, phone, stylist_id) VALUES (:name, :phone, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("phone", this.phone)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

}
