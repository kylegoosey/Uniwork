insert :: Ord a => a -> [a] -> [a] 
insert x []     = [x]
insert x (y:ys)
    | x < y     = x : y : ys
    | x == y    = x : y : ys
    | otherwise = y : insert x ys

isort :: Ord a => [a] -> [a]
isort []     = []
isort (x:xs) =  insert x (isort xs)

fibonacci :: [Int]
fibonacci = makeFibonacci 1 1
makeFibonacci n m = n : makeFibonacci m (m+n)

average :: [Int] -> Int 
average xs 
    | count == 0 = error "..."
    | otherwise = total `div` count 
  where
    total = sum xs
    count  = length xs
