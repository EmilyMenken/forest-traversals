import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TreeProblems {

  /*
   postOrder (Node Version)
   -----------
   Given the root of a tree print out the values of the nodes in post-order.
   Print each value on a separate line.

   Example:
   If the tree is:
          5
       /  |  \
      3   9   8
        / | \  
       4  1  2
   The output should be:
   3
   4
   1
   2
   9
   8
   5

   If the root is null, do nothing.
   */
  public static <T> void postOrder(Node<T> root) {

    if (root == null) 
    {
      return; //does nothing
    }

  for (Node<T> child : root.children) {
    postOrder(child);
  }
  System.out.println(root.value);
}//end postOrder version 1

  /*
   postOrder (Node Version)
   -----------
   Given the root of a tree print out the values of the nodes in post-order.
   Print each value on a separate line.
   If the tree is null or does not contain the root, do nothing.

   Example:
   For a tree represented as:
     5 -> [3, 9, 8]
     3 -> []
     9 -> [4, 1, 2]
     4 -> []
     1 -> []
     2 -> []
   The output should be:
   3
   4
   1
   2
   9
   8
   5
   */
  public static <T> void postOrder(Map<T, List<T>> tree, T root) {

    if (tree == null || root == null || !tree.containsKey(root)) 
    {
      return; //does nothing
    }

  for (T child : tree.get(root)) {
    postOrder(tree, child);
  }
  System.out.println(root);
}//end postOrder version 2

  /*
   sumTree (Node Version)
   -----------
   Given a tree built with the Node class (with integer values), compute and return the sum of all the node values.
   Example:
   If the tree is:
          5
       /  |  \
      3   9   8
        / | \  
       4  1  2
   then the method should return 32.
   A null tree should return 0
  */
  public static int sumTree(Node<Integer> root) {
    if (root == null) 
    {
      return 0; //returns 0
    }

  int sum = root.value;
  for (Node<Integer> child : root.children) {
    sum += sumTree(child);
  }
  return sum;

  }//end sumTree version 1

  /*
   sumTree (Map Version)
   -----------
   Given a tree represented as a map (where every node appears as a key and leaf nodes map to an empty list),
   compute the sum of all nodes.
   Example:
   For a tree represented as:
     5 -> [3, 9, 8]
     3 -> []
     9 -> [4, 1, 2]
     4 -> []
     1 -> []
     2 -> []
   the method should return 32.

   A null tree should return 0

   Hint: There's a simple way to do this!
  */
  public static int sumTree(Map<Integer, List<Integer>> tree) {
    if (tree == null)
    {
      return 0;//returns 0
    } 

  int sum = 0;
  for (int key : tree.keySet()) {
    sum += key;
  }
  return sum;

  }//end sumTree version 2

  /*
   findRoot
   -----------
   Given a tree represented as a map where each key is a parent node and the value is a list of its children,
   find the root of the tree. The root is the node with no parents.
   Example:
   If the tree is represented as:
     20 -> [40]
     8  -> []
     30 -> []
     10 -> [20, 30, 99]
     40 -> []
     99 -> [8]
   then the method should return 10.

  You can assume the tree is non-null and well-formed.

   Hint: No recursion needed! Think about how you would do this by hand.
  */
  public static <T> T findRoot(Map<T, List<T>> tree) {
  Set<T> children = new HashSet<>();

  for (List<T> childList : tree.values()) {
    children.addAll(childList);
  }//end for

  for (T node : tree.keySet()) {
    if (!children.contains(node)) {
      return node;
    }//end if
  }//end for

  return null; //returns null incase there is in fact no root

  }//end findRoot 

  /*
   maxDepth (Node Version)
   -----------
   Compute the maximum depth of a tree using the Node class. The depth is the number of nodes along
   the longest path from the root down to the farthest leaf. The root is at depth 1. If the tree is null, return 0.
   Example:
   For a tree structured as:
          A
       /  |  \
      B   E   C
      |      / \
      E     D   Q
             \ 
              Z
   the maximum depth is 4.

   
  */
  public static <T> int maxDepth(Node<T> root) {
    if (root == null) 
    {
      return 0;//returns 0
    }

  int max = 0;
  for (Node<T> child : root.children) {
    max = Math.max(max, maxDepth(child));
  }
  return 1 + max;

  }//end maxDepth (node)

  /*
   maxDepth (Map Version)
   -----------
   Compute the maximum depth of a tree using the Node class. The depth is the number of nodes along
   the longest path from the root down to the farthest leaf. The root is at depth 1. If the tree is null, return 0.
   Example:
   For a tree structured as:
    A -> [B, E, C]
    B -> [E]
    E -> []
    C -> [D, Q]
    D -> [Z]
    Q -> []
    Z -> []
   the maximum depth is 4 (A->C->D->Z).

   Hint: Use findRoot to start. Then, make a recursive helper method.
  */
  public static int maxDepth(Map<String, List<String>> tree) {
    if (tree == null || tree.isEmpty()) 
    {
      return 0;//returns 0
    }

  String root = findRoot(tree);
  return maxDepthHelper(tree, root);
} //end maxDepth (map)

private static int maxDepthHelper(Map<String, List<String>> tree, String node) {
  if (!tree.containsKey(node) || tree.get(node).isEmpty()) 
  {
    return 1;
  }
  int max = 0;
  for (String child : tree.get(node)) {
    max = Math.max(max, maxDepthHelper(tree, child));
  }
  return 1 + max;
  }//end maxDepth helper

}//end treeProblems
