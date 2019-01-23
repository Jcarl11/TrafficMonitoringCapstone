package com.example.trafficmonitoring;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Report2Entity
{
    public Report2Entity(Date timeStamp, String facility, String facilityType, String LOS)
    {
        this.timeStamp = timeStamp;
        this.facility = facility;
        this.facilityType = facilityType;
        this.LOS = LOS;
    }
    public Report2Entity(){}
    public Date getTimeStamp() {return timeStamp;}
    public void setTimeStamp(Date timeStamp) {this.timeStamp = timeStamp;}
    public String getFacility() {return facility;}
    public void setFacility(String facility) {this.facility = facility;}
    public String getFacilityType() {return facilityType;}
    public void setFacilityType(String facilityType) {this.facilityType = facilityType;}
    public String getLOS() {return LOS;}
    public void setLOS(String LOS) {this.LOS = LOS;}

    public String toJSON()
    {
        JSONObject json = new JSONObject();
        try {
            json.put("timeStamp", GlobalVariables.getInstance().convertDateToISO(getTimeStamp()));
            json.put("facility", getFacility());
            json.put("facilityType",getFacilityType());
            json.put("LOS", getLOS());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    private Date timeStamp;
    private String facility;
    private String facilityType;
    private String LOS;
}
