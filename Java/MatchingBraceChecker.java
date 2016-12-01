 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hw3;

/**
 *
 * @author tukkkur
 */
public class BraceChecker {
     public int findError(String code[])
{
    MyArrayStack<String> stack = new MyArrayStack<>();
    
    for (int j = 0; j < code.length; j++)
    {
      String string = code[j];
      for (int i = 0; i < string.length(); i++)
      {
      char ch = string.charAt(i);
      if (ch == '{') 
      {
        stack.push(Character.toString(ch)); 
      }
      else if (ch =='[')
      {
      stack.push(Character.toString(ch));
      }
      else if (ch == '(')
      {
          stack.push(Character.toString(ch));
      }
    
         else if (ch =='}' || ch == ']' || ch ==')')
          {
               if (stack.isEmpty())
              {
                  return j;
              }
               else if (ch == '}' && stack.peek().equals("{"))
               {
                   stack.pop();
               }
               else if (ch == ']' && stack.peek().equals("["))
               {
                   stack.pop();
               }
               else if (ch == ')' && stack.peek().equals("("))
               {
                   stack.pop();
               }
               
               else if (ch == '}' && !stack.peek().equals("{"))
               {
                   return j;
               }
               else if (ch == ']' && !stack.peek().equals("["))
               {
                   return j;
               }
               else if (ch == ')' && !stack.peek().equals("("))
               {
                   return j;
               }
          }
     }
    }
    
   if (!stack.isEmpty())
   {
       return code.length;
   }
   else return -1;
    }
   
}
