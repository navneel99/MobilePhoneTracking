package mobile;
public class MobilePhone{
  public int phno;
  public Boolean status = false;
  public Exchange baseStation;
  public MobilePhone(int number){
    phno = number;
  }
  public int number(){
    return phno;
  }
  public Boolean status(){
    return status;
  }
  public void switchOn(){
    status = true;
  }
  public void switchOff(){
    status = false;
  }
  public Exchange location(){
    if (status == true){
      return baseStation;
      //RETURN BASE STATION
    } else {
    	baseStation = null;
    	return baseStation;
      //RETURN EXCEPTION
    }
  }
}
