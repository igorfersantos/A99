package br.com.igorfernandes.A99.provider.correios.dto;

import br.com.igorfernandes.A99.view.shipping.ShippingStep;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CorreiosShippingStatusDeserializer implements JsonDeserializer<CorreiosShippingStatus> {

    @Override
    public CorreiosShippingStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray eventosArrayObject = jsonObject.get("eventos").getAsJsonArray();

        List<Evento> eventos = new ArrayList<>();
        for (JsonElement eventoArr : eventosArrayObject) {
            Evento evento = context.deserialize(eventoArr, Evento.class);
            eventos.add(evento);
        }

        CorreiosShippingStatus correiosShippingStatus = new CorreiosShippingStatus(
                jsonObject.get("codObjeto").getAsString(),
                context.deserialize(jsonObject.get("tipoPostal").getAsJsonObject(), TipoPostal.class),
                jsonObject.get("dtPrevista").getAsString(),
                jsonObject.get("modalidade").getAsString(),
                eventos,
                jsonObject.get("situacao").getAsString(),
                jsonObject.get("autoDeclaracao").getAsBoolean(),
                jsonObject.get("encargoImportacao").getAsBoolean(),
                jsonObject.get("percorridaCarteiro").getAsBoolean(),
                jsonObject.get("bloqueioObjeto").getAsBoolean(),
                jsonObject.get("arEletronico").getAsBoolean());

//        Gson gson = new Gson();
//
//        CorreiosShippingStatus correiosShippingStatus = gson.fromJson(json.getAsJsonObject(),
//                CorreiosShippingStatus.class);

        List<ShippingStep> shippingSteps = correiosShippingStatus.getEventos()
                .stream()
                .map(e -> new ShippingStep(
                        // TODO: refatorar isso aqui pra um método
                        String.format(CorreiosConstants.ICON_URL_FORMAT, e.getIcone()),
                        e.getDescricao(),
                        formatEventDescription(e),
                        e.getDtHrCriado()
                ))
                .collect(Collectors.toList());

        correiosShippingStatus.getSteps().clear();
        correiosShippingStatus.getSteps().addAll(shippingSteps);

        return correiosShippingStatus;
    }

    private String formatEventDescription(Evento evento) {
        StringBuilder eventDescriptionBuilder = new StringBuilder();

        if (evento.getUnidadeDestino() != null) {
            eventDescriptionBuilder.append(
                    "para " + evento.getUnidadeDestino().getTipo() + ",\n"
                            + evento.getUnidadeDestino().getCidade() + '/' + evento.getUnidadeDestino().getUf() + '\n'
                            + "de ");
        }
        else {
            eventDescriptionBuilder.append(evento.getUnidade().getTipo() + ",\n"
                    + evento.getUnidade().getCidade() + '/' + evento.getUnidade().getUf() + '\n');
        }

        return eventDescriptionBuilder.toString();
    }
}
