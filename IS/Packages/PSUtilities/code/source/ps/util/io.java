package ps.util;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2007-05-15 10:39:44 EDT
// -----( ON-HOST: 5DWKV81.ncmc.ncm.pvt

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.*;

// --- <<IS-END-IMPORTS>> ---

public final class io

{
  // ---( internal utility methods )---

  final static io _instance = new io();

  static io _newInstance()
  {
    return new io();
  }

  static io _cast(Object o)
  {
    return (io) o;
  }

  // ---( server methods )---

  public static final void close(IData pipeline) throws ServiceException
  {
    // --- <<IS-START(close)>> ---
    // @sigtype java 3.5
    // [i] object:0:required stream
    IDataCursor cursor = pipeline.getCursor();

    Object stream;

    try
    {
      if (cursor.first("stream"))
      {
        // If the input object is of type java.io.InputStream
        stream = cursor.getValue();
        if (stream instanceof InputStream)
        {
          ((InputStream) stream).close();
        }
        // If the input object is of type java.io.OutputStream
        else if (stream instanceof OutputStream)
        {
          OutputStream os = (OutputStream) stream;
          os.flush();
          os.close();
        }
        // If the input object is of type java.io.Writer
        else if (stream instanceof Writer)
        {
          Writer writer = (Writer) stream;
          writer.flush();
          writer.close();
        }
        // If the input object is of type java.io.Reader
        else if (stream instanceof Reader)
        {
          ((Reader) stream).close();
        }
        else
        {
          throw new ServiceException("Incorrect object type 'stream'");
        }
      }
    }
    catch (IOException ioe)
    {
      throw new ServiceException(ioe);
    }
    finally
    {
      cursor.destroy();
    }
    // --- <<IS-END>> ---

  }

  public static final void createTempFile(IData pipeline) throws ServiceException
  {
    // --- <<IS-START(createTempFile)>> ---
    // @sigtype java 3.5
    // [i] field:0:optional prefix
    // [i] field:0:required suffix
    // [i] field:0:optional folder
    // [o] field:0:required tempFile
    // [o] object:0:required tempWriter
    IDataCursor cursor = pipeline.getCursor();

    String prefix = null;
    String suffix = null;
    File folder = null;

    if (cursor.first("prefix"))
    {
      prefix = (String) cursor.getValue();
    }

    if (prefix == null || prefix.length() < 3)
    {
      throw new ServiceException("The field 'prefix' should be atleast 3 characters long");
    }

    if (cursor.first("suffix"))
    {
      suffix = (String) cursor.getValue();
    }

    if (cursor.first("folder"))
    {
      folder = new File((String) cursor.getValue());
    }
    try
    {
      // Create the file from the input paramters
      File tempFile = File.createTempFile(prefix, suffix, folder);
      // Save the full file path to output parameters.
      IDataUtil.put(cursor, "tempFile", tempFile.getCanonicalPath());
      IDataUtil.put(cursor, "tempWriter", new BufferedWriter(new FileWriter(tempFile)));
    }
    catch (IOException ioe)
    {
      throw new ServiceException(ioe);
    }
    finally
    {
      cursor.destroy();
    }
    // --- <<IS-END>> ---

  }

  public static final void deleteFile(IData pipeline) throws ServiceException
  {
    // --- <<IS-START(deleteFile)>> ---
    // @sigtype java 3.5
    // [i] field:0:required filename
    // [o] field:0:required deleted {"true","false"}
    IDataCursor cursor = pipeline.getCursor();

    if (cursor.first("filename"))
    {
      File file = new File((String) cursor.getValue());
      IDataUtil.put(cursor, "deleted", String.valueOf(file.delete()));
    }
    cursor.destroy();
    // --- <<IS-END>> ---

  }

  public static final void getReader(IData pipeline) throws ServiceException
  {
    // --- <<IS-START(getReader)>> ---
    // @sigtype java 3.5
    // [i] object:0:required inputStream
    // [o] object:0:required reader
    IDataCursor cursor = pipeline.getCursor();

    if (cursor.first("inputStream"))
    {
      InputStream inputStream = (InputStream) cursor.getValue();
      // Create a BufferedReader from the input stream
      BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
      IDataUtil.put(cursor, "reader", reader);
    }

    cursor.destroy();
    // --- <<IS-END>> ---

  }

