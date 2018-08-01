package com.kmakrutin;

import static com.kmakrutin.TestUtils.getResourceAsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

public class JacksonUnmarshallingJsonWithUnknownPropertiesTest
{
  @Test( expected = UnrecognizedPropertyException.class )
  public void givenJsonHasUnknownValuesWhenDeserializingThenException() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    MyDto readValue = mapper.readValue( getResourceAsString( "/unknown_values.json" ), MyDto.class );
    assertNotNull( readValue );
  }

  @Test
  public void givenJsonHasUnknownValuesButJacksonIsIgnoringUnknownsWhenDeserializingThenCorrect() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper().configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
    MyDto readValue = mapper.readValue( getResourceAsString( "/unknown_values.json" ), MyDto.class );
    assertNotNull( readValue );
    assertThat( readValue.getStringValue(), equalTo( "a" ) );
    assertThat( readValue.isBooleanValue(), equalTo( true ) );
    assertThat( readValue.getIntValue(), equalTo( 1 ) );
  }

  @Test
  public void givenJsonHasUnknownValuesButIgnoredOnClassWhenDeserializingThenCorrect() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    MyDtoIgnoreUnknown readValue = mapper.readValue( getResourceAsString( "/unknown_values.json" ), MyDtoIgnoreUnknown.class );
    assertNotNull( readValue );
    assertThat( readValue.getStringValue(), equalTo( "a" ) );
    assertThat( readValue.isBooleanValue(), equalTo( true ) );
    assertThat( readValue.getIntValue(), equalTo( 1 ) );
  }

  @Test
  public void givenNotAllFieldsHaveValuesInJsonWhenDeserializingAJsonToAClassThenCorrect() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    MyDto readValue = mapper.readValue( getResourceAsString( "/not_all_values.json" ), MyDto.class );
    assertNotNull( readValue );
    assertThat( readValue.getStringValue(), equalTo( "a" ) );
    assertThat( readValue.isBooleanValue(), equalTo( true ) );
    assertThat( readValue.getIntValue(), equalTo( 0 ) );
  }
}
