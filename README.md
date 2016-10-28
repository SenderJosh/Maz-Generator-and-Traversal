# Maz-Generator-and-Traversal

Visual representation of finding exits (if one exists) in a maze using a stack or queue datastructure, or through recursion as was the requirement for the homework. I've also made a MazeGenerator class, not as a requirement, but I wanted to test larger mazes without having to make my own mazes manually. The algorithm is kinda similar to the DFS method in that I do use a Stack to develop the paths, but it hasn't been implemented such that it will continue until every cell has been visited (not necessarily, at least).

-

Main.java is an example. 

-
Traverse using Recursion with m.findExitUsingRecursion(m.findStart())

Traverse using Stack with m.findExitUsingStack()

Traverse using Queue with m.findExitUsingQueue()

Not necessary to use the MazeGenerator class (you can use your own maze.txt)
