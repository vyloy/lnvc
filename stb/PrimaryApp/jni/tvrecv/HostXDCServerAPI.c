#include "HostIncludeApi.h"

/******************* Create a unit of XDC Client Process **********************/
UTIU32 CreateXDCClientHandler(UTIS32 Module_Index, UTIU16 width, UTIU16 height, UTIU8 depth, UTIU8 format, UTIU16 *r_width, UTIU16 *r_height, UTIU8 *r_depth, UTIU8 *r_format)
{
	return 1;
}

/******************* Free a unit of XDC Client Process **********************/
UTIU8 FreeXDCClientHandler(UTIS32 Module_Index, UTIU32 xdc_handler)
{
	return 0;
}

/******************* Display one xdc content on another xdc content Process ??? **********************/
UTIU8 DisplayXDCClient(UTIS32 Module_Index, UTIU32 dst_xdc_handler, UTIU16 dstx, UTIU16 dsty, UTIU16 dstw, UTIU16 dsth, UTIU32 src_xdc_handler, UTIU16 srcx, UTIU16 srcy, UTIU16 srcw, UTIU16 srch, UTIU8 op)
{
	return 0;
}

/******************* Push one XDC client to the top level, show its content Process **********************/
UTIU8 SetCurXDCClient(UTIS32 Module_Index, UTIU32 xdc_handler)
{
	return 0;
}

/******************* Set Current XDC displayer attributes Process **********************/
void GetHostXDCInfo(UTIS32 Module_Index, UTIU16 *width, UTIU16 *height, UTIU8 *depth, UTIU8 *format)
{
}

/******************* Set Current Platte data Process **********************/
void SetXDCClientPlatte(UTIS32 Module_Index, UTIU8 depth, UTIU32 color_num, UTIU32 *color_value)
{
}

/******************* Get Current Platte data Process **********************/
void GetXDCClientPlatte(UTIS32 Module_Index, UTIU8 *depth, UTIU32 *color_num, UTIU32 *color_value)
{
}

/******************* Set Current Pen style attributes Process **********************/
void SetXDCClientPenStyle(UTIS32 Module_Index, UTIU8 pen_type, UTIU16 pen_width, UTIU8 pen_R, UTIU8 pen_G, UTIU8 pen_B)
{
}

/******************* Get Current Pen style attributes Process **********************/
void GetXDCClientPenStyle(UTIS32 Module_Index, UTIU8 *pen_type, UTIU16 *pen_width, UTIU8 *pen_R, UTIU8 *pen_G, UTIU8 *pen_B)
{
}

/******************* Draw a Point Process **********************/
void XDCClientDrawPoint(UTIS32 Module_Index, UTIU16 x, UTIU16 y)
{
}

/******************* Draw a Line Process **********************/
void XDCClientDrawLine(UTIS32 Module_Index, UTIU8 line_type, UTIU8 line_style, UTIU16 sx, UTIU16 sy, UTIU16 dx, UTIU16 dy, UTIU16 r)
{
}

/******************* Draw a Rectangle Process **********************/
void XDCClientDrawRect(UTIS32 Module_Index, UTIU8 line_style, UTIU16 x, UTIU16 y, UTIU16 width, UTIU16 height)
{
}

/******************* Fill a Rectangle Process **********************/
void XDCClientFillRect(UTIS32 Module_Index, UTIU16 x, UTIU16 y, UTIU16 width, UTIU16 height)
{
}

/******************* Draw a Ellipse Process **********************/
void XDCClientDrawEllipse(UTIS32 Module_Index, UTIU8 line_style, UTIU16 x, UTIU16 y, UTIU16 v_r, UTIU16 h_r)
{
}

/******************* Fill a Ellipse Process **********************/
void XDCClientFillEllipse(UTIS32 Module_Index, UTIU16 x, UTIU16 y, UTIU16 v_r, UTIU16 h_r)
{
}

/******************* Draw a Polygon Process **********************/
void XDCClientDrawPolygon(UTIS32 Module_Index, UTIU8 line_style, UTIU16 point_num, UTIU16 *point_x, UTIU16 *point_y)
{
}

