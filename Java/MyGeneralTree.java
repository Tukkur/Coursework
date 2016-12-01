package hw4;
import java.util.ArrayList;
import java.util.List;

public class MyGeneralTree<T>
{
	MyGeneralTreeNode<T> root;

	public MyGeneralTree(T data)
	{
		this.root = new MyGeneralTreeNode<>(data);
	}

	public MyGeneralTree(MyGeneralTreeNode<T> root)
	{
		this.root = root;
	}

	public int getHeight()
	{
            return root.getHeight();
	}

	public int getNodeCountAtLevel(int level)
	{
            int counter = 1;
            int nodes = 0;
            MyGeneralTreeNode<T> current = new MyGeneralTreeNode<>(root.data);
            MyGeneralTreeNode<T> holder = new MyGeneralTreeNode<>(root.data);
            current = root;
            int currentLevel = 1;
            if (root==null)
            {
                return 0;
            }
            else if (level==0)
            {
                return 1;
            }
            else if(level==1)
            {
                return current.children.size();
            }
            else if(level==2)
            {
                for(MyGeneralTreeNode<T> child : current.children)
                {
                    nodes += child.children.size();
                }
                return nodes;
            }
            else
            return root.getNodeCountAtLevel(level, 0);
	}
        
        private MyGeneralTreeNode<T> getNextBranch(MyGeneralTreeNode<T> node)
        {
            for(int i = 0; i< node.children.size(); i++)
            {
                if(node.children.get(i).children != null)
                {
                    return node.children.get(i);
                }
            }
            return null;
        }

	public int getNumberOfBranchNodes()
	{
            if(root == null)
            {
                return 0;
            }
            return root.getNumberOfBranchNodes();
	}

	public int getNumberOfLeaves()
	{
            if(root==null)
            {
                return 0;
            }
            return root.getNumberOfLeaves();
	}

	public int getNumberOfNodes()
	{
            if(root==null)
            {
                return 0;
            }

            return root.getNumberOfNodes();
            
	}
        
        
}
