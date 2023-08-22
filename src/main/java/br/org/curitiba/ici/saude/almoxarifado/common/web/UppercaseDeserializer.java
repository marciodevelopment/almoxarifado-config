package br.org.curitiba.ici.saude.almoxarifado.common.web;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class UppercaseDeserializer extends JsonDeserializer<String> {

  @Override
  public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    String text = node.asText();
    if (text == null)
      return null;
    return text.toUpperCase();
  }



}
