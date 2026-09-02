package project;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class GlavniHttpServer {

    public static void main(String[] args) throws IOException {
    	
        int port = 8080;
        
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        server.createContext("/api/provera", new ParcelaHandler());
        
        server.setExecutor(null); 
        server.start();
        
        System.out.println("PROINVEST BACKEND JE ONLINE");
        System.out.println("Backend sluša na: http://localhost:" + port + "/api/provera");
        System.out.println("Spreman za prihvatanje JSON zahteva sa frontenda..."); 
    }

    static class ParcelaHandler implements HttpHandler {
        private final DroolsService droolsService = new DroolsService();
        private final Gson gson = new Gson(); 

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // cors omogucavanje
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                try {

                    InputStream inputStream = exchange.getRequestBody();
                    String jsonZahtev = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                    
                    System.out.println("\n[SERVER]: Stigao sirovi JSON sa frontenda.");

                    //citamo json
                    ParcelaInput inputPodaci = gson.fromJson(jsonZahtev, ParcelaInput.class);
                    System.out.println("[SERVER]: Uspešno mapirana zona: " + inputPodaci.getUrbanistickaZona());

                    // pokrecemo pravila
                    PreporukaGradnje resenje = droolsService.pokreniPravila(inputPodaci);
                    System.out.println("[SERVER]: Drools pravila uspešno izvršena.");

                    // odgovor u json
                    String jsonOdgovor = gson.toJson(resenje);

                    // slanje
                    byte[] responseBytes = jsonOdgovor.getBytes(StandardCharsets.UTF_8);
                    exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                    exchange.sendResponseHeaders(200, responseBytes.length);
                    
                    OutputStream outputStream = exchange.getResponseBody();
                    outputStream.write(responseBytes);
                    outputStream.flush();
                    outputStream.close();
                    
                    System.out.println("[SERVER]: JSON odgovor poslat nazad.");

                } catch (Exception e) {
                    System.err.println("[GREŠKA]: Problem tokom obrade: " + e.getMessage());
                    e.printStackTrace();
                    
                    String greskaPoruka = "{\"error\": \"Greška na serveru: " + e.getMessage() + "\"}";
                    byte[] greskaBytes = greskaPoruka.getBytes(StandardCharsets.UTF_8);
                    
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(500, greskaBytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(greskaBytes);
                    os.close();
                }
            } else {
              
                String odbijenica = "{\"error\": \"Dozvoljen je samo POST metod.\"}";
                byte[] odbijenicaBytes = odbijenica.getBytes(StandardCharsets.UTF_8);
                
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(405, odbijenicaBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(odbijenicaBytes);
                os.close();
            }
        }
    
}
 
}