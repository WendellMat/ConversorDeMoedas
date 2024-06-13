import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConsultaAPI {
    private HttpResponse<String> consulta(String moedaBase, String moedaDestino) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String line = null;
        Scanner in = new Scanner(new FileReader("ChaveAPIExchangeRate.txt"));
        if (in.hasNextLine()) {
            line = in.nextLine();
        }

        String endereco = "https://v6.exchangerate-api.com/v6/" + line + "/pair/" + moedaBase + "/" + moedaDestino;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    public double consultaCambio(String moedaBase, String moedaDestino) {
        Gson gson = new Gson();
        HttpResponse<String> moedaTemporaria;
        try {
            moedaTemporaria = consulta(moedaBase, moedaDestino);
            Moeda moedaConvertida = gson.fromJson(moedaTemporaria.body(), Moeda.class);
            double valorDeCambio = moedaConvertida.conversion_rate();
            return valorDeCambio;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
