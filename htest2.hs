------------------------- Exercise 1

add :: [Int] -> Int
add []     = 0
add (x:xs) = x + add xs

times :: [Int] -> Int
times []   = 1 
times(x:xs) = x* times xs

range :: Int -> Int -> [Int]
range x y  
    | x > y  = []
    | x == y   = [y]
    |otherwise = x : range (x+1) y 



factorial :: Int -> Int
factorial x = times (range (1) (x))


------------------------- Exercise 2

count :: [a] -> Int
count []     = 0
count (_:xs) = 1 + count xs

append :: [a] -> [a] -> [a] 
append [] (y:ys)     =  (y:ys)
append (x:xs) (y:ys) = x : append xs (y:ys)
 

concatenate :: [[a]] -> [a]
concatenate [] = []
concatenate (x:xs) = append x : (concatenate xs)


------------------------- Exercise 3

member :: Int -> [Int] -> Bool
member y (x:xs)
    | xs == []  = False 
    | x == y    = True 
    | otherwise = member y xs
    

remove :: Int -> [Int] -> [Int]
remove y (x:xs)
    | x == y    =  remove y xs
    | xs == []  = [] 
    | otherwise =  x : remove y xs


 
--at :: [a] -> Int -> a
--at x y
  --  x !! y 

------------------------- Exercise 4

final :: [a] -> a
final (x:xs)
    | [xs] == []  = x
    | otherwise = final xs 

ordered :: [Int] -> Bool
ordered = undefined

pair :: [a] -> [b] -> [(a,b)]
pair = undefined

find :: Int -> [(Int,String)] -> String
find = undefined


------------------------- Exercise 5


------------------------- Exercise 6

merge = undefined

msort = undefined