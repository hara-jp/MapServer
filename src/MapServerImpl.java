// -*- Java -*-
/*!
 * @file  MapServerImpl.java
 * @brief MapServer RTC 
 * @date  $Date$
 *
 * $Id$
 */

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import javax.imageio.ImageIO;

import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.CorbaPort;
import jp.go.aist.rtm.RTC.util.IntegerHolder;
import jp.go.aist.rtm.RTC.util.StringHolder;

import org.ho.yaml.Yaml;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import RTC.OGMap;
import RTC.OGMapConfig;
import RTC.OGMapTile;
import RTC.ReturnCode_t;

/*!
 * @class MapServerImpl
 * @brief MapServer RTC 
 *
 */
public class MapServerImpl extends DataFlowComponentBase {

  private OGMap ogMap;
  
  public OGMap getOGMap() {return ogMap;}

	/*!
   * @brief constructor
   * @param manager Maneger Object
   */
	public MapServerImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        m_mapServerPort = new CorbaPort("mapServer");
        // </rtc-template>

    }

    /*!
     *
     * The initialize action (on CREATED->ALIVE transition)
     * formaer rtc_init_entry() 
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onInitialize() {
        // Registration: InPort/OutPort/Service
        // <rtc-template block="registration">
        
        // Set service provider to Ports
        try {
        	m_mapServerPort.registerProvider("mapServer", "RTC::OGMapServer", m_mapServer);
        } catch (ServantAlreadyActive e) {
            e.printStackTrace();
        } catch (WrongPolicy e) {
            e.printStackTrace();
        } catch (ObjectNotActive e) {
            e.printStackTrace();
        }
        
        // Set service consumers to Ports
        
        // Set CORBA Service Ports
        addPort(m_mapServerPort);
        m_mapServer.setRTC(this);
        // </rtc-template>
        bindParameter("debug", m_debug, "0");
        bindParameter("filename", m_filename, "testMap.yaml");
        return super.onInitialize();
    }

    /***
     *
     * The finalize action (on ALIVE->END transition)
     * formaer rtc_exiting_entry()
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onFinalize() {
//        return super.onFinalize();
//    }

    /***
     *
     * The startup action when ExecutionContext startup
     * former rtc_starting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStartup(int ec_id) {
//        return super.onStartup(ec_id);
//    }

    /***
     *
     * The shutdown action when ExecutionContext stop
     * former rtc_stopping_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onShutdown(int ec_id) {
//        return super.onShutdown(ec_id);
//    }

    /***
     *
     * The activated action (Active state entry action)
     * former rtc_active_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onActivated(int ec_id) {
    	try {
    		File f = new File(m_filename.value);
    		BufferedReader br = new BufferedReader(new FileReader(f));
    		StringBuffer strBuf = new StringBuffer();
    		while(true) {
    			String r = br.readLine();
    			if (r == null) {
    				break;
    			}
    			strBuf.append(r);
    			strBuf.append("\n");
    			System.out.println(r);
    		}
    		br.close();
    		

    		HashMap param = (HashMap)Yaml.load(strBuf.toString());//load(new FileReader(f));
    		BufferedImage file = ImageIO.read(new File((String)param.get("image")));
    		HashMap config = (HashMap)param.get("config");
    		ogMap = new OGMap(new RTC.Time(0, 0), new OGMapConfig(0, 0, 0, 0, new RTC.Pose2D(new RTC.Point2D(0, 0), 0)),
    				new OGMapTile());
    		ogMap.config.origin.position.x = ((Double)config.get("origin_x")).doubleValue();
    		ogMap.config.origin.position.y = ((Double)config.get("origin_y")).doubleValue();
    		ogMap.config.origin.heading    = ((Double)config.get("origin_th")).doubleValue();
    		ogMap.config.xScale =  ((Double)config.get("xScale")).doubleValue();
    		ogMap.config.yScale =  ((Double)config.get("yScale")).doubleValue();
    		ogMap.map.row =  ((Integer)config.get("row")).intValue();
    		ogMap.map.column =  ((Integer)config.get("column")).intValue();
    		ogMap.config.width = file.getWidth();
    		ogMap.config.height = file.getHeight();
    		ogMap.map.width = file.getWidth();
    		ogMap.map.height = file.getHeight();
    		ogMap.map.cells = new byte[file.getWidth() * file.getHeight()];
    		for(int i = 0;i < file.getHeight();i++) {
    			for(int j = 0;j < file.getWidth();j++) {
    				byte r = (byte)(0xFF & file.getRGB(j, i));
    				ogMap.map.cells[i*file.getWidth() + j] = r;
    			}
    		}
			///BufferedImage image = ImageIO.read(new File(m_filename.value));
		} catch (Exception e) {
			e.printStackTrace();
			return RTC.ReturnCode_t.RTC_ERROR;
		}
        return super.onActivated(ec_id);
    }

    /***
     *
     * The deactivated action (Active state exit action)
     * former rtc_active_exit()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onDeactivated(int ec_id) {
        return super.onDeactivated(ec_id);
    }

    /***
     *
     * The execution action that is invoked periodically
     * former rtc_active_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onExecute(int ec_id) {
//        return super.onExecute(ec_id);
//    }

    /***
     *
     * The aborting action when main logic error occurred.
     * former rtc_aborting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//  @Override
//  public ReturnCode_t onAborting(int ec_id) {
//      return super.onAborting(ec_id);
//  }

    /***
     *
     * The error action in ERROR state
     * former rtc_error_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    public ReturnCode_t onError(int ec_id) {
//        return super.onError(ec_id);
//    }

    /***
     *
     * The reset action that is invoked resetting
     * This is same but different the former rtc_init_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onReset(int ec_id) {
//        return super.onReset(ec_id);
//    }

    /***
     *
     * The state update action that is invoked after onExecute() action
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStateUpdate(int ec_id) {
//        return super.onStateUpdate(ec_id);
//    }

    /***
     *
     * The action that is invoked when execution context's rate is changed
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onRateChanged(int ec_id) {
//        return super.onRateChanged(ec_id);
//    }
//
	// Configuration variable declaration
	// <rtc-template block="config_declare">
    /*!
     * 
     * - Name:  debug
     * - DefaultValue: 0
     */
    protected IntegerHolder m_debug = new IntegerHolder();
    /*!
     * 
     * - Name:  filename
     * - DefaultValue: testMap
     */
    protected StringHolder m_filename = new StringHolder();
	// </rtc-template>

    // DataInPort declaration
    // <rtc-template block="inport_declare">
    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    
    // </rtc-template>

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    /*!
     */
    protected CorbaPort m_mapServerPort;
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    /*!
     */
    protected OGMapServerSVC_impl m_mapServer = new OGMapServerSVC_impl();
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    
    // </rtc-template>


}