  public static final void openFile(IData pipeline) throws ServiceException
  {
    // --- <<IS-START(openFile)>> ---
    // @sigtype java 3.5
    // [i] field:0:required filename
    // [i] field:0:optional openFor {"input","output"}
    // [i] field:0:optional asStream {"true","false"}
    // [i] field:0:optional append {"true","false"}
    // [i] field:0:optional createPath {"true","false"}
    // [o] object:0:optional stream
    // [o] object:0:optional reader
    // [o] object:0:optional writer
    IDataCursor cursor = pipeline.getCursor();

    String openFor = "input";
    boolean asStream = false;
    boolean append = false;
    boolean createPath = false;
    String filename = null;

    if (cursor.first("filename"))
    {
      filename = (String) cursor.getValue();
      if (cursor.first("openFor"))
      {
        openFor = (String) cursor.getValue();
      }

      if (cursor.first("asStream"))
      {
        asStream = Boolean.valueOf((String) cursor.getValue()).booleanValue();
      }

      if (cursor.first("append"))
      {
        append = Boolean.valueOf((String) cursor.getValue()).booleanValue();
      }

      if (cursor.first("createPath"))
      {
        createPath = Boolean.valueOf((String) cursor.getValue()).booleanValue();
      }

      try
      {
        if ("output".equalsIgnoreCase(openFor) && createPath)
        {
          new File(filename).getParentFile().mkdirs();
        }

        if (asStream)
        {
          if ("input".equalsIgnoreCase(openFor))
          {
            IDataUtil.put(cursor, "stream", new FileInputStream(filename));
          }
          else
          {
            IDataUtil.put(cursor, "stream", new FileOutputStream(filename, append));
          }
        }
        else if ("input".equalsIgnoreCase(openFor))
        {
          IDataUtil.put(cursor, "reader", new FileReader(filename));
        }
        else
        {
          IDataUtil.put(cursor, "writer", new FileWriter(filename, append));
        }
      }
      catch (IOException ioe)
      {
        throw new ServiceException(ioe);
      }
    }
    cursor.destroy();
    // --- <<IS-END>> ---

  }

  public static final void readLine(IData pipeline) throws ServiceException
  {
    // --- <<IS-START(readLine)>> ---
    // @sigtype java 3.5
    // [i] object:0:required reader
    // [o] field:0:required line
    IDataCursor cursor = pipeline.getCursor();

    if (cursor.first("reader"))
    {
      // Get the reader from the pipeline
      BufferedReader reader = (BufferedReader) cursor.getValue();
      String line = null;
      try
      {
        // Read a line of data from the reader.
        line = reader.readLine();
      }
      catch (IOException ioe)
      {
        throw new ServiceException(ioe);
      }

      IDataUtil.put(cursor, "line", line);
    }

    cursor.destroy();
    // --- <<IS-END>> ---

  }

  public static final void write(IData pipeline) throws ServiceException
  {
    // --- <<IS-START(write)>> ---
    // @sigtype java 3.5
    // [i] object:0:required stream
    // [i] object:0:required content
    // [i] field:0:optional newline {"true","false"}
    IDataCursor cursor = pipeline.getCursor();

    boolean newline = false;
    Writer writer = null;
    Object object;
    Object content = null;

    if (cursor.first("stream"))
    {
      object = cursor.getValue();
      // If the input object is an instance of java.io.OutputStream, create a
      // java.io.BufferedWriter object from it.
      if (object instanceof OutputStream)
      {
        writer = new BufferedWriter(new OutputStreamWriter((OutputStream) object));
      }
      else if (object instanceof Writer)
      {
        writer = (Writer) object;
      }
    }

    if (writer == null)
    {
      throw new ServiceException("Missing input 'stream'");
    }

    // Get the content to be written
    if (cursor.first("content"))
    {
      content = cursor.getValue();
    }

    if (content == null)
    {
      throw new ServiceException("Missing input 'content'");
    }

    // Create string reprentation of the content.
    String contentString = formatContent(content);
    if (cursor.first("newline"))
    {
      newline = Boolean.valueOf((String) cursor.getValue()).booleanValue();
    }

    try
    {
      // write out the string
      writer.write(contentString);
      if (newline)
      {
        writer.write(lineSeparator);
      }
      writer.flush();
    }
    catch (IOException ioe)
    {
      throw new ServiceException(ioe.getMessage());
    }

    cursor.destroy();
    // --- <<IS-END>> ---

  }

  // --- <<IS-START-SHARED>> ---
  private static String lineSeparator = System.getProperty("line.separator");

  // Method that creates a formatted string from an object.
  private static String formatContent(Object obj)
  {
    // Return the same string
    if (obj instanceof String)
    {
      return (String) obj;
    }
    // If the object is a byte array, create a string from it
    else if (obj instanceof byte[])
    {
      return new String((byte[]) obj);
    }

    StringBuffer buffer = new StringBuffer();
    // If the object is a string list, put each string in its own line.
    if (obj instanceof String[])
    {
      String[] strArray = (String[]) obj;
      for (int i = 0; i < strArray.length; i++)
      {
        buffer.append(strArray[i]);
        buffer.append(lineSeparator);
      }
    }
    // If the object is a recordlist, put each record on its own line
    else if (obj instanceof IData[])
    {
      IData[] idataArray = (IData[]) obj;
      for (int i = 0; i < idataArray.length; i++)
      {
        buffer.append(idataArray[i]);
        buffer.append(lineSeparator);
      }
    }
    // If the object is an inputstream, read from it and return the content
    else if (obj instanceof InputStream)
    {
      try
      {
        InputStream is = (InputStream) obj;
        byte[] bytes = new byte[1024];
        int numRead = 0;
        while ((numRead = is.read(bytes)) >= 0)
        {
          buffer.append(new String(bytes, 0, numRead));
        }
        is.close();
      }
      catch (IOException ioe)
      {
        buffer.append("Error : Could not read from input stream");
      }
    }
    // assume that the object is a record and use its toString method.
    else
    {
      buffer.append(((IData) obj).toString());
    }

    return buffer.toString();

  }
  // --- <<IS-END-SHARED>> ---
}
