package talkbox;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class ExpressionList 
{
	
	ArrayList<Expression> exp_list;
    ArrayList<Integer> count; 
    DefaultListModel<String> exp_name_list;
	
	
	ExpressionList()
	{
		
		
		exp_list = new ArrayList<Expression>();
		count = new ArrayList<Integer>();
		exp_name_list = new DefaultListModel<String>();
		
		
	}
	
	public void addExp(Expression exp)
	{
		
		int index = indexOf(exp);
		if( index != -1 )
		{
			
		int exp_count = count.get(index).intValue();
		exp_count ++;
		count.set(index, Integer.valueOf(exp_count));
		
		}
		
		else 
			
		{
			
			exp_list.add(exp);
			count.add(Integer.valueOf(1));
			exp_name_list.addElement(exp.GetName());
			
		}
		
		
	}

	
	// this method will delete the exp from the list once it's count hits zero
	public void decrement_exp(Expression exp)
	{
		
		int exp_index =	indexOf(exp);
		
		if (exp_index != -1)
		{
			
			if (count.get(exp_index).intValue() <= 1)
			{
				
				exp_list.remove(exp_index);
				count.remove(exp_index);
				exp_name_list.remove(exp_index);
				
			}
			
			else
				
			{
				
				int exp_count = count.get(exp_index).intValue();
				exp_count --; 
				count.set(exp_index, Integer.valueOf(exp_count));
				
				
			}
			
			
		}
	
	
		
	}
	
	public DefaultListModel<String> getNameList()
	{
		return exp_name_list;
	}
	
	
	private Integer indexOf(Expression exp)
	{
		int index = -1;
		for (int i = 0; i < exp_list.size(); i++)
		
		{
			
			if( exp == exp_list.get(i));
			{
				index = i;
			}
			
		}
		return index;
	}

}
