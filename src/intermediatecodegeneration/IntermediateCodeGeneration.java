package intermediatecodegeneration;

import java.util.ArrayList;
import java.util.HashMap;

public class IntermediateCodeGeneration {

    private static char[] operands={'/','*'};
    private static int backCounterForNegativeVariable=0;
    private static StringBuffer LastArgument=new StringBuffer("t0");
    public static ArrayList<StringBuffer> Quadraples = new ArrayList<StringBuffer>();
    private static StringBuffer TempQuadrapleExpression=new StringBuffer(""),Temp2QuadrapleExpression=new StringBuffer("");

    /* buildOperandHahMap() returns the count of total occurence of '/' and '*' in the given expression and returns it
       in the form of HashMap<Character,Integer> 
    */
    public static HashMap<Character,Integer> buildOperandHahMap(String expression){
        HashMap<Character,Integer> operandPosition=new HashMap<>();
        int divValue=0,mulValue=0;
        
        for(char ch:expression.toCharArray()){
            switch(ch){
                case '/':
                   divValue++;  
                   break;
                case '*':
                   mulValue++;  
                   break; 
            } 
        }
        
        operandPosition.put('/', divValue);
        operandPosition.put('*', mulValue);

        return operandPosition;
    }
    


    /* 1.evaluateBracs() first stores the index position of '(' and ')' in OpenCloseBracketPositions 
       2.Function then finds the priority of brackets by counting the totoal number of '(' after current '(' 
	 i,e if 2 '(' after current '(' then the priority of current '(' will be 2+1=3 and store it in BracketPriority
       3.Function the finds index position of corresponding ')' for our current '(' by counting total number of '(' 
	  after our current '('.
         Hence if current '(' has 2 '(' after it then current '(' corresponing closing ')' will be 2+2+1
       4.At last it appends type of bracket,opening position,closing position and its priority inside a single StringBuffer
	 and stores it in BracketsPositionWithPriority.  
    */

    public static ArrayList<StringBuffer>  evaluateBracs(StringBuffer expression,String from){
       ArrayList<StringBuffer> BracketPriority= new ArrayList<>(),BracketsPositionWithPriority= new ArrayList<>();
       ArrayList<Integer> OpenCloseBracketPositions= new ArrayList<>();
       char ch;
       int openBracketCounter=0, closingBracketValue;
        
        for(int i=0;i<expression.length();i++){
            ch=expression.charAt(i);
            if(ch=='(')
                OpenCloseBracketPositions.add(Integer.parseInt(String.valueOf(new StringBuffer(String.valueOf(1)).append(i))));
            if(ch==')')
                OpenCloseBracketPositions.add(Integer.parseInt(String.valueOf(new StringBuffer(String.valueOf(2)).append(i))));
        }
        
        for(int i=OpenCloseBracketPositions.size()-1;i>=0;i--){
            openBracketCounter=0;
            if(String.valueOf(OpenCloseBracketPositions.get(i)).charAt(0)=='1'){
                if(i!=0){
                   for(int j=i-1;j>=0;j--){
                       if(String.valueOf(OpenCloseBracketPositions.get(j)).charAt(0)!='1')
                           break;
                       openBracketCounter++;
                   }
                }
                BracketPriority.add(new StringBuffer(String.valueOf(OpenCloseBracketPositions.get(i))).append(openBracketCounter));
            }
        }
        
       for(int i=0;i<OpenCloseBracketPositions.size();i++){
          openBracketCounter=0;
          if(String.valueOf(OpenCloseBracketPositions.get(i)).charAt(0)=='1'){
                for(int j=i+1;j<OpenCloseBracketPositions.size();j++){
                    if(String.valueOf(OpenCloseBracketPositions.get(j)).charAt(0)=='2')
                             break;
                    openBracketCounter++;
                }
                closingBracketValue=(openBracketCounter+openBracketCounter)+1;

                for(int k=0;k<BracketPriority.size();k++){
                    if(String.valueOf(BracketPriority.get(k)).substring(0,String.valueOf(BracketPriority.get(k)).length()-1)
                            .equalsIgnoreCase(String.valueOf(OpenCloseBracketPositions.get(i)))){
                            StringBuffer sb=new StringBuffer(String.valueOf(BracketPriority.get(k)).substring(0,String.valueOf
(BracketPriority.get(k)).length()-1)).append("-")
                                    .append(String.valueOf(OpenCloseBracketPositions.get((i+closingBracketValue))).substring(1));     
                            BracketsPositionWithPriority.add(sb.append(String.valueOf(BracketPriority.get(k)).charAt(String.valueOf
(BracketPriority.get(k)).length()-1)));
                }
            }
         }
          
       } 
      
      return BracketsPositionWithPriority;
      
    }


    
     /*12-31 where,
     1st character respresnts priority
     2nd character to'-' represents index position of '(' 
     character after '-' till the 2nd last character of string respresents represents index position of ')'
     last character respresents the priority of brackets.*/

