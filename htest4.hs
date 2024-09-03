

------------------------- Exercise 1

doubles :: [Int] -> [Int]
doubles x = [2*n | n <- x]

multiplesOfThree :: [Int] -> [Int]
multiplesOfThree x = [n | n <- x , n `mod` 3 == 0]

doubleMultiplesOfThree :: [Int] -> [Int]
doubleMultiplesOfThree x = [2*n | n <- x, n `mod` 3 == 0]

shorts :: [[a]] -> [[a]]
shorts x = [n | n <-x, length n < 4 ]

incrementPositives :: (Ord a,Num a) => [a] -> [a]
incrementPositives x = [n+1 | n <- x, n > 0]

difference :: Eq a => [a] -> [a] -> [a]
difference x y = [ n | n <- x , n `notElem` y ]

oddLengthSums :: Num a => [[a]] -> [a]
oddLengthSums x = [sum x | (n , x) <- zip [0.. (length x)] x, even n]

everyother :: [a] -> [a]
everyother x = [x | (n, x) <- zip [0..(length x)] x, even n]

--same :: Eq a => [a] -> [a] -> [Int]
--same x y = [x | (a,b) <- zip x y ] 


------------------------- Exercise 2

combinations :: [a] -> [b] -> [(a,b)]
combinations c d = [(x, y) | x <- c, y <- d]

selfcombinations :: [a] -> [(a,a)]
selfcombinations c = [(x,y)|x <- c, y <- c ]

pyts :: Int -> [(Int,Int,Int)]
pyts x = [(a,b,c) | a <- [1..x], b <- [1..x], c <- [1..x], a^2 +b^2 == c^2, a < b]


------------------------- Exercise 3

allTrue :: [Bool] -> Bool
allTrue = foldr (&&) True

someTrue :: [Bool] -> Bool
someTrue = foldr (||) True

largest :: Ord a => [a] -> a
largest x = foldr1 max x

smallest :: Ord a => [a] -> a
smallest x = foldr1 min x

every :: (a -> Bool) -> [a] -> Bool
every x = foldr (\y acc -> x y && acc) True

some :: (a -> Bool) -> [a] -> Bool
some = undefined

select :: (a -> Bool) -> [a] -> [a]
select = undefined

------------------------- Exercise 4


evenLength :: String -> Bool
evenLength = undefined

count :: Char -> String -> Int
count = undefined

successive :: Char -> String -> Int
successive = undefined


------------------------- Exercise 5

selections :: [a] -> [[a]]
selections = undefined

splits :: [a] -> [([a],[a])]
splits = undefined

permutations :: [a] -> [[a]]
permutations = undefined
 