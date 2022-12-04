package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RockPaperScissors2{
    public static void main(String[] args) throws Exception {
        ArrayList<String> importedFile=importFile("src/files/DataSet.txt");
        int result=roundScore(importedFile)+moveScore(importedFile);
        System.out.println(result);
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

    public static ArrayList<Character> parseMyMoves(ArrayList<String> importedFile){
      ArrayList<Character> myMoves=new ArrayList<>();

      for(int i=0;i<importedFile.size();i++){
        myMoves.add(importedFile.get(i).charAt(1));
      }

      return myMoves;
    }

    public static ArrayList<Character> parseOpposingMoves(ArrayList<String> importedFile){
      ArrayList<Character> opposingMoves=new ArrayList<>();

      for(int i=0;i<importedFile.size();i++){
        char moveMade=importedFile.get(i).charAt(0);

        switch(moveMade){
          case 'A':
            moveMade='R';
            break;
          case 'B':
            moveMade='P';
            break;
          case 'C':
            moveMade='S';
            break;
        }
        opposingMoves.add(moveMade);
      }

      return opposingMoves;
    }

    public static char roundLoss(char opposingMove){
      char move='|';

      switch(opposingMove){
        case 'R':
          move='S';
          break;
        case 'P':
          move='R';
          break;
        case 'S':
          move='P';
          break;
      }

      return move;
    }

    public static char roundWin(char opposingMove){
      char move='|';

      switch(opposingMove){
        case 'R':
          move='P';
          break;
        case 'P':
          move='S';
          break;
        case 'S':
          move='R';
          break;
      }

      return move;
    }

    public static ArrayList<Character> myMovesToMake(ArrayList<String> importedFile){
      ArrayList<Character> movesToMake=new ArrayList<>();
      ArrayList<Character> myMoveValues=parseMyMoves(importedFile);
      ArrayList<Character> opposingMoves=parseOpposingMoves(importedFile);

      for(int i=0;i<myMoveValues.size();i++){
        if(myMoveValues.get(i)=='Y'){
          movesToMake.add(opposingMoves.get(i));
        }else{
          switch(myMoveValues.get(i)){
            case 'X':
              movesToMake.add(roundLoss(opposingMoves.get(i)));
              break;
            case 'Z':
              movesToMake.add(roundWin(opposingMoves.get(i)));
              break;
          }
        }
      }
      return movesToMake;
    }

    public static int roundScore(ArrayList<String> importedFile){
      int result=0;
      ArrayList<Character> roundResult=parseMyMoves(importedFile);

      for(int i=0;i<roundResult.size();i++){
        switch(roundResult.get(i)){
          case 'X':
            break;
          case 'Y':
            result+=3;
            break;
          case 'Z':
            result+=6;
            break;
        }
      }
      return result;
    }

    public static int moveScore(ArrayList<String> importedFile){
      int result=0;
      ArrayList<Character> roundMoves=myMovesToMake(importedFile);

      for(int i=0;i<roundMoves.size();i++){
        switch(roundMoves.get(i)){
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
