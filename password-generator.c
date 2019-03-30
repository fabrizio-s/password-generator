#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <x86intrin.h>
#include "SFMT.h"

void shuffle(sfmt_t*, char*, int);

int main(int argc, char* argv[]) {
	unsigned int seed = __rdtsc() & 4294967295L;
	sfmt_t sfmt;
	sfmt_init_gen_rand(&sfmt, seed);

	char chars[] = "abcdefghijklmnopqrstuvwxyz0123456789";
	int size = sizeof(chars)/sizeof(char);

	strcat(chars, "()");

	printf("%s\n", chars);
	shuffle(&sfmt, chars, size);
	printf("%s\n", chars);

	return EXIT_SUCCESS;
}

void shuffle(sfmt_t* sfmt, char* chars, int size) {
	for (int k = size; k > 0; k--) {
		if (chars[k] != '\0') {
			int index = sfmt_genrand_uint32(sfmt)%(k + 1);
			char temp = chars[index];
			chars[index] = chars[k];
			chars[k] = temp;
		}
	}
}
