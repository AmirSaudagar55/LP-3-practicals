import random
import time
import numpy as np

# Deterministic Quick Sort
def deterministic_partition(arr, low, high):
    pivot = arr[high]
    i = low - 1
    for j in range(low, high):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i+1], arr[high] = arr[high], arr[i+1]
    return i+1

def deterministic_quick_sort(arr, low, high):
    if low < high:
        pi = deterministic_partition(arr, low, high)
        deterministic_quick_sort(arr, low, pi - 1)
        deterministic_quick_sort(arr, pi + 1, high)

# Randomized Quick Sort
def randomized_partition(arr, low, high):
    pivot_index = random.randint(low, high)
    arr[pivot_index], arr[high] = arr[high], arr[pivot_index]
    return deterministic_partition(arr, low, high)

def randomized_quick_sort(arr, low, high):
    if low < high:
        pi = randomized_partition(arr, low, high)
        randomized_quick_sort(arr, low, pi - 1)
        randomized_quick_sort(arr, pi + 1, high)

# Analysis function to compare both Quick Sorts
def analyze_sorting_algorithms():
    sizes = [100, 1000, 5000, 10000]
    runs = 10
    results = []

    for size in sizes:
        deterministic_times = []
        randomized_times = []

        for _ in range(runs):
            arr = np.random.randint(0, 100000, size).tolist()
            arr_copy = arr.copy()

            # Deterministic Quick Sort
            start_time = time.time()
            deterministic_quick_sort(arr, 0, len(arr) - 1)
            deterministic_time = time.time() - start_time
            deterministic_times.append(deterministic_time)

            # Randomized Quick Sort
            start_time = time.time()
            randomized_quick_sort(arr_copy, 0, len(arr_copy) - 1)
            randomized_time = time.time() - start_time
            randomized_times.append(randomized_time)

        avg_deterministic_time = np.mean(deterministic_times)
        avg_randomized_time = np.mean(randomized_times)

        results.append({
            'size': size,
            'deterministic_time': avg_deterministic_time,
            'randomized_time': avg_randomized_time
        })

    return results

# Run analysis
results = analyze_sorting_algorithms()

# Print results
for result in results:
    print(f"Array Size: {result['size']}")
    print(f"Deterministic Quick Sort Time: {result['deterministic_time']:.5f} seconds")
    print(f"Randomized Quick Sort Time: {result['randomized_time']:.5f} seconds")
    print("")
