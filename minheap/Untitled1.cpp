/* Author: Momin Butt
	Date: October 25, 2020
Decription: This program enqueues elements into a priority queue by building a
min-heap of the numbers (top-down). To dequeue, the program extracts the minimum
while maintaining a valid heap. The program uses an array to implement the heap.

*/

#define _CRT_SECURE_NO_WARNINGS

	#include <iostream>
	#include <stdio.h>
	#include <stdlib.h>
	
	// returns the index of the parent node
	int parent(int i) 
	{
    return (i - 1) / 2;
	}

	// return the index of the left child 
	int left_child(int i) 
	{
    return 2*i + 1;
	}

	// return the index of the right child 
	int right_child(int i) 
	{
    return 2*i + 2;
	}	
	
	//swap function
	void swap(int *x, int *y) {
    	int temp = *x;
    	*x = *y;
    	*y = temp;
}
	

	void print_queue(int* parray, int items, int pointer)
	{
		int i;
		printf(" | queue: [");
		for (i = 0; i < items; i++)
		{
			if (i == pointer)
				printf("^");
			printf("%d, ", parray[i]);

		}
		printf("]");
	}
		//building the heap
	// moves the item at position i of array a
	// into its appropriate position
	
	void min_heapify(int array[], int i, int n){
    
	// find left child node
    int left = left_child(i);

    // find right child node
    int right = right_child(i);

    // find smallest among 3 nodes
    int smallest = i;

    // check if the left node is smaller than the current node
    if (left <= n && array[left] < array[smallest]) {
        smallest = left;
    }

    // check if the right node is smaller than the current node
    if (right <= n && array[right] < array[smallest]) {
        smallest = right;
    }

    // swap the largest node with the current node 
    // and repeat this process until the current node is smaller than 
    // the right and the left node
    if (smallest != i) {
        int temp = array[i];
        array[i] = array[smallest];
        array[smallest] = temp;
        min_heapify(array, smallest, n);
}
    }
	// returns the  first element of the array 
	//which is the minimum element of the heap
	int get_min(int array[]) {
    return array[0];
}

	//deletes the min element and return the min element
	int dequeue_min(int array[], int *n) {
	int min_item = array[0];

    // replace the first item with the last item
    array[0] = array[*n - 1];
    *n = *n - 1;

    // keep the integrity of the heap by bubbling the new first item 
    min_heapify(array, 0, *n);
    return min_item;
}
	// converts an array into a heap
	void build_min_heap(int array[], int n) {
    int i;
    for (i = n/2; i >= 0; i--) {
        min_heapify(array, i, n);
    }
    
}
// prints the heap
void print_heap(int array[], int n) {
    int i;
    printf("\nheap:\n");
    for (i = 0; i < n; i++) {
        printf("\n%d\n", array[i]);
        
    }
    printf("\n");
}
	int main()
	{
		FILE* input;
		int n, i, j, min, index;
		char str[64];
		int* parray;
		input = fopen("test_dat.txt", "r");
		if (input == NULL)
		{
			printf("\nCan't open input");
			exit(0);
		}
		else
			printf("\nOpenned file successfully.");
		printf("\nChoose a number: ");
		scanf("%d", &n);

		parray = (int *)malloc(8 * n);

		for (i = 0; i < n; i++)
		{
			fscanf(input, "%s\n", str);
			printf("\n%s", str);
			parray[i] = atoi(str);
		}
	build_min_heap(parray, n);
	print_heap(parray, n);
	dequeue_min(parray, &n);
	printf("Dequeued min\n");
	print_heap(parray, n);
	
	 
	
	
	}
	

