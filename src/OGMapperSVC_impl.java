// -*-Java-*-
/*!
 * @file  OGMapperSVC_impl.java
 * @brief Service implementation code of MobileRobot.idl
 *
 */
import RTC.MAPPER_STATEHolder;
import RTC.OGMapConfig;
import RTC.OGMapHolder;
import RTC.OGMapperPOA;
import RTC.Pose2D;
import RTC.RETURN_VALUE;
import RTC.OGMap;
/*!
 * @class OGMapperSVC_impl
 * Example class implementing IDL interface RTC::OGMapper
 */
public class OGMapperSVC_impl extends OGMapperPOA{
    
	private MapServerImpl rtc;
	
    public OGMapperSVC_impl() {
        // Please add extra constructor code here.
    }

    /*
     * Methods corresponding to IDL attributes and operations
     */
    public RETURN_VALUE initializeMap(OGMapConfig config, Pose2D initialPose) {
        // Please insert your code here and remove the following warning pragma
        // TODO "Code missing in function <RETURN_VALUE initializeMap(OGMapConfig config, Pose2D initialPose)>"
        return null;
    }

    public RETURN_VALUE startMapping() {
        // Please insert your code here and remove the following warning pragma
        // TODO "Code missing in function <RETURN_VALUE startMapping()>"
        return null;
    }

    public RETURN_VALUE stopMapping() {
        // Please insert your code here and remove the following warning pragma
        // TODO "Code missing in function <RETURN_VALUE stopMapping()>"
        return null;
    }

    public RETURN_VALUE suspendMapping() {
        // Please insert your code here and remove the following warning pragma
        // TODO "Code missing in function <RETURN_VALUE suspendMapping()>"
        return null;
    }

    public RETURN_VALUE resumeMapping() {
        // Please insert your code here and remove the following warning pragma
        // TODO "Code missing in function <RETURN_VALUE resumeMapping()>"
        return null;
    }

    public RETURN_VALUE getState(MAPPER_STATEHolder state) {
        // Please insert your code here and remove the following warning pragma
        // TODO "Code missing in function <RETURN_VALUE getState(MAPPER_STATE state)>"
        return null;
    }

    public RETURN_VALUE requestCurrentBuiltMap(OGMapHolder map) {
        // Please insert your code here and remove the following warning pragma
        // TODO "Code missing in function <RETURN_VALUE requestCurrentBuiltMap(OGMap map)>"
    	OGMapHolder tmpMap = new OGMapHolder(rtc.getOGMap());
    	map = tmpMap;
        return null;
    }

//  End of example implementational code
}
