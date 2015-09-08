package RTC;

/**
* RTC/OGMapperHolder.java .
* IDL-to-Java?????(?????)??????"3.2"???????????
* idl/MobileRobot.idl??
* 2015?5?12? 15?55?36? JST
*/


/*!
   * @interface OGMapper
   * @brief Occupancy Grid Map Builder Service Interface
   */
public final class OGMapperHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.OGMapper value = null;

  public OGMapperHolder ()
  {
  }

  public OGMapperHolder (RTC.OGMapper initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.OGMapperHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.OGMapperHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.OGMapperHelper.type ();
  }

}
