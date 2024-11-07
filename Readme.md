Huffman Encoding : https://www.youtube.com/watch?v=uDS8AkTAcIU&pp=ygUQaHVmZm1hbiBlbmNvZGluZw%3D%3D
knapsack 0/1 : https://m.youtube.com/watch?v=5JXz5VEg_g8&pp=ygUnMCAxIGtuYXBzYWNrIHVzaW5nIGR5bmFtaWMgcHJvZ3JhbW1pbmcg
____________________________________________________________________________________________________

# 0/1 Knapsack Problem

Given:
- `n` items, each with a `weight` and `profit`.
- A knapsack with a maximum capacity `W`.

### Dynamic Programming Approach

Let `dp[i][w]` represent the maximum profit we can achieve using the first `i` items with a knapsack capacity `w`.

### Recursive Formula

The formula for filling the `dp` table is:

\[
dp[i][w] = \begin{cases} 
      dp[i-1][w] & \text{if } \text{weight of item } i > w \\
      \max(dp[i-1][w], \ \text{profit of item } i + dp[i-1][w - \text{weight of item } i]) & \text{if } \text{weight of item } i \leq w 
   \end{cases}
\]

Explanation:
- If the weight of the current item is greater than the knapsack capacity `w`, we cannot include it. Therefore:
  \[
  dp[i][w] = dp[i-1][w]
  \]
- If the weight of the item is less than or equal to `w`, we have two choices:
  1. Exclude the item: `dp[i][w] = dp[i-1][w]`
  2. Include the item: `dp[i][w] = \text{profit of item } i + dp[i-1][w - \text{weight of item } i]`

We take the maximum of these two values:
\[
dp[i][w] = \max(dp[i-1][w], \ \text{profit of item } i + dp[i-1][w - \text{weight of item } i])
\]

### Base Case
\[
dp[0][w] = 0 \quad \text{for all } w
\]
This means if there are no items, the maximum profit is `0` regardless of the knapsack capacity.



To explain the 0/1 Knapsack problem in a format similar to the table you provided, let's walk through the example.

### Problem Setup
- We have 4 items with given weights and profits:
  - Item 1: weight = 1, profit = 2
  - Item 2: weight = 3, profit = 4
  - Item 3: weight = 5, profit = 7
  - Item 4: weight = 7, profit = 10
- **Knapsack Capacity**: 8

### Dynamic Programming Approach

Let `dp[i][w]` represent the maximum profit we can achieve with the first `i` items and a knapsack capacity `w`.

### Recursive Formula
The formula for filling the table is:

\[
dp[i][w] = \begin{cases} 
      dp[i-1][w] & \text{if } \text{weight of item } i > w \\
      \max(dp[i-1][w], \ \text{profit of item } i + dp[i-1][w - \text{weight of item } i]) & \text{if } \text{weight of item } i \leq w 
   \end{cases}
\]

- If the weight of the current item is more than the current knapsack capacity `w`, we can't include the item, so we take the value from the previous row (`dp[i-1][w]`).
- If the weight of the item is less than or equal to `w`, we have two choices:
  1. **Exclude the item**: The maximum profit remains as `dp[i-1][w]`.
  2. **Include the item**: Add the item's profit to `dp[i-1][w - weight of item]`.

We then take the maximum of these two options.

### Filling the Table

| Item (Weight, Profit) | Capacity 0 | Capacity 1 | Capacity 2 | Capacity 3 | Capacity 4 | Capacity 5 | Capacity 6 | Capacity 7 | Capacity 8 |
|------------------------|------------|------------|------------|------------|------------|------------|------------|------------|------------|
| 0 (No items)           | 0          | 0          | 0          | 0          | 0          | 0          | 0          | 0          | 0          |
| 1 (1, 2)               | 0          | 2          | 2          | 2          | 2          | 2          | 2          | 2          | 2          |
| 2 (3, 4)               | 0          | 2          | 2          | 4          | 6          | 6          | 6          | 6          | 6          |
| 3 (5, 7)               | 0          | 2          | 2          | 4          | 6          | 7          | 9          | 9          | 11         |
| 4 (7, 10)              | 0          | 2          | 2          | 4          | 6          | 7          | 9          | 10         | 12         |

### Explanation of the Table

1. **Row 0**: This represents the case with no items, so all values are `0`.
2. **Row 1**: Only Item 1 is available. For capacities `1` and above, we can include Item 1, so `dp[1][w] = 2` for all `w >= 1`.
3. **Row 2**: Items 1 and 2 are available.
   - For capacities less than `3`, we can only take Item 1.
   - For capacities `3` and above, we can include Item 2. For example:
     - At capacity `4`, we have two choices: exclude Item 2 (profit = 2) or include it (profit = 6). We take the maximum, `6`.
4. **Row 3**: Items 1, 2, and 3 are available.
   - For capacities less than `5`, we can only consider Items 1 and 2.
   - For capacities `5` and above, we consider including Item 3 as well. For example:
     - At capacity `8`, we take the maximum between excluding Item 3 (profit = 6) and including it (profit = 11), resulting in `11`.
5. **Row 4**: All items are available.
   - At capacity `8`, the maximum profit we can achieve is `12` by including Items 2 and 4.

The maximum achievable profit with a knapsack capacity of 8 is **12** (shown in the last cell, `dp[4][8]`).

### Optimal Selection
For a knapsack capacity of 8, the optimal selection of items is Item 2 and Item 4, yielding a profit of **12**.
