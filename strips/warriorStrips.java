
/**
 * warriorStrips.java
 * Strips on the beer problem
 *
 * Created: Jan 2005
 * STRIPS for the beer problem
 * @author pdg
 * 2013 Version
 */

//import pmatch.MStratgVector;
//import pmatch.MStratg;
//import pmatch.MatchDetails;

import pmatch.*;
import sheffield.*;
import java.io.*;
import java.util.*;


public class warriorStrips {
  public static void main(String[] args) {
    EasyWriter scr=new EasyWriter();

    //create the operators
	
	
    StripsOp move = new StripsOp("move from ?r1 to ?r2", //the operator makes Warrior move from place to place
                                    "Warrior at ?r2",
                                    "Warrior at ?r1",
                                    "Warrior at ?r1");
								   
    StripsOp carry = new StripsOp("carry ?obj from ?r1 to ?r2", //the operator makes Warrior carry objects such as Ladder, Hook and Rope from place to place
                                    "Warrior at ?r2|?obj at the ?r2",
                                    "?obj at the ?r1|Warrior at ?r1",
                                    "?obj at the ?r1|Warrior at ?r1");
	
	StripsOp attach = new StripsOp("attach the hook to the rope", //the operator makes Warrior attach the Hook to the Rope
                                    "Warrior attached the Hook to the Rope",
                                    "Hook at the pit|Rope at the pit",							 
                                    "Hook at the pit|Rope at the pit|Warrior at pit|Snake in the pit");
								
	StripsOp get = new StripsOp("get treasure with Rope and Hook", //the operator makes Warrior get treasure by the Hook and the Rope
									"Warrior got treasure",
									"Warrior attached the Hook to the Rope",
									"Warrior attached the Hook to the Rope|Warrior at pit|Snake in the pit");
								
	StripsOp down = new StripsOp("Ladder is down", //the operator makes Warrior put down the ladder
									"Warrior puts down the ladder",
									"Ladder at the pit",
									"Ladder at the pit|Warrior at pit");
							
	StripsOp climb = new StripsOp("climb down the Ladder to the treasure", //the operator makes Warrior get treasure by the Ladder
                                    "Warrior got treasure",
                                    "Warrior at pit",							 
                                    "Warrior puts down the ladder|Warrior at pit");
    
									 
    //form them atto a vector

    ArrayList<StripsOp> warriorOps = new ArrayList<StripsOp>();
	
	warriorOps.add(move);
	warriorOps.add(carry);
	warriorOps.add(attach);
	warriorOps.add(get);
	warriorOps.add(down);
	warriorOps.add(climb);
    

    //create atstance of Strips1, give it the operators, atit state & goal state

    Strips1 str=new Strips1(warriorOps,
                            new MStringVector("Warrior at W|Ladder at the L|Hook at the H|Rope at the R|Snake in the pit"),// it depends the case no-snake or snake in a pit
                            new MStringVector("Warrior got treasure"));													   // "Snake in the pit" could be removed or stayed


    //run Strips
    boolean res=str.run();
    scr.println("Result is "+res); //result
    scr.println("Plan is   "+str.getPlan()); //plan

  }
}

