/*
 * Gerardo Lopez
 * This class interacts with the calculator graphical
 * user interface. It is the back end logic that accepts infix 
 * expressions as input, converts the infix expression to postfix,
 * and evaluates the expression to update the GUI. 
 */

import java.util.StringTokenizer;
import data_structures.*;


public class ExpressionEvaluator 
{
	private Stack<String> stack;
	private Queue<String> queue;
	
	public ExpressionEvaluator()
	{
		stack = new Stack<String>();
		queue = new Queue<String>();
	}
	
	public String processInput(String s)
	{
		stack.makeEmpty();
		queue.makeEmpty();
		String expression = s;
		StringTokenizer tokens = new StringTokenizer(expression);
		
		while(tokens.hasMoreTokens())
		{
			String tok = tokens.nextToken();
			if(tok.equals("("))
			{
				stack.push(tok);
			}
			else if(tok.equals(")"))
			{
				while(!stack.isEmpty() && !stack.peek().equals("("))        
				{
					queue.enqueue(stack.pop());
				}
				if(stack.peek().equals("("))
				{
					stack.pop();
				}
			}
			else if(isOperator(tok))
			{
				while(!stack.isEmpty() 
					 && (!stack.peek().equals("(")) 
					 && tokPreced(stack.peek()) >= tokPreced(tok))
				{
					queue.enqueue(stack.pop());
				}
				stack.push(tok);
			}
			else if(isNum(tok))
			{
				queue.enqueue(tok);
			}
		}
		
		while(!stack.isEmpty())
		{
			queue.enqueue(stack.pop());
		}
		
		while(!queue.isEmpty())
		{
			if(isNum(queue.peek()))
			{
				stack.push(queue.dequeue());
			}
			else if(isOperator(queue.peek()))
			{
				double input2 = Double.parseDouble(stack.pop());
				double input1 = Double.parseDouble(stack.pop());
				double answer = 0;
			
				if(queue.peek().equals("+"))
				{
					answer = input1 + input2;
				}
				else if(queue.peek().equals("-"))
				{
					answer = input1 - input2;
				}
				else if(queue.peek().equals("*"))
				{
					answer = input1 * input2;
				}
				else if(queue.peek().equals("/"))
				{
					answer = input1 / input2;
				}
				else if(queue.peek().equals("^"))
				{
					answer = Math.pow(input1, input2);
				}
				stack.push(""+answer);
				queue.dequeue();
			}
			else queue.dequeue();
		}
		return stack.pop();
	}
	
	private int tokPreced(String tok)
	{
		if(tok.equals("+") || tok.equals("-"))
		{
			return 1;
		}
		else if(tok.equals("*") || tok.equals("/"))
		{
			return 2;
		}
		else if(tok.equals("^"))
		{
			return 3;
		}
		return 0;
	}
	
	private boolean isOperator(String tok)
	{
		if(tok.equals("^"))
		{
			return true;
		}
		if(tok.equals("+"))
		{
			return true;
		}
		else if(tok.equals("-"))
		{
			return true;
		}
		else if(tok.equals("*"))
		{
			return true;
		}
		else if(tok.equals("/"))
		{
			return true;
		}
		return false;
	}
	
	private boolean isNum(String token)
	{
		if(token.matches("[0.0-9.9]+"))
		{
			return true;
		}
		return false;
	}	
}

