// -*-Java-*-
/*!
 * @file  OGMapServerSVC_impl.java
 * @brief Service implementation code of MobileRobot.idl
 *
 */
import RTC.OGMapHolder;
import RTC.OGMapServerPOA;
import RTC.RETURN_VALUE;
/**
 * OGMapServerSVC_impl
 * <p>
 * Example class implementing IDL interface RTC::OGMapServer
 */
public class OGMapServerSVC_impl extends OGMapServerPOA{
    private MapServerImpl rtc;
    
    public OGMapServerSVC_impl() {
        // Please add extra constructor code here.
    }

    /*
     * Methods corresponding to IDL attributes and operations
     */
    public RTC.RETURN_VALUE requestCurrentBuiltMap(RTC.OGMapHolder map) {
        // Please insert your code here and remove the following warning pragma
        // TODO "Code missing in function <RETURN_VALUE requestCurrentBuiltMap(OGMap map)>"
        map.value = rtc.getOGMap();

        return RTC.RETURN_VALUE.RETVAL_OK;
    }

    public void setRTC(MapServerImpl mapServerImpl){
        this.rtc = mapServerImpl;
    }
//  End of example implementational code
}
