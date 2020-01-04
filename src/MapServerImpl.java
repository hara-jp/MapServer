// -*- Java -*-
/*!
 * @file  MapServerImpl.java
 * @brief MapServer RTC
 * @date  $Date$
 *
 * $Id$
 */

import java.io.File;

import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.CorbaPort;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import jp.go.aist.rtm.RTC.util.StringHolder;
import jp.go.aist.rtm.RTC.util.IntegerHolder;
import RTC.ReturnCode_t;

import RTC.OGMap;
import RTC.OGMapConfig;
import RTC.OGMapTile;

/**
 * MapServerImpl
 * <p>
 * MapServer RTC
 *
 * Map Distribution Service
 *
 */
public class MapServerImpl extends DataFlowComponentBase {
  private OGMap ogMap;

  public OGMap getOGMap() { return ogMap; }

  /**
   * constructor
   * @param manager Manager Object
   */
    public MapServerImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        m_mapServerPort = new CorbaPort("mapServer");
        // </rtc-template>

    }

    /*!
     *
     * The initialize action (on CREATED-&gt;ALIVE transition)
     * former rtc_init_entry() 
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
            m_mapServerPort.registerProvider("OGMapServer", "RTC::OGMapServer", m_mapServer);
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
        bindParameter("filename", m_filename, "testMap.yaml");
        bindParameter("debug", m_debug, "0");
        return super.onInitialize();
    }

    /**
     *
     * The finalize action (on ALIVE-&gt;END transition)
     * former rtc_exiting_entry()
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onFinalize() {
//        return super.onFinalize();
//    }

    /**
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

    /**
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

    /**
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
        try{
            File f = new File(m_filename.value);
            this.ogMap = MapLoader.loadMap(f);
        } catch(Exception e){
            e.printStackTrace();
            return RTC.ReturnCode_t.RTC_ERROR;
        }
        return super.onActivated(ec_id);
    }

    /**
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

    /**
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

    /**
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

    /**
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

    /**
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

    /**
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

    /**
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
    /**
     */
    // Configuration variable declaration
    // <rtc-template block="config_declare">
    /*!
     * This must be Map parameter file (Yaml).
     * - Name: filename filename
     * - DefaultValue: testMap.yaml
     * - Range: null
     * - Constraint: null
     */
    protected StringHolder m_filename = new StringHolder();
    /*!
     * 
     * - Name: null debug
     * - DefaultValue: 0
     * - Range: null
     * - Constraint: null
     */
    protected IntegerHolder m_debug = new IntegerHolder();
    // </rtc-template>


    /**
     */
    // DataInPort declaration
    // <rtc-template block="inport_declare">
    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    
    // </rtc-template>

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    /*!
     * Map Districution Service Port.
     */
    protected CorbaPort m_mapServerPort;
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    /*!
     * Map Distribution Service.
     * - Return Value:  RTC.OGMap struct
     */
    protected OGMapServerSVC_impl m_mapServer = new OGMapServerSVC_impl();
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    
    // </rtc-template>


}
