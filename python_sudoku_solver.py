import numpy as np

#Here is an example of a Sudoku puzzle. The zeros represent the blank cells
#one would encounter when viewing a paper-based sudoku puzzle.
grid = [
    [5,3,0,0,7,0,0,0,0],
    [6,0,0,1,9,5,0,0,0],
    [0,9,8,0,0,0,0,6,0],
    [8,0,0,0,6,0,0,0,3],
    [4,0,0,8,0,3,0,0,1],
    [7,0,0,0,2,0,0,0,6],
    [0,6,0,0,0,0,2,8,0],
    [0,0,0,4,1,9,0,0,5],
    [0,0,0,0,8,0,0,7,9]
    ]

def guesses(row, column, number):
    """This function is used to guess which number(s) would fit the blank
cell, under condition of the rules: the number being guessed must
appear neither along the same row, column, nor within the same 3x3 square
in which the blank cell appears. Thus, we test the row and column along which,
as well as the square within which the blank cell appears. The row, column,
and number being guessed are provided within the function arguments."""
    
    #Here is our grid from the global scope
    global grid
    
    #Here is our row test. We check each column for the fixed row in the
    #function argument.
    for i in range(9):
        if grid[row][i] == number:
            return False
    
    #Here is our column test. We check each row for the fixed column in the
    #function argument.
    for j in range(9):
        if grid[j][column] == number:
            return False
    
    #Here is our 3x3 square test. For the given row and column, we use the
    #floor function to iterate across three rows and three columns, one unit
    #at a time, starting at the (i,j)^{th} cell of the matrix, where i and j
    #can be any order-unique combination of values belonging to the set
    #{0, 3, 6}. 
    x_0 = (column//3)*3
    y_0 = (row//3)*3
    for j in range(3):
        for i in range(3):
            if grid[y_0+j][x_0+i] == number:
                return False
            
    return True

def solve():
    """This function uses recursion and back-tracking to solve the puzzle.
It calls the guesses function above until it finds a suitable number. Then,
via recursion, the solve function calls itself to repeat the process. Via
back-tracking, if at any point of the recursive process the guesses function
is unable to find a suitable number, the solve function resets the previously
found suitable number back to zero, moving on within the range of possible
numbers until it finds a different suitable number. This process continues
until the matrix is completely rid of zeros."""
    
    #Here is our grid from the global scope
    global grid
    
    #Here is our solver.
    for j in range(9):
        for i in range(9):
            if grid[j][i] == 0:
                for number in range(1, 10):
                    if guesses(j, i, number):
                        grid[j][i] = number
                        solve()
                        grid[j][i] = 0
                return
    print(np.matrix(grid))
    input(f'\nPress Enter to see if there is another solution.')
    print(f'\n')

solve()
print(f'Nope! That is all! Now for the original!\n')
print(np.matrix(grid))