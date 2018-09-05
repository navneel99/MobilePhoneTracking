package mobile;
public class MobilePhoneSet{
	Myset S;
  public MobilePhoneSet(){
    S = new Myset();
  }
	public int length(){
		return S.length();
	}
  public void add(MobilePhone m){  //Most probably unneccesary
    S.Insert(m);
  }
  public MobilePhoneSet Union(MobilePhoneSet m) {
	  S = S.Union(m.S);
	  return this;
  }
	public MobilePhone getPhoneByIndex (int ind){
		Object ele = S.atIndex(ind);
		MobilePhone mob = (MobilePhone) ele ;
		return mob;
	}
	public MobilePhone getPhone(int num){
		int len = S.length();
		for( int k =0; k<len; k++){
			Object ele = S.atIndex(k);
			MobilePhone element = (MobilePhone) ele;
			if (element.phno ==num){
				return element;
			} else {
				continue;
			}
		}
		MobilePhone element = null;
		return element;
	}
	public void remove(MobilePhone mob){
		//int number  = mob.phno;
		 S.Delete(mob);
	}
	/*public static void main(String args[]){
		MobilePhoneSet a = new MobilePhoneSet();
		MobilePhone mob1 = new MobilePhone(123);
		MobilePhone mob2 = new MobilePhone(234);
		a.add(mob1);
		a.add(mob2);
		a.remove(mob1);
		System.out.print(a.getPhoneByIndex(0).phno);
	}*/
	// Add a return method
  //Add more as required
}
