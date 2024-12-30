package Principal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Moeda;
import modelos.MoedaApi;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class TestePrinciapl {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<Moeda> listaMoeda = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String endereco = "https://v6.exchangerate-api.com/v6/1b3d359e030040c30ce7ee8e/latest/USD" ;



        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        MoedaApi moedaApi = gson.fromJson(json, MoedaApi.class);


        Moeda moeda = new Moeda(moedaApi);
        listaMoeda.add(moeda);

        FileWriter escrita = new FileWriter("listMoedas.json");
        escrita.write(gson.toJson(listaMoeda));

        escrita.close();


    }

}
