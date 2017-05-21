/**
 * Binary Tree:
 * Binary tree only will have two children for each node.
 * For each node, its left child is smaller and right child is bigger.
 * Do not need to have two children for each node.
 * 
 * Tree:
 * Search, insert and delete items quickly.
 * Time to perform an operation on a tree is O(log N).
 * 
 * Unbalanced Tree:
 * A tree in which most of the nodes are found on one side of route.
 * Ordered data tends to create unbalanced trees.
 * Very Slow
 * 
 * root: the top node of a tree
 * path: the lines that connect all the nodes
 * leaf: a node that does not have any children
 * 
 * @author Alfred
 *
 */

public class BinaryTree {
	 
	Node root;
	
	public void addNode(int key, String name){
		
		//create a new Node and initialize it
		Node newNode = new Node(key, name);
		
		//if no root, it becomes the root
		if(root == null){
			
			root = newNode;
			
		}else {
			
			//set the beginning to traverse the tree
			Node focusNode = root;
			
			//parent of the new node
			Node parent;
			
			while(true){
				
				//start at root
				parent = focusNode;
				
				if(key < focusNode.key){
					
					//switch focus node to it's left child
					focusNode = focusNode.leftChild;
					
					//if the left child has no children
					if(focusNode == null){
						
						//place the new nod on the left of it
						parent.leftChild = newNode;
						
						return;
						
					}
				}else{
					
					//switch focus node to it's right node
					focusNode = focusNode.rightChild;
					
					//if the right child has no children
					if(focusNode == null){
						
						//place the new node on the right of it
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
	
	/*
	 * All nodes are visited in ascending order.
	 * Recursion is used to go to one node and
	 * then go to it's child node and so forth
	 */
	public void inOrderTraverseTree(Node focusNode){
		
		if(focusNode != null){
			
			//traverse the left node
			inOrderTraverseTree(focusNode.leftChild);
			
			//visit the currently focused on node
			System.out.println(focusNode);
			
			//traverse the right node
			inOrderTraverseTree(focusNode.rightChild);
		}
	}
	
	//traverse: parent --> left --> right
	public void preorderTraverseTree(Node focusNode){
		
		if(focusNode != null){
			
			System.out.println(focusNode);
			
			preorderTraverseTree(focusNode.leftChild);

			preorderTraverseTree(focusNode.rightChild);
		}
	}
	
	//traverse: left --> right --> parent
	public void postOrderTraverseTree(Node focusNode){
		
		if(focusNode != null){
			
			postOrderTraverseTree(focusNode.leftChild);

			postOrderTraverseTree(focusNode.rightChild);
			
			System.out.println(focusNode);
		}
	}
	
	public Node findNode(int key){
		
		//start at the top(root) of the tree
		Node focusNode = root;
		
		while(focusNode.key != key){
			
			if(key < focusNode.key){
				
				//shift the focus node to the left child
				focusNode = focusNode.leftChild;
				
			}else{
				
				//shift the focus node to the right child
				focusNode = focusNode.rightChild;
			}
			
			//node wasn't found
			if(focusNode == null) return null;
		}
		
		return focusNode;
	}
	
	public boolean removeNode(int key){
		
		//start at the top of node
		Node focusNode = root;
		Node parent = root;
		
		//whether to search to the right or left
		boolean isItLeftChild = true;
		
		//find the node to remove by key
		while(focusNode.key != key){
			
			parent = focusNode;
			
			//if search to the left
			if(key < focusNode.key){
				
				isItLeftChild = true;
				
				focusNode = focusNode.leftChild;
				
			}else{
				
				isItLeftChild = false;
				
				focusNode = focusNode.rightChild;
			}
			
			if(focusNode == null) return false;
		}
		
		//if the node doesn't have children
		if(focusNode.leftChild == null && focusNode.rightChild == null){
			
			//if the node is root, delete it
			if(focusNode == root){
				
				root = null;
				
			}else if(isItLeftChild){
				
				//it is a left child, delete it in it's parent
				parent.leftChild = null;
				
			}else{
				
				//it is a right child, delete it in it's parent
				parent.rightChild = null;
			}
		}
		
		//if no right child
		else if(focusNode.rightChild == null){
			
			if(focusNode == root){
				
				root = focusNode.leftChild;
				
			}else if(isItLeftChild){
				
				//move focus node's left child up to parent node's left child
				parent.leftChild = focusNode.leftChild;
				
			}else {
				//move focus node's left child up to parent node's right child
				parent.rightChild = focusNode.leftChild;
			}
		}
		
		//if no left child
		else if(focusNode.leftChild == null){
			
			if(focusNode == root){
				
				root = focusNode.rightChild;
				
			}else if(isItLeftChild){
				
				//move focus node's right child up to parent node's left child
				parent.leftChild = focusNode.rightChild;
				
			}else {
				
				//move focus node's right child up to parent node's right child
				parent.rightChild = focusNode.rightChild;
			}
		}
		
		//have two children, need to find deleted node's replacement
		else{
			
			Node replacement = getRelacementNode(focusNode);
			
			if(focusNode == root){
				
				root = replacement;
				
			}else if(isItLeftChild) {
				
				parent.leftChild = replacement;
				
			}else{
				
				parent.rightChild = replacement;
				
			}
			
			/* 
			 * move focus node's left child to replacement's left child.
			 * avoid breaking the tree
			 */
			replacement.leftChild = focusNode.leftChild;
		}
		
		return true;
	}
	
	//find the most left of replaced node's right child
	public Node getRelacementNode(Node replacedNode){
		
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		
		Node focusNode = replacedNode.rightChild;
		
		//find the most left child
		while(focusNode != null){
			
			replacementParent = replacement;
			
			replacement = focusNode;
			
			focusNode = focusNode.leftChild;
		}
		
		if(replacement != replacedNode.rightChild){
			
			replacementParent.leftChild = replacement.rightChild;
			
			replacement.rightChild = replacedNode.rightChild;
		}
		
		return replacement;
	}
	
	public static void main(String[] args) {
		
		BinaryTree theTree = new BinaryTree();
		
		//add nodes
		theTree.addNode(50, "Boss");
		theTree.addNode(25, "Vice President");
		theTree.addNode(15, "Office Manager");
		theTree.addNode(30, "Secretary");
		theTree.addNode(75, "Sales Manager");
		theTree.addNode(85, "Salesman 1");
		theTree.addNode(70, "Salesman 2");
		theTree.addNode(5, "Salesman 3");
		theTree.addNode(16, "Salesman 4");
		theTree.addNode(80, "Salesman 4");
		
		//different ways to traverse binary tree
		System.out.println("inOrderTraverseTree:");
		theTree.inOrderTraverseTree(theTree.root);
		System.out.println();
		
		System.out.println("preorderTraverseTree:");
		theTree.preorderTraverseTree(theTree.root);
		System.out.println();
		
		System.out.println("postOrderTraverseTree:");
		theTree.postOrderTraverseTree(theTree.root);
		System.out.println();
		
		//find node
		int findNodeKey = 75;
		System.out.println("Node with the key " + findNodeKey + ":");
		System.out.println(theTree.findNode(findNodeKey));
		System.out.println();
		
		//remove node
		int removeNodeKey = 75;
		System.out.println("Remove node with the key " + removeNodeKey + ":");
		theTree.removeNode(removeNodeKey);
		theTree.inOrderTraverseTree(theTree.root);
	}
}

class Node{
	
	int key;
	String name;
	
	Node leftChild;
	Node rightChild;
	
	Node(int key, String name){
		
		this.key = key;
		this.name = name;
	}
	
	public String toString(){
		
		return name + " has the key " + key;
	}
}
