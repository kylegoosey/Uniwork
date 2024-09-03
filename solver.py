import numpy as np

# Load sudokus
sudoku = np.load("data/very_easy_puzzle.npy")
print("very_easy_puzzle.npy has been loaded into the variable sudoku")
print(f"sudoku.shape: {sudoku.shape}, sudoku[0].shape: {sudoku[0].shape}, sudoku.dtype: {sudoku.dtype}")

# Load solutions for demonstration
solutions = np.load("data/very_easy_solution.npy")
print()

# Print the first 9x9 sudoku...
print("First sudoku:")
print(sudoku[0], "\n")

# ...and its solution
print("Solution of first sudoku:")
print(solutions[0])






def sudoku_solver(sudoku):
    def is_valid_starting_position(sudoku): #checks if the given sudoku is consistent at its starting position 
        for x in range(9):
            for y in range(9):
                num = sudoku[x][y]
                if num != 0:
                    sudoku[x][y] = 0 # sets each non-empty position to 0 temporarily in order for the is_valid funtcion to work correctly
                    if is_valid(sudoku, x, y, num) is False:
                        return False #this means that the starting values of the board arent consistent                     
                    sudoku[x][y] = num
        return True
    def is_valid(sudoku, x, y, num):#checks if the element that is about to be added is currently valid
        subgrid_row_start = 3 * (x // 3) #finds the upper left element of the 3x3 grid which the element is a part of.
        subgrid_col_start = 3 * (y // 3)



        for i in range(0,9):
            if sudoku[x][i] == num:
                return False
            elif sudoku[i][y] == num :
                return False
            elif sudoku[subgrid_row_start + i // 3][subgrid_col_start + i % 3] == num: #this will check each for the 9 elements in the 3x3 grid that the checked element is a part of 
                return False
            
        return True




    def find_empty_locations(sudoku): # finds the index of all the empty elements in the grid
        for x in range(0,9):
            for y in range(0,9):
                if sudoku[x][y] == 0:
                    return x, y
        return None, None




    def solve_sudoku_recursive(sudoku):#the backtracking algorithm 
        row, col = find_empty_locations(sudoku)

        if row is None:
            return True

        for num in range(1, 10): #every number is tested on the first empty element until a currently valid solution is found, then the next element is checked. this is repeated until either there are no more empty cells, meaning the grid is solved or until a cell has no possible values that are legal moves. in that case, the algorithm backtracts and tries different valid combinations until none are left, meaning no valid solution or a solution is found.
            if is_valid(sudoku, row, col, num) is True:
                sudoku[row][col] = num

                if solve_sudoku_recursive(sudoku) is True:
                    return True

                sudoku[row][col] = 0
        return False
    if  is_valid_starting_position(sudoku) is False:
                sudoku = np.array([[-1,-1,-1,-1,-1,-1,-1,-1,-1],
                                   [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                                   [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                                   [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                                   [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                                   [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                                   [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                                   [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                                   [-1,-1,-1,-1,-1,-1,-1,-1,-1]])
                return sudoku
    
    if solve_sudoku_recursive(sudoku) is True: #outputs of the algorithm 
        return sudoku
    else:
        sudoku = np.array([[-1,-1,-1,-1,-1,-1,-1,-1,-1],
                          [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                          [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                          [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                          [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                          [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                          [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                          [-1,-1,-1,-1,-1,-1,-1,-1,-1],
                          [-1,-1,-1,-1,-1,-1,-1,-1,-1]])
        return sudoku
    












SKIP_TESTS = False

if not SKIP_TESTS:
    import time
    import numpy as np
    __SCORES = {}
    difficulties = ['very_easy', 'easy', 'medium', 'hard']

    for difficulty in difficulties:
        print(f"Testing {difficulty} sudokus")
        
        sudokus = np.load(f"data/{difficulty}_puzzle.npy")
        solutions = np.load(f"data/{difficulty}_solution.npy")
        
        count = 0
        for i in range(len(sudokus)):
            sudoku = sudokus[i].copy()
            print(f"This is {difficulty} sudoku number", i)
            print(sudoku)
            
            start_time = time.process_time()
            your_solution = sudoku_solver(sudoku)
            end_time = time.process_time()
            
            if not isinstance(your_solution, np.ndarray):
                print("\033[91m[ERROR] Your sudoku_solver function returned a variable that has the incorrect type. If you submit this it will likely fail the auto-marking procedure result in a mark of 0 as it is expecting the function to return a numpy array with a shape (9,9).\n\t\033[94mYour function returns a {} object when {} was expected.\n\x1b[m".format(type(your_solution), np.ndarray))
            elif not np.all(your_solution.shape == (9, 9)):
                print("\033[91m[ERROR] Your sudoku_solver function returned an array that has the incorrect shape.  If you submit this it will likely fail the auto-marking procedure result in a mark of 0 as it is expecting the function to return a numpy array with a shape (9,9).\n\t\033[94mYour function returns an array with shape {} when {} was expected.\n\x1b[m".format(your_solution.shape, (9, 9)))
            
            print(f"This is your solution for {difficulty} sudoku number", i)
            print(your_solution)
            print("Is your solution correct?")
            if np.array_equal(your_solution, solutions[i]):
                print("Yes! Correct solution.")
                count += 1
            else:
                print("No, the correct solution is:")
                print(solutions[i])
            
            print("This sudoku took {} seconds to solve.\n".format(end_time-start_time))

        print(f"{count}/{len(sudokus)} {difficulty} sudokus correct")
        __SCORES[difficulty] = {
            'correct': count,
            'total': len(sudokus)
        }