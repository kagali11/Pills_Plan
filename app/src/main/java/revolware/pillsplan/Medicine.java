package revolware.pillsplan;

/**
 * Created by Adam on 9/25/2016.
 */
public class Medicine {

    public String NAME;
    public String DESCRIPTION;
    public String STATE;
    public String INDICATION;
    public String LIFE;
    public String DISTRIBUTION;
    public String ABSORPTION;
    public String CLEARENCE;


    public void setNAME(String name){
        NAME = name;
    }

    public String getNAME(){
        return NAME;
    }

    public void setDESCRIPTION(String description){
        DESCRIPTION = description;
    }

    public String getDESCRIPTION(){
        return DESCRIPTION;
    }

    public void setSTATE(String state){
        STATE = state;
    }

    public String getSTATE(){
        return STATE;
    }

    public void setINDICATION(String indication){
        INDICATION = indication;
    }

    public String getINDICATION(){
        return INDICATION;
    }

    public void setLIFE(String life){
        LIFE = life;
    }

    public String getLIFE(){
        return LIFE;
    }

    public void setDISTRIBUTION(String distribution){
        DISTRIBUTION = distribution;
    }

    public String getDISTRIBUTION(){
        return DISTRIBUTION;
    }

    public void setABSORPTION(String absorption){
        ABSORPTION = absorption;
    }

    public String getABSORPTION(){
        return ABSORPTION;
    }

    public void setCLEARENCE(String clearence){
        CLEARENCE = clearence;
    }

    public String getCLEARENCE(){
        return CLEARENCE;
    }

}
