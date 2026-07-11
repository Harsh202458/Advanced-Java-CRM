package crm;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import crm.service.CRMSystem;
import crm.model.Customer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WebMain {
      private static final CRMSystem crm = new CRMSystem();

    public static void main(String[] args) throws Exception {
              HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

          server.createContext("/", new HttpHandler() {
                        @Override
                        public void handle(HttpExchange exchange) throws IOException {
                                          if (!exchange.getRequestURI().getPath().equals("/")) {
                                                                exchange.sendResponseHeaders(404, -1);
                                                                return;
                                          }
                                          byte[] response = Files.readAllBytes(Paths.get("../public/index.html"));
                                          exchange.getResponseHeaders().set("Content-Type", "text/html");
                                          exchange.sendResponseHeaders(200, response.length);
                                          OutputStream os = exchange.getResponseBody();
                                          os.write(response);
                                          os.close();
                        }
          });

          server.createContext("/api/customers", new HttpHandler() {
                        @Override
                        public void handle(HttpExchange exchange) throws IOException {
                                          List<Customer> customers = crm.getCustomers();
                                          StringBuilder json = new StringBuilder("[");
                                          for(int i = 0; i < customers.size(); i++) {
                                                                json.append(customers.get(i).toJson());
                                                                if (i < customers.size() - 1) json.append(",");
                                          }
                                          json.append("]");

                            String response = json.toString();
                                          exchange.getResponseHeaders().set("Content-Type", "application/json");
                                          // Enable CORS
                            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                                          byte[] bytes = response.getBytes();
                                          exchange.sendResponseHeaders(200, bytes.length);
                                          OutputStream os = exchange.getResponseBody();
                                          os.write(bytes);
                                          os.close();
                        }
          });

          server.setExecutor(null); // creates a default executor
          System.out.println("Web Dashboard running at http://localhost:8080");
              server.start();
    }
}
