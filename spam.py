# This skeleton code simply classifies every input as ham
#
# Here you can see there is a parameter k that is unused, the
# point is to show you how you could set up your own. You might
# also pass in extra data via a train method (also does nothing
# here). Modify this code as much as you like so long as the 
# accuracy test in the cell below runs.

class SpamClassifier:
    def __init__(self, k):
        self.k = k
        
    def train(self):
        pass
        
    def predict(self, testing_features):
        final = np.array([])
        training_features = training_spam[:, 1:]



        distances = np.sqrt(np.sum((testing_features[:, np.newaxis] - training_features) ** 2, axis=2))


        for i in range(len(testing_features)):
            knn = []
            knnvalue = []
            spamweight = 0
            hamweight = 0
            for j in range(len(training_features)):
                if distances[i][j] == 0:
                    weight = 10
                else:
                    weight = 1 / distances[i][j]

                if len(knn) < round(math.sqrt(len(training_features))):
                    knn.append(weight)
                    knnvalue.append(training_spam[j][0])
                else:
                    minweight = min(knn)
                    if minweight < weight:
                        minindex = knn.index(minweight)
                        knn.pop(minindex)
                        knnvalue.pop(minindex)
                        knn.append(weight)
                        knnvalue.append(training_spam[j][0])

            for m in range(len(knn)):
                if knnvalue[m] == 1:
                    spamweight = spamweight + knn[m]
                else:
                    hamweight = hamweight + knn[m]
            if spamweight < hamweight:
                final = np.append(final, 0)
            else:
                final = np.append(final, 1)
            
 

        return final
    

def create_classifier():
    classifier = SpamClassifier(k=1)
    classifier.train()
    return classifier

classifier = create_classifier()