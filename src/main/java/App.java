import java.util.*;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import org.sql2o.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylist-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      Stylist newStylist = new Stylist(name, phone);
      newStylist.save();
      model.put("stylists", newStylist);
      model.put("stylists", Stylist.allStylists());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.allStylists());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", newStylist);
      model.put("stylists", Stylist.allStylists());
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:sId/clients/new", (request, response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Stylist myStylist = Stylist.find(Integer.parseInt(request.params(":sId")));
      model.put("myStylist", myStylist);
      model.put("template", "templates/client-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients", (request, response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      String name = request.queryParams("name");
      String phone = request.queryParams("phone");
      Client newClient = new Client(name, phone, stylist.getId());
      newClient.save();
      model.put("client", newClient);
      model.put("template", "templates/clients.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/clients", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("clients", Client.allClients());
    //   model.put("template", "templates/clients.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    get("/client/:cId", (request, response) -> {
     Map<String, Object> model = new HashMap<String,Object>();
     Client individualClient = Client.find(Integer.parseInt(request.params(":cId")));
     model.put("individualClient", individualClient);
     model.put("template", "templates/client.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

  }
}
