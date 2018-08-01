package com.kmakrutin.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ItemDeserializer extends StdDeserializer<Item>
{
  public ItemDeserializer()
  {
    super( Item.class );
  }

  @Override
  public Item deserialize( JsonParser jsonParser, DeserializationContext deserializationContext ) throws IOException, JsonProcessingException
  {
    JsonNode node = jsonParser.getCodec().readTree( jsonParser );
    int id = ( Integer ) node.get( "id" ).numberValue();
    String itemName = node.get( "itemName" ).asText();
    int userId = ( Integer ) node.get( "createdBy" ).numberValue();

    return new Item( id, itemName, new User( userId, null ) );
  }
}
