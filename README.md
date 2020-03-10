# DNASteg
Program to take binary of file and perform data hiding operations into DNA sequences

To run:
  1. Go to directory
  2. javac Driver_dnasteg.java
  3. java Driver_dnasteg [-e | -d] [inputfile.txt] [referencestrand.txt]
  
 Example:
   echo | java Driver_dnasteg -e binary1.txt ref1.txt > steg1.txt
   //this will encode the binary1.txt file into comma separated values into the text file steg1.txt with the ref1.txt as the DNA     reference strand key
   
   echo | java Driver_dnasteg -d steg1.txt ref1.txt > decoded1.txt
   //this will decode the steg1.txt file into its orginial binary string format based on ref1.txt in decoded1.txt
   
 
