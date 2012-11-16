#include "HostIncludeApi.h"

UTIS32 UTIPrint(const UTIS8 * format, ...)
{
#if OS_WINDOWS
	int i;
	char buffer[1024];
	va_list ap;
	va_start(ap, format);
	vsprintf(buffer,format, ap);
	i=strlen(buffer);
	if (i>=2 && buffer[i-1]=='\n' && buffer[i-2]!='\r')
	{
		buffer[i-1]='\r';
		buffer[i]='\n';
		buffer[i+1]=0;
	}
	MFCPrintf(buffer);
	va_end(ap);

#elif OS_LINUX
	va_list ap;
	va_start(ap, format);
	vprintf(format, ap);
	va_end(ap);

#endif
	
	return 0;
}



// UTIS32 UTIPrint(UTIS32 level, const UTIS8 * format, ...)
// {
// 	UTIS32 cnt = 0;
// 	va_list ap;
// 
// 	switch(level)
// 	{
// 	case 1:
// 	case 2:
// 	//case 3:
// 	//case 4:
// 		va_start(ap, format);
// 		cnt = vprintf(format, ap);
// 		va_end(ap);
// 		break;
// 	default:
// 		break;
// 	}
// 
// // 	FILE *DebugFp;
// // 	DebugFp = fopen("d:\\debug.log", "a");
// // 	fprintf(DebugFp, "\n");
// // 	fclose(DebugFp);
// 
// 	return 0;
// }

/*Debug info from module*/
UTIS32 UTIDebugModuleInfo(UTIS32 iModuleIndex, UTIU32 level, UTIU32 length, UTIU8 *data)
{
	switch(level)
	{
	case 1:
	case 2:
	case 3:
	case 4:
		printf("%s", data);
		break;
	default:
		break;
	}

	return 0;
}

