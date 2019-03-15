package kit.personal.ssoserver.entity;

import java.util.ArrayList;
import java.util.List;

public class GPSEmail {
    private String funcNo;
    private String email;
    public static GPSEmail extractEmail(AppUser gps){
        GPSEmail gpsEmail = new GPSEmail();
        gpsEmail.setFuncNo(gps.getUsername());
        gpsEmail.setEmail(gps.getEmail());
        return gpsEmail;
    }

    public static List<GPSEmail> extractEmail(List<AppUser> gpsList){
        List<GPSEmail> gpsEmailList = new ArrayList<>();
        for (AppUser gps: gpsList){
            gpsEmailList.add(extractEmail(gps));
        }
        return gpsEmailList;
    }

    public String getFuncNo() {
        return funcNo;
    }

    public GPSEmail setFuncNo(String funcNo) {
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
