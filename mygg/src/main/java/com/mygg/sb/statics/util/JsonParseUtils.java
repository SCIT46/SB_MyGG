package com.mygg.sb.statics.util;

import org.json.simple.JSONObject;

public class JsonParseUtils
	{
		static Long getJSONObjectToLong(JSONObject obj, String id) {return ((Long)obj.get(id));}
		static int getJSONObjectToInt(JSONObject obj, String id) {return (((Long)obj.get(id)).intValue());}
		static String getJSONObjectToString(JSONObject obj, String id) {return ((String) obj.get(id));}
		static Double getJSONObjectToDouble(JSONObject obj, String id) {return ((Double) obj.get(id));}
		static boolean getJSONObjectToBoolean(JSONObject obj, String id) { return (((boolean)obj.get(id)));}
	}
