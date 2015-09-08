package RTC;

/**
* RTC/OGMapHolder.java .
* IDL-to-Java?????(?????)??????"3.2"???????????
* idl/MobileRobot.idl??
* 2015?5?12? 15?55?36? JST
*/

public final class OGMapHolder implements org.omg.CORBA.portable.Streamable
{
  public RTC.OGMap value = null;

  public OGMapHolder ()
  {
  }

  public OGMapHolder (RTC.OGMap initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTC.OGMapHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTC.OGMapHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTC.OGMapHelper.type ();
  }

}
