#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>

int main() {
    int res;

    printf("hello lnt33\n");
    res = mkfifo("/data/lnt/fifo2", S_IRWXO);
    if (res != 0)
    {
        printf("Error while creating a pipe (return:%d, errno:%d)", res);
    }
    return 0;
}

