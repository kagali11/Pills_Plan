package revolware.pillsplan;

/**
 * Created by Jakub on 26.02.2016.
 */
public class AlarmInfo {

    private String medicine ;
    private String nPills ;
    private String date ;
    private String frequency ;
    private String name ;

    //Constructors----------------------------------------------------------------------------------
    
    public AlarmInfo()
    {
        medicine = "";
        nPills = "";
        date = "";
        frequency = "";
        name  = "";
    }

    public AlarmInfo(String sMedicine, String s_nPills, String sDate,String sFrequency, String sName)
    {
        medicine = sMedicine;
        nPills = s_nPills;
        date = sDate;
        frequency = sFrequency;
        name  = sName;
    }

    //Setters---------------------------------------------------------------------------------------

    public void setMedicine(String sMedicine){ medicine = sMedicine; }
    public void setNPills(String sNPills){ nPills = sNPills; }
    public void setDate(String sDate){ date = sDate; }
    public void setFrequency(String sFrequency){ frequency = sFrequency; }
    public void setName(String sName){ name = sName; }

    //getters---------------------------------------------------------------------------------------

    public String getMedicine(){return medicine ; }
    public String getNPills(){return nPills; }
    public String getDate(){return date; }
    public String getFrequency(){return frequency; }
    public String getName(){return name; }

    //overrides toString() method so it will display itemCode and shelfLife
    public String toString()
    {
        return medicine + "\n" + nPills + "\n" + date + "\n" + frequency + "\n" + name + "\n";
    }
}