     public static int[] getOpeningClosingBracketPositions(String expression){
        int[] OpeningClosingBracketPositions = new int[2];
        StringBuffer sb1 = new StringBuffer(),sb2 = new StringBuffer();
        for(int i=1;i<expression.length();i++){
                if(expression.charAt(i)=='-')
                        break;
                sb1.append(expression.charAt(i));
        }
        
        for(int i=expression.indexOf('-')+1;i<expression.length()-1;i++){
                sb2.append(expression.charAt(i));
        }
       
        OpeningClosingBracketPositions[0]=Integer.parseInt(String.valueOf(sb1));
        OpeningClosingBracketPositions[1]=Integer.parseInt(String.valueOf(sb2));
        return OpeningClosingBracketPositions;
    } 
     


    /* ElementForCurrentPriority() will evaluates every expression for current priority and stores its
       result in expression itself.
       Expression now has solved value for expression inside '(' ')' */

    public static StringBuffer ElementForCurrentPriority(StringBuffer expression,int CurrentPriority){
        int[] OpeningClosingBracketPositions;
         while(getTotalElementsForCurrentPriority(expression,CurrentPriority)==true){
                ArrayList<StringBuffer> Bracs = evaluateBracs(expression,"ElementForCurrentPriority");
                int priority;
                String str;
                OpeningClosingBracketPositions=new int[2];
                for(int i=0;i<Bracs.size();i++){
                    str=new String(Bracs.get(i));
                    priority=Integer.parseInt(String.valueOf(str.charAt(str.length()-1)));
                    
                    if(priority==CurrentPriority){
                        OpeningClosingBracketPositions=getOpeningClosingBracketPositions(str);
                        String temp=performOperation(expression.substring(OpeningClosingBracketPositions[0]+1, 
OpeningClosingBracketPositions[1]),false);
                        String s=new String(expression);
                        expression=new StringBuffer(s.replace(s.substring(OpeningClosingBracketPositions[0], 
                                    OpeningClosingBracketPositions[1]+1), temp));
                        Temp2QuadrapleExpression=new StringBuffer(s.replace(s.substring(OpeningClosingBracketPositions[0], 
                                    OpeningClosingBracketPositions[1]+1), TempQuadrapleExpression));
                        break;
                    } 
                }
         }
         return expression;
    } 
    


    /* getTotalElementsForCurrentPriority() will return the 1st set of brackets for current priority */

    public static boolean getTotalElementsForCurrentPriority(StringBuffer expression,int CurrentPriority){
        ArrayList<StringBuffer> Bracs = evaluateBracs(expression,"getTotalElementsForCurrentPriority"+" "+CurrentPriority);
        int priority;
        String str;
         for(int i=0;i<Bracs.size();i++){
                str=new String(Bracs.get(i));
                priority=Integer.parseInt(String.valueOf(str.charAt(str.length()-1)));
                if(priority==CurrentPriority)
                    return true;
         }
         return false;
    }
    
     


    /* generateIntermidiateCode() will call ElementForCurrentPriority() for each and every possible prioirites */

    public static StringBuffer generateIntermidiateCode(StringBuffer expression){
        int MaximumPriority=10;
        Quadraples.clear();
        LastArgument=new StringBuffer("t0");
        
        while(MaximumPriority>=0){
              expression=ElementForCurrentPriority(expression,MaximumPriority); 
              --MaximumPriority;
        }
        return new StringBuffer(performOperation(String.valueOf(expression),true));
    } 




