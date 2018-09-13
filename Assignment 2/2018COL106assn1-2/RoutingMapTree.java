package mobile;
public class RoutingMapTree{
  Myset tree;
  Exchange root;
  public RoutingMapTree(){
    tree = new Myset();
    root = new Exchange(0);
    root.parent = null;
    tree.Insert(root);
  }
  public void InsertNodeinTree(int i , Exchange a){  //Inserts child  under node b
    if (this.containsNode(a)){
    Exchange b = new Exchange(i);
    b.parent = a;
    tree.Insert(b);
    a.children.Insert(b);
  } else {
    System.out.println("Error - The exchange was not found.");
  }
  }

	public Exchange findPhone(MobilePhone m){
		return m.baseStation;
	}

	public Exchange lowestRouter(Exchange a, Exchange b){
		Exchange start = a;
		while (start.Parent() != null){
			if (start.containsExchange(b)){
				return start;
			} else{
				start = start.Parent();
			}
		}
		return start;
	}
	//public ExchangeList routeCall(MobilePhone a, MobilePhone b){

	//}
  public Boolean containsNode(Exchange a){
    if (tree.IsMember(a)){
      return true;
    } else{
      return false;
    }
  }
  public Exchange getNode(int a){
    int lngth = this.tree.length();
    for (int k = 0; k< lngth ; k++){
      Object node = tree.atIndex(k);
      Exchange nodes = (Exchange) node;
      if (nodes.id == a){
        return nodes;
      } else{
        continue;
      }
    }
    return null;
  }
  void a() throws NullPointerException{
    throw new NullPointerException("lolol");
  }
  public void switchOn(MobilePhone a, Exchange b){

    if (a.status == true) {
      if (a.baseStation == b){
        try{
        a();
      } catch(Exception e) {
        System.out.println("Error - The Phone is switched on. It is already registered to the specified Exchange.");
      }
      } else {
        try{
          a();
        } catch(Exception e){
        System.out.println("Error -The phone is currently on. Please switch it off to change the Station.");
      }
      }

    }
    else {
      Exchange old_baseStation = a.baseStation;
      a.status = true;
      a.baseStation = b;
      b.phoneset.add(a);
    }
  }
  public void switchOff(MobilePhone a){
    if (a.status == true){
      a.status = false;
    } else {};
  }
  public void performAction(String actionMessage){
		String[] words = actionMessage.split(" ");

    if (words[0].equals("addExchange")){
      //System.out.println("Started addExchange!");
      String a = words[1];
      int a1 = Integer.parseInt(a);
      String b = words[2];
      int b1 = Integer.parseInt(b);
			Exchange check = this.getNode(b1);
      Exchange newer = new Exchange(b1);
      Exchange old = this.getNode(a1);
			if (check != null){
				System.out.println("The 'new' node already exists.");
			} else {
      if (old != null){
        newer.parent = old;
        old.children.Insert(newer);
        tree.Insert(newer);
      } else{
        System.out.println("The original node doesn't exist.");
        //EXCEPTION (old doesn't exist)
      }
		}
    } else if (words[0].equals("switchOnMobile")){
      //System.out.println("Switched on mobile!");
      String a = words[1];
      String b = words[2];
      int a1 = Integer.parseInt(a);
      int b1 = Integer.parseInt(b);
      Exchange old=this.getNode(b1);
      if (old != null){              //THE exchange exists
        //Check if it has any children.
        int num_children = old.numChildren();
        if (num_children == 0){
      MobilePhone phone = old.phoneset.getPhone(a1); //Mobilephone belonged at the givn input exchange.
      if (phone != null){  //The Mobile phone was already registered at old.
        phone.status = true;
      } else { //Mobile phone did not belong at the old exchange.
      //Either it existed at some other node or none. Remove that.
      MobilePhone phone1 = root.residentSet().getPhone(a1);
      //System.out.println("A phone did not exist originally in this node.");
      if (phone1 == null){
        MobilePhone newer = new MobilePhone(a1);
        newer.status = true;
        newer.baseStation = old;
        old.phoneset.add(newer);
      }
        else{
          //System.out.println("The phone was in some other node.");
          boolean curr_stat = phone1.status;
          if (curr_stat == true){
            System.out.println("The mobile is currently switched on at another Exchange. Please switch it off to change the Exchange.");
          } else{
            phone1.status = true;
            Exchange old_parent = phone1.location();
            old_parent.phoneset.remove(phone1);
            Exchange parent = old_parent.Parent();
            while (parent != null){
              parent.phoneset.remove(phone1);
              parent = parent.Parent();
            }
            phone1.baseStation = old;
            old.phoneset.add(phone1);
          }
        }
        }
      } else {
        try{
          a();
        } catch(Exception e){
          System.out.print("Error- Identifier ");
          System.out.print(b);
          System.out.println(" already has base Exchanges. Cannot add Mobiles directly");
        }
      }
      } else {
        try{
          a();
        } catch (Exception e){
        System.out.print("Error - No Exchange with identifier ");
        System.out.println(b);
      //Throw EXCEPTION
    }
      }
    } else if (words[0].equals("switchOffMobile")){
      //System.out.println("Switched Off Mobile!");
      String a = words[1];
      int a1 = Integer.parseInt(a);
      Object root_object = tree.atIndex(0);
      Exchange root = (Exchange) root_object;
      MobilePhoneSet all_phones = root.residentSet();
      MobilePhone phone = all_phones.getPhone(a1);
      if (phone != null){
        phone.status = false;
      } else {
        try{
          a();
        } catch(Exception e){
          System.out.print("Error- No Mobile Phone with identifier ");
          System.out.println(a);
        }
      }
    } else if (words[0].equals("queryNthChild")){
			System.out.print(actionMessage + ": ");
      String a = words[1];
      String b = words[2];
      int a1 = Integer.parseInt(a);
      int b1 = Integer.parseInt(b);
      //System.out.println(a1);
      Exchange old = this.getNode(a1);

    if (old == null){
      try{
        a();
      } catch (Exception e){
        System.out.print("Error- No Exchange ");
        System.out.println(a);
      }
    }else{
      Exchange newer = old.child(b1);
      if (newer == null){
        try{
        a();
      } catch (Exception e){
        System.out.print("Error- No ");
        System.out.print(b);
        System.out.print(" child of Exchange ");
        System.out.println(a);
      }
    } else{
      int result = newer.id;
      System.out.println(result);
    }
  }
    } else if (words[0].equals("queryMobilePhoneSet")){
			System.out.print(actionMessage + ": ");
      String a = words[1];
      int a1 = Integer.parseInt(a);
      Exchange curr_node = this.getNode(a1);
      if (curr_node == null){
        try{
          a();
        } catch(Exception e){
          System.out.print("Error- No Exchange ");
          System.out.println(a);

        }
      }else{
      MobilePhoneSet phones = curr_node.residentSet();
      int ln = phones.length();
      for (int k =0 ; k<ln; k++){
        MobilePhone phone = phones.getPhoneByIndex(k);
        if (phone.status == true){
        System.out.print(phone.phno);
				if(k != ln-1){
        System.out.print(", ");
			}
      }
    }
      System.out.print("\n");
      //Print Identifiers of all the mobilephones in the set
    }
    } else {
      try{
        a();
      } catch(Exception e){
        System.out.println("Error- Wrong Query Message.");
      }
    }
    //System.out.println(actionMessage);
  }
	public static void main(String[] args){
		System.out.println("RoutingMapTree working!");
		RoutingMapTree t = new RoutingMapTree();
		t.performAction("addExchange 0 1");
		t.performAction("addExchange 0 2");
		t.performAction("addExchange 1 3");
		t.performAction("addExchange 1 4");
		t.performAction("addExchange 1 5");
		t.performAction("addExchange 4 6");
		t.performAction("addExchange 4 7");
		//t.performAction("switchOnMobile 987 4");
		//t.performAction("switchOnMobile 986 4");
		//t.performAction("switchOnMobile 985 4");
		t.performAction("queryMobilePhoneSet 1");
		Object a = t.tree.atIndex(6);
		Object b = t.tree.atIndex(7);
		Exchange a1 = (Exchange) a;
		Exchange b1 = (Exchange) b;
		Exchange res =t.lowestRouter(a1,b1);
		System.out.println(res.id);

	}
  /*public String performAction(String actionMessage){
    String[] words = actionMessage.split(" ");
    String answer = "";
    if (words[0].equals("addExchange")){
      //System.out.println("Started addExchange!");
      String a = words[1];
      int a1 = Integer.parseInt(a);
      String b = words[2];
      int b1 = Integer.parseInt(b);
      Exchange check = this.getNode(b1);
      Exchange newer = new Exchange(b1);
      Exchange old = this.getNode(a1);
      if (check != null){
        answer = "The 'new' node already exists.";
        //System.out.println("The 'new' node already exists.");
      } else {
      if (old != null){
        newer.parent = old;
        old.children.Insert(newer);
        tree.Insert(newer);
      } else{
        answer = "The original node doesn't exist.";
        //System.out.println("The original node doesn't exist.");
        //EXCEPTION (old doesn't exist)
      }
    }
    } else if (words[0].equals("switchOnMobile")){
      //System.out.println("Switched on mobile!");
      String a = words[1];
      String b = words[2];
      int a1 = Integer.parseInt(a);
      int b1 = Integer.parseInt(b);
      Exchange old=this.getNode(b1);
      if (old != null){              //THE exchange exists
        //Check if it has any children.
        int num_children = old.numChildren();
        if (num_children == 0){
      MobilePhone phone = old.phoneset.getPhone(a1); //Mobilephone belonged at the givn input exchange.
      if (phone != null){  //The Mobile phone was already registered at old.
        phone.status = true;
      } else { //Mobile phone did not belong at the old exchange.
      //Either it existed at some other node or none. Remove that.
      MobilePhone phone1 = root.residentSet().getPhone(a1);
      //System.out.println("A phone did not exist originally in this node.");
      if (phone1 == null){
        MobilePhone newer = new MobilePhone(a1);
        newer.status = true;
        newer.baseStation = old;
        old.phoneset.add(newer);
      }
        else{
          //System.out.println("The phone was in some other node.");
          boolean curr_stat = phone1.status;
          if (curr_stat == true){
            answer = "The mobile is currently switched on at another Exchange. Please switch it off to change the Exchange.";
            //System.out.println("The mobile is currently switched on at another Exchange. Please switch it off to change the Exchange.");
          } else{
            phone1.status = true;
            Exchange old_parent = phone1.location();
            old_parent.phoneset.remove(phone1);
            Exchange parent = old_parent.Parent();
            while (parent != null){
              parent.phoneset.remove(phone1);
              parent = parent.Parent();
            }
            phone1.baseStation = old;
            old.phoneset.add(phone1);
          }
        }
        }
      } else {
        try{
          a();
        } catch(Exception e){
          answer = "Error- Identifier "+b+" already has base Exchanges. Cannot add Mobiles directly";
          //System.out.print("Error- Identifier ");
          //System.out.print(b);
          //System.out.println(" already has base Exchanges. Cannot add Mobiles directly");
        }
      }
      } else {
        try{
          a();
        } catch (Exception e){
          answer = "Error - No Exchange with identifier "+b;
        //System.out.print("Error - No Exchange with identifier ");
        //System.out.println(b);
      //Throw EXCEPTION
    }
      }
    } else if (words[0].equals("switchOffMobile")){
      //System.out.println("Switched Off Mobile!");
      String a = words[1];
      int a1 = Integer.parseInt(a);
      Object root_object = tree.atIndex(0);
      Exchange root = (Exchange) root_object;
      MobilePhoneSet all_phones = root.residentSet();
      MobilePhone phone = all_phones.getPhone(a1);
      if (phone != null){
        phone.status = false;
      } else {
        try{
          a();
        } catch(Exception e){
          answer = "Error- No Mobile Phone with identifier "+a;
          System.out.print("Error- No Mobile Phone with identifier ");
          System.out.println(a);
        }
      }
    } else if (words[0].equals("queryNthChild")){
      System.out.print(actionMessage + ": ");
      answer = actionMessage + ": ";
      String a = words[1];
      String b = words[2];
      int a1 = Integer.parseInt(a);
      int b1 = Integer.parseInt(b);
      //System.out.println(a1);
      Exchange old = this.getNode(a1);

    if (old == null){
      try{
        a();
      } catch (Exception e){
        answer = "Error- No Exchange "+a;
        //System.out.print("Error- No Exchange ");
        //System.out.println(a);
      }
    }else{
      Exchange newer = old.child(b1);
      if (newer == null){
        try{
        a();
      } catch (Exception e){
        answer = "Error- No "+b+" child of Exchange "+a;
        //System.out.print("Error- No ");
        //System.out.print(b);
        //System.out.print(" child of Exchange ");
        //System.out.println(a);
      }
    } else{
      int result = newer.id;
      System.out.println(result);
      answer += result;
    }
  }
    } else if (words[0].equals("queryMobilePhoneSet")){
      //System.out.print(actionMessage + ": ");
      answer = actionMessage+ ": ";
      String a = words[1];
      int a1 = Integer.parseInt(a);
      Exchange curr_node = this.getNode(a1);
      if (curr_node == null){
        try{
          a();
        } catch(Exception e){
          answer += "Error- No Exchange " + a;
          //System.out.print("Error- No Exchange ");
          //System.out.println(a);

        }
      }else{
      MobilePhoneSet phones = curr_node.residentSet();
      int ln = phones.length();
      for (int k =0 ; k<ln; k++){
        MobilePhone phone = phones.getPhoneByIndex(k);
        if (phone.status == true){
        answer+=phone.phno;
        System.out.print(phone.phno);
        if(k != ln-1){
          answer+=", ";
        System.out.print(", ");
      }
      }
    }
      //System.out.print("\n");
      //Print Identifiers of all the mobilephones in the set
    }
    } else {
      try{
        a();
      } catch(Exception e){
        answer = "Error- Wrong Query Message.";
        System.out.println("Error- Wrong Query Message.");
      }
    }
    //System.out.println(actionMessage);
    return answer;
  }*/

}
