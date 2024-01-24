# Image Processing Implementation (JAVA)
One of the big problems of machine learning (specifically, supervised learning) is called classification, in which a model aims to predict the label of input data. For example, given an email as an input, determine whether it is spam or not.

K-NN classifiers aim to provide a solution to the classification problem and stem from the assumption that two similar inputs will likely have the same label. For example, if you take a spam email and change a single word, it’s probably still spam. K-NN finds the k “closest” or “most similar” training data points to the input (nearest neighbors) and outputs the most common label among these neighbors. You can think of the output/prediction as a majority vote between the k closest neighbors.

The choice of K is a hyperparameter that can affect the performance of the KNN algorithm. A small value of K may lead to overfitting, where the model may be too sensitive to noise in the data, while a large value of K may lead to underfitting, where the model may be too simple and unable to capture the underlying structure in the data.

This is an implementation of KNN Classification using JAVA. The focus of this implementation was to use data structures like d-heaps and priority queues.
