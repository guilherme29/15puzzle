# 15-Puzzle game solver
This is a remake of an assignment I had to do to my artifical intelligence class. The original one wasn't really good so I've decided to remake it.

## How to use:

compile Puzzle with javac
```Shell
  cd src/
  javac Puzzle.java
```

run Puzzle, per example:
```Shell
  java Puzzle dfs 15
```
write your current matrix and your objective matrix (the one you want to get to), I have some examples in test_cases.txt if you're out of ideas.
```Shell
  13 11 15 4
  8 9 1 5
  12 14 0 2
  7 10 3 6
  
  13 11 4 5
  8 0 14 15
  12 1 3 2
  7 9 10 6
```
Now just wait for the results!
Remember that some algorithms can take a very long time depending in your input. 

## Available algorithms you can run the program with:
```Shell
  java Puzzle bfs
  java Puzzle bfs D
  java Puzzle dfs
  java Puzzle dfs D
  java Puzzle idfs
  java Puzzle idfs D
```
where D is the maximum Depth (optional).

## How to improve this:
As I am not worried about how good the program actually is (I've already done the Artificial Intelligence class) and I know some people may end up in this repository looking for help here is some guidance on how to improve what I've done.

- some of my functions build Strings in loops, and that's not optimal (java will always copy the entire string), so you may want to change that for something else. This is specially damaging to the unbounded dfs implementation.


## Some notes about the program:
The dfs implementation uses a different function to expand each node just so I can ensure myself it doesn't get stuck in a corner.

The program is setup to use matrices of any size, though I didn't bother testing. Head over to Const.java and change the values to your liking.

If you want to monitor memory usage you can check it using htop on the shell while using the program.

If you just want to count the Nodes, you can add some prints on the linkedlists to print their size while the program it's running.

You can also use the shell to monitor the time of each algorithm.
