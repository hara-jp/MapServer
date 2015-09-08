package RTC;


/**
* RTC/OGMapperOperations.java .
* IDL-to-Java?????(?????)??????"3.2"???????????
* idl/MobileRobot.idl??
* 2015?5?12? 15?55?36? JST
*/


/*!
   * @interface OGMapper
   * @brief Occupancy Grid Map Builder Service Interface
   */
public interface OGMapperOperations 
{

  /// Initialize Current Build Map Data
  RTC.RETURN_VALUE initializeMap (RTC.OGMapConfig config, RTC.Pose2D initialPose);
  RTC.RETURN_VALUE startMapping ();
  RTC.RETURN_VALUE stopMapping ();
  RTC.RETURN_VALUE suspendMapping ();
  RTC.RETURN_VALUE resumeMapping ();
  RTC.RETURN_VALUE getState (RTC.MAPPER_STATEHolder state);

  /// Request Current Build Map Data
  RTC.RETURN_VALUE requestCurrentBuiltMap (RTC.OGMapHolder map);
} // interface OGMapperOperations