/******************* Fill a Polygon Process **********************/
void XDCClientFillPolygon(UTIS32 Module_Index, UTIU16 point_num, UTIU16 *point_x, UTIU16 *point_y)
{
}

/******************* Fill Area at a start point Process **********************/
void XDCClientFillAtPoint(UTIS32 Module_Index, UTIU16 x, UTIU16 y, UTIU32 color)
{
}

/******************* Copy, Paste, Get Area Process **********************/
UTIU8 XDCClientCopyArea(UTIS32 Module_Index, UTIU16 x, UTIU16 y, UTIU16 width, UTIU16 height, UTIU32 *area_handler)
{
	return 0;
}

UTIU8 XDCClientPasteArea(UTIS32 Module_Index, UTIU32 src_xdc_handler, UTIU16 x, UTIU16 y, UTIU16 width, UTIU16 height)
{
	return 0;
}

UTIU8 XDCClientFreeArea(UTIS32 Module_Index, UTIU32 xdc_handler)
{
	return 0;
}

UTIU8 *XDCClientGetArea(UTIS32 Module_Index, UTIU32 xdc_handler, UTIU32 *xdc_data_len, UTIU16 *width, UTIU16 *height, UTIU8 *status)
{
	//return area data address
	//*result is process status
	//
	*status=1;
	return NULL;
}

/******************* Show Pixmap Process **********************/
UTIU8 XDCClientPreparePixmap(UTIS32 Module_Index, UTIU16 x, UTIU16 y, UTIU16 width, UTIU16 height, UTIU8 depth)
{
	return 1;
}

UTIU8 XDCClientFillPixmapData(UTIS32 Module_Index,UTIU16 apdu_number,UTIU16 last_apdu_number,UTIU8 *pData, UTIU32 data_len)
{
	return 1;
}

/******************* Show Image File Process **********************/
UTIU8 XDCClientPrepareImageFile(UTIS32 Module_Index, UTIU8 *file_name, UTIU8 name_len, UTIU16 x, UTIU16 y)
{
	return 1;
}

UTIU8 XDCClientFillImageFile(UTIS32 Module_Index, UTIU16 apdu_number, UTIU16 last_apdu_number, UTIU8 *pData, UTIU32 data_len)
{
	return 1;
}

/******************* Font control Process **********************/
UTIU8 XDCClientGetFontNum(UTIS32 Module_Index, UTIU32 country_id)
{
	return 0;
}

UTIU8 XDCClientGetFontName(UTIS32 Module_Index, UTIU8 *font_name, UTIU32 country_id, UTIU8 i, UTIU32 *font_country_id)
{
	return 0;
}

void XDCClientSelectFont(UTIS32 Module_Index, UTIU8 *font_name, UTIU8 name_len)
{
}

UTIU8 XDCClientGetCurFont(UTIS32 Module_Index, UTIU8 *font_name)
{
	return 0;
}

void XDCClientSetFontType(UTIS32 Module_Index, UTIU8 italic, UTIU8 underline, UTIU8 rotate, UTIU8 rotate_degree, UTIU16 font_size, UTIU16 font_weight, UTIU32 font_color)
{
}

void XDCClientGetCurFontType(UTIS32 Module_Index, UTIU8 *italic, UTIU8 *underline, UTIU8 *rotate, UTIU8 *rotate_degree, UTIU16 *font_size, UTIU16 *font_weight, UTIU32 *font_color)
{
}

void XDCClientShowText(UTIS32 Module_Index, UTIU16 x, UTIU16 y, UTIU16 style, UTIU8 *pData, UTIU32 text_len)
{
}

void XDCClientMeasureText(UTIS32 Module_Index, UTIU8 *mh, UTIU8 *mw, UTIU8 *ascend, UTIU8 *descent, UTIU8 *pData, UTIU32 text_len)
{
}

void XDCClientClearWindow(UTIS32 Module_Index, UTIU16 x, UTIU16 y, UTIU16 width, UTIU16 height)
{
}

void XDCClientSetPlayerPos(UTIS32 Module_Index, UTIU16 x, UTIU16 y, UTIU16 width, UTIU16 height)
{
}
