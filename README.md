# 15-Puzzle game solver
This is a remake of an assignment I had to do to my artifical intelligence class. The original one wasn't really good so I've decided to remake it.

## How to use:

compile src with javac

run Algorithm, per example:
```Shell
  java Algorithm dfs 15
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
  java Algorithm bfs
  java Algorithm bfs D
  java Algorithm dfs
  java Algorithm dfs D
  java Algorithm idfs D
```
where D is the maximum Depth

## How to improve this:
As I am not worried about how good the program actually is (I've already done the Artificial Intelligence class) and I know some people may end up in this repository looking for help here is some guidance on how to improve what I've done.

- some of my functions use Strings in loops, and that's not optimal (java will always copy the entire string), so you may want to change that for something else.

## Some notes about the program:
My unbounded DFS implementation doesn't really have a stack, has that would be pointless (the stack would continuosly grow and the tail would never even get checked). Basically when it expands a node it checks if any of them is the objective. 
If not, it expands the first and completely discards the rest.

If you want to monitor memory usage you can check it using htop on the shell while using the program.

If you just want to count the Nodes, you can add some prints on the linkedlists to print their size while the program it's running.
