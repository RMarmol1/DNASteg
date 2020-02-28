/* CYB642
 * DNA Steg
 * Filename: Driver_dnasteg
 * Student name: Rafael Marmol
 *
 * Runs DNA steg 
 *
 * Class: Driver_grephy
 */
 import java.io.*;
 import java.util.Scanner;
 import java.util.ArrayList;
 
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
    booelean dArg = false;
      
    // Set arguments from cmd line to variables
    if(args.length == 0){
      System.out.println("Please enter input file");
    }
    else {
      inputFile = args[0];
    }
    //dnaRefFile = args[1];

      
    // work with desired file if found
    File fileIn = new File (inputFile);
    File fileSeq = new File (dnaRefFile);
     
    try{
       
      // scanners for checking alphabet and another to check each line 
      Scanner inputDecode = new Scanner(fileIn);
      //Scanner inputRef = new Scanner(dnaRefFile);
        
      // create array to hold alphabet of file (max value of # accepted characters (A-Z, a-z, 0-9))
      ArrayList<String> dnaToDecode = new ArrayList<String>();
      
     
      
      // used to keep place in array
      int count = 0;
      
      //used get each letter into a string
      String fileString = "";
      String temp = "";
     
      while(inputDecode.hasNext()){

        temp+=inputDecode.next();
        
      }
      
      
      //put 2 letters
      // Creating array of string length 
        //char[] dnaCode = new char[temp.length()]; 
  
        // Copy character by character into array 
        for (int i = 0; i < temp.length(); i++) { 
            if(i == temp.length()-1){
              dnaToDecode.add(temp.substring(i, temp.length()));
            }
            else{
              dnaToDecode.add(temp.substring(i,i+2));
              i++;
            }
        } 
  
        
      //print contents of arraylist
      /*for(String s:dnaToDecode){
        System.out.println(s);
      }*/
      
      
      
      //convert binary to DNA
      dnaToDecode = convertToDNA(dnaToDecode);
      /*for(String s:dnaToDecode){
        System.out.print(s);
      }*/
      
      //System.out.println("test:" + dnaToDecode.indexOf("A"));
      //System.out.println("");
      
      //convert to complement
      //((AC) (CG) (GT) (TA)
      ArrayList<String> dnaComplement = new ArrayList<String>();
      dnaComplement = getComplement(dnaToDecode);
      /*for(String s:dnaComplement){
        System.out.print(s);
      }
      System.out.println("");*/
      //Make pairs
      

      //find indexes in dna reference strand
      String dnaRef = "AGCACTCTCAAAGATAGAGGGCGTTGTCAAGAATAAGACCGGATCTCTCGCCAACCACCCACCCTTTCATGACTTAGCTTAGTGCCTCAACATTGTGCCACAGGCACCGGGTAGAGCCCAGACAGTTAAGTAGTGATGCTGTGCTACAAATAGCTGGATATTTTTTTAGGACGGTTGCACTTACACTATCCGTGGTTGGCGGAGATCAACTGCAGCAGAACGCGGCAGCGTGGTATTTCTGTTACTGACCCCAGTAGTTGAGGCAGCTTGTATGTGAACGCAATCGAACGCACTATAGGAAACCCTTGGGTACGTAGGTCCTTTAACACAATTAGTGAGTGTAAAGAGTTGATTCCGTAAACCTCGTATATCTACTTCTGTAGGCCATCCGGGAGTACACTAAGTTCCAGTCATCCGTGAACTGGTGGTGTGCCCAGGCTTTCGATACTTGCTGCTTGGCAGAGCGTCATGCTTCGAGCGGCGATTATGTACGGAAATAAATAATACTTGCGGTGTTGCGGTTCCGTCGCCATAGGTTCAGACTTGGCCTATGCTCTGGCATCGCTACGTAAGCGGCGCGTTGTATACGGCGAGCGGTGGTCTACGGCGTACGGTGTTAACCACCGACTGGTAAGAGAGGGTTCGTGCAGGTTTTTGGTGGGAAGTTCGTAGCTTTTGGTATTCAGACCTGGAATATCCGCGGTACAATGCTGCACAAGTGTATGGACAGTGTTACCGGCTTATCCCTATAGCAACTTTCGTAATATACCTGGGGAGCCCCGGCGACGTCGTGTATACCGCTCCCGCTATGGCATTCCTCCAAACATGTGTCTAATCTATCGGCGGATCGCCCACAGCAAACGAGTTACGAAGAATATAACTTCGATATATAGGAGTAAGTATTCTAGGACTCTTCTGAACGCACTTAGAACAGGTTAGCTCTTTGATACACCATATTGTTGATTTTGTATTGCTTGGGCGGCACCCCAGGCGAAGCTAT";

      //System.out.println(dnaRef);
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
      
      /*System.out.println("Reference list:");
      for(String s:dnaRefList){
        System.out.println(s);
      }
      System.out.println("");*/
      
      
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
      /*for(String s:dnaPairs){
        System.out.println(s);
      }
      
      System.out.println("");*/

      for(String s:indexes){
        System.out.print(s + ", ");
      }      
      
      // print new value
      
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