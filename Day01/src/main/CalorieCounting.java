package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CalorieCounting {
    public static void main(String[] args) throws Exception {
        System.out.println("Largest Calorie Amount: "+largestCalorieAmount(parseFile(importFile("src/files/DataSet.txt"))));
    }

    /**
     * importFile
     * @param fileLocation - String passed to function location of file to be imported into program
     * @return - retuns an ArrayList with all of the data stored within the file
     * @throws FileNotFoundException - gives an exception if the file is not found at the given location
     * Method creates and returns an ArrayList with a copy of the file contents stored within it
     */
    public static ArrayList<String> importFile(String fileLocation) throws FileNotFoundException{
        ArrayList<String> fileContents=new ArrayList<>();
        Scanner readFile=new Scanner(new File(fileLocation));

        while(readFile.hasNextLine()){
            fileContents.add(readFile.nextLine());
        }

        readFile.close();
        return fileContents;
    }

    /**
     * parseFile
     * @param values - ArrayList of file values passed to function
     * @return - returns ArrayList with the total calories each elf has
     * Function goes through the passed ArrayList adding all of the values together until reaches empty index value
     * Once empty index value is reached it adds the current total to another ArrayList and resets the total to 0
     * This process continues till it gets to the end of the ArrayList passed in
     * The ArrayList with all the total values is then returned to the main program
     */
    public static ArrayList<Integer> parseFile(ArrayList<String> values){
        ArrayList<Integer> totalElfCalories=new ArrayList<>();
        int totalCalories=0;

        for(int i=0;i<values.size();i++){
            if(values.get(i)!=""){
                totalCalories+=Integer.parseInt(values.get(i));
            }else if(values.get(i)==""){
                totalElfCalories.add(totalCalories);
                totalCalories=0;
            }
        }

        return totalElfCalories;
    }

    /**
     * largestCalorieAmount
     * @param values - ArrayList of total calorie values for each elf passed to function
     * @return - returns integer value which is the largest calorie amount out of all the elves
     * Function iterates through all of the values within the passed ArrayList comparing them to largestCalorie
     * Once a value is found that is bigger it overrides largestCalorie with the found value
     * This process continues until all of the values within the passed ArrayList have been iterated through
     * The largestCalorie value is then returned to the main program
     */
    public static int largestCalorieAmount(ArrayList<Integer> values){
        int largestCalorie=0;

        for(int i=0;i<values.size();i++){
            if(largestCalorie<values.get(i)){
                largestCalorie=values.get(i);
            }
        }

        return largestCalorie;
    }
}
