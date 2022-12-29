package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class SupplyStack {
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Character>> result=movingCrates(crateCreation(importFile("src/files/StacksCleaned.txt")),parseFileMovements(importFile("src/files/Operations.txt")));
        String trueAnswer="";
        for(int i=0;i<result.size();i++){
            trueAnswer+=result.get(i).get(result.get(i).size()-1);
        }

        System.out.println(result);
        System.out.println(trueAnswer);
    }

    /**
     * 
     * @param fileLocation
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<String> importFile(String fileLocation) throws FileNotFoundException{
        ArrayList<String> file=new ArrayList<>();
        Scanner readFile=new Scanner(new File(fileLocation));

        while(readFile.hasNextLine()){
            file.add(readFile.nextLine());
        }

        readFile.close();
        return file;
    }

    /**
     * 
     * @param importedFile
     * @return
     */
    public static ArrayList<ArrayList<Integer>> parseFileMovements(ArrayList<String> importedFile){
        ArrayList<ArrayList<Integer>> movesMake=new ArrayList<>();

        for(int i=0;i<importedFile.size();i++){
             ArrayList<Integer> temporaryStorage=new ArrayList<>();
             temporaryStorage.add(Integer.parseInt(importedFile.get(i).substring(importedFile.get(i).indexOf(" ")+1,importedFile.get(i).indexOf("f")-1)));
             temporaryStorage.add(Integer.parseInt(importedFile.get(i).substring(importedFile.get(i).indexOf("om")+3,importedFile.get(i).indexOf("to")-1))-1);
             temporaryStorage.add(Integer.parseInt(importedFile.get(i).substring(importedFile.get(i).indexOf("to")+3))-1);
             movesMake.add(temporaryStorage);
        }

        return movesMake;
    }

    /**
     * 
     * @param importedFile
     * @return
     */
    public static ArrayList<ArrayList<Character>> crateCreation(ArrayList<String> importedFile){
        ArrayList<ArrayList<Character>> crates=new ArrayList<>();

        for(int i=0;i<importedFile.size();i++){
            ArrayList<Character> temporaryStorage=new ArrayList<>();
            char[] splittingString=importedFile.get(i).toCharArray();
            for(int j=0;j<splittingString.length;j++){
                if(splittingString[j]!=','){
                    temporaryStorage.add(splittingString[j]);
                }
            }
            crates.add(temporaryStorage);
        }
        return crates;
    }

    /**
     * 
     * @param crateValues
     * @param movementValues
     * @return
     */
    public static ArrayList<ArrayList<Character>> movingCrates(ArrayList<ArrayList<Character>> crateValues,ArrayList<ArrayList<Integer>> movementValues){
        for(int i=0;i<movementValues.size();i++){
            ArrayList<Character> valuesMove=new ArrayList<>();

            for(int j=0;j<movementValues.get(i).get(0);j++){
                char valueToAdd=crateValues.get(movementValues.get(i).get(1)).get(crateValues.get(movementValues.get(i).get(1)).size()-1);
                valuesMove.add(valueToAdd);
                // crateValues.get(movementValues.get(i).get(2)).add(valueToAdd);
                crateValues.get(movementValues.get(i).get(1)).remove(crateValues.get(movementValues.get(i).get(1)).size()-1);
            }
            
            while(!valuesMove.isEmpty()){
                crateValues.get(movementValues.get(i).get(2)).add(valuesMove.get(valuesMove.size()-1));
                valuesMove.remove(valuesMove.size()-1);
            }

            valuesMove.clear();

            System.out.println(crateValues+"\t"+i);
        }
        return crateValues;
    }
}