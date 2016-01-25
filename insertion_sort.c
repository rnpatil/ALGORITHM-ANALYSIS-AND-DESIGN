#include "stdio.h"
#include "stdlib.h"

/**
 * A data structor to store an array
 */
typedef struct int_array {
    int length;
    int *data;
} int_array_t;

/**
 * read a set of numbers from a text file, and store them in an array. 
 * @param fname: the name of the file to read from
 * @param array: a pointer to an array structure to populate with data. 
 */
int int_array_read(const char* fname, int_array_t *array) {

    FILE *file = fopen(fname, "r");

    if (file == NULL) {
	return -1;
    }

    fseek(file, 0, SEEK_END);

    int len = ftell(file);

    // rewind the file
    fseek(file, 0L, SEEK_SET);

    // temp storage for the array values, can be at most
    // the number of bytes in the file. Yes, this is an overestimate
    int *tmp = (int*)malloc(len * sizeof(int));

    // number of numbers read
    int n = 0;

    while(fscanf(file, "%i", &tmp[n]) == 1) {
	n++;
    }

    fclose(file);

    array->data = (int*)realloc(tmp, n * sizeof(int));
    array->length = n;

    return 0;
}


/**
 * print the values of an array struct to the console.
 */
int int_array_print(int_array_t *array) {
    for(int i = 0; i < array->length; ++i) {
	printf("%i", array->data[i]);
	if (i < array->length - 1) {
	    printf(", ");
	}
    }
    printf("\n");
    return 0;
}

/**
 * free the contents of an array struct
 */
int int_array_free(int_array_t *array) {
    if(array) {
	free(array->data);
	array->data = 0;
	array->length = 0;
	return 0;
    }
    return -1;
}

/**
 * perform an in-place insertion sort of an array struct. 
 */

int int_array_insertion_sort(int_array_t *array) {

    int j;

    for(int i = 1; i <array->length; ++i) {
	
	int key=array->data[i];

	j = i - 1;
 	
  while(j >=0 && array->data[j] > key)
     {
 	array->data[j+1] = array->data[j];

	 j = j - 1;

     }
 array->data[j+1] = key;

    }
    printf("\n");
    return 0;

}


int main(int argv, const char** argc) {

    if (argv != 2) {
	printf("usage: isort filename\n");
	return -1;
    }

    // an array struct to read data into.
    int_array_t array = {0, 0};

    // read the data into the struct
    int_array_read(argc[1], &array);

    // display the initial data
    printf("initial data:\n");
    int_array_print(&array);

    // sort the array
    int_array_insertion_sort(&array);

    // display the results
    printf("sorted data:\n");
    int_array_print(&array);

    // free the data
    int_array_free(&array);

    return 0;
}
