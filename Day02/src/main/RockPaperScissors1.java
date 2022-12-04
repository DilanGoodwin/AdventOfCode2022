package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RockPaperScissors1 {
    public static void main(String[] args) throws Exception {
        ArrayList<String> importedFile=importFile("src/files/DataSet.txt");
        int resultScore=gameScore(gameResult(importedFile))+moveScore(importedFile);
        System.out.println(resultScore);
        
    }

    public static ArrayList<String> importFile(String fileLocation) throws FileNotFoundException{
        ArrayList<String> file=new ArrayList<>();
        Scanner readFile=new Scanner(new File(fileLocation));

        while(readFile.hasNextLine()){
            String data=readFile.nextLine();
            data=data.charAt(0)+""+data.charAt(2);
            file.add(data);
        }

        readFile.close();
        return file;
    }

    public static ArrayList<Character> parseFile(ArrayList<String> importedFile,int positionValue){
        ArrayList<Character> playerMoves=new ArrayList<>();

        for(int i=0;i<importedFile.size();i++){
            char moveMade=importedFile.get(i).charAt(positionValue);

            switch(moveMade){
                case 'X':case 'A':
                    moveMade='R';
                    break;
                case 'Y':case 'B':
                    moveMade='P';
                    break;
                case 'Z':case 'C':
                    moveMade='S';
                    break;
            }
            playerMoves.add(moveMade);
        }
        return playerMoves;
    }

    public static ArrayList<Integer> gameResult(ArrayList<String> importedFile){
        ArrayList<Integer> result=new ArrayList<>();
        ArrayList<Character> myMoves=parseFile(importedFile,1);
        ArrayList<Character> opposingMoves=parseFile(importedFile,0);

        for(int i=0;i<myMoves.size();i++){
            if((myMoves.get(i))==(opposingMoves.get(i))){
                result.add(3);
            }else{
                switch(myMoves.get(i)){
                    case 'R':
                        if(opposingMoves.get(i)=='P'){
                            result.add(0);
                        }else{
                            result.add(6);
                        }
                        break;
                    case 'P':
                        if(opposingMoves.get(i)=='S'){
                            result.add(0);
                        }else{
                            result.add(6);
                        }
                        break;
                    case 'S':
                        if(opposingMoves.get(i)=='R'){
                            result.add(0);
                        }else{
                            result.add(6);
                        }
                        break;
                }
            }
        }
        return result;
    }

    public static int gameScore(ArrayList<Integer> moveResults){
        int result=0;

        for(int i=0;i<moveResults.size();i++){
            result+=moveResults.get(i);
        }

        return result;
    }

    public static int moveScore(ArrayList<String> importedFile){
        int result=0;
        ArrayList<Character> myMoves=parseFile(importedFile,1);

        for(int i=0;i<myMoves.size();i++){
            switch(myMoves.get(i)){
                case 'R':
                    result+=1;
                    break;
                case 'P':
                    result+=2;
                    break;
                case 'S':
                    result+=3;
                    break;
            }
        }
        return result;
    }
}
