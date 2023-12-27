# Simplified-Fork-Choice-Algorithm

Problem Statement:

We will implement a “modified” PoS-based voting algorithm. The original
Casper FFG consists of two layers:

1. The bottom layer is the tree of transactions.
2. The top layer is the sub-tree of checkpoints extracted from the tree of transactions.

For the sake of simplicity, the following assumptions are made:

1. There are 10 validators in the system. The set of the validators are fixed. The deposits of the validators are given in the table below:

| Validator ID   | 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   |
| -------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Deposit Amount | 500 | 100 | 300 | 250 | 150 | 500 | 600 | 350 | 200 | 150 |

2. The checkpoint tree is already given.
3. The checkpoint tree is a full binary tree.
