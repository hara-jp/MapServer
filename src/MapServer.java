// -*- Java -*-
/*!
 * @file MapServer.java
 * @date $Date$
 *
 * $Id$
 */

import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.RTObject_impl;
import jp.go.aist.rtm.RTC.RtcDeleteFunc;
import jp.go.aist.rtm.RTC.RtcNewFunc;
import jp.go.aist.rtm.RTC.RegisterModuleFunc;
import jp.go.aist.rtm.RTC.util.Properties;

/**
 * MapServer
 * <p> 
 * MapServer RTC
 */
public class MapServer implements RtcNewFunc, RtcDeleteFunc, RegisterModuleFunc {

//  Module specification
//  <rtc-template block="module_spec">
    public static String component_conf[] = {
            "implementation_id", "MapServer",
            "type_name",         "MapServer",
            "description",       "MapServer RTC",
            "version",           "1.0.0",
            "vendor",            "Sugar Sweet Robotics",
            "category",          "Navigation",
            "activity_type",     "STATIC",
            "max_instance",      "1",
            "language",          "Java",
            "lang_type",         "compile",
            // Configuration variables
            "conf.default.filename", "testMap.yaml",
            "conf.default.debug", "0",

            // Widget
            "conf.__widget__.filename", "text",
            "conf.__widget__.debug", "text",
            // Constraints

            "conf.__type__.filename", "string",
            "conf.__type__.debug", "string",

    	    ""
            };
//  </rtc-template>

    public RTObject_impl createRtc(Manager mgr) {
        return new MapServerImpl(mgr);
    }

    public void deleteRtc(RTObject_impl rtcBase) {
        rtcBase = null;
    }
    public void registerModule() {
        Properties prop = new Properties(component_conf);
        final Manager manager = Manager.instance();
        manager.registerFactory(prop, new MapServer(), new MapServer());
    }
}
