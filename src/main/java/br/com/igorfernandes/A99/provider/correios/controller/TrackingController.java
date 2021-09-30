package br.com.igorfernandes.A99.provider.correios.controller;

import br.com.igorfernandes.A99.provider.correios.dto.CorreiosShippingStatus;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;

public class TrackingController {
    private static TrackingController instance;
    private final Gson gson = new Gson();

    private TrackingController() {
    }

    public static TrackingController getInstance() {
        if (instance == null) {
            instance = new TrackingController();
    }
        return instance;
    }

    // TODO: Fazer um método geral para mais de um tipo de entrega
    public Optional<CorreiosShippingStatus> findCorreiosShippingStatus(String shippingCode) {
        // TODO: Validate the shipping code
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        String urlRequest =
                String.format("https://rastreamento.correios.com.br/app/resultado.php?objeto=%s&mqs=S", shippingCode);

        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url(urlRequest)
                .method("POST", body)
                .addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:63.0) Gecko/20100101 Firefox/63.0")
                .addHeader("Accept", "*/*")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Connection", "keep-alive")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            Optional<ResponseBody> responseBody = Optional.ofNullable(response.body());
            if (responseBody.isPresent()) {
                return Optional.ofNullable(gson.fromJson(responseBody.get().string(), CorreiosShippingStatus.class));
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