    /* getBackValue() will generate back value for given operator by checking for 3 scenarios
       1. Storing all the numbers from current operator in reverse order untill it encounter 
          another symbol and then return its result in reverse order.
       2. If symbol i.e '-' is 1st character in string then it returns back value as negative
       3. If there are no numbers before current operator the it returns back value as 0 */
     
    public static int getBackValue(String expression,int backValue){
        char[] valuseArray = expression.toCharArray();
        StringBuilder sb = new StringBuilder();
        int counterPostion=Integer.MIN_VALUE, count=0;
        
        for(int i=backValue-1;i>=0;i--){
            if(valuseArray[i]=='/' || valuseArray[i]=='*' || valuseArray[i]=='+' || valuseArray[i]=='-' || valuseArray[i]=='(' || 
valuseArray[i]==')'){
                counterPostion=i;
                break;
            }
            else{
                sb.append(valuseArray[i]);
            }
            count++;
        }
      
        if(counterPostion==0){ 
          return -Integer.parseInt(new String(sb.reverse()));
        }
        if(count==0){
            backCounterForNegativeVariable++;
            return 0;
        }

        
   
       return Integer.parseInt(new String(sb.reverse()));
    } 
    



    public static StringBuffer getBack2Value(StringBuffer expression,int backValue){
        char[] valuseArray = new String(expression).toCharArray();
        StringBuilder sb = new StringBuilder();
        int counterPostion=Integer.MIN_VALUE, count=0;
        
        for(int i=backValue-1;i>=0;i--){
            if(valuseArray[i]=='/' || valuseArray[i]=='*' || valuseArray[i]=='+' || valuseArray[i]=='-' || valuseArray[i]=='(' || 
valuseArray[i]==')'){
                counterPostion=i;
                break;
            }
            else
                sb.append(valuseArray[i]);
            
            count++;
        }
      
        if(counterPostion==0)
            return new StringBuffer("-").append(sb.reverse()); 
        if(count==0){
            backCounterForNegativeVariable++;
            return new StringBuffer("0");
        }
        return new StringBuffer(String.valueOf(sb.reverse()));
    }
    



     /* getBackValue() will generate front value for given operator by  Storing all the numbers from current operator untill it 	  	encounters. */

