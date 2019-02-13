package kit.personal.ssoserver.entity;

import java.util.ArrayList;
import java.util.List;

public class GPSEmail {
    private Integer funcNo;
    private String email;
    public static GPSEmail extractEmail(GPS gps){
        GPSEmail gpsEmail = new GPSEmail();
        gpsEmail.setFuncNo(gps.getFuncNo());
        gpsEmail.setEmail(gps.getEmail());
        return gpsEmail;
    }

    public static List<GPSEmail> extractEmail(List<GPS> gpsList){
        List<GPSEmail> gpsEmailList = new ArrayList<>();
        for (GPS gps: gpsList){
            gpsEmailList.add(extractEmail(gps));
        }
        return gpsEmailList;
    }

    public Integer getFuncNo() {
        return funcNo;
    }

    public GPSEmail setFuncNo(Integer funcNo) {
        this.funcNo = funcNo;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public GPSEmail setEmail(String email) {
        this.email = email;
        return this;
    }
}
