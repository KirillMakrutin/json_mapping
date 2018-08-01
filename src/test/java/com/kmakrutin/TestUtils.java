package com.kmakrutin;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class TestUtils
{
  private TestUtils()
  {
  }

  public static String getResourceAsString( String resource ) throws IOException
  {
    return IOUtils.resourceToString( resource, StandardCharsets.UTF_8 );
  }
}
