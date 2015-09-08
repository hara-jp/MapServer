package RTC;


/**
* RTC/OGMap.java .
* IDL-to-Java?????(?????)??????"3.2"???????????
* idl/MobileRobot.idl??
* 2015?5?12? 15?55?36? JST
*/

public final class OGMap implements org.omg.CORBA.portable.IDLEntity
{

  /// Time stamp.
  public RTC.Time tm = null;

  /// OccupancyGridMap Configuration
  public RTC.OGMapConfig config = null;

  /// OccupancyGridMap Data
  public RTC.OGMapTile map = null;

  public OGMap ()
  {
  } // ctor

  public OGMap (RTC.Time _tm, RTC.OGMapConfig _config, RTC.OGMapTile _map)
  {
    tm = _tm;
    config = _config;
    map = _map;
  } // ctor

} // class OGMap
