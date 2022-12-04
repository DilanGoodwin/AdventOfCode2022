package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RucksackReorganization2{
    public static void main(String[] args) throws Exception {
        System.out.println(characterValue(appearsBothCompartments(importFile("src/files/DataSet.txt"))));
        
    }

    public static ArrayList<String> importFile(String fileLocation) throws FileNotFoundException{
        ArrayList<String> file=new ArrayList<>();
        Scanner readFile=new Scanner(new File(fileLocation));

        while(readFile.hasNextLine()){
            file.add(readFile.nextLine());
        }

        readFile.close();
        return file;
    }

    public static char compareStrings(String compartment1,String compartment2,String compartment3){
        char occursAll='|';
        ArrayList<Character> appears2Strings=new ArrayList<>();

        for(int i=0;i<compartment1.length();i++){
            for(int j=0;j<compartment2.length();j++){
                if((compartment1.charAt(i))==(compartment2.charAt(j))){
                    appears2Strings.add(compartment1.charAt(i));
                }
            }
        }

        for(int i=0;i<compartment3.length();i++){
            for(int j=0;j<appears2Strings.size();j++){
                if(compartment3.charAt(i)==appears2Strings.get(j)){
                    occursAll=appears2Strings.get(j);
                }
            }
        }

        return occursAll;
    }

    public static ArrayList<Character> appearsBothCompartments(ArrayList<String> importedFile){
        ArrayList<Character> commonCharacters=new ArrayList<>();

        for(int i=0;i<importedFile.size();i+=3){
            char result=compareStrings(importedFile.get(i),importedFile.get(i+1),importedFile.get(i+2));
            commonCharacters.add(result);
        }

        return commonCharacters;
    }

    public static int characterValue(ArrayList<Character> commonCharacters){
        int result=0;

        for(int i=0;i<commonCharacters.size();i++){
            int asciiValue=(int)commonCharacters.get(i);
            if((asciiValue>=65)&&(asciiValue<=90)){
                result+=(asciiValue-38);
            }else if((asciiValue>=97)&&(asciiValue<=122)){
                result+=(asciiValue-96);
            }
        }

        return result;
    }
}
