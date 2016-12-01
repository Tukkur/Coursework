package hw4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyGeneralTreeNode<T>
{
	T data;
	List<MyGeneralTreeNode<T>> children = new LinkedList<>();

	public MyGeneralTreeNode(T data)
	{
		this.data = data;
	}

	public int getHeight()
	{
            if(isLeaf())
            {
                return 1;
            }
            int height = 0;
            for(MyGeneralTreeNode child : children)
            {
                int childHeight = 0;
                
                childHeight = child.getHeight();
                if(height < childHeight)
                {
                    height = childHeight;
                } 
            }
            return height+1;
	}
        public int getNodeCountAtLevel(int level, int counter){
            
            int nodes = 0;
            if(level-2==counter)
            {
                for(MyGeneralTreeNode<T> child : children)
                {
                    nodes+= child.children.size();
                }
                return nodes;
            }
            for(MyGeneralTreeNode<T> child : children)
            {
                nodes += child.getNodeCountAtLevel(level, counter++);
            }
            return nodes;
        }

	public int getNumberOfBranchNodes()
	{
            if(!isLeaf())
           {
               return 1;
           }
           int count = 1; 
           for(MyGeneralTreeNode<T> child : children)
           {
               count += child.getNumberOfBranchNodes();
           }
           return count;
        }

	public int getNumberOfLeaves()
	{
           if(isLeaf())
           {
               return 1;
           }
           int count = 1; 
           for(MyGeneralTreeNode<T> child : children)
           {
               count += child.getNumberOfLeaves();
           }
           return count;
	}

	public int getNumberOfNodes()
	{
            return getNumberOfLeaves() + getNumberOfBranchNodes();
	}

	public boolean isLeaf()
	{
            return children.isEmpty();
	}



	@Override public String toString()
	{
		return data.toString();
	}
}
