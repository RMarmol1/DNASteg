/* CYB642
 * DNA Steg
 * Filename: Driver_dnasteg
 * Student name: Rafael Marmol
 *
 * Encodes binary text file into comma separated values based on a given DNA reference strand
 * Decodes comma separated values against given DNA reference strand into binary text
 *
 * Class: Driver_steg
 */
 import java.io.*;
 import java.util.Scanner;
 import java.util.ArrayList;
 import java.util.Arrays;
 
class Driver_dnasteg{
  
  /* main
   *  parameters:
   *        args -- arguments from command line
   *  return value: none
   * 
   *  runs main method of program
   */  
  public static void main(String[] args) throws FileNotFoundException {
    
    // variables to hold args from cmd line    
    String inputFile = "";
    String dnaRefFile = "";
    boolean eArg = false;
    boolean dArg = false;
      
    // Set arguments from cmd line to variables
    //encrypt arg
    if(args[0].equals("-e")){
      eArg = true;
      inputFile = args[1];
      dnaRefFile = args[2];      
        
    }
    //decrypt arg
    else if(args[0].equals("-d")){
      dArg = true;
      inputFile = args[1];
      dnaRefFile = args[2];
    }
    else {
      System.out.println("Invalid arguments.");
    }

      
    // work with desired file if found
    File fileIn = new File (inputFile);
    File fileRef = new File (dnaRefFile);
     
    try{
       
      // scanners to check each line of input file
      Scanner inputDecode = new Scanner(fileIn);
      Scanner inputRef = new Scanner(fileRef);
        
      // create array to hold binary numbers
      ArrayList<String> dnaToDecode = new ArrayList<String>();
      ArrayList<String> binToEncode = new ArrayList<String>();
      
     
      //used to build string
      String temp = "";
     
      //if encoding grabs 2 bits at a time and adds to arraylist
      if(eArg == true){
        
        while(inputDecode.hasNext()){

          temp+=inputDecode.next();
        
        }
        
        
        for (int i = 0; i < temp.length(); i++) { 
          if(i == temp.length()-1){
            binToEncode.add(temp.substring(i, temp.length()));
          }
          else{
            binToEncode.add(temp.substring(i,i+2));
            i++;
          }
        }
      }
      //if decoding grabs text from file as string
      else if (dArg == true){
        
        while(inputDecode.hasNext()){
          temp += inputDecode.next();
          
        }
        
        //splits numbers
        dnaToDecode = new ArrayList<String>(Arrays.asList(temp.split(",")));
        
      }
      
      
      //get reference strand
      String dnaRef = "";

      while(inputRef.hasNext()){

        dnaRef+=inputRef.next();
        
      }     
      
      //convert dnaRef to array with 2 letters
        ArrayList<String> dnaRefList = new ArrayList<String>();
        for (int i = 0; i < dnaRef.length(); i++) { 
            if(i == dnaRef.length()-3){
              dnaRefList.add(dnaRef.substring(i, dnaRef.length()));
              i = dnaRef.length();
            }
            else{
              dnaRefList.add(dnaRef.substring(i,i+2));
              i++;
            }
        }
        
      if (eArg == true){
        //convert binary to DNA
        binToEncode = convertToDNA(binToEncode);
      
      
        //convert to complement
        //((AC) (CG) (GT) (TA)
        ArrayList<String> dnaComplement = new ArrayList<String>();
        dnaComplement = getComplement(binToEncode);
      
        //convert dnaComplement to pairs
        ArrayList<String> dnaPairs = new ArrayList<String>();
        for(int i = 0; i < dnaComplement.size(); i++){
          if(i == dnaComplement.size()-2){
            dnaPairs.add(dnaComplement.get(i)+dnaComplement.get(dnaComplement.size()-1));
            i = dnaComplement.size();
          }
          else{
            dnaPairs.add(dnaComplement.get(i)+dnaComplement.get(i+1));
            i++;
          }
        
        }
      
        //find indexes
        ArrayList<String> indexes = new ArrayList<String>();
        int n = 0;
        for(String s:dnaPairs){
          n = dnaRefList.indexOf(s);
          indexes.add(String.valueOf(n));
        }
      
        //printing index values of reference strand
        for(String s:indexes){
          System.out.print(s + ", ");
        }      
      }
      
      //decode process
      else if (dArg == true){
        
        //start with getting index values
        //find indexes
        ArrayList<String> indexValues = new ArrayList<String>();
        String refVal = "";
        for(String s:dnaToDecode){
          refVal = dnaRefList.get(Integer.valueOf(s));
          indexValues.add(refVal);
        }
        
        //convert to singles
        ArrayList<String> dnaSingles = new ArrayList<String>();
        for(String s:indexValues){
          dnaSingles.add(s.substring(0,1));
          dnaSingles.add(s.substring(1,s.length()));
        }
        
        //convert index values to complement
        dnaSingles = getComplement(dnaSingles);
        
        //convert to binary
        dnaSingles = convertToBin(dnaSingles);
        
        
        //print binary
        for(int i = 0; i < dnaSingles.size(); i++){
          if(((i) % 4 ) == 0 && i != 0){
            System.out.print(" " + dnaSingles.get(i));
          }
          else{
            System.out.print(dnaSingles.get(i));
          }
        }
        
      }
      
      //if not true
      else{
        System.out.println("Something went wrong");
      }  
      
    }
    
    // if file not found
    catch (FileNotFoundException ex){
      System.out.println("ERROR: File Not Found.");
    }
  }
  
  public static ArrayList<String> convertToDNA (ArrayList<String> s){
    ArrayList<String> dna = new ArrayList<String>();
    
    for(int i = 0; i < s.size(); i++){
      //System.out.println("convert:" + s.get(i));
      if(s.get(i).equals("00")){
        dna.add("A");
      }
      else if (s.get(i).equals("01")){
        dna.add("T");
      }
      else if (s.get(i).equals("10")){
        dna.add("C");
      }
      else if (s.get(i).equals("11")){
        dna.add("G");
      }
    }
    
    return dna;
    
  }
  
  public static ArrayList<String> convertToBin (ArrayList<String> s){
    ArrayList<String> bin = new ArrayList<String>();
    
    for(int i = 0; i < s.size(); i++){
      if(s.get(i).equals("A")){
        bin.add("00");
      }
      else if (s.get(i).equals("T")){
        bin.add("01");
      }
      else if (s.get(i).equals("C")){
        bin.add("10");
      }
      else if (s.get(i).equals("G")){
        bin.add("11");
      }
    }
    
    return bin;
    
  }
  
  public static ArrayList<String> getComplement (ArrayList<String> s){
    ArrayList<String> comp = new ArrayList<String>();
    
    for(int i = 0; i < s.size(); i++){
      if(s.get(i).equals("A")){
        comp.add("T");
      }
      else if (s.get(i).equals("T")){
        comp.add("A");
      }
      else if (s.get(i).equals("C")){
        comp.add("G");
      }
      else if (s.get(i).equals("G")){
        comp.add("C");
      }
    }
      
      return comp;
  }
  
  

 }