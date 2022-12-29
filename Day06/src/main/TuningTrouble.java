package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TuningTrouble {
  public static void main(String[] args) throws FileNotFoundException {
    System.out.println(checkParsedFile(parseImportedFile(importFile("src/files/DataStream.txt"))));
    //System.out.println(checkParsedFile(parseImportedFile("mjqjpqmgbljsphdztnvjfqwrcgsmlb")));
  }

  public static String importFile(String fileLocation) throws FileNotFoundException{
    ArrayList<String> file=new ArrayList<>();
    Scanner readFile=new Scanner(new File(fileLocation));

    while(readFile.hasNextLine()){
      file.add(readFile.nextLine());
    }

    readFile.close();
    return file.get(0);
  }

  public static ArrayList<ArrayList<Character>> parseImportedFile(String importedFile){
    ArrayList<ArrayList<Character>> separatePackets=new ArrayList<>();

    for(int i=0;i<importedFile.length()-13;i++){
      char[] temp=importedFile.substring(i,(i+14)).toCharArray();
      ArrayList<Character> tempArray=new ArrayList<>();

      for(int j=0;j<temp.length;j++){
        tempArray.add(temp[j]);
      }

      separatePackets.add(tempArray);
      
    }

    return separatePackets;
  }

  public static int checkParsedFile(ArrayList<ArrayList<Character>> parsedFile){
    for(int array1=0;array1<parsedFile.size();array1++){
      int checking=0;
      ArrayList<Character> tempChecking=new ArrayList<>();

      for(int i=0;i<parsedFile.get(array1).size();i++){
        tempChecking.add(parsedFile.get(array1).get(i));
      }

      checking=0;
      for(int array2=0;array2<parsedFile.get(array1).size();array2++){
        tempChecking.remove(0);

        for(int i=0;i<tempChecking.size();i++){
          if(tempChecking.contains(parsedFile.get(array1).get(array2))){
            checking+=1;
          }
        }
      }

      if(checking==0){
        return parsedFile.get(array1).size()+(array1);
      }
    }
    return 0;
  }
}