    public static int getFrontValue(String expression,int frontValue){
        char[] valuseArray = expression.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=frontValue+1;i<expression.length();i++){
           if(valuseArray[i]=='/' || valuseArray[i]=='*' || valuseArray[i]=='+' || valuseArray[i]=='-' || valuseArray[i]=='(' ||
                  valuseArray[i]==')'){
                break;
            }
            else
                sb.append(valuseArray[i]);
        }   
        return Integer.parseInt(new String(sb));
    }
    
     public static StringBuffer getFront2Value(StringBuffer expression,int frontValue){
        char[] valuseArray = new String(expression).toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=frontValue+1;i<expression.length();i++){
           if(valuseArray[i]=='/' || valuseArray[i]=='*' || valuseArray[i]=='+' || valuseArray[i]=='-' || valuseArray[i]=='(' ||
                valuseArray[i]==')'){
                break;
            }
            else
                sb.append(valuseArray[i]);
        }   
        return new StringBuffer(sb);
    }
    
    public static String performOperation(String expression , boolean ReferTemporaryQuadrapleExpression){
       System.out.println("\nExpression is "+expression);
       int operandCounter=0;
       char ch;
       int back=0,front=0,cal=0,indexPosition=0,IndexPosition=0,  lastVal=0;
       StringBuffer QuadrapleExpression = new StringBuffer(),back2=new StringBuffer(),front2=new StringBuffer(),QuadrapleToBeAdded=new 
StringBuffer() ;
       String oldString="",oldString2="";
       
       QuadrapleExpression=(ReferTemporaryQuadrapleExpression==true) ? Temp2QuadrapleExpression : new StringBuffer(expression);
      
       System.out.println(" Expression at start is "+expression);
       while(operandCounter<=1){
           HashMap<Character,Integer> operandPosition=buildOperandHahMap(expression);   
           char operationCharacter=operands[operandCounter];
           int values=operandPosition.get(operationCharacter);
  
           if(values!=0){
              oldString="";
                   while(values!=0){
                       indexPosition= expression.indexOf(operationCharacter);
                              back=getBackValue(expression,indexPosition);

                              if(backCounterForNegativeVariable>0){
                                  indexPosition= expression.indexOf(operationCharacter,indexPosition + 1);
                                  backCounterForNegativeVariable=0;
                                  back=getBackValue(expression,indexPosition);
                              }
                          
                              front=getFrontValue(expression,indexPosition); 
                              
                                     switch(operationCharacter){
                                        case '/':
                                            cal=(back/front);
                                            break;
                                        case '*':
                                             cal=back*front;
                                             break;  
                                    }
                                    
                                    oldString="".concat(String.valueOf(back));
                                    oldString=oldString.concat(String.valueOf(operationCharacter));
                                    oldString=oldString.concat(String.valueOf(front));
                                    expression = expression.replace(oldString, String.valueOf(cal));
                                    
                                    indexPosition= String.valueOf(QuadrapleExpression).indexOf(operationCharacter);
                                    back2=getBack2Value(QuadrapleExpression,indexPosition);
                                    if(backCounterForNegativeVariable>0){
                                        indexPosition= String.valueOf(QuadrapleExpression).indexOf(operationCharacter,indexPosition + 
1);
                                        backCounterForNegativeVariable=0;
                                        back2=getBack2Value(QuadrapleExpression,indexPosition);
                                     }
                                    front2=getFront2Value(QuadrapleExpression,indexPosition); 
                                    
                                    lastVal=Integer.parseInt(String.valueOf(LastArgument).substring(1));
                                    LastArgument=new StringBuffer(new StringBuffer("t").append(String.valueOf(++lastVal)));
                                    
                                    oldString2="".concat(String.valueOf(back2));
                                    oldString2=oldString2.concat(String.valueOf(operationCharacter));
                                    oldString2=oldString2.concat(String.valueOf(front2));
                                  
                                    QuadrapleExpression=new StringBuffer(String.valueOf(QuadrapleExpression).replace(oldString2, 
LastArgument));

                                    QuadrapleToBeAdded= new StringBuffer("");
                                    QuadrapleToBeAdded = QuadrapleToBeAdded.append(operationCharacter).append(",");
                                    QuadrapleToBeAdded =QuadrapleToBeAdded.append(back2).append(",");
                                    QuadrapleToBeAdded =QuadrapleToBeAdded.append(front2).append(",");
                                    QuadrapleToBeAdded =QuadrapleToBeAdded.append(LastArgument);
                                    Quadraples.add(QuadrapleToBeAdded);
                                    
                                    System.out.println(" Expression is "+ expression);
                           
                    values--;
                   }  
           }
           operandCounter++;
       }
       
       while(checkOnlyIfSingleOperandExist(expression)==false){
            ch=Character.MIN_VALUE; 
            for(char ch2:expression.toCharArray()){
                if(ch2=='-' || ch2=='+'){
                    ch=ch2;
                    break;
                }
            }
            IndexPosition=expression.indexOf(ch);
            
           if(IndexPosition==0){
                    for(int i=IndexPosition+1;i<expression.length();i++){
                        if(expression.charAt(i)=='-' || expression.charAt(i)=='+'){
                            ch=expression.charAt(i);
                            break;
                        }
                    }
                    
                    indexPosition=expression.indexOf(ch,1);
                    back=getBackValue(expression,indexPosition);
                    front=getFrontValue(expression,indexPosition);
                    switch(ch){            
                        case '+':
                            cal=back+front;
                           break;  
                       case '-':
                            cal=back-front;
                           break;
                   }
                    oldString="".concat(String.valueOf(back));
                   oldString=oldString.concat(String.valueOf(ch));
                   oldString=oldString.concat(String.valueOf(front));
                   expression = expression.replace(oldString, String.valueOf(cal));
                   
                   indexPosition= String.valueOf(QuadrapleExpression).indexOf(ch,1);
                   back2=getBack2Value(QuadrapleExpression,indexPosition);
                  
                   front2=getFront2Value(QuadrapleExpression,indexPosition);
                                   
                   lastVal=Integer.parseInt(String.valueOf(LastArgument).substring(1));

                   LastArgument=new StringBuffer(new StringBuffer("t").append(String.valueOf(++lastVal)));

                    oldString2="".concat(String.valueOf(back2));
                    oldString2=oldString2.concat(String.valueOf(ch));
                    oldString2=oldString2.concat(String.valueOf(front2));

                    QuadrapleExpression=new StringBuffer(String.valueOf(QuadrapleExpression).replace(oldString2, LastArgument));

                    QuadrapleToBeAdded = new StringBuffer("");    
                    QuadrapleToBeAdded = QuadrapleToBeAdded.append(ch+",");
                    QuadrapleToBeAdded =QuadrapleToBeAdded.append(back2+",");
                    QuadrapleToBeAdded =QuadrapleToBeAdded.append(front2+",");
                    QuadrapleToBeAdded =QuadrapleToBeAdded.append(LastArgument);
                    Quadraples.add(QuadrapleToBeAdded);

                    System.out.println(" Expression is "+ expression);
                   
            }
            else{
                    indexPosition=expression.indexOf(ch);
                    back=getBackValue(expression,indexPosition);
                    front=getFrontValue(expression,indexPosition);
                    switch(ch){             
                        case '+':
                            cal=back+front;
                           break;   
                       case '-':
                            cal=back-front;
                           break;
                   }
                   oldString="".concat(String.valueOf(back));
                   oldString=oldString.concat(String.valueOf(ch));
                   oldString=oldString.concat(String.valueOf(front));
                   expression = expression.replace(oldString, String.valueOf(cal));
                   
                   indexPosition= String.valueOf(QuadrapleExpression).indexOf(ch);
                   back2=getBack2Value(QuadrapleExpression,indexPosition);
                   front2=getFront2Value(QuadrapleExpression,indexPosition); 
                                    
                    lastVal=Integer.parseInt(String.valueOf(LastArgument).substring(1));
                    LastArgument=new StringBuffer(new StringBuffer("t").append(String.valueOf(++lastVal)));          

                    oldString2="".concat(String.valueOf(back2));
                    oldString2=oldString2.concat(String.valueOf(ch));
                    oldString2=oldString2.concat(String.valueOf(front2));

                    QuadrapleExpression=new StringBuffer(String.valueOf(QuadrapleExpression).replace(oldString2, LastArgument));

                    QuadrapleToBeAdded = new StringBuffer("");
                    QuadrapleToBeAdded = QuadrapleToBeAdded.append(ch).append(",");
                    QuadrapleToBeAdded =QuadrapleToBeAdded.append(back2).append(",");
                    QuadrapleToBeAdded =QuadrapleToBeAdded.append(front2).append(",");
                    QuadrapleToBeAdded =QuadrapleToBeAdded.append(LastArgument);
                    Quadraples.add(QuadrapleToBeAdded);

                    System.out.println(" Expression is "+ expression);
                   
            }
       }
       System.out.println("Expression is "+expression);

       TempQuadrapleExpression=QuadrapleExpression;
       return expression;
    }
    
    
    
   

    /* checkOnlyIfSingleOperandExist() checks the number of operators in expression and returns true only if single operator
       exist and it has no back value and only 1 front value by checking that length of front value will be equal to 1 less than
       length of expression */

    public static boolean checkOnlyIfSingleOperandExist(String expression){
        int count=0 , IndexPosition;
        char ch1=Character.MIN_VALUE;
        boolean SingleOperandCounter=false;
        for(char ch2:expression.toCharArray()){
            if(ch2=='-' || ch2=='+'){
                ch1=ch2;
                count++;
            }    
        }
       
        IndexPosition=expression.indexOf(ch1);
        if(IndexPosition==0){
            int front=getFrontValue(expression,IndexPosition);
  
            String frontStr=String.valueOf(front);
            if(frontStr.length()==expression.length()-1)
                SingleOperandCounter=true;
        }
        if(count>=1 && SingleOperandCounter==false)
            return false;
        else
            return true;
    }
 
}
