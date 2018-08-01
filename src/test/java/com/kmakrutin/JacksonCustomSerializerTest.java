package com.kmakrutin;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kmakrutin.serializer.Item;
import com.kmakrutin.serializer.ItemSerializer;
import com.kmakrutin.serializer.ItemWithSerializer;
import com.kmakrutin.serializer.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonCustomSerializerTest
{
  @Test
  public void defaultSerialization() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    Item myItem = new Item( 1, "theItem", new User( 2, "theUser" ) );
    String serialized = mapper.writeValueAsString( myItem );
    log.info( "Default serializer: {}", serialized );
    assertEquals( myItem, mapper.readValue( serialized, Item.class ) );
  }

  @Test
  public void customSerializer() throws JsonProcessingException
  {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer( Item.class, new ItemSerializer() );
    mapper.registerModule( simpleModule );
    String serialized = mapper.writeValueAsString( new Item( 1, "theItem", new User( 2, "theUser" ) ) );
    log.info( "Custom serializer: {}", serialized );
  }

  @Test
  public void customSerializerInClass() throws JsonProcessingException
  {
    ObjectMapper mapper = new ObjectMapper();
    String serialized = mapper.writeValueAsString( new ItemWithSerializer( 1, "theItem", new User( 2, "theUser" ) ) );
    log.info( "Custom serializer in class: {}", serialized );
  }
}
