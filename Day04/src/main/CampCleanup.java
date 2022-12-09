package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CampCleanup {
    public static void main(String[] args) throws Exception {
        sortingImportedData(importFile("src/files/DataSet.txt"));
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

    public static void sortingImportedData(ArrayList<String> importedFile){
        int numberCrossover=0;

        for(int i=0;i<importedFile.size();i++){
            String firstArray=importedFile.get(i).substring(0,importedFile.get(i).indexOf(","));
            String secondArray=importedFile.get(i).substring(importedFile.get(i).indexOf(",")+1);

            int arrayOne1=Integer.parseInt(firstArray.substring(0,firstArray.indexOf("-")));
            int arrayOne2=Integer.parseInt(firstArray.substring(firstArray.indexOf("-")+1));
            int arrayTwo1=Integer.parseInt(secondArray.substring(0,secondArray.indexOf("-")));
            int arrayTwo2=Integer.parseInt(secondArray.substring(secondArray.indexOf("-")+1));

            if((arrayOne1<=arrayTwo1)&&(arrayOne2>=arrayTwo2)){
                numberCrossover++;
            }else if((arrayOne1>=arrayTwo1)&&(arrayOne2<=arrayTwo2)){
                numberCrossover++;
            }
        }

        System.out.println(numberCrossover);
    }
}
