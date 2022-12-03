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
     * 
     * @param fileLocation
     * @return
     * @throws FileNotFoundException
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
     * 
     * @param values
     * @return
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
     * 
     * @param values
     * @return
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
