package Principal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Conversor;
import modelos.Moeda;
import modelos.MoedaApi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String endereco = "";
        int opcao = 0;
        Scanner scanner = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        while (opcao != 7) {

            System.out.println("* ******************************************\n" +
                    "* Seja bem-vindo/a ao Conversor de Moeda\n"+
                    "* 1) Dólar =>> Peso Argentino\n" +
                    "* 2) Peso Argentino =>> Dólar\n" +
                    "* 3) Dólar =>> Real Brasileiro\n" +
                    "* 4) Real brasileiro =>> Dólar\n" +
                    "* 5) Dólar =>> Peso Colombiano\n" +
                    "* 6) Peso Colombiano =>> Dólar\n" +
                    "* 7) Sair\n" +
                    "* Escolha uma opção valida: \n" +
                    "* ******************************************\n");
            try {
                opcao = scanner.nextInt();
                if (opcao == 7) {
                    break;
                }

                switch (opcao) {
                    case 1:
                        System.out.println("Dolar Para peso Argentino: \n");
                        endereco = "https://v6.exchangerate-api.com/v6/1b3d359e030040c30ce7ee8e/pair/USD/ARS";
                        break;
                    case 2:
                        System.out.println("Peso Argentino para Dolar: \n");
                        endereco = "https://v6.exchangerate-api.com/v6/1b3d359e030040c30ce7ee8e/pair/ARS/USD";
                        break;
                    case 3:
                        System.out.println("Dolar para Real: \n");
                        endereco = "https://v6.exchangerate-api.com/v6/1b3d359e030040c30ce7ee8e/pair/USD/BRL";
                        break;
                    case 4:
                        System.out.println("Real para Dolar: \n");
                        endereco = "https://v6.exchangerate-api.com/v6/1b3d359e030040c30ce7ee8e/pair/BRL/USD";
                        break;
                    case 5:
                        System.out.println("Dolar para Peso Colombiano: \n");
                        endereco = "https://v6.exchangerate-api.com/v6/1b3d359e030040c30ce7ee8e/pair/USD/COP";
                        break;
                    case 6:
                        System.out.println("Peso Colombiano para Dolar: \n");
                        endereco = "https://v6.exchangerate-api.com/v6/1b3d359e030040c30ce7ee8e/pair/COP/USD";
                        break;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        continue;
                }

                System.out.println("Escolha o valor para Converter");
                double valor = scanner.nextDouble();

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                MoedaApi moedaApi = gson.fromJson(json, MoedaApi.class);
                Moeda moeda = new Moeda(moedaApi);

                Conversor conversor = new Conversor(moeda);
                conversor.setValorDesejado(valor);

                System.out.println("Valor Para converter: " + moeda.getValorParaConverterMoeda());
                System.out.println("Valor desejado: " + conversor.getValorDesejado() + " [" + moeda.getNomeMoeda() + "]");
                System.out.println("Convertendo: " + conversor.getConverter() + " [" + moeda.getNomeDaMoedaConvertida() + "]\n");

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            }  catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

        System.out.println("Programa Finalizado");
    }
}
