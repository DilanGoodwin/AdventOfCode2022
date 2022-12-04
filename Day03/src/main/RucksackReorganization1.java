package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RucksackReorganization1 {
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

    public static char compareStrings(String compartment1,String compartment2){
        char occursBoth='|';

        char[] charactersCompartment1=compartment1.toCharArray();
        char[] charactersCompartment2=compartment2.toCharArray();

        for(int i=0;i<charactersCompartment1.length;i++){
            for(int j=0;j<charactersCompartment2.length;j++){
                if(charactersCompartment1[i]==charactersCompartment2[j]){
                    occursBoth=charactersCompartment1[i];
                }
            }
        }
        return occursBoth;
    }

    public static ArrayList<Character> appearsBothCompartments(ArrayList<String> importedFile){
        ArrayList<Character> commonCharacters=new ArrayList<>();

        for(int i=0;i<importedFile.size();i++){
            String data=importedFile.get(i);
            int len=data.length();
            char result=compareStrings(data.substring(0, (len/2)),data.substring((len/2), len));
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
