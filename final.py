import sys

#sys.path.append('C:/Users/jay dev/Anaconda3/Lib/site-packages/numpy')


import pandas as pd;
import numpy as np;
from pandas import Series
from pandas import DataFrame

import sklearn.neighbors
from sklearn.neighbors import NearestNeighbors

import spacy

nlp = spacy.load('en_core_web_md') 

path = sys.argv[1]
# "D:\\final_year_project\\ml\\final.csv"

dataset = pd.read_csv(path)

numaricSubset = dataset.select_dtypes(include = [np.number])
numaricSubset = numaricSubset.iloc[:,1:-2]

stringSubset = dataset.select_dtypes(include = [np.object])

maxVisitsRowIndex = dataset["visits"].idxmax()

maxVisitsRowNumaric = numaricSubset.iloc[maxVisitsRowIndex]

maxVisitsRowString = stringSubset.iloc[maxVisitsRowIndex]

def getNumaricDataDistance(numaricSubset:DataFrame , maxVisitsRowNumaric:Series)->Series:
    neigh = NearestNeighbors()
    neigh.fit(numaricSubset)
    distanceDetails = neigh.kneighbors([maxVisitsRowNumaric])
    distance = distanceDetails[0].flatten()
    indexes = distanceDetails[1].flatten()
    return Series(distance , index = indexes)
    

numaricDistanceSeries = getNumaricDataDistance(numaricSubset , maxVisitsRowNumaric)

def calculateSimilarityScore(word1:str , word2:str)->int:
    concatinatedWords = word1+" "+word2
    tokens = nlp(concatinatedWords)
    return tokens[0].similarity(tokens[1])

def findSimilarity(stringSubset:DataFrame,maxVisitsRowString:Series)->DataFrame:
    similarity = {}
    for (columnName,columnValues) in stringSubset.iteritems():
        columnSimilarity = [None]*len(columnValues)
        for index,item in columnValues.items():
            columnSimilarity[index] = calculateSimilarityScore(item, maxVisitsRowString[columnName])
        similarity[columnName] = columnSimilarity
    return pd.DataFrame(similarity)

def calcualteAverageSimilarity(similarityDataFrame:DataFrame)->Series:
    averageSimilarityList = [None]*len(similarityDataFrame)
    for index,row in similarityDataFrame.iterrows():
        averageSimilarityList[index] = row.mean()
    return Series(averageSimilarityList)

def getAverageSimilarity(stringSubset:DataFrame,maxVisitsRowString:Series)->Series:
    similarityDataFrame = findSimilarity(stringSubset, maxVisitsRowString)
    averageSimilarity = calcualteAverageSimilarity(similarityDataFrame)
    return averageSimilarity

textSimilaritySeries = getAverageSimilarity(stringSubset,maxVisitsRowString)


def formatNumaricDistance(numaricDistanceSeries:Series)->Series:
    subtractedList = [None]*len(numaricDistanceSeries)
    maxValue = numaricDistanceSeries.max()
    for index in numaricDistanceSeries.index:
        subtractedList[index] = maxValue-numaricDistanceSeries[index]
    subtractedSeries = Series(subtractedList)
    subtractedMax = subtractedSeries.max()
    dividedSeries = subtractedSeries.divide(subtractedMax)
    return dividedSeries

numaricPrioritySeries = formatNumaricDistance(numaricDistanceSeries)

numaricColumnCount =len(numaricSubset.columns)

stringColumnCount = len(stringSubset.columns)

totalColumnCount = numaricColumnCount+stringColumnCount

numaricRatio = numaricColumnCount/totalColumnCount

stringRatio = stringColumnCount/totalColumnCount

updatedTextSimilarity = textSimilaritySeries.mul(stringRatio)

updatedNumaricSeries = numaricPrioritySeries.mul(numaricRatio)

columnPriority = updatedTextSimilarity.add(updatedNumaricSeries)

visitColumn = dataset["visits"]

visitMaxValue = visitColumn.max()

visitPriority = visitColumn.div(visitMaxValue)

finalPriority = columnPriority.add(visitPriority)

finalPriority = finalPriority.divide(2)

dataset["priority"] = finalPriority

dataset.to_csv(path,index = False)

print('written')





