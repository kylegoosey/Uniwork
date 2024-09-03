

double :: Int -> Int
double x = 2 * x

doubles :: [Int] -> [Int]
doubles []     = []
doubles (x:xs) = double x : doubles xs

doublesm :: [Int]  -> [Int]
doublesm = map double

multiplesOfThree :: [Int] -> [Int]
multiplesOfThree (x:xs)
    | xs == []      = []
    |  mod x 3 == 0 = x : multiplesOfThree xs
    | otherwise     = multiplesOfThree xs

doubleMultiplesOfThree :: [Int] -> [Int]
doubleMultiplesOfThree (x : xs)
    | xs == []       = []
    | mod  x 3  == 0 = x * 2 : doubleMultiplesOfThree xs
    | otherwise      = doubleMultiplesOfThree xs


------------------------- Exercise 2

shorts :: [String] -> [String]
shorts x = filter length x
 where
     filter length (x:xs)
        | xs == []      = []
        | length  x < 4 = x : filter length xs
        | otherwise     = filter length xs

incrementPositives :: [Int] -> [Int]
incrementPositives = map (+1) . filter (>= 0)






difference :: String -> String -> String
difference x y  =  filter ( `notElem` y) x 
 

 



oddLengthSums :: [[Int]] -> [Int]
oddLengthSums = undefined


------------------------- Exercise 3


numbered :: [a] -> [(Int,a)]
numbered x = zip [1..length x] x

everyother :: [a] -> [a]
everyother = undefined

same :: Eq a => [a] -> [a] -> [Int]
same = undefined